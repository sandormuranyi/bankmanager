package hu.iit.uni.miskolc.infrend.bean;


import hu.iit.uni.miskolc.infrend.data.UserDAO;
import hu.iit.uni.miskolc.infrend.model.UserModel;
import org.primefaces.event.FlowEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Random;

@ManagedBean(name = "userWizard")
@ViewScoped
public class UserWizard implements Serializable {

    private UserModel user = new UserModel();

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void save() {

        if (user != null) {
            UserDAO.createUser(user.getFirstname(),
                    user.getLastname(), user.getStreet(),
                    user.getCity(), user.getPostalCode(),
                    user.getPersonalID(), user.getPhone(),
                    user.isActive(), user.getUserCode());
            FacesMessage msg = new FacesMessage("Successful", "Added : " + user.getFirstname()
                    + ", ID: " + user.getUserCode());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    public static String generateUserCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String onFlowProcess(FlowEvent event) {
        user.setUserCode(generateUserCode());
        return event.getNewStep();
    }
}