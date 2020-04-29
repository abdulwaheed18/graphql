package com.waheedtechblog.graphql.repository;

import com.waheedtechblog.graphql.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
