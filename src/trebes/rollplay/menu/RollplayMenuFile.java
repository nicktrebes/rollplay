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

package trebes.rollplay.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import trebes.rollplay.app.RollplayApp;
import trebes.rollplay.app.RollplayContent;
import trebes.rollplay.data.RollplayFile;

@SuppressWarnings("serial")
public class RollplayMenuFile extends JMenu {
	private FileClose close;
	private FileCloseAll closeAll;
	private FileSave save;
	private FileSaveAll saveAll;
	private FileSaveAs saveAs;
	
	public RollplayMenuFile(RollplayApp app) {
		super("File");
		
		RollplayContent content = app.getContent();
		
		JMenu fileNew = new JMenu("New");
		fileNew.add(new FileNewDND5(content));
		fileNew.add(new FileNewSWN0(content));
		fileNew.add(new FileNewSWNR(content));
		
		add(fileNew);
		add(new FileOpen(content));
		
		addSeparator();
		
		add(close = new FileClose(content));
		add(closeAll = new FileCloseAll(content));
		
		addSeparator();
		
		add(save = new FileSave(content));
		add(saveAs = new FileSaveAs(content));
		add(saveAll = new FileSaveAll(content));
		
		addSeparator();
		
		add(new FileExit(content));
	}
	
	public void setFileOpen(boolean open) {
		close.setEnabled(open);
		closeAll.setEnabled(open);
		save.setEnabled(open);
		saveAll.setEnabled(open);
		saveAs.setEnabled(open);
	}
	
	private class FileClose extends JMenuItem {
		public FileClose(RollplayContent content) {
			super("Close");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_CLOSE);
		}
	}
	
	private class FileCloseAll extends JMenuItem {
		public FileCloseAll(RollplayContent content) {
			super("Close All");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_CLOSE_ALL);
		}
	}
	
	private class FileExit extends JMenuItem {
		public FileExit(RollplayContent content) {
			super("Exit");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_EXIT);
		}
	}
	
	private class FileNewDND5 extends JMenuItem {
		public FileNewDND5(RollplayContent content) {
			super(RollplayFile.Type.DND5.toString());
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_NEW_DND5);
		}
	}
	
	private class FileNewSWN0 extends JMenuItem {
		public FileNewSWN0(RollplayContent content) {
			super(RollplayFile.Type.SWN0.toString());
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_NEW_SWN0);
		}
	}
	
	private class FileNewSWNR extends JMenuItem {
		public FileNewSWNR(RollplayContent content) {
			super(RollplayFile.Type.SWNR.toString());
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_NEW_SWNR);
		}
	}
	
	private class FileOpen extends JMenuItem {
		public FileOpen(RollplayContent content) {
			super("Open...");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_OPEN);
		}
	}
	
	private class FileSave extends JMenuItem {
		public FileSave(RollplayContent content) {
			super("Save");
			setActionCommand(RollplayContent.FILE_SAVE);
		}
	}
	
	private class FileSaveAll extends JMenuItem {
		public FileSaveAll(RollplayContent content) {
			super("Save All");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_SAVE_ALL);
		}
	}
	
	private class FileSaveAs extends JMenuItem {
		public FileSaveAs(RollplayContent content) {
			super("Save As...");
			addActionListener(content);
			setActionCommand(RollplayContent.FILE_SAVE_AS);
		}
	}
}