package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import util.Tools;

import data.BudgetData;

import graphics.DefaultHook;
import graphics.Screen;

public class MainMenuLayout extends DefaultHook {

	private Screen screen;
	private Font bigFont, smallFont;
	private Button[] buttons;

	public MainMenuLayout(Screen scr) {
		screen = scr;
		bigFont = screen.getFont().deriveFont(64f);
		smallFont = screen.getFont().deriveFont(14f);

	}

	public void setup() {
		int cx = screen.getWidth() / 2, cy = screen.getHeight() / 2;

		buttons = new Button[2];
		buttons[0] = new Button(screen, cx - 200, cy - 160, 400, 120,
				BudgetData.getName(), 48, new NewAccountCallback());

		buttons[1] = new Button(screen, 10, 550, 100, 40, "Quit", 14,
				new QuitButtonCallback());

	}

	private class NewAccountCallback implements Callback {
		public void invoke() {
			NewAccountMenu newAccScrn = new NewAccountMenu(screen);
			newAccScrn.setup();
			screen.popHook();
			screen.pushHook(newAccScrn);
		}
	}

	private class QuitButtonCallback implements Callback {
		public void invoke() {
			screen.shutdown();
		}
	}

	@Override
	public void step(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.setColor(new Color(.9f, .95f, .97f));
		graphics.fillRect(0, 0, 800, 600);
		
		graphics.setColor(new Color(.7f, .8f, .8f));

		graphics.drawImage(Tools.createDropShadow(new Rectangle2D.Double(0, 0,
				120, 600), 5), 0, 0, null);
		graphics.fillRect(0, 0, 120, 600);
		graphics.setColor(new Color(.8f, .9f, .8f));

		graphics.drawImage(Tools.createDropShadow(new Rectangle2D.Double(0, 0,
				800, 120), 10), 0, 0, null);
		graphics.fillRect(0, 0, 800, 120);
		graphics.setColor(Color.WHITE);
		for (Button button : buttons)
			button.draw(graphics);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_Q)
			new QuitButtonCallback().invoke();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		for (Button button : buttons)
			button.update(event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		for (Button button : buttons)
			button.update(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		for (Button button : buttons) {
			button.update(event);
		}
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		for (Button button : buttons) {
			button.update(event);
		}
	}

}
