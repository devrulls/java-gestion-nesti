package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.EncryptPassword;
import model.EntityUser;
import model.ModelUser;
import views.ViewLogin;
import views.ViewNesti;


/**
 *
 * @author @author HERNANDEZ Raul @devrulls
 */
public class ControllerLogin implements ActionListener {

    public static ViewNesti nesti_main;
    private ViewLogin view;
//    private ViewNesti viewMain;
    private ModelUser model;
    private EntityUser user;

    public ControllerLogin(ViewLogin view, ModelUser model, EntityUser user) {
        this.view = view;
        this.model = model;
        this.user = user;
        

        view.btn_sign_in.addActionListener(this);
    }
        public void start() {
        view.setTitle("Nesti - Login");
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btn_sign_in) {

            String pwd = new String(view.input_password.getPassword());
            if (view.input_username.getText().equals("") || pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "Please complete all fields");
            } else {
                String loginPasswordHash = EncryptPassword.md5(pwd);

                user.setUsername(view.input_username.getText());
                user.setPwd(loginPasswordHash);

                if (model.login(user)) {
                    
                    
                    JOptionPane.showMessageDialog(null, "Congratulations, welcome to Nesti-App");
                    view.dispose();
//                    viewMain.setVisible(true);
                    nesti_main = new ViewNesti(user);
                    nesti_main.setUser(user);
                    nesti_main.setVisible(true);
                    nesti_main.setLocationRelativeTo(null);
                    
                    
//                    if (main == null){
//                        main = new ViewMain();
//                        main.setVisible(true);
//                        main.setLocationRelativeTo(null);
//                    }

                } else {
                    JOptionPane.showMessageDialog(null, "OH NO!!! incorrect information");

                }
            }
        }
    }

}
