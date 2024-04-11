package screens;

import components.Book;
import components.Screen;
import helpers.Helpers;

import java.util.Scanner;

public class ModifyBookScreen extends Screen {
    public String name;

    public ModifyBookScreen(String name) {
        this.name = name;
    }

    public ModifyBookScreen(String notification, String name) {
        super(notification);
        this.name = name; // constructor changing werkt hier niet
    }

    @Override
    public void showScreen() {
        System.out.println("[\uD83D\uDCD5 Boek aanpassen]");

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator()); // fix bug where space results in error

        System.out.print("Auteur: ");
        String author = scanner.next();

        System.out.print("Rating: ");
        int rating = scanner.nextInt();

        // validate rating
        if (!AddBookScreen.validateRating(rating)) {
            Helpers.clearScreen();
            new AddBookScreen("Een rating moet tussen de 1-5 aan.").showScreen();
        }

        System.out.print("Opmerking (optioneel): ");
        String notes = scanner.next();
        if (notes.isEmpty()) notes = null;

        new Book(name, author, rating, notes).saveBook(); // save book

        Helpers.clearScreen();
        new StartScreen(STR."'\{name}' successvol aangepast.").showScreen();
    }
}
