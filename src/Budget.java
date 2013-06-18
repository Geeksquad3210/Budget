

import graphics.Screen;
import menu.Menu;
import util.Preferences;

public class Budget {

    public static void main(String[] args) {
        Screen screen = new Screen();
        Menu menu = new Menu(screen);

        screen.setup();
        Preferences.load();
        menu.setup();
        screen.pushHook(menu);
        screen.run();
        Preferences.save();
        System.exit(0);
    }
}
