package com.spring.projectsem4.repository.role;

import com.spring.projectsem4.model.Role;
import com.spring.projectsem4.model.enumm.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

    @Query(value = "SELECT * FROM roles WHERE roles.role_name =: roleName", nativeQuery = true)
    Optional<Role> findByRoleName(@Param("roleName") RoleName roleName);
}
