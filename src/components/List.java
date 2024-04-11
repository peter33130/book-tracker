package components;

import helpers.Helpers;
import java.util.Scanner;

public class List {
    private final Scanner scanner;
    private String title;
    private final String[] items;
    private final String sub;

    public List(Scanner scanner, String[] items, String sub) {
        this.scanner = scanner;
        this.items = items;
        this.sub = sub;
    }

    public List(Scanner scanner, String title, String[] items, String sub) {
        this.scanner = scanner;
        this.title = title;
        this.items = items;
        this.sub = sub;
    }

    public int showList() {
        if (title != null) System.out.println(STR."[\{this.title}]"); // print titleif (title != null) System.out.println(STR."[\{this.title}]"); // print title

        int counter = 1;
        for (String item : items) {
            System.out.println(STR."\{counter}. \{item}");
            counter++;
        }

        System.out.print(STR."\{this.sub} ");

        int choice = this.scanner.nextInt();

        // makes sure choice is valid
        if (choice > counter) {
            Helpers.clearScreen();
            this.showList();
        }

        return choice;
    }
}
