

import data.FilesInFolder;
import graphics.Screen; 
import menu.IntroMenu;
import util.Preferences;

public class Budget {

    public static void main(String[] args) {
    	FilesInFolder.setup();
    	
        Screen screen = new Screen();
        IntroMenu menu = new IntroMenu(screen);

        screen.setup();
        Preferences.load();
        menu.setup();
        screen.pushHook(menu);
        screen.run();
        Preferences.save();
        System.exit(0);
       
    }
}
