package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.RoleDao;
import SpaceProg.teamwork.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional

public class RoleServiceSimple implements RoleService {
    private final RoleDao dao;

    public RoleServiceSimple(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    public Role findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void saveRole(Role role) {
        dao.save(role);
    }

    @Override
    public void updateRole(Role role) {
        dao.save(role);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
