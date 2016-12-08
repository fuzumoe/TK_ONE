 
package server;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
 
import java.util.List;
 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
 
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.Order;
import models.Room;
import models.User;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import stubs.RepositoryStub;


/**
 *
 * @author adam
 */
public class ServerGUI extends JFrame  {

    private static final long serialVersionUID = 1L;
    
    /**
      * ****************
      * Constants
      * ****************
     */
    private final String searchUser = "Search for user"; 
    private final String searchRoom = " Search for room";
    private final String searchOrders = "Search for Order";
    private final String searchOnlineUsers = "Search for  Online User";
    
    BufferedReader reader;

    /**
     * Services variables 
     */
    private final   RESTfulService resService;
    public RepositoryStub rep = RepositoryStub.staticInstance;
    


    
    /**
     * Repository Variables
     */
    private final   SOAPServer soapService; 
    private List<Room>  rooms;
    private List<User> users;
    private List<Order> orders;
    private final Timer timer;
  
    
    /**
     * table model Variables
     */
    private final DefaultTableModel modeljTable1; 
    private final DefaultTableModel modeljTable2;
    private final DefaultTableModel modeljTable3;
    private final  TableRowSorter<TableModel> rowSorterjTable1;
    private final  TableRowSorter<TableModel> rowSorterjTable2 ;
    private final  TableRowSorter<TableModel> rowSorterjTable3 ;
          
    /**
     * Repository Entities Variables
     */
    private int id;
    private String room;
    private Date resDate;
    private String additionalService;
    private double price;
    private int amount;
    
    
    private  String fullName;
    private  String  userName;
    private  String  userPassword;
    private String orticket;
    private String orderer ="";
    private int oramount;
    private double orcoast;
  
    private PrintStream printStream; 
    
    private boolean refresh = true;

   
    /**
     * Creates new form ServerGUI
     * @throws java.io.IOException
     */
    String[][]  data;
    String[] columns;
    
    public ServerGUI() throws IOException {
         data = new String [][]{};
   
 
         columns = new String [] {"ID","Room", "Price", "Amount","Avai. Date ","Additional Service" };
         
         modeljTable1 =  new DefaultTableModel(data, columns);
         
         columns = new String [] {"Name", "Username", "Password"};
         modeljTable2 = new DefaultTableModel(data, columns);
         
        columns= new String [] {"Room","Amount", "Cost","Res By","Res Date" };
         modeljTable3 = new DefaultTableModel(data, columns);
         initComponents();
        
         reader = new BufferedReader(new InputStreamReader(System.in));
         printStream = new PrintStream(new CustomOutputStream(jTextArea1));
         System.setOut(printStream);
         System.setErr(printStream);
        
       
        resService = new  RESTfulService(); 
        soapService = new  SOAPServer();
        
        rooms = rep.getRoomsRepository().getAllRooms();
        users = rep.getUsersRepository().getAllUsers();
        orders = rep.getOrdersRepository().getAllOrders();
 
      
         
       
         
           rowSorterjTable1    = new  TableRowSorter<>(jTable1.getModel());
           rowSorterjTable2    = new  TableRowSorter<>(jTable2.getModel());
           rowSorterjTable3    = new  TableRowSorter<>(jTable3.getModel());
//       
          
          jTable1.setRowSorter(rowSorterjTable1);
          jTable2.setRowSorter(rowSorterjTable2);
          jTable3.setRowSorter(rowSorterjTable3);
          
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable3.getTableHeader().setReorderingAllowed(false);
            
         TableColumn dataColumn = jTable1.getColumnModel().getColumn(4);
         dataColumn.setCellEditor(new DatePickerCellEditor());
     
         constructRoomsTable();
         constructUsersTable();
         constructOrdersTable();   
         
         timer = new Timer(700, new Update());
         timer.start();
         refresh = true;

    }
   
    
    public void constructRoomsTable( ){
        
         rooms = rep.getRoomsRepository().getAllRooms();

          modeljTable1.setRowCount(0);
          
          for(@SuppressWarnings("LocalVariableHidesMemberVariable")Room room: rooms){
             
           modeljTable1.addRow(new String[]{room.getId()+"",room.getRoom(), 
                                         room.getPrice()+"",room.getAmount()+"",
                                         room.getStringFromDate(room.getDateFromString(room.getAvailable()))+"",
                                         room.getAdditionalService()+""});

          }
     
    }
    
    
  
