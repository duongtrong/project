package com.spring.projectsem4.repository.role;

import com.spring.projectsem4.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoleRepositoryImpl implements RoleRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Modifying(clearAutomatically = true)
    public Role mergeRole(Role role) {
        return entityManager.merge(role);
    }
}
