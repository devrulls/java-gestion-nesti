package app;

import controller.ControllerUser;
import model.EntityUser;
import model.ModelUser;
import views.ViewRegister;

/**
 *
 * @author HERNANDEZ Raul @devrulls
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewRegister viewReg = new ViewRegister();
        EntityUser user = new EntityUser();
        ModelUser model = new ModelUser();
        ControllerUser controller = new ControllerUser(viewReg, model, user);

        controller.start();
        viewReg.setVisible(true);

    }

}
