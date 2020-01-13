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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		} else if (command.contentEquals(FILE_NEW)) {
			fileNew();
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
		tab.onFocus();
	}
	
	public boolean fileClose() {
		return false; // TODO
	}
	
	public boolean fileCloseAll() {
		return false; // TODO
	}
	
	public void fileNew() {
		
	}
	
	public void fileOpen() {
		
	}
	
	public void fileSave() {
		
	}
	
	public void fileSaveAll() {
		
	}
	
	public void fileSaveAs() {
		
	}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		if (getTabCount() == 0) {
			System.exit(0);
		}
	}
	
	public static final String FILE_CLOSE = "fc";
	public static final String FILE_CLOSE_ALL = "fca";
	public static final String FILE_EXIT = "fe";
	public static final String FILE_NEW = "fn";
	public static final String FILE_OPEN = "fo";
	public static final String FILE_SAVE = "fs";
	public static final String FILE_SAVE_ALL = "fsa";
	public static final String FILE_SAVE_AS = "fsas";
	public static final String HELP_WELCOME = "hw";
}