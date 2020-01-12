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

@SuppressWarnings("serial")
public class RollplayContent extends JTabbedPane implements ActionListener {
	private RollplayMenuBar menu;
	
	public RollplayContent() {
		menu = null;
		
		addTab(new RollplayWelcome(this));
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
		}
	}
	
	public void addTab(RollplayTab tab) {
		String title = tab.getTitle();
		addTab(title,tab);
		int index = indexOfTab(title);
		setTabComponentAt(index,tab.getHeader());
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
	
	public void setMenuBar(RollplayMenuBar menu) {
		this.menu = menu;
	}
	
	public static final String FILE_CLOSE = "fc";
	public static final String FILE_CLOSE_ALL = "fca";
	public static final String FILE_EXIT = "fe";
	public static final String FILE_NEW = "fn";
	public static final String FILE_OPEN = "fo";
	public static final String FILE_SAVE = "fs";
	public static final String FILE_SAVE_ALL = "fsa";
	public static final String FILE_SAVE_AS = "fsas";
}