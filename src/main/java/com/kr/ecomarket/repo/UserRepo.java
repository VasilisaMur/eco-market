package com.kr.ecomarket.repo;

import com.kr.ecomarket.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
