package hu.iit.uni.miskolc.infrend.bean;

import hu.iit.uni.miskolc.infrend.data.AccountDAO;
import hu.iit.uni.miskolc.infrend.data.LogDAO;
import hu.iit.uni.miskolc.infrend.model.LogModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@ManagedBean(name = "transaction")
public class TransactionBean {


    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession session = request.getSession();

    private String account;
    private String destAccount;
    private int money;
    private String comment;

    private boolean isAvaliableAccount = false;

    public boolean isAvaliableAccount() {
        return isAvaliableAccount;
    }

    public void setAvaliableAccount(boolean avaliableAccount) {
        isAvaliableAccount = avaliableAccount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(String destAccount) {
        this.destAccount = destAccount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String sendMoney() {
        if (AccountDAO.isAccountValid(destAccount)) {
            String account = (String) session.getAttribute("accountNum");
            int cashOut = money * -1;

            System.out.println("Destination: " + destAccount);
            System.out.println("From: " + account);
            System.out.println("Balance: " + money);

            AccountDAO.addBalance(account, cashOut);
            AccountDAO.addBalance(destAccount, money);

            LogModel logFrom = new LogModel(destAccount, "Money sent", money,  new Date());
            LogModel logDestionation = new LogModel(destAccount, "Money recived", money,  new Date());
            LogDAO.addLog(logFrom);
            LogDAO.addLog(logDestionation);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nincs találat",
                            "Nem találtam számlát"));
        }
        return "admin.xhtml";
    }

    public String cashIn() {
        String account = (String) session.getAttribute("accountNum");
        System.out.println("Destination: " + account);
        System.out.println("Price: " + money);
        AccountDAO.addBalance(account, money);
        LogModel log = new LogModel(account, "Cash in", money,  new Date());
        LogDAO.addLog(log);
        return "admin.xhtml";
    }

    public String cashOut() {
        String account = (String) session.getAttribute("accountNum");
        int cashOut = money * -1;
        System.out.println("Destination: " + account);
        System.out.println("Price: " + cashOut);
        AccountDAO.addBalance(account, cashOut);
        LogModel log = new LogModel(account, "Cash out", money,  new Date());
        LogDAO.addLog(log);
        return "admin.xhtml";

    }

    public void checkDestination() {
        if (AccountDAO.isAccountValid(destAccount)){
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sikeres keresés",
                            "Ilyen account létezik"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nincs találat",
                            "Nem találtam számlát"));
        }
    }

    public void goBack() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
    }

    public void listener() {
        account = (String) session.getAttribute("accountNum");
    }

}
