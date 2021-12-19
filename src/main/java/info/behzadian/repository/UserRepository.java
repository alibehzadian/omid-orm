package info.behzadian.repository;

import info.behzadian.repository.base.BaseRepository;
import info.behzadian.repository.base.SimpleRepository;
import info.behzadian.repository.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements BaseRepository<User, Long> {

    private final SimpleRepository<User, Long> repository;

    public UserRepository() {
        this.repository = new SimpleRepository<>(User.class);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
