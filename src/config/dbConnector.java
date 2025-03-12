
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class dbConnector {
    
    private Connection connect;
    
   private String url = "jdbc:mysql://localhost:3306/hotelmanagement_db";
   private String user = "root";
   private String password = "";


       public dbConnector() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement_db", "root", "");
            System.out.println("Database Connected!");
        } catch (SQLException e) {
            System.out.println("Can't connect to database: " + e.getMessage());
        }
    }
        
        //Function to save data
        public boolean insertData(String query) {
    try {
        Statement stmt = connect.createStatement();
        int rowsAffected = stmt.executeUpdate(query);
        return rowsAffected > 0; // Return true if at least one row is inserted
    } catch (SQLException ex) {
        System.out.println("SQL Insert Error: " + ex);
        return false;
    }
}

        
        
       public ResultSet getData(String query) {
        try {
            Statement stmt = connect.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL Query Error: " + e.getMessage());
            return null;
        }
    }

    public void updateData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                    int rowsUpdated = pst.executeUpdate();
                        if(rowsUpdated > 0){
                            JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        }else{
                            System.out.println("Data Update Failed!");
                        }
                        pst.close();
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
            }
        
        }

       }
    
    
        



