package components;

import configuration.Config;
import helpers.Helpers;
import java.io.*;
import java.util.*;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String slug;
    public String title;
    public String author;
    public int rating;
    public String notes;
    public final Date date = new Date();

    public Book(String title, String author, int rating) {
        this.slug = createSlug(title);
        this.title = title;
        this.author = author;
        this.rating = rating;

    }

    public Book(String title, String author, int rating, String notes) {
        this(title, author, rating);
        this.notes = notes;
    }

    /**
     * Get a book directly by slug
     * @param slug - The slug of the book
     */
    public static Book getBookBySlug(String slug) {
        File file = new File(STR."\{Config.booksPath}/\{slug}.ser");
        if (!file.exists()) return null;
        Book book = null;

        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            Object obj = objectInput.readObject();
            if (obj instanceof Book) book = (Book) obj;
        } catch (Exception error) {
            Helpers.handleException(error);
        }

        return book;
    }

    /**
     * Get a book directly by the title
     * @param title - The title of the book
     */
    public static Book getBookByTitle(String title) {
        String slug = Book.createSlug(title);
        return Book.getBookBySlug(slug);
    }

    public void saveBook() {
        File file = new File(STR."\{Config.booksPath}/\{this.slug}.ser");
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

    /**
     * Search for a book
     * @param query - The query to search for
     */
    public static ArrayList<Book> searchBook(String query) {
        String[] words = Book.queryToArray(query);
        ArrayList<Book> results = new ArrayList<>();

        for (Book book : Book.getAllBooks()) {
            String title = book.title.toLowerCase();
            for (String word : words) {
                if (results.contains(book)) continue;
                if (title.contains(word)) results.add(book);
            }
        }

        return results;
    }

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(Config.booksPath).listFiles())) {
            Book book = Book.getBookBySlug(file.getName().replaceAll(".ser", ""));
            if (book != null) books.add(book);
        }

        return books;
    }

    public static String createSlug(String title) {
        return title.toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("-+", "-");
    }

    public static String[] queryToArray(String query) {
        return query.toLowerCase().split(" ");
    }

    public static boolean wasLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month == Helpers.getCurrentMonth();
    }

    public void printBook() {
        System.out.println(STR."Titel: \{this.title}");
        System.out.println(STR."Auteur: \{this.author}");
        System.out.println(STR."Rating: \{Book.createRating(this.rating)}");
        System.out.println(STR."Datum: \{this.date.toString()}");
        System.out.println(STR."Opmerking: \{this.notes}");

    }

    private static String createRating(int rating) {
        return "⭐".repeat(Math.max(0, rating));
    }
}
