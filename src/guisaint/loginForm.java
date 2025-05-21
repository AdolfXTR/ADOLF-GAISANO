/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guisaint;

import admin.Admins;
import admin.bookedRooms;
import config.Logs;
import config.Session;
import config.dbConnector;
import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import config.passwordHasher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import users.User;
/**
 *
 * @author Owner
 */
public class loginForm extends javax.swing.JFrame {

    /**
     * Creates new form loginForm
     */
    public loginForm() {
        initComponents();
    }
    
    Color hover = new Color(0,205,51);
    Color defaultcolor = new Color(0,102,0);
    
    static String status;
    static String type;

     public static boolean loginAcc(String username, String password){
        dbConnector connector = new dbConnector();
        try{
            String query = "SELECT * FROM tbl_users  WHERE u_username = '" + username + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()){     
   
                String hashedPass = resultSet.getString("u_password");
                String rehashedPass = passwordHasher.hashPassword(password);
                
                if(hashedPass.equals(rehashedPass)){        
                status = resultSet.getString("u_status");   
                type = resultSet.getString("u_type");
                Session sess = Session.getInstance();
                sess.setUid(resultSet.getInt("u_id"));
                sess.setFname(resultSet.getString("u_fname"));
                sess.setLname(resultSet.getString("u_lname"));
                sess.setEmail(resultSet.getString("u_email"));
                sess.setUsername(resultSet.getString("u_username"));
                sess.setType(resultSet.getString("u_type"));
                sess.setStatus(resultSet.getString("u_status"));
                return true;   
                }else{
                return false;
                }
        }else{
            return false;
        }          
        }catch (SQLException | NoSuchAlgorithmException ex) {
            return false;
        }

    }
     
          public String getUserId(String username) {
        dbConnector dbc = new dbConnector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userId = null;

      try {
    Connection con = dbc.getConnection();  // ✅ get the connection from dbConnect
    String sql = "SELECT u_id FROM users WHERE u_username = ?";
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, username);
    rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getString("u_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
           
       
        }
        return userId;
    }
     public void logEvent(int userId, String username, String userType) {
    dbConnector dbc = new dbConnector();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;
    String ut = null;

    try {
        // Assuming there's no 'log_time' column, remove it from the query
        String sql = "INSERT INTO tbl_log (u_id, u_username, login_time, u_type, log_status) VALUES (?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, new Timestamp(new Date().getTime())); // Make sure 'login_time' column exists
        pstmt.setString(4, userType);
        ut = "Active";
        pstmt.setString(5, ut);

        pstmt.executeUpdate();
        System.out.println("Login log recorded successfully.");
    } catch (SQLException e) {
        System.out.println("Error recording log: " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
        }
    }
}
      public String getUserTypeFromDatabase(String username) {
    String type = "";
    String query = "SELECT u_type FROM tbl_users WHERE LOWER(u_username) = LOWER(?)";
    
    // Use an instance of dbConnector to get the connection
    dbConnector connector = new dbConnector();  // Create instance of dbConnector
    try (Connection con = connector.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            type = rs.getString("u_type");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return type;
}
      public String getStatusFromDatabase(String username) {
    String status = "";
    String query = "SELECT log_status FROM tbl_log WHERE LOWER(u_username) = LOWER(?) ORDER BY login_time DESC LIMIT 1";
    
    // Use an instance of dbConnector to get the connection
    dbConnector connector = new dbConnector();  // Create instance of dbConnector
    try (Connection con = connector.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            status = rs.getString("log_status");
            System.out.println("status: "+status);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        Main = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ext = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        un = new javax.swing.JTextField();
        ps = new javax.swing.JPasswordField();
        pazz = new javax.swing.JCheckBox();
        reg = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        Main.setBackground(new java.awt.Color(255, 255, 255));
        Main.setPreferredSize(new java.awt.Dimension(800, 500));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Right.setBackground(new java.awt.Color(0, 102, 102));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hotel Management");
        Right.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 181, -1, 77));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("© 2025 France Adolf P. Borja. All Rights Reserved.");
        Right.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 428, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-hotel-100.png"))); // NOI18N
        Right.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 30, -1, -1));

        Main.add(Right, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));
        Right.getAccessibleContext().setAccessibleName("");

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));
        Left.add(ext, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, 40));

        Main.add(Left, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        jLabel2.setText("Password");
        Main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 90, 40));

        un.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unActionPerformed(evt);
            }
        });
        Main.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 300, 50));

        ps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psActionPerformed(evt);
            }
        });
        Main.add(ps, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 300, 50));

        pazz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pazzActionPerformed(evt);
            }
        });
        Main.add(pazz, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, 40, 50));

        reg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reg.setText("New user? Click Here to Register!");
        reg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regMouseClicked(evt);
            }
        });
        Main.add(reg, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 397, -1, 20));

        jLabel4.setText("Username");
        Main.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 90, 40));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setText("LOGIN");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Main.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, 80, 30));

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setText("EXIT");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Main.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 90, 32));

        jLabel5.setText("Forgot Password ?");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        Main.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void unActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unActionPerformed

    private void pazzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pazzActionPerformed
       
        if(pazz.isSelected())
            ps.setEchoChar((char)0);
        else
            ps.setEchoChar('*');
    }//GEN-LAST:event_pazzActionPerformed

    private void psActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psActionPerformed

    private void regMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regMouseClicked
        
        registrationForm rfm = new registrationForm();
        rfm.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_regMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  String username = un.getText();
String password = new String(ps.getPassword());
dbConnector connector = new dbConnector();

// Input validation
if (username.isEmpty() && ps.getPassword().length == 0) {
    JOptionPane.showMessageDialog(null, "All fields are required!");
} else if (username.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Username is required!");
} else if (ps.getPassword().length == 0) {
    JOptionPane.showMessageDialog(null, "Password is required!");
} else if (ps.getPassword().length < 8) {
    JOptionPane.showMessageDialog(null, "Password should have at least 8 characters.");
} else {
    if(loginAcc(un.getText(), ps.getText())){
        if(!status.equals("Active")){
            JOptionPane.showMessageDialog(null, "In-Active Account, Contact the Admin!");
        } else {
            JOptionPane.showMessageDialog(null, "Login Successfully!");

            Logs.logFunctionCall(un.getText() + " logged in successfully");

            if(type.equals("Admin")){
                Admins usf= new Admins();
                usf.setVisible(true);
                this.dispose();
            } else if(type.equals("Receptionist")){
                bookedRooms user = new bookedRooms();
                
                user.setVisible(true);
                user.ApproveBooking.setEnabled(false);
                user.jButton2.setEnabled(false);
                this.dispose();
            } else if(type.equals("Client")){
                User user = new User();
                user.setVisible(true);
                this.dispose();
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Login Unsuccessful!");
    }
}   

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        ForgotPassword fp = new ForgotPassword();
        fp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel Right;
    private javax.swing.JLabel ext;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JCheckBox pazz;
    private javax.swing.JPasswordField ps;
    private javax.swing.JLabel reg;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
