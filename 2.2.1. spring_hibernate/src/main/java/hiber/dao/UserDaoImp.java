package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
  private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory){
      this.sessionFactory = sessionFactory;
   }


   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   public void getUser(String model, int series) {
      Query<User> us = sessionFactory.getCurrentSession().createQuery("FROM User user JOIN FETCH user.car car where car.model= : model AND car.series= : series", User.class);
      us.setParameter("model", model);
      us.setParameter("series", series);
      List<User> users = us.getResultList();
      System.out.println(users);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
