import java.util.Scanner;
public class Arrays {
    public static void main(String[] args) {
        // int[] grades; // declaration
        // grades = new int[5]; // initialization
        int[] scores = new int[5]; // declaration and initialization
        // int[] ages = {20, 18, 19, 21}; // delaration and assignment
        
        // assigning values to an array
        // arrayName[index] = value;
        scores[0] = 35;
        scores[1] = 40;
        scores[2] = 67;
        scores[3]= 90;
        scores[4]= 100;
        
        // accessing elements in an array
        System.out.println("The first score is: " + scores[0]);
        System.out.println("The second score is: " + scores[1]);
        System.out.println("The third score is: " + scores[2]);
        System.out.println("The fourth score is: " + scores[3]);
        System.out.println("The fifth score is: " + scores[4]);

        // loop through an array
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }
        // or
        for (int score: scores) {
            System.out.println(score);
        }

        // accepting values from the user
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = input.nextInt();
        int[] ages = new int[size];

        for (int i = 0; i < ages.length; i++) {
            System.out.print("Enter age " + (i + 1) + ": ");
            ages[i] = input.nextInt();
        }

        for (int age: ages) {
            System.out.println(age);
        }

        input.close();
    }
}
