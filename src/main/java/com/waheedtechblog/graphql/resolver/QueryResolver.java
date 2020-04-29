package com.waheedtechblog.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.waheedtechblog.graphql.model.Repo;
import com.waheedtechblog.graphql.model.User;
import com.waheedtechblog.graphql.repository.RepoRepository;
import com.waheedtechblog.graphql.repository.UserRepository;

public class QueryResolver implements GraphQLQueryResolver {

    private UserRepository userRepository;
    private RepoRepository repoRepository;

    public QueryResolver(UserRepository userRepository, RepoRepository repoRepository) {
        this.userRepository = userRepository;
        this.repoRepository = repoRepository;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Iterable<Repo> findAllRepo(){
        return repoRepository.findAll();
    }

    public Long totalUsers(){
        return userRepository.count();
    }

    public Long totalRepos(){
        return repoRepository.count();
    }
}
