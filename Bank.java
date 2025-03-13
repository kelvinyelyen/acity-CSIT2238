public class Bank {
    // instance variables (attributes or fields)
    private String owner;
    private double balance;
    private String BankName;
    
    // constructor (initilaizes the object)
    public Bank (String owner, double balance) {
        // this refers to the current object
        this.owner = owner;
        this.balance = balance; 
        this.BankName = "Bank of ACity"; // default value
    }

    // instance methods (behaviors)
    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposited: " + amount + " New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance > amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + "New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds");   
        }
    }

    public void details() {
        Bank kofi_Account = new Bank("Kofi", 100000);
        System.out.println("Owner: " + kofi_Account.owner + " Balance: " + kofi_Account.balance + " Bank Name: " + kofi_Account.BankName);
    }

    // main method
    public static void main(String[] args) {
        // create an object or instance of the class
        Bank kofi_Account = new Bank("Kofi", 100000);

        // call the instance methods
        kofi_Account.deposit(400);
        kofi_Account.withdraw(200);
        kofi_Account.details();
    }
}
