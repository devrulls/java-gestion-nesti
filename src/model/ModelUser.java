package model;

import controller.ControllerTableUser;
import database.DBConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


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

    public int check_user_by_username(String username) {
        Connection conexion = getConnection();

        String sql = "SELECT count(id_user) FROM nesti_user WHERE username =? ";

        try {
            ps = conexion.prepareStatement(sql);

            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                int resultado = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "resultado db: count(id)" + resultado);
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            return 1;
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

    public boolean search_by_name_table(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "SELECT * FROM nesti_user WHERE name = ?";

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
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error, " + ex);
            }

        }
    }
    
    public boolean load_Jcombobox(EntityRole role) {
        Connection conexion = getConnection();

        String sql = "SELECT description_role FROM nesti_role";

        try {
            ps = conexion.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                role.setName(rs.getString("description_role"));
                return true;
            } 
        } catch (SQLException ex) {
            System.out.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error, " + ex);
            }
        
        }
        return false;
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

    public boolean login(EntityUser user) {
        Connection conexion = getConnection();

        String sql = "SELECT u.id_user, u.name, u.username, u.pwd, u.id_role, r.description_role FROM nesti_user as u inner join nesti_role as r ON u.id_role = r.id_role WHERE username =? ";

        try {
            ps = conexion.prepareStatement(sql);

            ps.setString(1, user.getUsername());

            rs = ps.executeQuery();

            if (rs.next()) {

                if (user.getPwd().equals(rs.getString("u.pwd"))) {

                    user.setId_user(rs.getInt("u.id_user"));
//                  user.setName(rs.getString("name"));
                    user.setUsername(rs.getString("u.username"));
                    user.setId_role(rs.getInt("u.id_role"));
                    user.setDescription_role(rs.getString("r.description_role"));
                    return true;
                } else {
                    return false;
                }
            }
            return false;

        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error, " + ex);
            }

        }

    }

    public ArrayList<Object[]> load_table_users(JTable table_users) {
        Connection conexion = getConnection();
        ArrayList<Object[]> array_users = new ArrayList<>();
        array_users.clear();
        table_users.setModel((TableModel) new ControllerTableUser(array_users));

        String sql = "SELECT * FROM nesti_user";

        try {
            ps = conexion.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[table_users.getColumnCount()];
                fila[0] = rs.getInt("id_user");
                fila[1] = rs.getString("name");
                fila[2] = rs.getString("username");
                fila[3] = rs.getString("pwd");
                fila[4] = rs.getString("id_role");

                array_users.add(fila);

            }
           table_users.setModel((TableModel) new ControllerTableUser(array_users));
        } catch (SQLException ex) {
            
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error, " + ex);
            }

        }
        return array_users;

    }

}
