package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;
    @NotEmpty
    @Size(min = 4)
    private String categoryTitle;
    @NotBlank
    private String categoryDescription;

//    public CategoryDto() {
//    }
//
//    public CategoryDto(int categoryId, String categoryTitle, String categoryDescription) {
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
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
