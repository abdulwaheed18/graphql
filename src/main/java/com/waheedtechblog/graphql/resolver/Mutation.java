package com.waheedtechblog.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.waheedtechblog.graphql.model.Repo;
import com.waheedtechblog.graphql.model.User;
import com.waheedtechblog.graphql.repository.RepoRepository;
import com.waheedtechblog.graphql.repository.UserRepository;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {

    private UserRepository userRepository;
    private RepoRepository repoRepository;

    public Mutation(UserRepository userRepository, RepoRepository repoRepository) {
        this.userRepository = userRepository;
        this.repoRepository = repoRepository;
    }

    public User newUser(String login, String username, String email, Long repoId) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setUsername(username);
        user.setRepo(new Repo(repoId));
        userRepository.save(user);
        return user;
    }

    public Repo addRepo(String repoName, String url, String description){
        Repo repo = new Repo();
        repo.setDescription(description);
        repo.setRepoName(repoName);
        repo.setUrl(url);
        repoRepository.save(repo);
        return repo;
    }

    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public User updateEmail(String email, Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
           // throw new BookNotFoundException("The book to be updated was found", id);
        }
        user.get().setEmail(email);
        userRepository.save(user.get());
        return user.get();
    }

}
