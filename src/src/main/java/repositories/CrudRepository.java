package repositories;

import java.util.List;

public interface CrudRepository<T> {
    void save(T model);

    List<T> findAll();

    List<T> findAllByAge(int age);

    void update(int ModelID, String firstargument, String seconedargument, String thirdargument);

    void delete(int ID);
}

