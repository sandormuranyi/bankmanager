package hu.iit.uni.miskolc.infrend.data;


import hu.iit.uni.miskolc.infrend.model.AccountModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Random;

public class AccountDAO {

    public static List<AccountModel> showAccount(String userId) {

        List<AccountModel> accounts;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AccountModel.class);
        session.beginTransaction();
        accounts = criteria.add(Restrictions.eq("owner", userId)).list();
        session.getTransaction().commit();

        if (accounts != null && accounts.size() > 0) {
            System.out.println("Find account:" + accounts.get(0));
        }
        return accounts;
    }

    public static String addAccount(String userCode, String cash) {
        String accountNumber =  generateNumber(userCode);
        AccountModel accountModel = new AccountModel(userCode, cash,accountNumber);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(accountModel);
        session.getTransaction().commit();

        return accountNumber;
    }

    public static void addBalance(String accountNumber, int cash) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AccountModel.class);
        session.beginTransaction();
        AccountModel account = (AccountModel) criteria.add(Restrictions.eq("accountNumber", accountNumber)).uniqueResult();
        int currentCash = Integer.valueOf(account.getBalance());
        String updatedBalance = String.valueOf(currentCash + cash);
        account.setBalance(updatedBalance);
        session.update(account);
        session.getTransaction().commit();

        System.out.println("Add balance" + account);

    }

    public static boolean isAccountValid(String accountNumber) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AccountModel.class);
        session.beginTransaction();
        AccountModel account = (AccountModel) criteria.add(Restrictions.eq("accountNumber", accountNumber)).uniqueResult();
        session.getTransaction().commit();

        if (account != null) {
            result = true;
        }
        return result;
    }

    private static String generateNumber(String code) {
        return code + "-" + generateCode();
    }

    public static String generateCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        return String.format("%04d", number);
    }
}
