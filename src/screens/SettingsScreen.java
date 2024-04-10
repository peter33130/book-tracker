package screens;

import components.List;
import components.Screen;
import configuration.Config;
import configuration.UserConfig;
import helpers.Helpers;
import java.io.File;
import java.util.Scanner;

public class SettingsScreen extends Screen {
    public SettingsScreen() {}

    public SettingsScreen(String notification) {
        super(notification);
    }

    @Override
    public void showScreen() {
        Scanner scanner = new Scanner(System.in);
        int choice = new List(
                scanner,
                "⚙️  Instellingen",
                new String[]{"Verander uw naam", "Reset applicatie", "Terug naar hoofdmenu"},
                "Type het nummer van het scherm waar u naartoe wilt gaan:"
        ).showList();

        switch (choice) {
            case 1 -> {
                System.out.print("Wat is uw nieuwe naam?: ");
                String name = scanner.next();
                new UserConfig(name).write();
            }
            case 2 -> {
                System.out.println("Weet u zeker dat u de applicatie wilt resetten?");
                System.out.print("Uw antwoord: ");
                String anwser = scanner.next();
                if (anwser.equalsIgnoreCase("ja")) {
                    File[] files = new File(Config.storagePath).listFiles();
                    if (files != null) for (File file : files) file.delete();
                    System.exit(1);
                }
            }
            case 3 -> {
                Helpers.clearScreen();
                new StartScreen().showScreen();
            }
            default -> new StartScreen("Het nummer dat u heeft ingevoerd is ongeldig.").showScreen();
        }

        Helpers.clearScreen();
        new StartScreen().showScreen();
    }
}
