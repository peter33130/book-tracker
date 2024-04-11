package screens;

import components.Book;
import components.List;
import components.Screen;
import helpers.Helpers;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchBookScreen extends Screen {
    public SearchBookScreen() {
    }

    public SearchBookScreen(String notification) {
        super(notification);
    }

    public void showScreen() {
        System.out.println("[\uD83D\uDD0D Zoeken]");

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator()); // fix bug where space results in error
        System.out.print("Uw zoekfunctie: ");
        String query = scanner.next();

        // print list
        Helpers.clearScreen();
        ArrayList<String> titles = new ArrayList<>();
        for (Book result : Book.searchBook(query)) titles.add(result.getTitle());
        int choice = new List(scanner, "Resultaten", titles.toArray(new String[0]), "Maak uw keuze: ").showList();
        Helpers.clearScreen();
        Book.getBookByTitle(titles.get(choice - 1)).printBook();
        System.out.println(); // new line
        new StartScreen().showScreen();
    }
}