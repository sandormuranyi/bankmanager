package hu.iit.uni.miskolc.infrend.data;

import hu.iit.uni.miskolc.infrend.model.LogModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class LogDAO {

    public static List<LogModel> searchLogForAccount(String accountNumber) {
        List<LogModel> logs;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LogModel.class);
        session.beginTransaction();
        logs = criteria.add(Restrictions.eq("accountNumber", accountNumber)).list();
        session.getTransaction().commit();
        return logs;
    }

    public static List<LogModel> searchLogForDate(Date date) {
        Date now = new Date();
        List<LogModel> logs;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LogModel.class);
        session.beginTransaction();
        logs = criteria.add(Restrictions.between("date", date, now)).list();
        session.getTransaction().commit();
        return logs;
    }

    public static List<LogModel> searchLogForPrice(Long minMoney, Long maxMoney) {
        List<LogModel> logs;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LogModel.class);
        session.beginTransaction();
        logs = criteria.add(Restrictions.between("amount", minMoney, maxMoney)).list();
        session.getTransaction().commit();
        return logs;
    }

    public static void addLog(LogModel log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(log);
        session.getTransaction().commit();
    }
}
