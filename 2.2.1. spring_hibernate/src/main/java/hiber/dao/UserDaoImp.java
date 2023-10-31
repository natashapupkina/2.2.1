package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User getUserByModelAndSeries(String model, int series) {
        User user = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE car.model=:model " +
                        "AND car.series=:series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
        System.out.println(user);
        return user;
    }
}
