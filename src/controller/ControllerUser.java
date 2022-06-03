package controller;

import database.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.EncryptPassword;
import model.EntityRole;
import model.EntityUser;
import model.ModelUser;
import views.ViewNesti;

/**
 *
 * @author @author HERNANDEZ Raul @devrulls
 */
public class ControllerUser implements ActionListener {

    private ViewNesti view;
    private ModelUser model;
    private EntityUser user;

    public ControllerUser() {
    }

    public ControllerUser(ViewNesti view, ModelUser model, EntityUser user) {
        this.view = view;
        this.model = model;
        this.user = user;

        view.btn_register.addActionListener(this);
        view.btn_search_user.addActionListener(this);
        view.btn_modify.addActionListener(this);
        view.btn_remove.addActionListener(this);
        view.btn_table1.addActionListener(this);
        view.input_searchT.addActionListener(this);
    }

    //iniciar
    public void start() {
        view.setTitle("Nesti - User Register");
        view.setLocationRelativeTo(null);
        view.input_id.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btn_register) {
            System.out.println("testttt");
            String pwd = new String(view.input_password.getPassword());
            String confirm_pwd = new String(view.input_confirmPassword.getPassword());

            if (view.input_name.getText().equals("") || view.input_username.getText().equals("") || pwd.equals("") || confirm_pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "please complete all fields");

            } else {
                if (pwd.equals(confirm_pwd)) {

                    if (model.check_user_by_username(view.input_username.getText()) == 0) {
                        String newPasswordHash = EncryptPassword.md5(pwd);
                        user.setName(view.input_name.getText());
                        user.setUsername(view.input_username.getText());
                        user.setPwd(newPasswordHash);
                        String value = view.combo_role.getSelectedItem().toString();
                        user.setRole(value);
                        if (model.save(user)) {
                            JOptionPane.showMessageDialog(null, "User successfully registered");
                            cleanForm();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error");
                            cleanForm();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The user with the username: " + view.input_username.getText() + " already exists");
                        view.input_username.setText(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match, please check again.");
                    view.input_password.setText(null);
                    view.input_confirmPassword.setText(null);
                }

            }

        }

        if (e.getSource() == view.btn_search_user) {
            user.setUsername(view.input_search.getText());

            if (model.search_by_username(user)) {
                view.input_id.setText(String.valueOf(user.getId_user()));
                view.input_name.setText(user.getName());
                view.input_username.setText(user.getUsername());
                int value_role = user.getId_role();
                String role = "";
                if (value_role == 1) {
                    role = "SUPER ADMIN";

                } else if (value_role == 2) {
                    role = "ADMIN";
                }
                view.combo_role.setSelectedItem(role);
            } else {
                JOptionPane.showMessageDialog(null, "Username does not exist ");
                cleanForm();
            }
        }

        if (e.getSource() == view.btn_modify) {
            user.setId_user(Integer.parseInt(view.input_id.getText()));
            user.setName(view.input_name.getText());
            user.setUsername(view.input_username.getText());
            user.setPwd(String.valueOf(view.input_password.getPassword()));
            String value = view.combo_role.getSelectedItem().toString();
            user.setRole(value);
            if (model.update(user)) {
                JOptionPane.showMessageDialog(null, "user sucessfully modified");
                cleanForm();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                cleanForm();
            }
        }

        if (e.getSource() == view.btn_remove) {
            user.setId_user(Integer.parseInt(view.input_id.getText()));
            if (model.delete(user)) {
                JOptionPane.showMessageDialog(null, "User successfully deleted");
                cleanForm();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                cleanForm();
            }
        }

        if (e.getSource() == view.btn_table1) {
            model.load_table_users(view.table_user);

        }

        if (e.getSource() == view.input_searchT) {
            String field_name = view.input_searchT.getText();
            if (!"".equals(field_name)) {

            }

        }

    }

    public void btn_ok(JTable table_user, JTextField input_searchT) {
        DefaultTableModel modelTable = new DefaultTableModel();
        table_user.setModel(modelTable);
        DBConnection conn = new DBConnection();
        conn.getConnection();
        String field_name = input_searchT.getText().trim();
        String where = "";
        if (!"".equals(field_name)) {
            where = "WHERE name='" + field_name + "'";
        }
        String sql = "SELECT * FROM nesti_user " + where;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            modelTable.addColumn("ID");
            modelTable.addColumn("NAME");
            modelTable.addColumn("USERNAME");
            modelTable.addColumn("PASSWORD");
            modelTable.addColumn("ROLE");

            ResultSetMetaData rsMD = rs.getMetaData();

            int countCols = rsMD.getColumnCount();
            int witdh_col[] = {5, 70, 30, 160, 5};

            for (int i = 0; i < countCols; i++) {
                table_user.getColumnModel().getColumn(i).setPreferredWidth(witdh_col[i]);
            }

            while (rs.next()) {
                Object fila[] = new Object[countCols];
                for (int i = 0; i < countCols; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelTable.addRow(fila);
            }
            conn.getConnection().close();
        } catch (Exception ex) {
            System.out.println("Error, " + ex);
        }
    }

    public void cleanForm() {
        view.input_id.setText(null);
        view.input_search.setText(null);
        view.input_name.setText(null);
        view.input_username.setText(null);
        view.input_password.setText(null);
        view.input_confirmPassword.setText(null);
        view.combo_role.setSelectedIndex(0);
    }

}
