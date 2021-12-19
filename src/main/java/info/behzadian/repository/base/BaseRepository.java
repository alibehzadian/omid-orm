package info.behzadian.repository.base;

import java.util.List;

public interface  BaseRepository<T, ID> {
    List<T> findAll();

    T findById(ID id);

    void deleteById(ID id);
}
