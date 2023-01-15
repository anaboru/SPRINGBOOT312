package springbootproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import springbootproject.models.User;

import java.util.List;


@Repository
public class UserDAOImp implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();    }
    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
    @Override
    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
        entityManager.flush();
    }
    @Override
    public void delete(int id) {
        entityManager.remove(getUserById(id));
    }

}
