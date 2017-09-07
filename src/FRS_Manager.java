
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *FRS manager
 * @author Shashank Shekhar
 */
public class FRS_Manager {
    
    Datamanager d;
    Bookingmanager b;
    Displaymanager disp;
    Searchmanager s;
    public ArrayList<Flight> spicejet;
    public ArrayList<Flight> silkair; 
    
    public static void main(String[] args)  {
        
        FRS_Manager frsManager= new FRS_Manager();
        
        if(args[0].equalsIgnoreCase("spicejet.csv")&&args[1].equalsIgnoreCase("silkair.csv")&&args.length==2)
        {
            
            frsManager.initialize();
        }
        else{
            System.out.println("File not found");
            System.exit(0);
        }
            
    }
    /**
     * method to initialize variables and instantiate different classes
     */
    public void initialize()
    {
        FRS_Manager frsManager= new FRS_Manager();
        
     
        frsManager.d = new Datamanager(frsManager);
        try {
            frsManager.spicejet = frsManager.d.readspicejet();
        } catch (IOException ex) {
            Logger.getLogger(FRS_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            frsManager.silkair = frsManager.d.readsilkair();
        } catch (IOException ex) {
            Logger.getLogger(FRS_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        frsManager.disp = new Displaymanager(frsManager);
        frsManager.disp.displayscreen1();
        frsManager.s = new Searchmanager(frsManager);
        frsManager.b = new Bookingmanager(frsManager);
        
    }
}