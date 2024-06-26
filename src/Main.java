import configuration.Config;
import configuration.UserConfig;
import helpers.Helpers;
import screens.InitAppScreen;
import screens.StartScreen;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // create storage directory when it doesn't exist
        File storage = new File(Config.booksPath);
        if (!storage.exists()) {
            boolean created = storage.mkdirs();
            if (!created) {
                System.out.println("Sorry, deze applicatie moet bestanden kunnen aanmaken om te werken.");
                System.exit(0); // close application
            }
        }

        // check if app is configured
        File config = new File(Config.configPath);
        if (!config.exists()) {
            Helpers.clearScreen();
            new InitAppScreen().showScreen();
        }

        Main.promptStartScreen(STR."Welkom, \{UserConfig.read().getName()}.");
    }

    public static void promptStartScreen(String notification) {
        Helpers.clearScreen();

        try {
            new StartScreen(notification).showScreen();
        } catch (Exception error) {
            Main.promptStartScreen("Sorry, er is iets onverwacht gegaan.");
        }
    }
}