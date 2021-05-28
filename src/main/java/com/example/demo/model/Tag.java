package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @Column(name = "name")
    public String name;

    @Column(name = "href")
    public String href;

    @ManyToMany(mappedBy = "tags")
    public Set<Story> stories;

    public Tag() {
        this.stories = new HashSet<>();
    }

    public Tag withName(String name) {
        this.name = name;
        return this;
    }

    public Tag withHref(String href) {
        this.href = href;
        return this;
    }

    public Tag link(Story story) {
        this.stories.add(story);
        return this;
    }

    public Tag(String name, String href) {
        this.name = name;
        this.href = href;
    }
}
