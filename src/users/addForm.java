
package users;

import Config.config;
import config.Logs;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class addForm extends javax.swing.JFrame {

    
    public addForm() {
        setUndecorated(true);
        initComponents();
        
    }
    public String destination;
   File selectedFile;
   public String oldpath;
   public String path;
   
    



public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/Images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = (ImagePath != null) ? new ImageIcon(ImagePath) : new ImageIcon(pic);

    int labelWidth = label.getWidth();
    if (labelWidth == 0) labelWidth = 150; // fallback width if not initialized

    int newHeight = getHeightFromWidth(ImagePath, labelWidth);
    if (newHeight <= 0) newHeight = 150; // fallback height

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(labelWidth, newHeight, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
}

public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
           
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!"+ex);
        }
        
        return -1;
    }

    public static String emails, usernames;
    
    public boolean duplicateChecker(){
        config conf = new config();
        try{
            String query = "SELECT * FROM users WHERE uname = '"+uname.getText()+"' OR email = '"+email.getText()+"'";
            ResultSet resultSet = conf.getData(query);
            
            if(resultSet.next()){
                emails = resultSet.getString("email");
                if(emails.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText(null);
                }
                usernames = resultSet.getString("uname");
                if(usernames.equals(uname.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    uname.setText(null);
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
    }
     public boolean updateChecker(){
        config conf = new config();
        try{
            String query = "SELECT * FROM users WHERE (uname = '"+uname.getText()+"' OR email = '"+email.getText()+"')AND id!='"+id.getText()+"' ";
            ResultSet resultSet = conf.getData(query);
            
            if(resultSet.next()){
                emails = resultSet.getString("email");
                if(emails.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText(null);
                }
                usernames = resultSet.getString("uname");
                if(usernames.equals(uname.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    uname.setText(null);
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
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

        jPanel9 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        utype = new javax.swing.JComboBox<>();
        lname = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        pname = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        update = new javax.swing.JButton();
        add = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        ustatus = new javax.swing.JComboBox<>();
        id = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        sq = new javax.swing.JComboBox<>();
        ans = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Last Name:");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 90, -1));

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("User Type:");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 90, -1));
        jPanel9.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 160, -1));
        jPanel9.add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 160, -1));

        utype.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        utype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Staff" }));
        utype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utypeActionPerformed(evt);
            }
        });
        jPanel9.add(utype, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 160, -1));
        jPanel9.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 160, -1));

        jLabel33.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("First Name:");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 90, -1));

        jLabel34.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Username:");
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 90, -1));

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Password:");
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 90, -1));
        jPanel9.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 160, -1));

        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });
        jPanel9.add(pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 160, -1));

        jLabel24.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Email:");
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 90, -1));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 51, 102)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("X");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 50, 50));

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("USERFORM");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 860, 40));

        jPanel9.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 790, 80));

        delete.setBackground(new java.awt.Color(0, 255, 255));
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel9.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 80, -1));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        refresh.setBackground(new java.awt.Color(0, 255, 255));
        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel6.add(refresh);
        refresh.setBounds(40, 330, 80, 23);

        cancel.setBackground(new java.awt.Color(0, 255, 255));
        cancel.setText("CANCEL");
        cancel.setEnabled(false);
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel6.add(cancel);
        cancel.setBounds(40, 280, 80, 23);

        clear.setBackground(new java.awt.Color(0, 255, 255));
        clear.setText("CLEAR");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel6.add(clear);
        clear.setBounds(40, 240, 80, 23);

        update.setBackground(new java.awt.Color(0, 255, 255));
        update.setText("UPDATE");
        update.setEnabled(false);
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel6.add(update);
        update.setBounds(40, 140, 80, 23);

        add.setBackground(new java.awt.Color(0, 255, 255));
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel6.add(add);
        add.setBounds(40, 190, 80, 23);

        delete1.setBackground(new java.awt.Color(0, 255, 255));
        delete1.setText("DELETE");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });
        jPanel6.add(delete1);
        delete1.setBounds(40, 240, 80, 23);

        jButton7.setBackground(new java.awt.Color(0, 255, 255));
        jButton7.setText("ADD");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7);
        jButton7.setBounds(40, 280, 80, 23);

        jPanel9.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 130, 400));

        jLabel39.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Answer :");
        jPanel9.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 90, -1));

        ustatus.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ustatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        ustatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ustatusActionPerformed(evt);
            }
        });
        jPanel9.add(ustatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 160, -1));

        id.setEnabled(false);
        jPanel9.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 160, -1));

        jLabel38.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("id");
        jPanel9.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 60, -1));

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 170));

        jPanel9.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 220, 170));

        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel9.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 170, 30));

        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel9.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 170, 30));

        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is your Favourite Sports?", "Why are you Gay?", "What is your Favorite Color?" }));
        jPanel9.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 160, 30));
        jPanel9.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 160, 30));

        jLabel40.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Status:");
        jPanel9.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 90, 30));

        jLabel41.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Security Question :");
        jPanel9.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 130, -1));

        getContentPane().add(jPanel9);
        jPanel9.setBounds(0, 0, 780, 540);

        setSize(new java.awt.Dimension(795, 571));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        remove.setEnabled(false);
        select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/Images/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }else{
                    image.setIcon(ResizeImage(path, null, image));
                    select.setEnabled(false);
                    remove.setEnabled(true);

                }
            } catch (Exception ex) {
                System.out.println("File Error!"+ex);
            }
        }
    }//GEN-LAST:event_selectActionPerformed

    private void ustatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ustatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ustatusActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete1ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        if(fname.getText().isEmpty()
            || lname.getText().isEmpty()
            || email.getText().isEmpty()
            || uname.getText().isEmpty()
            || pname.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "All Fields are Required!");
            return;
        }

        if(pname.getText().length() < 8) {
            pname.setText("");

            JOptionPane.showMessageDialog(null, "Password Must be longer than 8!");
            return;
        }

        String emails = this.email.getText();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern patternEmail = Pattern.compile(emailRegex);
        Matcher matcherEmail = patternEmail.matcher(emails);

        if (!matcherEmail.matches()) {

            JOptionPane.showMessageDialog(this, "Invalid email format. Please use a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            this.email.setText("");
            this.email.requestFocus();
            return;
        }

       

        if (duplicateChecker()) {

            System.out.println("Duplicate Exist!");
            return;
        }

      config conf = new config();
int result = conf.insertData(
    "INSERT INTO users (fname, lname, u_type, email, uname, pname, status, image) " +
    "VALUES ('" + fname.getText() + "', '" + lname.getText() + "', '" + utype.getSelectedItem() + "', '" +
    email.getText() + "', '" + uname.getText() + "', '" + pname.getText() + "', '" +
    ustatus.getSelectedItem() + "', '" + destination + "')"
);

        if (result == 1) {
            try {
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                Logs.logFunctionCall("Admin Registered a user succesfully" + fname);
                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                UsersForm login = new UsersForm();
                login.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logs.logFunctionCall("File Copy Error");
                System.out.println("Insert Error: " + ex);
            }
        } else {
            Logs.logFunctionCall("a_addActionPerformed - Insert Failed");
        }
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        if (fname.getText().isEmpty() ||
            lname.getText().isEmpty() ||
            email.getText().isEmpty() ||
            uname.getText().isEmpty() ||
            pname.getText().isEmpty())
            {

            JOptionPane.showMessageDialog(null, "All Fields are Required!");
            return;
        }

        if (pname.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters!");
            return;
        }

    

        if (updateChecker()) {
            JOptionPane.showMessageDialog(null, "Duplicate user exists!");
            return;
        }

        config conf = new config();

        conf.updateData("UPDATE users SET fname='" + fname.getText() +
            "', lname='" + lname.getText() +
            "', email='" + email.getText() +
            "', account_type='" + utype.getSelectedItem() +
            "', uname='" + uname.getText() +
            "', status='" + ustatus.getSelectedItem() +
            "', image='" + destination +
            "' WHERE id='" + id.getText() + "'");

        if (destination.isEmpty()) {
            File existingFile = new File(oldpath);
            if (existingFile.exists()) {
                existingFile.delete();
            }
        } else {
            if (!oldpath.equals(path)) {
                imageUpdater(oldpath, path);
            }
        }

        Logs.logFunctionCall( "Admin updated user with ID: " + id.getText());

        JOptionPane.showMessageDialog(null, "Updated Successfully!");

        UsersForm usf = new UsersForm();
        usf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_updateActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_updateMouseClicked

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed

    }//GEN-LAST:event_cancelActionPerformed

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        UsersForm usf=  new  UsersForm();
        usf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void utypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utypeActionPerformed

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
            java.util.logging.Logger.getLogger(addForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    private javax.swing.JTextField ans;
    private javax.swing.JButton cancel;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fname;
    public javax.swing.JTextField id;
    public javax.swing.JLabel image;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JTextField lname;
    public javax.swing.JPasswordField pname;
    private javax.swing.JButton refresh;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JComboBox<String> sq;
    public javax.swing.JTextField uname;
    public javax.swing.JButton update;
    public javax.swing.JComboBox<String> ustatus;
    public javax.swing.JComboBox<String> utype;
    // End of variables declaration//GEN-END:variables
}
