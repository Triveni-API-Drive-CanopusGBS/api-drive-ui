package TimShort.Udemy.BankingApplication;

public class SavingsAccount extends Account {
    int safeBoxId;
    int safeBoxKey;
    private double savingIntRate;
    private double compoundedInterest;

    public SavingsAccount(String name, String sSN, double initDeposit) {
        super(name, sSN, initDeposit);
        setSavingAccountNumber();
    }

    //Create checking Account Number from the account number.
    public void setSavingAccountNumber() {
        this.accountNumber = "SAV" + accountNumber;
    }

    //Create a safeBoxWithKeyandId;
    public void createSafeBox() {
        this.safeBoxId = (int) (Math.random() * Math.pow(10, 3));
        this.safeBoxKey = (int) (Math.random() * Math.pow(10, 4));
    }

    //Create showInfo() to show the specific info of the Savings account
    @Override
    public void showInfo() {
        System.out.println("Your savings account number info is: " + accountNumber);
    }

    public void calculateCompoundInterest(int months) {
        this.savingIntRate = super.getBaseRate() - 0.25;
        this.compoundedInterest = balance * ((1 + savingIntRate / 100));
        this.setBalance(balance + compoundedInterest);
        //System.out.println(compoundedInterest);
        viewbalance();
    }
}
