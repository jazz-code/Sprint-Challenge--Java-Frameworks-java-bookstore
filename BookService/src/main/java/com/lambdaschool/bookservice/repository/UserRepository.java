package com.lambdaschool.bookservice.repository;

import com.lambdaschool.bookservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
