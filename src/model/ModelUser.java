package model;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNANDEZ Raul @devrulls
 */
public class ModelUser extends DBConnection {

    PreparedStatement ps;
    ResultSet rs;

    public boolean save(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "INSERT INTO nesti_user(name,username,pwd,id_role) values(?,?,?,?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPwd());
            ps.setInt(4, user.getId_role());

            int result = ps.executeUpdate();  //INSERT INTO DATBASE - RETURN INT

            if (result > 0) {

                return true;
            } else {

                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error, " + ex);
            }

        }

    }

    public boolean search_by_username(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "SELECT * FROM nesti_user WHERE username = ?";

        try {
            ps = conexion.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setId_user(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPwd(rs.getString("pwd"));
                user.setId_role(rs.getInt("id_role"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username does not exist ");
                return false;

            }
        } catch (Exception ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error, " + ex);
            }

        }
    }

    public boolean update(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "UPDATE nesti_user SET name=?,username=?,pwd=?,id_role=? WHERE id_user=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPwd());
            ps.setInt(4, user.getId_role());
            ps.setInt(5, user.getId_user());

            int result = ps.executeUpdate();  //INSERT INTO DATBASE - RETURN INT

            if (result > 0) {

                return true;
            } else {

                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error, " + ex);
            }

        }

    }

    public boolean delete(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "DELETE FROM nesti_user WHERE id_user=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, user.getId_user());

            int result = ps.executeUpdate();  //INSERT INTO DATBASE - RETURN INT
            return result > 0;

        } catch (Exception ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error, " + ex);
            }

        }

    }

}
