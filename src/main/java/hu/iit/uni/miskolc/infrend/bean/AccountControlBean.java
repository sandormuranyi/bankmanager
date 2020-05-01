package hu.iit.uni.miskolc.infrend.bean;

import hu.iit.uni.miskolc.infrend.data.AccountDAO;
import hu.iit.uni.miskolc.infrend.data.LogDAO;
import hu.iit.uni.miskolc.infrend.model.AccountModel;
import hu.iit.uni.miskolc.infrend.model.LogModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@ManagedBean(name = "dtAccountSelect")
@SessionScoped
public class AccountControlBean implements Serializable {

    private static final long serialVersionUID = 1L;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession session = request.getSession();
    private List<AccountModel> accounts ;
    private AccountModel selectedAccount;
    private String cash;

    public List<AccountModel> getAccounts() {
       String user = session.getAttribute("userCode").toString();
        accounts = AccountDAO.showAccount(user);
        return accounts;
    }


    public AccountModel getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(AccountModel selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public String newAccount() {
        String user = session.getAttribute("userCode").toString();
        String accountNumber = AccountDAO.addAccount(user, cash);
        LogModel log = new LogModel(accountNumber, "Account created", Long.valueOf(cash),  new Date());
        LogDAO.addLog(log);
        return "admin.xhtml";
    }

    public boolean isActive() {
        return (boolean) session.getAttribute("isActive");
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String startTransaction(int val) {
        String response;
        session.setAttribute("accountNum", selectedAccount.getAccountNumber());
        switch (val) {
            case 1:
                response = "transfer.xhtml";
                break;
            case 2:
                response = "cashin.xhtml";
                break;
            case 3:
                response = "cashout.xhtml";
                break;
            default:
                response = "error.xhtml";
                break;
        }
        return response;
    }
}
