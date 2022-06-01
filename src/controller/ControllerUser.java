package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.EncryptPassword;
import model.EntityUser;
import model.ModelUser;
import views.ViewRegister;

/**
 *
 * @author @author HERNANDEZ Raul @devrulls
 */
public class ControllerUser implements ActionListener {

    private ViewRegister view;
    private ModelUser model;
    private EntityUser user;

    public ControllerUser(ViewRegister view, ModelUser model, EntityUser user) {
        this.view = view;
        this.model = model;
        this.user = user;

        view.btn_register.addActionListener(this);
        view.btn_search_user.addActionListener(this);
        view.btn_modify.addActionListener(this);
        view.btn_remove.addActionListener(this);
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
            String pwd = new String(view.input_password.getPassword());
            String confirm_pwd = new String(view.input_confirmPassword.getPassword());

            if (view.input_name.getText().equals("") || view.input_username.getText().equals("") || pwd.equals("") || confirm_pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "please complete all fields");

            } else {
                if (pwd.equals(confirm_pwd)) {
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
                }

            }

        }

        if (e.getSource() == view.btn_search_user) {
            user.setUsername(view.input_username.getText());

            if (model.search_by_username(user)) {
                view.input_id.setText(String.valueOf(user.getId_user()));
                view.input_name.setText(user.getName());
                view.input_username.setText(user.getUsername());
                int value_role = user.getId_role();
                String role = "";
                if (value_role == 1) {
                    role = "SUPER ADMIN";

                } else if (value_role == 2) {
                    role = "SUPER ADMIN";
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
