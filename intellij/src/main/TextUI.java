package main;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    private Manager manager;
    private Scanner scanner = new Scanner(System.in);
    void introMessage() {
        System.out.println("Welcome to the Pomodoro Game. In this text based prototype you will have timer" +
                " moving between work and break periods where you can play games. Press any key and hit enter" +
                " to configure the timer.");
        scanner.nextLine();
    }
    void getConfiguration() {
//        System.out.println(manager);
        boolean confirmed = false;
        int workPeriodLength = -1;
        int breakPeriodLength = -1;
        while (!confirmed) {
            workPeriodLength = -1;
            breakPeriodLength = -1;
            while (workPeriodLength <= 0) {
                try {
                    System.out.println("Please enter the length (in minutes) of the work period:");
                    workPeriodLength = scanner.nextInt();
                    if (workPeriodLength <= 0) {
                        System.out.println("Please enter a number greater than 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer!");
                    scanner.nextLine();
                }
            }
            while (breakPeriodLength <= 0) {
                try {
                    System.out.println("Please enter the length (in minutes) of the break period:");
                    breakPeriodLength = scanner.nextInt();
                    if (breakPeriodLength <= 0) {
                        System.out.println("Please enter a number greater than 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer!");
                    scanner.nextLine();
                }
            }
            System.out.println("Chosen settings: Work Period: " + workPeriodLength + " minutes" + " | " +
                    "Break Period: " + breakPeriodLength + " minutes");
            String confirmationChoice = "x";
            while(!confirmationChoice.equals("y") && !confirmationChoice.equals("n")) {
                System.out.println("Are these settings ok? (y/n)");
                confirmationChoice = scanner.next();
                if (confirmationChoice.equals("y")) {
                    confirmed = true;
                } else if (confirmationChoice.equals("n")) {
                    confirmed = false;
                } else {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'");
                }
            }
        }
        this.manager = new Manager(workPeriodLength, breakPeriodLength);
    }

    void periodInterface() {
        System.out.println("\nStarting session! Get to work!");
        this.manager.startSession();

        String enteredCommand = "x";
        System.out.println("Press and enter 'p' to get the period type, 'c' to check the time remaining, or 'q'" +
                " to quit");
        while (!enteredCommand.equals("q")) {
            System.out.println("Enter a command (p/c) or 'q' to quit");
            enteredCommand = scanner.next();

            if (enteredCommand.equals("p")) {
                System.out.println(this.manager.getCurrentPeriod());
            } else if (enteredCommand.equals("c")) {
                System.out.println(this.manager.currentPeriod.timeRemainingMessage());
                boolean startedNextPeriod = this.manager.nextPeriod();
                if (startedNextPeriod) {
                    System.out.println("Starting " + this.manager.getCurrentPeriod() + " period!");
                }
            } else if (enteredCommand.equals("q")) {
                System.out.println("Goodbye! Hope you had a productive Pomodoro session!");
            } else {
                System.out.println("Invalid command. Please enter 'p', 'c', or 'q'");
            }
        }
    }

//    void getCommand() {
//        String command = scanner.nextLine();
//        //manager.execute(command);
//    }

    void render(String s) {
        System.out.println(s);
    }

//    public TextUI(Manager manager, Scanner scanner) {
//        this.manager = manager;
//        this.scanner = scanner;
//    }

    public TextUI() {

    }

    public static void main(String[] args) {
        TextUI text = new TextUI();
        text.introMessage();
        text.getConfiguration();
        text.periodInterface();
    }

}
