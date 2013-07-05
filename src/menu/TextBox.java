package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import graphics.Screen;

//import util.Preferences;

public class TextBox {

	public static int ABOVE = 0, RIGHT = 1, BELOW = 2, LEFT = 3;

	private Rectangle rect;
	private Font font;

	private String value;
	private int xOffset, yOffset;
	private boolean hover, pressed, initialized, isLetter;
	

	public TextBox(Screen screen, int x, int y, int w, int h,
			String text, float size, boolean isleter) {
		rect = new Rectangle(x, y, w, h);
		font = screen.getFont().deriveFont(size);
	
		// value = KeyEvent.getKeyText(Preferences.getValue(button));
		value = text;
		xOffset = yOffset = -1;
		hover = false;
		pressed = false;
		initialized = false;
		isLetter = isleter;
	}

	public boolean isPressed() {
		return pressed;
	}

	public boolean isHover() {
		return hover;
	}

	public void update(MouseEvent event) {
		int mask = (event.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK);
		boolean buttonDown = mask == MouseEvent.BUTTON1_DOWN_MASK;

		if (buttonDown && !hover) {
			pressed = false;
		}
		if (event.getX() >= rect.x && event.getX() <= rect.x + rect.width
				&& event.getY() >= rect.y
				&& event.getY() <= rect.y + rect.height)
			hover = true;
		else
			hover = false;

		if (hover && buttonDown)
			pressed = true;
	}

	public void update(KeyEvent event) {
		
		char c = event.getKeyChar();
		if (pressed){
			if (!initialized){
				initialized = true;
				value = "_";
			}
			if (value.length() >= 2 && c == '\b'){
				value = value.substring(0,value.length() - 2);
				value = value + "_";
			}
			
		
			else if (c == '.'){
				if (value.length() >= 2 && !value.substring(value.length() - 2, value.length() - 1).equals("."))
					value = value.substring(0,value.length() - 1) + "." + "_";
			}
			else if ((java.lang.Character.isLetter(c) && isLetter) || (java.lang.Character.isDigit(c) && !isLetter));
				value = value.substring(0,value.length() - 1) + c + "_";
		}
		
		xOffset = -1;
	}

	public String getValue(){
		return value;
	}
	
	public void draw(Graphics2D graphics) {
		if (xOffset == -1) {
			xOffset = (int) (rect.width - font.getStringBounds(value,
					graphics.getFontRenderContext()).getWidth()) / 2;
			yOffset = (int) (font.getLineMetrics(value,
					graphics.getFontRenderContext()).getAscent() + rect.height) / 2;
		}

		if (pressed)
			graphics.setColor(new Color(.3f, .3f, .3f));
		else if (hover)
			graphics.setColor(new Color(.6f, .6f, .6f));
		else
			graphics.setColor(Color.GRAY);

		graphics.fill(rect);
		graphics.setColor(Color.WHITE);
		graphics.setFont(font);

		xOffset = (int) (rect.width - font.getStringBounds(value,
				graphics.getFontRenderContext()).getWidth()) / 2;
		graphics.drawString(value, rect.x + xOffset, rect.y + yOffset);
		
	}
}