    public void constructUsersTable(){
      
          
         modeljTable2.setRowCount(0);
        users = rep.getUsersRepository().getAllUsers();
             for(User user: users)
              modeljTable2.addRow(new String[]{user.getFullName(),user.getUserName(),user.getUserPassword()});

  
    }
    public void constructOrdersTable(){

       modeljTable3.setRowCount(0);
        orders = rep.getOrdersRepository().getAllOrders();
        
        for(Order order :orders)
          modeljTable3.addRow(new String[]{order.getRoom(),order.getAmount()+"",
                                           order.getCoast()+"",order.getOrderer(),order.getReserveDate()});
   
      
        
    }
    
    public void refresh(){
       
      constructOrdersTable();
      constructRoomsTable();
      constructUsersTable();
     
    }
    
    /**
     *  this method will set the mouse cursor to change to search cursor
     * @param cursorIndex
     */
    
    /**
     * this method will change the text of JTextfield
     * @param jTextField
     * @param text
     */
     public void changeText(JTextField jTextField,String text) {
       changeCursor(Cursor.WAIT_CURSOR);
       
        if(jTextField.getText() == null || "".equals(jTextField.getText())){
            jTextField.setText(text);
        }
        
       changeCursor(Cursor.DEFAULT_CURSOR);
    }
    public void changeCursor(int CursorIndex){
        this.setCursor(Cursor.getPredefinedCursor(CursorIndex));
    }

