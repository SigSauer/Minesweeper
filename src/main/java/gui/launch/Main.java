package gui.launch;

import gui.menu.MenuLaunch;

public class Main {
    public static void main(String[] args) {
        try {
            new MenuLaunch();
            System.out.println("Starting program: true");
            MenuLaunch.menuLaunch(args);
        } catch (Exception e) {
            System.err.println("Starting program: false");

        }
    }
}
