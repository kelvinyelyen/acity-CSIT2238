import java.util.Scanner;

public class EvenNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Nth number: ");
        int n = sc.nextInt();

        System.out.println("First " + n + " even numbers:");
        for (int i = 1; i <= n; i++) {
            System.out.print((2 * i) + " ");
        }

        sc.close();
    }
}