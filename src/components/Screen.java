package components;

public abstract class Screen {
    public Screen() {}
    public Screen(String notification) { System.out.println(STR."\uD83D\uDD14 \{notification}\n"); }
    public abstract void showScreen();
}