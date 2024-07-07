package Generic;

import java.util.List;

public interface ShopRepository<T> {
    T add(T t);
    T findById(int id);
    T update(T t);
    List<T> findAll();
}
