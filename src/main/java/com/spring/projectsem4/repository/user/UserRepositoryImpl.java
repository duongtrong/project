package com.spring.projectsem4.repository.user;

import com.spring.projectsem4.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Modifying(clearAutomatically = true)
    public User mergeUser(User user) {
        return entityManager.merge(user);
    }
}
