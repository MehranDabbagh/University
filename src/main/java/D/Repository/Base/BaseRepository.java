package D.Repository.Base;



import D.Entities.Base.BaseEntity;
import D.MyConnection.PostgresConnection;
import D.MyConnection.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

public interface BaseRepository<T,I> {
    Connection connection= PostgresConnection.getInstance().getConnection();
     SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    I create(T t);
    T findById(I id);
    List<T> findAll();
    void Update(T t);
    void Delete(I id);
}
