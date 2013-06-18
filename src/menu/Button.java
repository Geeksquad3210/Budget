package menu;

import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import graphics.Screen;

public class Button {

    private Rectangle rect;
    private Callback callback;
    private Font font;
    private String label;
    private int xOffset, yOffset;
    private boolean pressed, hover, toggle, oldButtonDown;

    public Button(Screen screen, int x, int y, int w, int h, String text,
                  float size, Callback cb) {
        rect = new Rectangle(x, y, w, h);
        callback = cb;
        font = screen.getFont().deriveFont(size);
        label = text;
        xOffset = yOffset = -1;
        pressed = hover = toggle = oldButtonDown = false;
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

        if (pressed && !buttonDown) {
            pressed = false;
            callback.invoke();
            return;
        }
        
        if (!buttonDown || toggle){
        	hover = event.getX() >= rect.x && event.getX() <= rect.x + rect.width &&
        	event.getY() >= rect.y && event.getY() <= rect.y + rect.height;
        }
        
        if (hover && buttonDown && !oldButtonDown)
        	toggle = true;
        if (!buttonDown)
        	toggle = false;
       
        if (hover && toggle)
        	pressed = true;
        if (!hover || !buttonDown)
        	pressed = false;
        
        oldButtonDown = buttonDown;
    }

    public void draw(Graphics2D graphics) {
        if (xOffset == -1) {
            xOffset = (int) ((rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) * .48);
            yOffset = (int) ((font.getLineMetrics(label, graphics.getFontRenderContext()).getAscent() + rect.height) * .48);
        }

        if (isPressed())
            graphics.setColor(new Color(.3f, .3f, .3f));
        else if (isHover())
            graphics.setColor(new Color(.6f, .6f, .6f));
        else
            graphics.setColor(Color.GRAY);
        graphics.fill(rect);
        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        graphics.drawString(label, rect.x + xOffset, rect.y + yOffset);
    }
}
