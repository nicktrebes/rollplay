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

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import trebes.rollplay.app.RollplayApp;
import trebes.rollplay.app.RollplayContent;

@SuppressWarnings("serial")
public class RollplayMenuHelp extends JMenu {
	public RollplayMenuHelp(RollplayApp app) {
		super("Help");
		
		RollplayContent content = app.getContent();
		
		add(new HelpWelcome(content));
		add(new HelpAbout());
	}
	
	private class HelpWelcome extends JMenuItem {
		public HelpWelcome(RollplayContent content) {
			super("Welcome");
			addActionListener(content);
			setActionCommand(RollplayContent.HELP_WELCOME);
		}
	}
	
	private class HelpAbout extends JMenuItem implements ActionListener {
		public HelpAbout() {
			super("About RollPlay");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			new AboutFrame();
		}
	}
	
	private class AboutFrame extends JFrame {
		public AboutFrame() {
			super("About RollPlay");
			
			JTextArea textArea = new JTextArea(getText());
			textArea.setMargin(new Insets(10,10,10,10));
			
			setContentPane(textArea);
			this.setMinimumSize(new Dimension(800,600));
			pack();
			
			center();
			setVisible(true);
		}
		
		private void center() {
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = getSize();
			int x = ((screen.width - size.width) / 2);
			int y = ((screen.height - size.height) / 2);
			this.setBounds(x,y,size.width,size.height);
		}
		
		private String getText() {
			StringBuilder aboutText = new StringBuilder();
			aboutText.append(RollplayApp.getAppTitle() + "\n\n");
			
			try {
				BufferedReader license = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/LICENSE")));
				for (
					String line = license.readLine();
					line != null; line = license.readLine()
				) {
					aboutText.append(line);
					aboutText.append('\n');
				}
			} catch (IOException exception) {}
			
			return aboutText.toString();
		}
	}
}