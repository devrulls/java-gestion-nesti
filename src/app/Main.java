package app;

import controller.ControllerLogin;
import controller.ControllerUser;
import model.EntityRole;
import model.EntityUser;
import model.ModelUser;
import views.ViewLogin;
import views.ViewNesti;

/**
 *
 * @author HERNANDEZ Raul @devrulls
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityUser user = new EntityUser();

        ModelUser model = new ModelUser();
        ViewLogin viewLogin = new ViewLogin();
//        ViewNesti viewNesti = new ViewNesti();
        viewLogin.setVisible(true);
//        ControllerUser controller = new ControllerUser(viewNesti, model, user);

        ControllerLogin controllerLogin = new ControllerLogin(viewLogin, model, user);       
        controllerLogin.start();

//        controller.start();
//        viewNesti.setVisible(false);

    }

}
