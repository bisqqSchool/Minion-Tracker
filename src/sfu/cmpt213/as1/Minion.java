package sfu.cmpt213.as1;

import java.util.Scanner;

/**
 * Minion class model holds data to create a minion form the constructor.
 * Data includes the minions name, height, and the amount of evil deeds.
 * The class also supports two different printing formats controlled by the isUsingDebugFormat.
 * <p>
 * In addition, the class supports creating and adding a minion to the list,
 * attributing an evil deed(s) and removing a minion from the list with the
 * main Scanner INPUT.
 */

public class Minion {
    public static final Scanner INPUT = new Scanner(System.in);
    public static boolean isUsingDebugFormat;
    private final String name;
    private final double height;
    private int numberOfEvilDeeds;

    private Minion(String name, double height) {
        this.name = name;
        this.height = height;
        this.numberOfEvilDeeds = 0;
    }

    public static void addEvilDeedOrRemoveMinion(boolean isRemovingMinion) {
        System.out.println("(Enter 0 to cancel)");

        // left to be true for user input validation
        while (true) {
            System.out.print("> ");

            if (INPUT.hasNextInt()) {
                int userInput = INPUT.nextInt();

                boolean isInArrayListBounds = (userInput > 0) && (userInput <= Main.MINION_TRACKER_LIST.size());
                if (isInArrayListBounds) {
                    // Since input is between 1 - 6 we need to format it for arrays that start at 0
                    // in order to remove the minion at that index
                    userInput -= 1;

                    for (Minion minions : Main.MINION_TRACKER_LIST) {
                        if (isRemovingMinion) {
                            if (userInput == Main.MINION_TRACKER_LIST.indexOf(minions)) {
                                Main.MINION_TRACKER_LIST.remove(userInput);
                                return;
                            }
                        } else {
                            if (userInput == Main.MINION_TRACKER_LIST.indexOf(minions)) {
                                minions.numberOfEvilDeeds += 1;
                                System.out.print(minions.name + " has now down " + minions.numberOfEvilDeeds + " evil deed(s)! \n");
                                return;
                            }
                        }
                    }

                } else if (userInput == 0) {
                    return;

                } else {
                    System.out.println("Error: Please enter a selection between 0 and " + Main.MINION_TRACKER_LIST.size() + "\n");
                }

            } else {
                System.out.println("Error: invalid input, please try again \n");
                INPUT.next();
            }
        }
    }

    public static void addMinion() {
        String name;
        double height;

        // Left to be true for user input validation
        while (true) {
            INPUT.nextLine();
            System.out.print("Enter minion's name:   ");

            name = INPUT.nextLine();

            if (name.length() >= 1) {
                // Left to be true for user input validation
                while (true) {
                    System.out.print("Enter minion's height: ");

                    if (INPUT.hasNextDouble()) {
                        height = INPUT.nextDouble();

                        if (height >= 0) {
                            Minion createMinion = new Minion(name, height);
                            Main.MINION_TRACKER_LIST.add(createMinion);
                            return;

                        } else {
                            System.out.println("Error: Height must be >= 0. \n");
                        }

                    } else {
                        System.out.println("Error: No number found, invalid input please try again \n");
                        INPUT.next();
                    }
                }
            } else {
                System.out.print("\n");
                System.out.print("Error: Name must have 1 character or more, press enter to retry \n");
            }
        }
    }

    @Override
    public String toString() {

        return printingFormat();
    }

    private String printingFormat() {
        if (isUsingDebugFormat) {
            return getClass().getName() +
                    "[Name: " + this.name + ", " +
                    "Evil Deeds: " + this.numberOfEvilDeeds +
                    ", Height: " + this.height + "]";

        } else {
            return this.name + ", " +
                    this.height + "m, " +
                    this.numberOfEvilDeeds + " evil deed(s)";
        }
    }

}
