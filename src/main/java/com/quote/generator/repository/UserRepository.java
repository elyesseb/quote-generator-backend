package com.quote.generator.repository;

import com.quote.generator.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ufhopla
 * on 10/08/2021.
 */
public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}
