package db;
import model.User;

import java.awt.print.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseCode {
    public  User login(String email,String role) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT email,password,role FROM user WHERE email=?");
        pstm.setString(1,email);
        ResultSet set = pstm.executeQuery();
        if(set.next()){
            return  new User(null,null,set.getString(1),set.getString("password"),set.getString("role"));
        }
        return null;
    }

    public boolean userRegistation(User user) throws SQLException, ClassNotFoundException {
       PreparedStatement pstm =  DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO user(name,email,password,role) VALUES(?,?,?,?)");
       //pstm.setInt(1,Integer.parseInt(user.getId()));
       pstm.setString(1,user.getName());
       pstm.setString(2,user.getEmail());
       pstm.setString(3,user.getPassword());
       pstm.setString(4,user.getRole());
       return pstm.executeUpdate()>0;
    }
    public Book getBooks() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM ");
        return  null;
    }
}