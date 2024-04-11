package screens;

import components.Book;
import components.Screen;
import helpers.Helpers;
import java.util.Scanner;

public class AddBookScreen extends Screen {
    public AddBookScreen() {}

    public AddBookScreen(String notification) { super(STR."\{notification}"); }

    @Override
    public void showScreen() {
        System.out.println("[\uD83D\uDCD5 Boek toevoegen]");

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator()); // fix bug where space results in error
        System.out.print("Naam: ");
        String name = scanner.nextLine();

        // check if book already exist
        for (Book book : Book.getAllBooks())
            if (
                    book.getTitle().equalsIgnoreCase(name)
                    && Helpers.getCurrentMonth() == book.getDate().getMonth()
            ) {
                Helpers.clearScreen();
                new ModifyBookScreen(name).showScreen();
            }

        System.out.print("Auteur: ");
        String author = scanner.next();

        System.out.print("Rating: ");
        int rating = scanner.nextInt();

        // validate rating
        if (!validateRating(rating)) {
            Helpers.clearScreen();
            new AddBookScreen("Een rating moet tussen de 1-5 aan.").showScreen();
        }

        System.out.print("Opmerking (optioneel): ");
        String notes = scanner.next();
        if (notes.isEmpty()) notes = null;

        new Book(name, author, rating, notes).saveBook(); // save book

        Helpers.clearScreen();
        new StartScreen(STR."'\{name}' successvol toegevoegd.").showScreen();
    }

    public static boolean validateRating(int rating) {
        return rating >= 1 && rating <= 5;
    }
}
