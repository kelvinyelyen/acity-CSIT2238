import java.util.Scanner;
public class Example {
    int kofi = 10;

    // static method
    public double subtraction(double a, double b) {
        double difference = a - b;
        return difference;
    }
    // non-static method
    public double addition(double a, double b) {
        double sum = a + b;
        return sum;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number:");
        double a = input.nextDouble();

        System.out.print("Enter second number:");
        double b = input.nextDouble();
        
        // caling static methods
        // double total = addition(a, b);
        // double difference = subtraction(a, b);
        

        // calling non-static methods
        Example example = new Example();
        double total = example.addition(a, b);
        double difference = example.subtraction(a, b);
        
        System.out.println("The sum of " + a + " and " + b + " is " + total);
        System.out.println("The difference between " + a + " and " + b + " is " + difference);

        input.close();
    }
}
