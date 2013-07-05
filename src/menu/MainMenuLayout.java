package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import menu.budget.BudgetLayer;

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

		buttons[0] = new Button(screen, 10, 550, 100, 40, "Quit", 14,
				new QuitButtonCallback());
		
		buttons[1] = new Button(screen, 10, 500, 100, 40, "Budget", 14,
				new BudgetLayerCallback());

	}

	
	
	private class BudgetLayerCallback implements Callback {
		public void invoke() {
			BudgetLayer budlyr = new BudgetLayer(screen);
			budlyr.setup();
			screen.popHook();
			screen.pushHook(budlyr);
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
		drawTitle(graphics);
		graphics.setColor(Color.WHITE);
		for (Button button : buttons)
			button.draw(graphics);
	}
	
	private void drawTitle(Graphics2D graphics) {
		String text1 = BudgetData.getName();
		Date date = new Date();
		Format formatter = new SimpleDateFormat("MMMM dd, yyyy");
		Format formatter2 = new SimpleDateFormat("hh:mm:ss aa");
		String text2 = formatter.format(date);
		String text3 = formatter2.format(date);
		int xOffset1 = screen.getXOffset(graphics, bigFont, text1);
		int xOffset2 = screen.getXOffset(graphics, smallFont, text2) + 300;
		int xOffset3 = screen.getXOffset(graphics, smallFont, text3) + 300;
		graphics.setColor(Color.GRAY);
		graphics.setFont(bigFont);
		graphics.drawString(text1, xOffset1, 80);
		graphics.setColor(Color.GRAY);
		graphics.setFont(smallFont);
		graphics.drawString(text2, xOffset2, screen.getHeight() / 2 - 250);
		graphics.drawString(text3, xOffset2, screen.getHeight() / 2 - 220);
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
