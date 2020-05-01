package hu.iit.uni.miskolc.infrend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class LogModel {

    @Id
    private String id;
    private String accountNumber;
    private String event;
    private long amount;

    public LogModel(String accountNumber, String event, long amount, Date date) {
        this.accountNumber = accountNumber;
        this.event = event;
        this.amount = amount;
        this.date = date;

        this.id = accountNumber + event + date;
    }

    public LogModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
