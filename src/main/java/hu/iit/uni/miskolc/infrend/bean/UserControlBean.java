package hu.iit.uni.miskolc.infrend.bean;

import hu.iit.uni.miskolc.infrend.data.SearchDAO;
import hu.iit.uni.miskolc.infrend.model.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "dtUserSelect")
@SessionScoped
public class UserControlBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String searchfirstName;
    private String searchlastName;
    private String searchID;
    private String searchCID;
    private UserModel selectedUser;
    private List<UserModel> users;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession session = request.getSession();

    public void searchUsersbyfirstName() throws IOException {

        if ((searchfirstName != null) && (searchfirstName.matches("^[^0-9()]+$")) && (searchfirstName.length() > 2) && (searchfirstName.matches("\\w+\\.?"))) {
            List<UserModel> lista = SearchDAO.searchUserbyfName(searchfirstName);
            if (searchfirstName.equals(lista.get(0).getFirstname())) {
                users = lista;
                FacesContext.getCurrentInstance().getExternalContext().redirect("searchuser.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Not found", "Nouser with firstname: " + searchfirstName));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "A vezetéknév nem tartalmazhat üres karaktert és számot", ""));
        }
    }

    public void searchUsersbylasttName() throws IOException {
        if ((searchlastName != null) && (searchlastName.matches("^[^0-9()]+$")) && (searchlastName.length() > 2) && (searchlastName.matches("\\w+\\.?"))) {
            List<UserModel> userModels = SearchDAO.searchUserbylName(searchlastName);
            if (searchlastName.equals(userModels.get(0).getLastname())) {
                users = userModels;
                FacesContext.getCurrentInstance().getExternalContext().redirect("searchuser.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Not found",
                                "Nouser with lastname: " + searchlastName));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "A keresztnév nem tartalmazhat üres karaktert és számot", ""));
        }
    }

    public void searchUsersbyCID() throws IOException {
        if (searchCID != null && searchCID.matches("\\w+\\.?") && searchCID.length() == 6) {
            List<UserModel> userModels = SearchDAO.searchUserbyID(searchCID);
            if (searchCID.equals(userModels.get(0).getId())) {
                users = userModels;
                FacesContext.getCurrentInstance().getExternalContext().redirect("searchuser.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Not found",
                                "Nouser with ID: " + searchID));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Az azonosítónak 6 számból kell állnia",
                            ""));
        }
    }

    public void searchUsersbyPersonalID() throws IOException {

        if (searchID != null && searchID.matches("\\w+\\.?") && searchID.length() == 8) {
            List<UserModel> userModels = SearchDAO.searchUserbyPersonalID(searchID);
            if (searchID.equals(userModels.get(0).getPersonalID())) {
                users = userModels;
                FacesContext.getCurrentInstance().getExternalContext().redirect("searchuser.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Not found",
                                "Nouser with ID: " + searchID));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "A személyigazolványszámnak 8 karakterből kell állnia és nem tartalmazhat szóközt",
                            ""));
        }
    }

    public String getSearchfirstName() {
        return searchfirstName;
    }

    public void setSearchfirstName(String searchfirstName) {
        this.searchfirstName = searchfirstName;
    }

    public String getSearchlastName() {
        return searchlastName;
    }

    public void setSearchlastName(String searchlastName) {
        this.searchlastName = searchlastName;
    }

    public String getSearchID() {
        return searchID;
    }

    public void setSearchID(String searchID) {
        this.searchID = searchID;
    }

    public String getSearchCID() {
        return searchCID;
    }

    public void setSearchCID(String searchCID) {
        this.searchCID = searchCID;
    }

    public UserModel getSelectedUser() {
        return selectedUser;
    }

    public void setselectedUser(UserModel selectedUser) {
        this.selectedUser = selectedUser;
    }


    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }


    public String modify() throws IOException {
        session.setAttribute("id", selectedUser.getId());
        session.setAttribute("firstName", selectedUser.getFirstname());
        session.setAttribute("lastName", selectedUser.getLastname());
        session.setAttribute("postalCode", selectedUser.getPostalCode());
        session.setAttribute("city", selectedUser.getCity());
        session.setAttribute("street", selectedUser.getStreet());
        session.setAttribute("phone", selectedUser.getPhone());
        session.setAttribute("userCode", selectedUser.getUserCode());
        session.setAttribute("personalID", selectedUser.getPersonalID());
        return "edit.xhtml";
    }


    public String showAccounts() {
        session.setAttribute("userCode", selectedUser.getUserCode());
        session.setAttribute("isActive", selectedUser.isActive());
        return "accounts.xhtml";
    }


}
