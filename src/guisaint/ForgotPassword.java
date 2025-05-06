/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guisaint;

import config.Session;
import config.dbConnector;
import config.passwordHasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author alcay
 */
public class ForgotPassword extends javax.swing.JFrame {
 private String correctAnswer;
    /**
     * Creates new form ForgotPassword
     */
    public ForgotPassword() {
        initComponents();
    }

    
   private void fetchSecurityQuestion() {
    String username = un.getText();  
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    // Create a database connection
    dbConnector db = new dbConnector();  // Instantiate dbConnector
    Connection con = db.getConnection(); // Get connection

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT security_question, security_answer FROM tbl_users WHERE u_username = ?"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sq.removeAllItems();
            sq.addItem(rs.getString("security_question"));
            sq.setEnabled(true);
            correctAnswer = rs.getString("security_answer");
            submit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
   
public void logEvent(int userId, String username, String description) {
    dbConnector dbc = new dbConnector();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;

    try {
        // Fixed: include `log_description` in your INSERT
        String sql = "INSERT INTO tbl_log (u_id, u_username, login_time, u_type, log_status) VALUES (?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, new Timestamp(new Date().getTime())); // login_time
        pstmt.setString(4, "Success - User Action"); // u_type (general category)
        pstmt.setString(5, "Active"); // log_status

        pstmt.executeUpdate();
        System.out.println("Log event recorded successfully.");
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

   


   private void resetPassword() {
    String enteredAnswer = ans.getText();
    String newPassword = new String(Newpass.getText());

    // Declare variables here to fix your error
    int userId = -1;
    String uname2 = "";

    if (correctAnswer == null) {
        JOptionPane.showMessageDialog(this, "Please search for your username first.");
        return;
    }

    try {
        if (!passwordHasher.hashPassword(enteredAnswer).equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Incorrect security answer.");
            return;
        }
    } catch (NoSuchAlgorithmException ex) {
        JOptionPane.showMessageDialog(this, "Error verifying answer: " + ex.getMessage());
        return;
    }

    try {
        // Hash the new password before storing it
        String hashedPassword = passwordHasher.hashPassword(newPassword);

        // Instantiate the database connection
        dbConnector db = new dbConnector();
        Connection con = db.getConnection();
        dbConnector connector = new dbConnector(); // For logging query
        Session sess = Session.getInstance();

        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
            return;
        }

        try {
            // Update password in the database
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE tbl_users SET u_password = ? WHERE u_username = ?"
            );
            stmt.setString(1, hashedPassword);
            stmt.setString(2, un.getText());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password successfully reset!");

                // Try to log the password reset
                try {
                    String query2 = "SELECT * FROM tbl_users WHERE u_username = ?";
                    PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
                    pstmt.setString(1, un.getText());

                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                        userId = resultSet.getInt("u_id");
                        uname2 = resultSet.getString("u_username");

                        // Log the event
                   logEvent(userId, uname2, "User Reset Their Password");

                    }
                } catch (SQLException ex) {
                    System.out.println("Log Error (SQL): " + ex.getMessage());
                }

                dispose(); // Close the window
            } else {
                JOptionPane.showMessageDialog(this, "Error: Username not found or password update failed.");
            }

        } finally {
            con.close();
        }

    } catch (NoSuchAlgorithmException ex) {
        JOptionPane.showMessageDialog(this, "Error hashing password: " + ex.getMessage());
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while updating the password.");
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Right = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ext = new javax.swing.JLabel();
        un = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        sq = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        ans = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Newpass = new javax.swing.JTextField();
        submit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(Right, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));

        jLabel4.setText("Username :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 90, 40));

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORGOT PASS?");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));
        Left.add(ext, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, 40));
        Left.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 230, 40));

        Search.setText("Search");
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Left.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, 30));

        Left.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 230, 40));

        jLabel2.setText("New Password :");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 130, 40));

        ans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ansActionPerformed(evt);
            }
        });
        Left.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 230, 40));

        jLabel5.setText("Answer :");
        Left.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 90, 40));
        Left.add(Newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 346, 220, 50));

        submit.setBackground(new java.awt.Color(0, 102, 102));
        submit.setText("SUBMIT");
        submit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
        });
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        Left.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 100, 40));

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setText("CANCEL");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        Left.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 90, 40));

        getContentPane().add(Left, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 380, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ansActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed

      
    }//GEN-LAST:event_submitActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        loginForm ads = new loginForm();
        ads.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
            resetPassword(); 
    }//GEN-LAST:event_submitMouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        String username = un.getText();  
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    // Create a database connection
    dbConnector db = new dbConnector();  // Instantiate dbConnector
    Connection con = db.getConnection(); // Get connection

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT security_question, security_answer FROM tbl_users WHERE u_username = ?"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sq.removeAllItems();
            sq.addItem(rs.getString("security_question"));
            sq.setEnabled(true);
          correctAnswer = rs.getString("security_answer"); // still okay if hashed

            submit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    }//GEN-LAST:event_SearchMouseClicked

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
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JTextField Newpass;
    private javax.swing.JPanel Right;
    private javax.swing.JButton Search;
    private javax.swing.JTextField ans;
    private javax.swing.JLabel ext;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> sq;
    private javax.swing.JButton submit;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
