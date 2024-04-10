package helpers;

import java.util.Calendar;
import java.util.Date;

public class Helpers {
    public static void handleException(Exception error) {
        System.out.println("Sorry, er is iets fout gegaan.");
        System.out.println("Geef de onderstaande informatie door aan een administrator:");
        error.printStackTrace();
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
