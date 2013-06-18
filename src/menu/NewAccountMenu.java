package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import graphics.DefaultHook;
import graphics.Screen;

import data.BudgetData;

public class NewAccountMenu extends DefaultHook {

	private Screen _screen;
	private RoundRectangle2D.Double _box;
	private Font _title, _text;
	private Button[] _buttons;
	private TextBox _name;

	public NewAccountMenu(Screen scr) {
		_screen = scr;
		_title = _screen.getFont().deriveFont(64f);
		_text = _screen.getFont().deriveFont(11f);

	}

	public void setup() {

		_buttons = new Button[2];
		_buttons[0] = new Button(_screen, 525, 400, 150, 75,
				"Create", 36, new MainMenuLayoutCallback());

		_buttons[1] = new Button(_screen, 10, 550, 100, 40, "Cancel",
				14, new MenuCallback());

		_name = new TextBox(_screen, 150, 250, 500, 100, 120, "whoo", 32, "Name");
		//_box = new RoundRectangle2D.Double(100, 200, 600, 300, 25, 25);
	}

	private class MenuCallback implements Callback {
		public void invoke() {
			Menu menu = new Menu(_screen);
			_screen.popHook();
			menu.setup();
			_screen.pushHook(menu);
		}
	}
	
	private class MainMenuLayoutCallback implements Callback {
		public void invoke() {
			MainMenuLayout menu = new MainMenuLayout(_screen);
			menu.setup();
			_screen.popHook();
			_screen.pushHook(menu);
		}
	}

	@Override
	public void step(Graphics2D graphics) {
		graphics.setColor(new Color(.3f, .3f, .3f));
		graphics.fillRect(0,0,800,600);
		graphics.setColor(new Color( .2f, .2f, .2f));
		graphics.fillRoundRect(105, 205, 600, 300, 25, 25);
		graphics.setColor(new Color(.8f, .8f, .8f));
		graphics.fillRoundRect(100, 200, 600, 300, 25, 25);
		graphics.setColor(Color.GRAY);
		drawTitle(graphics);
		graphics.setColor(Color.black);
		for (Button button : _buttons)
			button.draw(graphics);
		_name.draw(graphics);
	}

	private void drawTitle(Graphics2D graphics) {
		String text1 = "Money Planner";
		String text2 = "code by Victor Jiao";
		int xOffset1 = _screen.getXOffset(graphics, _title, text1);
		int xOffset2 = _screen.getXOffset(graphics, _text, text2);
		graphics.setColor(Color.GRAY);
		graphics.setFont(_title);
		graphics.drawString(text1, xOffset1, _screen.getHeight() / 2 - 230);
		graphics.setColor(Color.WHITE);
		graphics.setFont(_text);
		graphics.drawString(text2, xOffset2, _screen.getHeight() / 2 - 180);
	}

	@Override 
	public void keyTyped(KeyEvent event) {
		_name.update(event);
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER)
			BudgetData.put(_name.getValue().substring(0, _name.getValue().length() - 1));
	}
	
	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
		for (Button button : _buttons)
			button.update(event);
		_name.update(event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		for (Button button : _buttons)
			button.update(event);
		_name.update(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		for (Button button : _buttons) {
			button.update(event);
		}
		_name.update(event);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		for (Button button : _buttons) {
			button.update(event);
		}
		_name.update(event);
	}
}
