package com.waheedtechblog.graphql.model;

import javax.persistence.*;

@Entity
public class Repo {

    @Id
    @Column(name="repoId", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="repoName", nullable = false)
    private String repoName;

    @Column(name="url", nullable = false)
    private String url;

    @Column(name="description", nullable = false)
    private String description;

    public Repo() {
    }

    public Repo(Long id) {
        this.id = id;
    }

    public Repo(String repoName, String url, String description) {
        this.repoName = repoName;
        this.url = url;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repo repo = (Repo) o;
        return id.equals(repo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", repoName='" + repoName + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
