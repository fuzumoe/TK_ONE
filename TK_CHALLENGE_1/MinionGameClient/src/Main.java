/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Group C
 */
public class Main {

     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws RemoteException {

  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MinionsController();
                } catch (RemoteException ex) {
                    Logger.getLogger(MinionsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
