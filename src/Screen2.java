
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Agarwal
 */
public class Screen2 extends javax.swing.JFrame {

    /**
     * Creates new form Screen2
     */
    
    
    Displaymanager disp;
    public Screen2(Displaymanager d) {
        initComponents();
        this.disp=d;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        source = new javax.swing.JLabel();
        seats = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        oo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FRSystem");
        setLocation(new java.awt.Point(100, 100));
        setMinimumSize(new java.awt.Dimension(920, 480));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Book My Flight");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 10, 900, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Spicejet Flight", "Via", "Spicejet Time", "Transit Time", "Silkair Flight", "Silkair Time", "Total Time"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 170, 883, 120);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("BOOK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(420, 360, 100, 30);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(300, 360, 90, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(550, 360, 80, 30);

        source.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        source.setForeground(new java.awt.Color(255, 255, 255));
        source.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(source);
        source.setBounds(30, 60, 180, 30);

        seats.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        seats.setForeground(new java.awt.Color(255, 255, 255));
        seats.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(seats);
        seats.setBounds(60, 110, 140, 30);

        count.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        count.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(count);
        count.setBounds(480, 60, 320, 40);

        date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(date);
        date.setBounds(480, 110, 400, 40);

        oo.setBackground(new java.awt.Color(0, 146, 99));
        oo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        oo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oo.setText("Sorry! NO Flight Available");
        oo.setOpaque(true);
        getContentPane().add(oo);
        oo.setBounds(10, 170, 880, 120);

        jLabel2.setBackground(new java.awt.Color(0, 146, 99));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 900, 420);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("©Copyright.randomly");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 420, 890, 30);

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(410, 350, 120, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(Screen2.jTable1.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Select one flight");
        }
        else
        {
            disp.s2.setVisible(false);
            disp.displayscreen3();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        disp.frsManager.initialize();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel count;
    public javax.swing.JLabel date;
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel oo;
    public javax.swing.JLabel seats;
    public javax.swing.JLabel source;
    // End of variables declaration//GEN-END:variables
}
