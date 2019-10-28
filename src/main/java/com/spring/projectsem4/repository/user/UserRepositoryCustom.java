package com.spring.projectsem4.repository.user;

import com.spring.projectsem4.model.User;

public interface UserRepositoryCustom {
    User mergeUser(final User user);
}
