package screens;

import components.Book;
import components.List;
import components.Screen;
import helpers.Helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookListScreen extends Screen {
    public BookListScreen() {}

    public BookListScreen(String notification) { super(notification); }

    @Override
    public void showScreen() {
        System.out.println("[\uD83D\uDCDA Boekenlijst]");

        // get last month books
        ArrayList<String > books = new ArrayList<>();
        for (Book book : Book.getAllBooks()) {
            if (book.date.getMonth() != Helpers.getCurrentMonth()) continue;
            books.add(book.title);
        }

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator()); // fix bug where space results in error

        int choice = new List(scanner, books.toArray(new String[0]), "Maak uw keuze:").showList();
        Helpers.clearScreen();
        Book.getBookByTitle(books.get(choice - 1)).printBook();
        System.out.println(); // new line
        new StartScreen().showScreen();


    }
}
