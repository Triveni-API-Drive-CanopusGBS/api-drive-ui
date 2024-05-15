package TimShort.Udemy.BankingApplication;

import java.util.List;

import TimShort.Udemy.BankingApplication.Utilities.CSV;


public class BankingApplication {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
    	//Read a CSV file by calling location.Each back-slash is escaped by another back-slash.
        String file = "C:\\IdeaProjects\\MyJavaDevelopmentPractice\\src\\utilities\\CSV.csv";
        
        List<String[]> newAccountHolders = CSV.read(file);
        for (String[] accountholder :newAccountHolders) {    //ForEach String[] in List
            if(accountholder[2].equalsIgnoreCase("Savings")){
                SavingsAccount savingAccount = new SavingsAccount(accountholder[0], accountholder[1], Double.parseDouble(accountholder[3]));
                savingAccount.showInfo();
                savingAccount.calculateCompoundInterest(12);
                System.out.println("***********************************");
            }
            else if(accountholder[2].equalsIgnoreCase("Checking")){
                CheckingAccount checkingAccount = new CheckingAccount(accountholder[0], accountholder[1], Double.parseDouble(accountholder[3]));
                checkingAccount.showInfo();
                checkingAccount.calculateCompoundInterest(12);
            }
            else {
                System.out.println("Not able to read the account type");
            }

        }



        //Read a CSV file then create new accounts based on that data by calling either checking or saving class.

    }
}
