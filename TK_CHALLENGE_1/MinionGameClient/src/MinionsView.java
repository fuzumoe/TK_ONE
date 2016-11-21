
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @authors Group C
 */
public class MinionsView extends javax.swing.JFrame {
   //view controller
    private MinionsController mainCtrl;
    //Syste toolkit
    public Toolkit toolkit;
    // wreter print stream
    private PrintStream printStream;
    //stream buffer readder
    public BufferedReader reader;

  // jPanell container
    private javax.swing.JPanel jPanel1;
    //jScrollPane1 container
    private javax.swing.JScrollPane jScrollPane1;
    //JTextArea for displaying results
    private javax.swing.JTextArea jTextArea1;
    //JLabel for displaying the minion
    private javax.swing.JLabel lMinion;
    // End of variables declaration//GEN-END:variables
    /**
    * constructor :: constructs by passing main controller
    */
    public MinionsView(MinionsController mainCtrl) throws RemoteException {
          //intizialize view components
        initComponents();
        //configure reader to ready from the application output to the system
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        //configure the printer to print the input red from the buffer  in jtextArea
       this.printStream = new PrintStream(new CustomOutputStream(jTextArea1));
       //set the  print stream object configuration
       System.setOut(printStream);
       //set the buffer reader object configurea
       System.setErr(printStream);
       //set the mouse cursor icon to be a banan image icon
        this.jPanel1.setCursor(Toolkit.getDefaultToolkit()
                .createCustomCursor(
                        new ImageIcon(getClass().getResource("/banana.png")).getImage(),
                        new Point(0, 0), "CUSTOM"));
        //reset the mouse cursor icon to be the default system cursor for the rest of the components
        this.jScrollPane1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.jTextArea1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        // set the views controller
        this.mainCtrl = mainCtrl;
    }
    //get the label where the minion will apear in
    public JLabel getMinionLabel() {
        return this.lMinion;
    }

    /**
     * set minions position on the screen with coordinates from RMI service
     *
     * @param coordinates
     */
    public void setMinionsPosition(Coordinates coordinates) {
//        this.mainView.getMinionLabel().setLocation(coordinates.getX(), coordinates.getY());
        this.lMinion.setLocation(coordinates.getX(), coordinates.getY());
    }
 
    @SuppressWarnings("unchecked")
    /**
    * initialize JFrame components
    */
     private void initComponents() {
        //JPanel game minion container 
        jPanel1 = new javax.swing.JPanel();
        //Minion where the minion apears
        lMinion = new javax.swing.JLabel();
        //scroller container of the jTextArea1
        jScrollPane1 = new javax.swing.JScrollPane();
        //jTextArea1 to display results of the game
        jTextArea1 = new javax.swing.JTextArea();
        //set what happens when close icon is pressed
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //JFrame background color
        setBackground(new java.awt.Color(51, 51, 51));
        //JFrame cursor
        setCursor(Toolkit.getDefaultToolkit()
            .createCustomCursor(
                new ImageIcon(getClass().getResource("/banana.png")).getImage(),
                new Point(0,0),"CUSTOM"));
       //jPanel1 background color
        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        //jPanel1 background color
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
       // jPanel1 default layout mananger
        jPanel1.setLayout(null);
        //set minion icon
        lMinion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minion.png"))); // NOI18N
        //add mouse motion listener to lMinion
        lMinion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lMinionMouseClicked(evt);
            }
        });
        //add lMinion TO jPanel1
        jPanel1.add(lMinion);
        //set ititial lMinion loction on the JFrame
        lMinion.setBounds(238, 95, 150, 131);
        //jTextArea1 background color
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        //jtextArea width
        jTextArea1.setColumns(20);
        //jTextArea1 font color
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        //jTextArea1 height
        jTextArea1.setRows(5);
        // jTextArea1 empty at first
        jTextArea1.setText(" ");
        // contain th jTextArea1 to jScrollPane1
        jScrollPane1.setViewportView(jTextArea1);
        // group container
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        //set jPanel1 jScrollPane1 horizontal  gropu configuration
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        //set jPanel1 jScrollPane1 vertical gropu configuration
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
         //set JFrame size
        setSize(new java.awt.Dimension(935, 500));
        //display the frame in the center of the screen;
        setLocationRelativeTo(null);
    } 
     //lMinionMouseClicked conlick function 
    private void lMinionMouseClicked(java.awt.event.MouseEvent evt) {
         this.mainCtrl.feedMinion();
    }//GEN-LAST:event_lMinionMouseClicked

    


class CustomOutputStream extends OutputStream{
    
    private JTextArea textArea;
    
    public CustomOutputStream(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void write(int i) throws IOException {
        textArea.append(String.valueOf((char) i));
        textArea.setCaretPosition(jTextArea1.getDocument().getLength());
     }
    
}

}
