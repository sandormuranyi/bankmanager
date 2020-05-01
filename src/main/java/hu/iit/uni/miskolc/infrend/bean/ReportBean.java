package hu.iit.uni.miskolc.infrend.bean;

import hu.iit.uni.miskolc.infrend.data.LogDAO;
import hu.iit.uni.miskolc.infrend.model.LogModel;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@ManagedBean(name="report")
@SessionScoped
public class ReportBean {

    private Date fromDate;
    private Long fromMoney;
    private Long toMoney;
    private String accountNum;
    private List<LogModel> reports;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Long getFromMoney() {
        return fromMoney;
    }

    public void setFromMoney(Long fromMoney) {
        this.fromMoney = fromMoney;
    }

    public Long getToMoney() {
        return toMoney;
    }

    public void setToMoney(Long toMoney) {
        this.toMoney = toMoney;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public List<LogModel> getReports() {
        return reports;
    }

    public void setReports(List<LogModel> reports) {
        this.reports = reports;
    }

    private Date date = new Date();
	
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date1) {
        this.date = date1;
    }

	public void dateSelectedAction(SelectEvent e){
        this.fromDate = (Date)e.getObject();
	}


	public void searchByAccountNum() throws IOException {
		this.reports = LogDAO.searchLogForAccount(accountNum);
        FacesContext.getCurrentInstance().getExternalContext().redirect("reports.xhtml");
	}

	public void searchByDate() throws IOException {
		this.reports = LogDAO.searchLogForDate(fromDate);
        FacesContext.getCurrentInstance().getExternalContext().redirect("reports.xhtml");
	}

	public void searchByAmount() throws IOException {
		this.reports = LogDAO.searchLogForPrice(fromMoney, toMoney);
        FacesContext.getCurrentInstance().getExternalContext().redirect("reports.xhtml");
	}
}
