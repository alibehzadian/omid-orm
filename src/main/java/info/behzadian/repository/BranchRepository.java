package info.behzadian.repository;

import info.behzadian.repository.base.BaseRepository;
import info.behzadian.repository.base.SimpleRepository;
import info.behzadian.repository.entity.Branch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchRepository implements BaseRepository<Branch, Long> {

    private final SimpleRepository<Branch, Long> repository;

    public BranchRepository() {
        this.repository = new SimpleRepository<>(Branch.class);
    }

    @Override
    public List<Branch> findAll() {
        return repository.findAll();
    }

    @Override
    public Branch findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
