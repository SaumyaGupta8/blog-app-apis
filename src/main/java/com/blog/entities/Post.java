package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title", length = 100, nullable = false)
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

//    public Integer getPostId() {
//        return postId;
//    }
//
//    public void setPostId(Integer postId) {
//        this.postId = postId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public Date getAddedDate() {
//        return addedDate;
//    }
//
//    public void setAddedDate(Date addedDate) {
//        this.addedDate = addedDate;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Post() {
//    }
//
//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public Post(Integer postId, String title, String content, String imageName, Date addedDate, Category category, User user, Set<Comment> comments) {
//        this.postId = postId;
//        this.title = title;
//        this.content = content;
//        this.imageName = imageName;
//        this.addedDate = addedDate;
//        this.category = category;
//        this.user = user;
//        this.comments = comments;
//    }
}
