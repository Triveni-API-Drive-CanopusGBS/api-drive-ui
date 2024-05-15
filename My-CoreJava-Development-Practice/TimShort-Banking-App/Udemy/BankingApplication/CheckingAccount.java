package TimShort.Udemy.BankingApplication;

public class CheckingAccount extends Account {
    private double checkingIntRate;
    private double compoundedInterest;


    public CheckingAccount(String name, String sSN, double initDeposit) {
        super(name, sSN, initDeposit);
        setCheckingAccountNumber();
    }

    public void setCheckingAccountNumber() {
        this.accountNumber = "CHK" + accountNumber;
    }

    // Override the Super class method, to write any code specific to Sub class
    @Override
    public void showInfo() {
        System.out.println("Your checking account info is: " + accountNumber);
    }
    //CALCULATE  interest rate

    public void calculateCompoundInterest(int months) {
        this.checkingIntRate = this.getBaseRate() * 0.15;
        this.compoundedInterest = balance * ((1 + checkingIntRate / 100));
        this.setBalance(balance + compoundedInterest);
        //System.out.println(compoundedInterest);
        viewbalance();
    }

}

