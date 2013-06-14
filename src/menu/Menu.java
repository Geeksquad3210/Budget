package menu;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import graphics.DefaultHook;
import graphics.Screen;

public class Menu extends DefaultHook {

    private Screen screen;
    private Font bigFont, smallFont;
    private Button[] buttons;

    public Menu(Screen scr) {
        screen = scr;
        bigFont = screen.getFont().deriveFont(64f);
        smallFont = screen.getFont().deriveFont(11f);
    }

    public void setup() {
        int cx = screen.getWidth() / 2,
            cy = screen.getHeight() / 2;

        buttons = new Button[2];
        buttons[0] = new Button(screen, cx - 200, cy - 160, 400, 120, "New Account", 48,
                                new PlayButtonCallback());

        buttons[1] = new Button(screen, cx + 10, cy + 80, 190, 80, "Quit", 18,
                                new QuitButtonCallback());
        
    }

    public interface Callback {
        void invoke();
    }

    private class PlayButtonCallback implements Callback {
        public void invoke() {
       //     World world = new World(screen);
        //    screen.popHook();
        //    screen.pushHook(world);
        //    screen.hideCursor();
        }
    }

    private class QuitButtonCallback implements Callback {
        public void invoke() {
            screen.shutdown();
        }
    }

    @Override
    public void step(Graphics2D graphics) {
        drawTitle(graphics);
        graphics.setColor(Color.WHITE);
        for (Button button : buttons)
            button.draw(graphics);
    }

    private void drawTitle(Graphics2D graphics) {
        String text1 = "Money Planner";
        String text2 = "code by Victor Jiao";
        int xOffset1 = screen.getXOffset(graphics, bigFont, text1);
        int xOffset2 = screen.getXOffset(graphics, smallFont, text2);
        graphics.setColor(Color.GRAY);
        graphics.setFont(bigFont);
        graphics.drawString(text1, xOffset1, screen.getHeight() / 2 - 250);
        graphics.setColor(Color.WHITE);
        graphics.setFont(smallFont);
        graphics.drawString(text2, xOffset2, screen.getHeight() / 2 - 200);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_P)
            new PlayButtonCallback().invoke();
        else if (event.getKeyCode() == KeyEvent.VK_Q)
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
        for (Button button : buttons)
            button.update(event);
    }
}
