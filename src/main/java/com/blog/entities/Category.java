package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(name = "title", length = 100, nullable = false)
    private String categoryTitle;
    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

//    public Category() {
//    }
//
//    public Category(int categoryId, String categoryTitle, String categoryDescription) {
//        this.categoryId = categoryId;
//        this.categoryTitle = categoryTitle;
//        this.categoryDescription = categoryDescription;
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryTitle() {
//        return categoryTitle;
//    }
//
//    public void setCategoryTitle(String categoryTitle) {
//        this.categoryTitle = categoryTitle;
//    }
//
//    public String getCategoryDescription() {
//        return categoryDescription;
//    }
//
//    public void setCategoryDescription(String categoryDescription) {
//        this.categoryDescription = categoryDescription;
//    }
}