    /**
     * This method is called from within the constructor to initialize the GUI form.
     * 
     */
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::Ticket Ordering Services Server::");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Rooms Repository"));

        jTable1.setModel(modeljTable1
        );
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton14.setText("Add User");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Remove User");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Search for user");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField1MouseExited(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Users Repository"));

        jTable2.setModel( modeljTable2
        );
        jScrollPane6.setViewportView(jTable2);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Orders Repository"));

        jTable3.setModel( modeljTable3 );
        jScrollPane8.setViewportView(jTable3);

        jButton11.setText("Add Room");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Remve Room");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Search for Room");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2MouseExited(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Search for Order");
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField3MouseExited(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jButton16.setText("REFRESH");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton12});

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        setSize(new java.awt.Dimension(1176, 687));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        changeText(jTextField2,searchRoom);
    }//GEN-LAST:event_jTextField2MouseExited

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
       
        jTextField3.setText("");

    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseExited
   
       changeText(jTextField3,searchOrders);
    }//GEN-LAST:event_jTextField3MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       
        changeCursor(Cursor.WAIT_CURSOR);
       
        try {
             
         if(refresh== true){
          timer.stop();
          jButton11.setText("SAVE");
            modeljTable1.addRow(new String[]{"","","", "",""});
            refresh=false;
         }else if(refresh== false){
           
        int index = jTable1.getSelectedRow();
        id = new Integer(modeljTable1.getValueAt(index-1,0).toString());
       room = modeljTable1.getValueAt(index, 1).toString();
       price = new Double(modeljTable1.getValueAt(index, 2).toString());
       amount = new Integer(modeljTable1.getValueAt(index, 3).toString());
       resDate = (Date) modeljTable1.getValueAt(index, 4);
       additionalService = modeljTable1.getValueAt(index, 5).toString();
       System.err.println(modeljTable1.getValueAt(index, 4).getClass().toString());
//    rep.getRoomsRepository().getAllRooms().add(new Room(index+1,ticket,amount,price));
        rep.getRoomsRepository().getAllRooms().add( new Room(room,amount,price,resDate.toString(),additionalService,id +1));
//    public Room(String room, int amount, double price, String available, String additionalService, int id) {

//      Room(String room, int amount, double price, Date available, String additionalService, int id) {
         modeljTable1.removeRow(index);
         modeljTable1.addRow(new String[]{id+"",room,price+"", amount+""});
         System.err.println("\n************************************************************"); 
         System.err.println("::Room "+room +" added::");
             timer.start();
            refresh=true;
           jButton11.setText(" Add Ticket");
           refresh();
           timer.start();
                }
        } catch (Exception e) {
            System.err.println(e);
        }
        
        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       changeCursor(Cursor.WAIT_CURSOR);
      
       changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_formWindowClosing

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
 
              }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
                   changeCursor(Cursor.WAIT_CURSOR);
       
        String text = jTextField2.getText();

                if (text.trim().length() == 0) {
                    rowSorterjTable1.setRowFilter(null);
                } else {
                    rowSorterjTable1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }  
                     
         
        changeCursor(Cursor.DEFAULT_CURSOR);

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
                changeCursor(Cursor.WAIT_CURSOR);
 
        String text = jTextField2.getText();

                if (text.trim().length() == 0) {
                    rowSorterjTable1.setRowFilter(null);
                } else {
                    rowSorterjTable1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }  
                       changeCursor(Cursor.DEFAULT_CURSOR);
                 
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        changeCursor(Cursor.WAIT_CURSOR);
 
        String text = jTextField3.getText();

                if (text.trim().length() == 0) {
                    rowSorterjTable3.setRowFilter(null);
                } else {
                    rowSorterjTable3.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }  
                       changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
      changeCursor(Cursor.WAIT_CURSOR);
        try {
            refresh();
            timer.start();
        } catch (Exception e) {
        }
  
                       changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        changeCursor(Cursor.WAIT_CURSOR);
          try { 
            if(refresh == true){
                  refresh =false;
                    jButton12.setText("Select and Delete");
                    timer.stop();
          
            
            }
            else if(refresh == false){
                
              int index = jTable1.getSelectedRow();
            id = new Integer(modeljTable1.getValueAt(index, 0).toString());
            room = modeljTable1.getValueAt(index, 1).toString();
            price = new Double(modeljTable1.getValueAt(index, 2).toString());
            amount = new Integer(modeljTable1.getValueAt(index, 3).toString());
//            Room tempe  = new Room(id, room, amount, price);
             rep.getRoomsRepository().removeRoom(index);
            refresh();
            System.err.println("\n************************************************************");
            System.err.println("\t::Room "+room +" removed::");
            
                refresh = true;
                jButton12.setText("Remve Ticket");
                refresh();
                timer.start();
            }
        } catch (Exception e) {
        }

        changeCursor(Cursor.DEFAULT_CURSOR);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        changeCursor(Cursor.WAIT_CURSOR);

        String text = jTextField1.getText();

        if (text.trim().length() == 0) {
            rowSorterjTable2.setRowFilter(null);
        } else {
            rowSorterjTable2.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }

        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseExited
        changeText(jTextField1,searchUser);
    }//GEN-LAST:event_jTextField1MouseExited

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        changeCursor(Cursor.WAIT_CURSOR);
        try {
            
      
         if(refresh == true){
             jButton15.setText("Select and Delete");
             timer.stop();
              refresh = false;
         }  else if(refresh == false){
              jButton15.setText("Remove User");
                refresh = true;
               int index = jTable2.getSelectedRow();
            fullName = modeljTable2.getValueAt(index, 0).toString();
            
            userName = modeljTable2.getValueAt(index, 1).toString();
            userPassword = modeljTable2.getValueAt(index, 2).toString();
            
            User user = 
            rep.getUsersRepository().getUserByUsername(userName);
            rep.getUsersRepository().getAllUsers().remove(user);
            
             System.err.println("\n************************************************************");
            System.err.println("::User "+fullName +" Deleted::");
            refresh();
           timer.start();
         }
           } catch (Exception e) {
        }
        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
 
        changeCursor(Cursor.WAIT_CURSOR);
        
        try {
            if(refresh == true){
             timer.stop();
             modeljTable2.addRow(new String[]{"","",""});
             jButton14.setText("SAVE");
             refresh = false;
        }else if(refresh == false){
               refresh = true;
               try{
            int index = jTable2.getSelectedRow();
            fullName = modeljTable2.getValueAt(index, 0).toString();
            
            userName = modeljTable2.getValueAt(index, 1).toString();
            userPassword = modeljTable2.getValueAt(index, 2).toString();
            rep.getUsersRepository().getAllUsers().add(new User(fullName, userName, userPassword));
            modeljTable2.removeRow(index);
            modeljTable2.addRow(new Object[]{fullName,userName, userPassword});
           
             System.err.println("\n************************************************************");
            System.err.println("::User "+fullName +" added::");
             jButton14.setText("Add User");
               }catch(Exception e){
                   e.printStackTrace();
               }
               refresh();
                     timer.start();
              }
        } catch (Exception e) {
        }

        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
     
    }//GEN-LAST:event_jTable1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ServerGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

 
    class Update implements ActionListener,Runnable{

        public Update() {
        }
        

        @Override
        public void actionPerformed(ActionEvent ae) {
         
               refresh();
          
         }

        @Override
        public void run() {
         }
        
        
    }
    class CustomOutputStream extends OutputStream {
    
     private JTextArea textArea;
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(jTextArea1.getDocument().getLength());
        
      }
    }
   
 
}
