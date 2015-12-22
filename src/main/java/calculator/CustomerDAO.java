package calculator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VeraL on 16.12.2015.
 */
public class CustomerDAO {
Model model;

    Session session = null;
   public Session openSession(){
        if (session == null) {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistryBuilder serviceRegistry = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry.buildServiceRegistry());

         session = sf.openSession();
        }
       return session;
    }

    public void addCustomer(Customer customer4, Session session){
    if(!session.isOpen()){
            openSession();
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            try {
                session.save(customer4);

                transaction.commit();
            } catch (HibernateException ex) {
                transaction.rollback();
                ex.printStackTrace();
            }
        }
        finally {
            session.close();
        }
    }

}
