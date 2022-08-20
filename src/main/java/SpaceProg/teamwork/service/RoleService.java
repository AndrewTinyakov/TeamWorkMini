package SpaceProg.teamwork.service;


import SpaceProg.teamwork.model.Role;

public interface RoleService {
    Role findById(Long id);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteById(Long id);

}
