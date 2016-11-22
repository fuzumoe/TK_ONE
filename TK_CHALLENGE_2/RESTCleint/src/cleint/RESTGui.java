package cleint;

import java.awt.Cursor;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.PrintStream;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.Order;

import models.Ticket;

/**
 *
 * @author adam
 */
public class RESTGui extends JFrame {

    private static final long serialVersionUID = 1L;
    private final String searchTicket = " Search for user";
    private final String searchOrders = "Search for Order";
    public RESTCleint rest;
    private List<Ticket> tickets;
    private List<Order> orders;
    private String ResponseForOrder ="Error! please correct your procedure";
    private DefaultTableModel modeljTable1;
    private DefaultTableModel modeljTable3;
    private TableRowSorter<TableModel> rowSorterjTable1;
    private TableRowSorter<TableModel> rowSorterjTable3;
    private int id;
    private String ticket;
    private double price;
    private int amount;
    public  String userMe;
    private PrintStream printStream;
    public  BufferedReader reader;
    private String[][] data;
    private String[] columns;
   

    public RESTGui() throws IOException {

        data = new String[][]{};

        columns = new String[]{"ID", "Ticket", "Price", "Available", "Needed"};
        modeljTable1 = new DefaultTableModel(data, columns);

        columns = new String[]{"Ticket", "Cost", "Amount", "Ordered By"};
        modeljTable3 = new DefaultTableModel(data, columns);

        initComponents();

        rest = new RESTCleint();

        reader = new BufferedReader(new InputStreamReader(System.in));
        printStream = new PrintStream(new CustomOutputStream(jTextArea1));
        System.setOut(printStream);
        System.setErr(printStream);
        System.out.println("***********************************");
        System.out.println(":....Service-Intiating....:");
        System.out.println("***********************************");
        tickets = rest.getTicketsList();
        orders =  new ArrayList<Order>();
        rowSorterjTable1 = new TableRowSorter<TableModel>(jTable1.getModel());

        rowSorterjTable3 = new TableRowSorter<TableModel>(jTable3.getModel());
        jTable1.setRowSorter(rowSorterjTable1);

        jTable3.setRowSorter(rowSorterjTable3);

        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);

