package com.waheedtechblog.graphql.repository;

import com.waheedtechblog.graphql.model.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface RepoRepository extends CrudRepository<Repo,Long> {
}
