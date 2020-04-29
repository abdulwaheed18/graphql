package com.waheedtechblog.graphql;

import com.waheedtechblog.graphql.exception.GraphQLErrorAdapter;
import com.waheedtechblog.graphql.model.Repo;
import com.waheedtechblog.graphql.model.User;
import com.waheedtechblog.graphql.repository.RepoRepository;
import com.waheedtechblog.graphql.repository.UserRepository;
import com.waheedtechblog.graphql.resolver.Mutation;
import com.waheedtechblog.graphql.resolver.QueryResolver;
import com.waheedtechblog.graphql.resolver.UserResolver;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class GraphqlJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlJavaApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public UserResolver authorResolver(RepoRepository repoRepository) {
        return new UserResolver(repoRepository);
    }

    @Bean
    public QueryResolver query(RepoRepository repoRepository, UserRepository userRepository) {
        return new QueryResolver(userRepository, repoRepository);
    }

    @Bean
    public Mutation mutation(RepoRepository repoRepository, UserRepository userRepository) {
        return new Mutation(userRepository, repoRepository);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, RepoRepository repoRepository) {
        return (args) -> {
            Repo repo = new Repo("repo-name", "https://stash.com/", "dummy stash");
            repoRepository.save(repo);
            userRepository.save(new User("abdulwaheed18", "waheed", "abdulwaheed18@gmail.com", repo));
        };
    }

}
