package D.Service.Base;



import java.util.List;

public interface BaseService <T ,I>{
    I login(T t);
    I create (T t);
    T findById(I id);
    List<T> findAll();
    void Update(T t);
    void Delete(I id);
}
