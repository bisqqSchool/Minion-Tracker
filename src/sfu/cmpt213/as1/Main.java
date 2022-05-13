package sfu.cmpt213.as1;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class file that executes the Minion Tracker program. Main Data includes
 * an arraylist of the Minion class. Main functionality of the class
 * includes setting up the Minion Tracker program and starting the main program loop.
 * <p>
 * In this class the user is allowed to setup the menu list which will output from the
 * generic class TextMenu
 */

public class Main {

    public static final List<Minion> MINION_TRACKER_LIST = new ArrayList<>();
    public static boolean isMainLoopRunning = true;

    public static void main(String[] args) {
        TextMenu.setMenuList("List minions");
        TextMenu.setMenuList("Add a new minion");
        TextMenu.setMenuList("Remove a minion");
        TextMenu.setMenuList("Attribute evil deed to a minion");
        TextMenu.setMenuList("DEBUG: Dump objects (toString)");
        TextMenu.setMenuList("Exit");

        setupProgramTitle("Welcome to the Evil Minion Tracker (tm)", "by Alex Biscoveanu.");
        startMainProgramLoop();
    }

    private static void setupProgramTitle(String programTitle, String programAuthor) {

        String starOutline = "";
        for (int i = 0; i < programTitle.length(); i++) {
            starOutline += "*";
        }

        System.out.print(starOutline + "\n");
        System.out.println(programTitle);
        System.out.println(programAuthor);
        System.out.print(starOutline + "\n\n");
    }

    private static void startMainProgramLoop() {
        while (isMainLoopRunning) {
            TextMenu.printMenuTitle("Main Menu", false);
            TextMenu.printMenuArrayContents();

            if (Minion.INPUT.hasNextInt()) {
                int userInput = Minion.INPUT.nextInt();
                TextMenu.menuSelection(userInput);

            } else {
                System.out.println("Error: invalid input, must input a value between 1 - 6 \n");
                Minion.INPUT.next();
            }
        }
    }

}
