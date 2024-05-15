package TimShort.Udemy.BankingApplication;

public abstract class Account implements IBaseRate {
    private final String name;
    private final String sSN;
    protected double balance;
    protected String accountNumber;
    private int id = 10000;

    public Account(String name, String sSN, double initDeposit) {
        this.name = name;
        this.sSN = sSN;
        this.balance = initDeposit;
        System.out.println("Name: " + name +
                "\nSSN: " + sSN +
                "\nInitDeposit " + initDeposit);
        createAccountNumber();
    }


    // create account number
    public void createAccountNumber() {
        id++;
        String lastTwoOfSSN = sSN.substring(sSN.length() - 2);
        int threeDigitRandomNumber = (int) (Math.random() * Math.pow(10, 3));
        this.accountNumber = lastTwoOfSSN + id + threeDigitRandomNumber;
    }

    //Do deposit();
    public void deposit(int deposit) {
        this.balance = balance + deposit;
        viewbalance();
    }

    protected void viewbalance() {
        System.out.println("Your money is deposited, Your left over balance is: " + balance);
    }

    //Do withdraw();
    public void withdraw(int deposit) {
        this.balance = balance + deposit;
        viewbalance();
    }

    //Do transfer();
    public void transfer(String towhere, int amount) {

    }

    public void showInfo() {
        System.out.println("Account{" +
                "name='" + name + '\'' +
                ", sSN='" + sSN + '\'' +
                ", balance=" + balance +
                '}');
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
