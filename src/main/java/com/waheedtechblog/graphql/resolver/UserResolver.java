package com.waheedtechblog.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.waheedtechblog.graphql.model.Repo;
import com.waheedtechblog.graphql.model.User;
import com.waheedtechblog.graphql.repository.RepoRepository;

import java.util.Optional;

public class UserResolver implements GraphQLResolver<User> {

    private RepoRepository repoRepository;

    public UserResolver(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Optional<Repo> getRepo(User user){
        return repoRepository.findById(user.getRepo().getId());
    }
}
