package com.lepric.btservice.service;

import com.lepric.btservice.model.Role;

import java.util.List;

public interface RoleService {
    Role AddRole(Role Role);
    Role UpdateRole(Role Role,long RoleID);
    Role GetRole(long RoleID);
    List<Role> GetRoles();
    boolean DeleteRole(long RoleID);

    List<Role> GetRoleUsers(long RoleID);

}
