package hu.iit.uni.miskolc.infrend.data;

import hu.iit.uni.miskolc.infrend.model.UserModel;
import org.hibernate.Session;

public class UserDAO {


    public static void createUser(String firstname, String lastname, String street, String city, String postalCode,
                                  String personalID, String phone, boolean isActive, String userCode) {
        System.out.println("create user");
        UserModel userModel = new UserModel(userCode, firstname, lastname, street, city, postalCode, personalID, phone
                , isActive, userCode);
        createUser(userModel);
    }

    public static void createUser(UserModel userModel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userModel);
        session.getTransaction().commit();
    }

    public static void updateUser(UserModel userModel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(userModel);
        session.getTransaction().commit();
    }
}
	
	
