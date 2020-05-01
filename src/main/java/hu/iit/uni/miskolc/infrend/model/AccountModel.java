package hu.iit.uni.miskolc.infrend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountModel {

    private String owner;
    private String balance;
    @Id
    private String accountNumber;

    public AccountModel() {
    }

    public AccountModel(String owner, String balance, String accountNumber) {
        super();
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "owner='" + owner + '\'' +
                ", balance='" + balance + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
