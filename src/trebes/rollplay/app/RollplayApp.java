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

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import trebes.rollplay.menu.RollplayMenuBar;

public class RollplayApp {
	private JFrame frame;
	private RollplayContent content;
	private RollplayMenuBar menuBar;
	
	public RollplayApp() {
		content = new RollplayContent(this);
		menuBar = new RollplayMenuBar(this);

		content.addTab(new RollplayWelcome(this));
		
		frame = new JFrame(getAppTitle());
		frame.setContentPane(content);
		frame.setJMenuBar(menuBar);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setVisible(true);
	}
	
	public RollplayContent getContent() {
		return content;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public RollplayMenuBar getMenuBar() {
		return menuBar;
	}
	
	public static final int VERSION_MAJOR = 0;
	public static final int VERSION_MINOR = 1;
	
	public static String getAppTitle() {
		return ("RollPlay " + VERSION_MAJOR + "." + VERSION_MINOR);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException
			| InstantiationException
			| IllegalAccessException
			| UnsupportedLookAndFeelException exception) {}
		
		new RollplayApp();
	}
}