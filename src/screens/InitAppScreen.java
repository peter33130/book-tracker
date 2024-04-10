package screens;

import components.Screen;
import configuration.UserConfig;
import java.util.Scanner;

public class InitAppScreen extends Screen {
    public InitAppScreen() {}

    public InitAppScreen(String notification) {
        super(notification);
    }

    public void showScreen() {
        System.out.println("[\uD83D\uDC4B Welkom]");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Het lijkt erop dat u voor het eerst deze applicatie gebruikt.");
        System.out.print("Wat is uw naam?: ");
        String name = scanner.next();
        new UserConfig(name).write(); // save config

    }
}
