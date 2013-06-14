package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Preferences {

    private static HashMap<String, Integer> _data;

    public static void put(String key, Integer data){
        if (_data == null) load();
        _data.put(key,data);
    }

    public static void load() {
        _data = new HashMap<String, Integer>();
        try {
            String filename = "preferences.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (br.ready()) {
                String[] data = br.readLine().split(" : ");
                _data.put(data[0], Integer.parseInt(data[1]));
            }

            br.close();
            loadDefault(false); // load in any missing options
            save();

        } catch (FileNotFoundException e) {
            System.out.println("You don't seem to have a preferences file. I'll make one for you, then.");
            loadDefault(true);
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        String filename = "preferences.txt";
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(filename));
            for (String key : _data.keySet()) {
                b.write(key);
                b.write(" : ");
                b.write("" + _data.get(key));
                b.newLine();
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getValue(String key) {
        if (_data == null) load();
        return _data.get(key);
    }

    private static void loadDefault(boolean force) {
        if (force || !_data.containsKey("screenHeight")) _data.put("screenHeight", 1024);
        if (force || !_data.containsKey("screenWidth")) _data.put("screenWidth", 1280);
    }


}
