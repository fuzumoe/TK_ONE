 

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 *  @authors Group C 
 */
public class PlugInView extends javax.swing.JFrame {

        //view controller
        private MinionsController mainCtrl;
        //players name
        private String playersName;
       /*__________________________________________*/
        // set of labels for both icon and text field discriptions
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        /*__________________________________________*/
        //nick name text field
        private javax.swing.JTextField nickNameTxt;
        // plugin button
        private javax.swing.JButton pluginButton;
    
    /**
     * Constructor :: ConstructS new form PlugIn from mainCtrl
     */
    public PlugInView( MinionsController mainCtrl) throws RemoteException {
        // intitialize JFrame components
        initComponents();
        //set main controller
        this.mainCtrl = mainCtrl;
    }
    
   // get player nick name from JTextField
    public String getPlayersName() {
        return playersName;
    }
   //set  players nick name 
    public void setPlayersName(String playersName) {
        this.playersName = playersName;
    }
    //return nickNameTxt JTextField
    public JTextField getNickNameTxt() {
        return nickNameTxt;
    }

       
   /**
   * intitialize JFrame components
   */
    @SuppressWarnings("unchecked")
    private void initComponents() {
       //JPanel for containing all the components of the JFrame
        jPanel1 = new javax.swing.JPanel();
        //nickNameTxt JTextField
        nickNameTxt = new javax.swing.JTextField();
        //MINION big sized icon for the Jframe
        jLabel3 = new javax.swing.JLabel();
        // JTextField discription jLabel2
        jLabel2 = new javax.swing.JLabel();
        // pluginButton for adding nick name 
        pluginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        //set close operation 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // set JFrame title
        setTitle("::FEED THE MINION GAME::");
        //set Jframe background
        setBackground(new java.awt.Color(51, 51, 51));
        //set JFrame prefred size
        setPreferredSize(new java.awt.Dimension(1020, 601));
        //set JFrame to be un-resizable
        setResizable(false);
        // Jframe layout manager
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));
        // set jPanel1 background
        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        // set JFrame position at the CENTER
        jPanel1.setLayout(null);
        //set JTextField background
        nickNameTxt.setBackground(new java.awt.Color(102, 102, 102));
        //set font  for JTextField
        nickNameTxt.setFont(new java.awt.Font("FreeSerif", 0, 18)); 
        // set font color of JTextField
        nickNameTxt.setForeground(new java.awt.Color(255, 255, 255));
        //empyt nickNameTxt JTextField
        nickNameTxt.setText(" ");
       
        // add nickNameTxt to  jPanel1
        jPanel1.add(nickNameTxt);
        // set the position of nickNameTxt in jPanel1
        nickNameTxt.setBounds(10, 58, 240, 40);
       //set jLabel3 font 
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14));  
        //set jLabel3 font color
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        //set jLabel3 text
        jLabel3.setText("Nick-Name");
        //add jLabel3 to jPanel1
        jPanel1.add(jLabel3);
        //set position of jLabel3
        jLabel3.setBounds(10, 30, 240, 30);
        //set jLabel2 font
        jLabel2.setFont(new java.awt.Font("Ubuntu Medium", 1, 18)); 
        //set jLabel2 background
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        //set jLabel2 at center of screen
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //set jLabel2 text
        jLabel2.setText("FEED THE MINION");
        //add jLabel1 jPanel1 
        jPanel1.add(jLabel2);
        //set position of jLabel2  
        jLabel2.setBounds(30, 190, 300, 70);
        //set pluginButton background
        pluginButton.setBackground(java.awt.Color.darkGray);
        //set color of pluginButton 
        pluginButton.setForeground(new java.awt.Color(255, 255, 255));
        //set text of pluginButton
        pluginButton.setText("PLAY");
        // add click listener to pluginButton
        pluginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pluginButtonActionPerformed(evt);
            }
        });
        // add pluginButton to jPanel1
        jPanel1.add(pluginButton);
        //set position of pluginButton
        pluginButton.setBounds(260, 58, 110, 40);
        //set icon of  jLabel1
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minionIcon.png"))); 
        // add jLabel1 to jPanel1 
        jPanel1.add(jLabel1);
        // set jLabel1 position
        jLabel1.setBounds(0, 0, 940, 610);
        //add jPanel1 to the frame layout manager
        getContentPane().add(jPanel1);
        // set JFrame size
        setSize(new java.awt.Dimension(950, 635));
        //set JFrame in the center of the screen
        setLocationRelativeTo(null);
    } 
    /**
    * on click event of pluginButton
    */
    private void pluginButtonActionPerformed(java.awt.event.ActionEvent evt) {
       mainCtrl.pluginPlayer();
    } 

   
}
