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

import trebes.rollplay.menu.RollplayMenuBar;
import trebes.rollplay.menu.RollplayMenuFile;

@SuppressWarnings("serial")
public class RollplayWelcome extends RollplayTab {
	public RollplayWelcome(RollplayApp app) {
		super(app,"Welcome!");
	}
	
	@Override public boolean close() { return true; }
	@Override public boolean isEdited() { return false; }
	@Override public boolean isFile() { return false; }
	@Override public boolean save() { return false; }
	@Override public boolean saveAs() { return false; }
	
	@Override
	public void onFocus() {
		RollplayMenuBar menuBar = app.getMenuBar();
		
		RollplayMenuFile menuFile = menuBar.getMenuFile();
		menuFile.setFileOpen(false);
	}
}