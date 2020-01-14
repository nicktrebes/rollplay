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

package trebes.rollplay.data;

import java.awt.event.FocusEvent;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RollplayDataInteger extends RollplayDataString {
	public RollplayDataInteger() {
		setInputVerifier(verifier);
	}
	
	public int getValue() {
		String text = getText();
		return (text.isEmpty() ?
			0 : Integer.parseInt(getText()));
	}
	
	private static final InputVerifier verifier = new InputVerifier() {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField)input).getText();
			if (!text.isEmpty()) {
				try {
					Integer.parseInt(text);
				} catch (NumberFormatException exception) {
					return false;
				}
			}
			return true;
		}
	};
	
	public static class ForceSign extends RollplayDataInteger {
		@Override
		public void focusLost(FocusEvent event) {
			super.focusLost(event);
			int value = getValue();
			if (value > 0) {
				setText("+" + value);
			}
		}		
	}
	
	public static class NonNeg extends RollplayDataInteger {
		@Override
		public void focusLost(FocusEvent event) {
			super.focusLost(event);
			if (getValue() < 0) {
				setText("");
			}
		}
	}
	
	public static class Positive extends RollplayDataInteger {
		@Override
		public void focusLost(FocusEvent event) {
			super.focusLost(event);
			if (getValue() <= 0) {
				setText("");
			}
		}
	}
}