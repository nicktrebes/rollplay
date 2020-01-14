/*
 * MIT License
 * 
 * RollPlay Character Sheet Editor
 * Copyright (c) 2020 Nick Trebes
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package trebes.rollplay.app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import trebes.rollplay.data.RollplayFile;
import trebes.rollplay.sheet.*;

@SuppressWarnings("serial")
public class RollplayContent extends JTabbedPane implements ActionListener, ChangeListener {
	private RollplayApp app;
	
	public RollplayContent(RollplayApp app) {
		this.app = app;
		addChangeListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.contentEquals(FILE_CLOSE)) {
			fileClose();
		} else if (command.contentEquals(FILE_CLOSE_ALL)) {
			fileCloseAll();
		} else if (command.contentEquals(FILE_EXIT)) {
			if (fileCloseAll()) {
				System.exit(0);
			}
		} else if (command.contentEquals(FILE_NEW_DND5)) {
			fileNew(RollplayFile.Type.DND5);
		} else if (command.contentEquals(FILE_NEW_SWN0)) {
			fileNew(RollplayFile.Type.SWN0);
		} else if (command.contentEquals(FILE_NEW_SWNR)) {
			fileNew(RollplayFile.Type.SWNR);
		} else if (command.contentEquals(FILE_OPEN)) {
			fileOpen();
		} else if (command.contentEquals(FILE_SAVE)) {
			fileSave();
		} else if (command.contentEquals(FILE_SAVE_ALL)) {
			fileSaveAll();
		} else if (command.contentEquals(FILE_SAVE_AS)) {
			fileSaveAs();
		} else if (command.contentEquals(HELP_WELCOME)) {
			addTab(new RollplayWelcome(app));
		}
	}
	
	public void addTab(RollplayTab tab) {
		add(tab);
		int index = indexOfComponent(tab);
		setTabComponentAt(index,tab.getHeader());
	}
	
	public boolean fileClose() {
		Component selected = this.getSelectedComponent();
		if (selected != null) {
			RollplayTab tab = (RollplayTab)selected;
			if (!tab.close()) {
				return false;
			}
			remove(tab);
		}
		return true;
	}
	
	public boolean fileCloseAll() {
		while (getTabCount() > 0) {
			RollplayTab tab = (RollplayTab)getComponentAt(0);
			if (!tab.close()) {
				return false;
			}
			remove(tab);
		}
		addTab(new RollplayWelcome(app));
		return true;
	}
	
	public void fileNew(RollplayFile.Type type) {
		JFileChooser fc = getFileChooser();
		fc.setDialogTitle("New Character Sheet");
		fc.setSelectedFile(new File("Untitled.rps"));
		int value = fc.showOpenDialog(app.getFrame());
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.getPath().isEmpty()) {
				return;
			}
			RollplaySheet sheet;
			if (type == RollplayFile.Type.DND5) {
				sheet = new RollplaySheetDND5(app,file);
			} else if (type == RollplayFile.Type.SWN0) {
				sheet = new RollplaySheetSWN0(app,file);
			} else if (type == RollplayFile.Type.SWNR) {
				sheet = new RollplaySheetSWNR(app,file);
			} else {
				return;
			}
			addTab(sheet);
		}
	}
	
	public void fileOpen() {
		JFileChooser fc = getFileChooser();
		fc.setDialogTitle("Open Character Sheet");
		int value = fc.showOpenDialog(app.getFrame());
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.getPath().isEmpty()) {
				return;
			}
			RollplaySheet sheet = RollplaySheet.open(app,file);
			if (sheet != null) {
				addTab(sheet);
			}
		}
	}
	
	public void fileSave() {
		Component selected = this.getSelectedComponent();
		if (selected != null) {
			((RollplayTab)selected).save();
		}
	}
	
	public void fileSaveAll() {
		int count = getTabCount();
		for (int n = 0; n < count; ++n) {
			((RollplayTab)getComponentAt(n)).save();
		}
	}
	
	public void fileSaveAs() {
		Component selected = this.getSelectedComponent();
		if (selected != null) {
			((RollplayTab)selected).saveAs();
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		if (getTabCount() == 0) {
			System.exit(0);
		} else {
			((RollplayTab)getSelectedComponent()).onFocus();
		}
	}
	
	public static JFileChooser getFileChooser() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMinimumSize(new Dimension(800,600));
		return fc;
	}
	
	public static final String FILE_CLOSE = "fc";
	public static final String FILE_CLOSE_ALL = "fca";
	public static final String FILE_EXIT = "fe";
	public static final String FILE_NEW_DND5 = "fnd";
	public static final String FILE_NEW_SWN0 = "fn0";
	public static final String FILE_NEW_SWNR = "fnr";
	public static final String FILE_OPEN = "fo";
	public static final String FILE_SAVE = "fs";
	public static final String FILE_SAVE_ALL = "fsa";
	public static final String FILE_SAVE_AS = "fsas";
	public static final String HELP_WELCOME = "hw";
	
	private static final FileNameExtensionFilter filter =
		new FileNameExtensionFilter("RollPlay sheet (*.rps)","rps");
}