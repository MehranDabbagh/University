package D.Repository.Base;




import D.MyConnection.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

public interface BaseRepository<T,I> {
    List<T> findAll();
    T findById(I id);

}
