package gk2026.com.service.impl;

import gk2026.com.dao.UserRoleDAO_24110188;
import gk2026.com.dao.impl.UserRoleDAOImpl_24110188;
import gk2026.com.model.UserRole_24110188;
import gk2026.com.service.UserRoleService_24110188;

public class UserRoleServiceImpl_24110188 implements UserRoleService_24110188 {
    private final UserRoleDAO_24110188 dao = new UserRoleDAOImpl_24110188();

    @Override
    public UserRole_24110188 findByName(String roleName) {
        return dao.findByName(roleName);
    }
}
