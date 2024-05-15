package TimShort.Udemy.BankingApplication.Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSV {
    //This method will read data from CSV file return as a list.
    public static List<String[]> read(String file) {  //You are returning a List of String[], meaning each element of List is array of Strings.Each lin in CSV is a string[].
        List<String[]> listOfStringArrays = new LinkedList<String[]>();
        {   //Initialize a List Array
            String dataRow;
            BufferedReader bufferedReader;
            {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    while((dataRow = bufferedReader.readLine()) != null) {//Reads each line of the file and thrown IOException
                        String[] dataRecords = dataRow.split(","); //Split and assign the strings to String[]
                        listOfStringArrays.add(dataRecords);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Could not Find FILE: ");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Could not Read Line: ");
                    e.printStackTrace();
                }
            }
        }
        return listOfStringArrays;
    }
}

