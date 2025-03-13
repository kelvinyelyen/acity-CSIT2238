// Structure of a Java Program
import java.util.Scanner;

public class JavaSyntax {
    public static void main(String[] args) {
        // Variables and Data Types
        int number = 10;
        double price = 99.99;
        char grade = 'A';
        String name = "Java";
        boolean isJavaFun = true;

        // Output in Java
        System.out.println("Number: " + number);
        System.out.println("Price: " + price);
        System.out.println("Grade: " + grade);
        System.out.println("Name: " + name);
        System.out.println("Is Java fun? " + isJavaFun);

        // Conditional Statements
        // if, if-else, and switch statements.
        if (number > 0) {
            System.out.println("Positive Number");
        } else {
            System.out.println("Negative Number");
        }

        // Nested if-else statement
        double bankBalance = 100000;
        if (bankBalance >= 90000) {
            if (bankBalance == 90000) {
                System.out.println("Budget is tight. Consider buying a BMW M3.");
            } else {
                System.out.println("Sufficient funds available. Buy a BMW M4 Competition.");
            }
        } else {
            System.out.println("Limited budget. Buy a Toyota Vitz.");
        }

        // Loops in Java
        // for loop
        System.out.println("Counting from 1 to 100:");
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }

        // while loop
        System.out.println("Counting from 1 to 10:");
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }

        // do-while loop
        int e = 1; // initialization
        do {
            System.out.println(e); //statement
            e++; //increment
        } while (e<100); //condition


        // Accepting User Input
        Scanner ic = new Scanner(System.in);
        System.out.print("How much do you want?: ");
        double cash = ic.nextDouble(); // read a double value
        System.out.println("Balance: " + cash);
        ic.close();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a choice: ");
        int choice = sc.nextInt(); // read an integer value

        // switch statement
        switch(choice) {
            case 1: 
                System.out.println("Hello");
            case 2: 
                System.out.println("Bye");
        }

        sc.close();
    }
}
