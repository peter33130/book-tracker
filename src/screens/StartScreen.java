package screens;

import components.List;
import components.Screen;
import helpers.Helpers;

import java.util.Scanner;

public class StartScreen extends Screen {
    public StartScreen() {
    }
    
    public StartScreen(String notification) {
        super(notification);
    }

    @Override
    public void showScreen() {
        Scanner scanner = new Scanner(System.in);
        int choice = new List(
                scanner,
                new String[]{
                        "\uD83D\uDCD5 Boek toevoegen of aanpassen",
                        "\uD83D\uDCDA Boekenlijst",
                        "\uD83D\uDD0D Zoeken",
                        "⚙️  Instellingen"
                },
                "Type het nummer van het scherm waar u naartoe wilt gaan:"
        ).showList();

        Screen screen = this;
        switch (choice) {
            case 1 -> screen = new AddBookScreen();
            case 2 -> screen = new BookListScreen();
            case 3 -> screen = new SearchBookScreen();
            case 4 -> screen = new SettingsScreen();
        }

        Helpers.clearScreen();
        screen.showScreen();
    }
}
