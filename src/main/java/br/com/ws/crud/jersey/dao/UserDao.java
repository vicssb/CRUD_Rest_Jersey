package br.com.ws.crud.jersey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
 
import br.com.ws.crud.jersey.model.User;
import br.com.ws.crud.jersey.dao.utils.Conn;

public class UserDao {

	public int criar(User user) throws SQLException {
		 
        String sql = "insert into usert (id, name, password, date_pub) values "
                + "((select nextval('usert_id_seq')), ?, ?, ? ) RETURNING id;";
 
        int idNewLocal = 0;
 
        Connection con = Conn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        try {
 
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setDate(3, new Date(user.getData().getTime()));
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
            	idNewLocal = rs.getInt("id"); // receives the value of the generated id
                                                // by the sequence
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
 
        return idNewLocal;
    }
	
	public User edit(User user) throws SQLException {
		 
        String sql = "update usert set name = ? , password = ? , date_pub = ? "
                + "where id = ? ;";
 
        User userEdited = null;
        Connection con = Conn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
 
        try {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setDate(3, new Date(user.getData().getTime()));
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            ps.close();
             
            userEdited = read(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } 
 
        return userEdited;
    }
	
	public boolean deletar(int id) throws SQLException {
		 
        String sql = "delete from usert where id = ? ;";
 
        Connection con = Conn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
 
        try {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
        return false;
    }
	
	public User read(int id) throws SQLException {
		 
        String sql = "select * from usert where id = ? ;";
 
        User user = null;
 
        Connection con = Conn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
 
        try {
            if (rs.next()) {
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(sql);
            	user.setPassword(sql);
            	user.setData(new java.util.Date(rs.getDate("date_pub")
                        .getTime()));
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
        }
        return user;
    }
	
	public ArrayList<User> list() throws SQLException {
		 
        String sql = "select * from usert;";
        ArrayList<User> users = new ArrayList<User>();
 
        Connection con = Conn.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        try {
 
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setData(new java.util.Date(rs.getDate("date_pub")
                        .getTime()));
                users.add(user);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
        }
        return users;
    }
}
