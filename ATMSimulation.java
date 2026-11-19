import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        double balance = 1000.00;

        String[] transactions = new String[100];
        int transactionCount = 0;

        int attempts = 0;
        int maxAttempts = 3;
        boolean authenticated = false;

        while (attempts < maxAttempts) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();
            attempts++;

            if (enteredPin == CORRECT_PIN) {
                authenticated = true;
                System.out.println("Authentication successful! Welcome.\n");
                break;
            } else {
                int remaining = maxAttempts - attempts;
                if (remaining > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                } else {
                    System.out.println("Incorrect PIN. Maximum attempts reached. Card locked.");
                }
            }
        }

        if (!authenticated) {
            scanner.close();
            return;
        }

        boolean running = true;

        while (running) {
            System.out.println("\n--- ATM MAIN MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.printf("Current Balance: $%.2f%n", balance);

            } else if (choice == 2) {
                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();

                if (depositAmount > 0) {
                    balance += depositAmount;
                    if (transactionCount < transactions.length) {
                        transactions[transactionCount] = String.format("Deposit: +$%.2f (Balance: $%.2f)", depositAmount, balance);
                        transactionCount++;
                    }
                    System.out.printf("Deposited $%.2f. New balance: $%.2f%n", depositAmount, balance);
                } else {
                    System.out.println("Error: Deposit amount must be greater than 0.");
                }

            } else if (choice == 3) {
                System.out.print("Enter withdrawal amount: $");
                double withdrawAmount = scanner.nextDouble();

                if (withdrawAmount <= 0) {
                    System.out.println("Error: Withdrawal amount must be greater than 0.");
                } else if (withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    if (transactionCount < transactions.length) {
                        transactions[transactionCount] = String.format("Withdrawal: -$%.2f (Balance: $%.2f)", withdrawAmount, balance);
                        transactionCount++;
                    }
                    System.out.printf("Withdrew $%.2f. Remaining balance: $%.2f%n", withdrawAmount, balance);
                } else {
                    System.out.println("Error: Insufficient balance.");
                }

            } else if (choice == 4) {
                System.out.println("\n--- TRANSACTION HISTORY ---");
                if (transactionCount == 0) {
                    System.out.println("No transactions yet.");
                } else {
                    for (int i = 0; i < transactionCount; i++) {
                        System.out.println((i + 1) + ". " + transactions[i]);
                    }
                }

            } else if (choice == 5) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                running = false;

            } else {
                System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }

        scanner.close();
    }
}