        constructTicketsTable();

    }

    public String getUserMe() {
        return userMe;
    }

    public void setUserMe(String userMe) {
        this.userMe = userMe;
    }

    public void constructTicketsTable() {
     modeljTable1.setRowCount(0);
        tickets = rest.getTicketsList();

        for (int i = 0; i < tickets.size(); i++) {
            id = tickets.get(i).getId();
            ticket = tickets.get(i).getTicket();
            price = tickets.get(i).getPrice();
            amount = tickets.get(i).getAmount();
            modeljTable1.addRow(new String[]{id + "", ticket, price + "", amount + "", ""});

        }

    }

    public void clearTableModel(DefaultTableModel model) {

        model.setRowCount(0);

    }

    public boolean isNumber(String checkMe) {
        boolean isInt = false;
        try {
            Integer.parseInt(checkMe);
            isInt = true;
        } catch (Exception e) {
            isInt = false;
        }
        return isInt;
    }

    public void refresh() {
        clearTableModel(modeljTable1);
        constructTicketsTable();

    }

    public void changeText(JTextField jTextField, String text) {
        changeCursor(Cursor.WAIT_CURSOR);

        if (jTextField.getText() == null || "".equals(jTextField.getText())) {
            jTextField.setText(text);
        }

        changeCursor(Cursor.DEFAULT_CURSOR);
    }

    public void changeCursor(int CursorIndex) {
        this.setCursor(Cursor.getPredefinedCursor(CursorIndex));
    }

    /**
     * This method is called from within the constructor to initialize the GUI
     * form.
     *
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::REST-CLEINT::");
        setPreferredSize(new java.awt.Dimension(828, 590));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Tickets"));

        jTable1.setModel(modeljTable1);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Shoping Cart"));

        jTable3.setModel(modeljTable3);
        jScrollPane8.setViewportView(jTable3);

        jButton11.setText("Add To Cart");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Search for Ticket");
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

        jButton3.setText("Remove From Cart");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jButton4.setText("Check out ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Refresh Repository");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(jTextField2)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4, jButton5});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton3, jButton4, jButton5, jTextField2, jTextField3});

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(849, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        changeText(jTextField2, searchTicket);
    }//GEN-LAST:event_jTextField2MouseExited

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked

        jTextField3.setText("");

    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseExited

        changeText(jTextField3, searchOrders);
    }//GEN-LAST:event_jTextField3MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        changeCursor(Cursor.WAIT_CURSOR);

        try {

            int index = jTable1.getSelectedRow();

            amount = new Integer(modeljTable1.getValueAt(index, 4).toString());
            price = new Double(modeljTable1.getValueAt(index, 2).toString());
            ticket = modeljTable1.getValueAt(index, 1).toString();
            int available = new Integer(modeljTable1.getValueAt(index, 3).toString());
            boolean isInt = isNumber(amount + "");
            
            if (amount > 0 && isInt == true && amount <= available) {
                available -= amount;
                modeljTable1.setValueAt(available, index, 3);
                boolean match = false;
               
                for(int i = 0; i < modeljTable3.getRowCount();i++ ){
                      if(ticket.equals(modeljTable3.getValueAt(i, 0).toString())){
                          match = true;
                          
                             int tempamount = 
              new Integer(modeljTable3.getValueAt(i, 2).toString())+amount;
                    double tempprice = price *tempamount;
                    modeljTable3.setValueAt(tempamount,i, 2);
                    modeljTable3.setValueAt(tempprice,i, 1);
                  System.err.println("\n************************************************************");
                  System.err.println("::" + amount + " Ticket/s of " + ticket + " updated in Cart::");
                       break;
                      }
                }
                
            
                if(match == false){
                price = new Double(modeljTable1.getValueAt(index, 2).toString()) * amount;
                modeljTable3.addRow(new String[]{ticket, price + "", amount + "", userMe});
                System.err.println("\n************************************************************");
                System.err.println("::" + amount + " Ticket/s of " + ticket + " added to Cart::");
                }
               
                }
             
            
            

        } catch (Exception e) {
        }
        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 
              
//             orders.add(new Order(ticket, userMe, amount, price));
      changeCursor(Cursor.WAIT_CURSOR);
        try {

            int index = jTable3.getSelectedRow();
            ticket = jTable3.getValueAt(index, 0).toString();
            amount = new Integer(jTable3.getValueAt(index, 2).toString());
            modeljTable3.removeRow(index);

            for (int i = 0; i < jTable1.getRowCount() + 1; i++) {
                String tempTicket = modeljTable1.getValueAt(i, 1).toString();
                if (tempTicket.equals(ticket)) {
                    int regainedAmount = new Integer(modeljTable1.getValueAt(i, 3).toString()) + amount;
                    modeljTable1.setValueAt(regainedAmount, i, 3);
                }

            }

            System.err.println("\n************************************************************");
            System.err.println("::Ticket" + ticket + " removed from Cart::");
        } catch (Exception e) {
        }

        changeCursor(Cursor.DEFAULT_CURSOR);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        changeCursor(Cursor.WAIT_CURSOR);

        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_formWindowClosing

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

              }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        changeCursor(Cursor.WAIT_CURSOR);

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        changeCursor(Cursor.WAIT_CURSOR);
        int rows = modeljTable3.getRowCount();
           System.out.println(rows);
         for(int i = 0; i < rows; i++){
             ticket = modeljTable3.getValueAt(i,0).toString();
             price  = new Double(modeljTable3.getValueAt(i, 1).toString());
             amount = new Integer(modeljTable3.getValueAt(i, 2).toString());
              
             orders.add(new Order(ticket, userMe, amount, price));
         }
         ResponseForOrder ="";
         String orderStatus = "";
         for(int i = 0; i< orders.size(); i++){
             
             ticket = orders.get(i).getTicket();
             amount = orders.get(i).getAmount();
             price = orders.get(i).getCoast();
             ResponseForOrder +="\n::Order for Ticket "+ticket +" "+amount+" in amount";
            orderStatus = rest.orderTicket(ticket, amount, price, userMe);
            if("false".equals(orderStatus)){
                ResponseForOrder+=":-Not enough tickets to satisfy your oder";
            }
            else if("true".equals(orderStatus)){
               ResponseForOrder+=":-oder successfully submited";
            }
               ResponseForOrder+="..:\n";
         }
           JOptionPane.showMessageDialog(this,ResponseForOrder);
        
           for(int i = 0; i < modeljTable3.getRowCount();  i++){
               modeljTable3.removeRow(i);
               
                }
            modeljTable3.setRowCount(0);
            ResponseForOrder="";
            orders.clear();
            refresh();
        changeCursor(Cursor.DEFAULT_CURSOR);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
       changeCursor(Cursor.WAIT_CURSOR);
         try {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              int index  = jTable1.getSelectedRow();

            amount = new Integer(modeljTable1.getValueAt(index, 4).toString());
            price = new Double(modeljTable1.getValueAt(index, 2).toString());
            ticket = modeljTable1.getValueAt(index, 1).toString();
            int available = new Integer(modeljTable1.getValueAt(index, 3).toString());
            boolean isInt = isNumber(amount + "");
            
            if (amount > 0 && isInt == true && amount <= available) {
                available -= amount;
                modeljTable1.setValueAt(available, index, 3);
                boolean match = false;
               
                for(int i = 0; i < modeljTable3.getRowCount();i++ ){
                      if(ticket.equals(modeljTable3.getValueAt(i, 0).toString())){
                          match = true;
                          
                             int tempamount = 
              new Integer(modeljTable3.getValueAt(i, 2).toString())+amount;
                    double tempprice = price *tempamount;
                    modeljTable3.setValueAt(tempamount,i, 2);
                    modeljTable3.setValueAt(tempprice,i, 1);
                  System.err.println("\n************************************************************");
                  System.err.println("::" + amount + " Ticket/s of " + ticket + " updated in Cart::");
                       break;
                      }
                }
                
            
                if(match == false){
                price = new Double(modeljTable1.getValueAt(index, 2).toString()) * amount;
                modeljTable3.addRow(new String[]{ticket, price + "", amount + "", userMe});
                System.err.println("\n************************************************************");
                System.err.println("::" + amount + " Ticket/s of " + ticket + " added to Cart::");
                }
               
                }
     
             }
          } catch (Exception e) {
        }
            changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       changeCursor(Cursor.WAIT_CURSOR);
        refresh();
        changeCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(RESTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RESTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RESTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RESTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    RESTGui restGui = new RESTGui();
                    RESTGuiLogin frame = new RESTGuiLogin(restGui);
                    frame.setVisible(true);

                } catch (IOException ex) {

                    Logger.getLogger(RESTGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    class CustomOutputStream extends OutputStream {

        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char) b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(jTextArea1.getDocument().getLength());

        }
    }

}
