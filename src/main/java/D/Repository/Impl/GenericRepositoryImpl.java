package D.Repository.Impl;

import D.Entities.Course;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.GenericRepository;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GenericRepositoryImpl<T, ID> implements GenericRepository<T, ID> {

    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public T save(T t) {
        Session session= sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    @Override
    public void update(T t) {
        Session session= sessionFactory.getCurrentSession();
        session.update(t);
    }

    @Override
    public void delete(T t) {
        Session session= sessionFactory.getCurrentSession();
        session.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        Session session= sessionFactory.getCurrentSession();
        session.delete(id);
    }
}
