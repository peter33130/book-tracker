package configuration;

import components.Book;
import components.Screen;
import helpers.Helpers;

import javax.swing.text.IconView;
import java.io.*;

public class UserConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    public final String name;

    public UserConfig(String name) {
        this.name = name;
    }

    public static UserConfig read() {
        File file = new File(Config.configPath);
        if (!file.exists()) return null;
        UserConfig config = null;

        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            Object obj = objectInput.readObject();
            if (obj instanceof UserConfig) config = (UserConfig) obj;
        } catch (Exception error) {
            Helpers.handleException(error);
        }

        return config;
    }

    public void write() {
        File file = new File(Config.configPath);
        try {
            FileOutputStream fileOutput = new FileOutputStream(file.getPath());
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(this);
            objectOutput.close();
            fileOutput.close();
        } catch (Exception error) {
            Helpers.handleException(error);
        }
    }
}
