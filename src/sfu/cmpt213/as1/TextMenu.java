package sfu.cmpt213.as1;

import java.util.ArrayList;
import java.util.List;

/**
 * The TextMenu class creates a generic menu list. Data includes an array list of
 * menu items that the user inputs. The class sets up the title and allows you to
 * choose between a main menu title or a submenu title with the addition to print out the menu items.
 * <p>
 * The TextMenu class also supports the menu selection, which allows you to choose the content
 * from the list that was inputted.
 */

public class TextMenu {

    public static List<String> menuItems = new ArrayList<>();

    public static void setMenuList(String addItem) {

        menuItems.add(addItem);
    }

    public static void printMenuTitle(String title, boolean isUsingSubmenu) {
        if (isUsingSubmenu) {
            System.out.println("\n" + title + ":");

            for (int i = 0; i < title.length() + 1; i++) {
                System.out.print("*");
            }
            System.out.print("\n");

        } else {
            for (int i = 0; i < title.length() + 4; i++) {
                System.out.print("*");
            }
            System.out.print("\n");

            System.out.println("* " + title + " *");

            for (int i = 0; i < title.length() + 4; i++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    public static void printMenuArrayContents() {
        int count = 1;
        for (String list : menuItems) {
            System.out.println(count + ". " + list);
            count++;
        }
        System.out.print("> ");
    }

    public static void menuSelection(int selection) {
        // Since input is between 1 - 6 we need to format it for arrays that start at 0
        selection -= 1;

        boolean isInArrayListBounds = (selection >= 0) && (selection < menuItems.size());
        if (isInArrayListBounds) {

            switch (selection) {
                case 0 -> {
                    // Print list of minions
                    getMenuContents();
                    System.out.print("\n");
                }
                case 1 -> {
                    // Add a new minion
                    Minion.addMinion();
                    System.out.print("\n");
                }
                case 2 -> {
                    // remove a minion
                    getMenuContents();
                    Minion.addEvilDeedOrRemoveMinion(true);
                    System.out.print("\n");
                }
                case 3 -> {
                    // Attribute evil deed to a minion
                    getMenuContents();
                    Minion.addEvilDeedOrRemoveMinion(false);
                    System.out.print("\n");
                }
                case 4 -> {
                    // DEBUG: Dump objects (toString)
                    System.out.println("\nAll minion objects:");
                    Minion.isUsingDebugFormat = true;
                    printMinionArrayContents();
                    System.out.print("\n");
                }
                case 5 -> {
                    // Exit
                    Main.isMainLoopRunning = false;
                }
                default -> {
                    // No need for default because if statement
                    // only allows inputs between 1 - 6 (Array: 0 - 5)
                    assert false;
                }
            }
        } else {
            System.out.println("Error:  Please enter a selection between 1 and 6 \n");
        }
    }

    // A method that holds common called contents/methods
    private static void getMenuContents() {
        printMenuTitle(menuItems.get(0), true);
        Minion.isUsingDebugFormat = false;
        printMinionArrayContents();
    }

    private static void printMinionArrayContents() {
        if (Main.MINION_TRACKER_LIST.isEmpty()) {
            System.out.println("No minions found. \n");
            return;
        }

        for (int i = 0; i < Main.MINION_TRACKER_LIST.size(); i++) {
            System.out.println((i + 1) + ". " + Main.MINION_TRACKER_LIST.get(i));
        }
    }

}
