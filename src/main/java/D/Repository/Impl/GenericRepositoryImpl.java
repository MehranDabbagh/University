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
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void deleteById(ID id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
