package hu.iit.uni.miskolc.infrend.data;

import hu.iit.uni.miskolc.infrend.model.UserModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class SearchDAO {
    public static List<UserModel> searchUserbyfName(String fname) {
        List<UserModel> users;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserModel.class);
        session.beginTransaction();
        users = criteria.add(Restrictions.eq("firstname", fname)).list();
        session.getTransaction().commit();
        return users;
    }

    public static List<UserModel> searchUserbylName(String lname) {
        List<UserModel> users;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserModel.class);
        session.beginTransaction();
        users = criteria.add(Restrictions.eq("lastname", lname)).list();
        session.getTransaction().commit();
        return users;
    }

    public static List<UserModel> searchUserbyPersonalID(String pid) {
        List<UserModel> users;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserModel.class);
        session.beginTransaction();
        users = criteria.add(Restrictions.eq("personalID", pid)).list();
        session.getTransaction().commit();
        return users;
    }

    public static List<UserModel> searchUserbyID(String pid) {
        List<UserModel> users;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserModel.class);
        session.beginTransaction();
        users = criteria.add(Restrictions.eq("userCode", pid)).list();
        session.getTransaction().commit();
        return users;
    }

}
