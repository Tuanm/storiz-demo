package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Story")
public class Story {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "post_at")
    public LocalDateTime postAt;

    @ManyToMany
    @JoinTable(
        name = "TagRef",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "name")
    )
    public Set<Tag> tags;
    
    public Story() {
        tags = new HashSet<>();
    }

    public Story withTitle(String title) {
        this.title = title;
        return this;
    }

    public Story withContent(String content) {
        this.content = content;
        return this;
    }

    public Story addTags(Tag... tags) {
        for (Tag tag : tags) this.tags.add(tag);
        return this;
    }

    public Story postAt(LocalDateTime postAt) {
        this.postAt = postAt;
        return this;
    }

    @Override
    public String toString() {
        return String.format("/story/%d", this.id);
    }
}
