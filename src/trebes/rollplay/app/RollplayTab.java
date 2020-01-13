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

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class RollplayTab extends JPanel implements ActionListener, FocusListener {
	private Header header;
	protected RollplayApp app;
	
	public RollplayTab(RollplayApp app, String title) {
		header = new Header(this,title);
		this.app = app;
		addFocusListener(this);
	}
	
	@Override public void focusLost(FocusEvent event) {}
	public abstract void onFocus();
	public abstract boolean close();
	public abstract boolean isEdited();
	public abstract boolean isFile();
	public abstract boolean save();
	public abstract boolean saveAs();
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().contentEquals(CLOSE_TAB)) {
			if (close()) {
				app.getContent().remove(this);
			}
		}
	}
	
	@Override
	public void focusGained(FocusEvent event) {
		onFocus();
	}
	
	public JPanel getHeader() {
		return header;
	}
	
	public String getTitle() {
		return header.label.getText();
	}
	
	public void setEdited(boolean edited) {
		header.prefix.setVisible(edited);
	}
	
	public void setTitle(String title) {
		header.label.setText(title);
	}
	
	public static final String CLOSE_TAB = "ct";
	
	private static class Header extends JPanel {
		private JLabel label;
		private JLabel prefix;
		
		public Header(RollplayTab tab, String title) {
			super();
			
			label = new JLabel(title);
			prefix = new JLabel("*");
			JButton button = new JButton("X");
			button.addActionListener(tab);
			button.setActionCommand(CLOSE_TAB);
			button.setMargin(new Insets(2,4,2,4));
			
			add(prefix);
			add(label);
			add(button);
			
			setOpaque(false);
			prefix.setVisible(tab.isEdited());
		}
	}
}