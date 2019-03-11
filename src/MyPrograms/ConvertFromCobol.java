package MyPrograms;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kerwan Miguel Antoine
 */

public class ConvertFromCobol {
    static Scanner input = new Scanner (System.in);
    static char moreParts;
    
    static String partNO;
    static String partDescription;
    static int QtyOnHand;
    static double unitPrice;
    
    public static void main(String[] args) {
        getData();
        
        switch (moreParts) {
            case 'Y':
                getData();
                break;
            case 'N':
                System.exit(0);
            default:
                System.out.println ("Wrong Input");
                break;
        }      
    }
    
    public static void writeToFile () {
        try (DataOutputStream writer = new DataOutputStream(
                new BufferedOutputStream (new FileOutputStream ("CH0508.DAT", true) ) );
                )
        {            
            writer.writeUTF("\nPART-NO - " + partNO);
            writer.writeUTF("\nPART-DESCRIPTION - " + partDescription);
            writer.writeUTF("\nQTY-ON-HAND - " + QtyOnHand);
            writer.writeUTF("\nUNIT-PRICE - $" + unitPrice);
            writer.writeUTF("\n");
            
            System.out.println("Record inserted!");
            
            writer.flush();
            writer.close();
        }
        catch(Exception ex){
            Logger.getLogger (ConvertFromCobol.class.getName()).log (Level.SEVERE, "Error writing to file", ex);
        } 
    }
    
    public static void getData () {
        System.out.print ("ENTER PART NO: ");
        partNO = input.next();
        System.out.print ("ENTER THE PART DESCRIPTION: ");
        partDescription = input.next();
        System.out.print ("ENTER THE QUANTITY ON HAND: ");
        QtyOnHand = input.nextInt();
        System.out.print ("ENTER THE UNIT PRICE: ");
        unitPrice = input.nextDouble();
        System.out.println ("\n");
        
        writeToFile();
        
        System.out.print ("DO YOU WISH TO CONTINUE?(Y/N) ");
        moreParts = input.next().toUpperCase().charAt(0);
    }
}
