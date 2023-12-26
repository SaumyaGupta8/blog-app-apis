package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();

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
//    public void setCategory(CategoryDto category) {
//        this.category = category;
//    }
//
//    public void setUser(UserDto user) {
//        this.user = user;
//    }
//
//    public Integer getPostId() {
//        return postId;
//    }
//
//    public void setPostId(Integer postId) {
//        this.postId = postId;
//    }
//
//    public CategoryDto getCategory() {
//        return category;
//    }
//
//    public UserDto getUser() {
//        return user;
//    }
//
//    public PostDto(Integer postId, String title, String content, String imageName, Date addedDate, CategoryDto category, UserDto user, Set<CommentDto> comments) {
//        this.postId = postId;
//        this.title = title;
//        this.content = content;
//        this.imageName = imageName;
//        this.addedDate = addedDate;
//        this.category = category;
//        this.user = user;
//        this.comments = comments;
//    }
//
//    public Set<CommentDto> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<CommentDto> comments) {
//        this.comments = comments;
//    }
//
//    public PostDto() {
//    }
}
