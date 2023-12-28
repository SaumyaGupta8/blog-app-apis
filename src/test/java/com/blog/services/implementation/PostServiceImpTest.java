package com.blog.services.implementation;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImpTest {

    @Mock
    private PostRepo postRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImp postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Post post = Post.builder()
            .postId(1)
            .title("Krishna")
            .addedDate(new Date())
            .content("He is lord")
            .build();

    PostDto postDto = PostDto.builder()
            .postId(1)
            .title("Krishna")
            .addedDate(new Date())
            .content("He is lord")
            .build();

//    @Test
//    void createPost_ShouldCreatePostSuccessfully() {
//        // Arrange
//        PostDto postDto = new PostDto();
//        int userId = 1;
//        int categoryId = 1;
//
//        User user = new User();
//        user.setId(userId);
//        Category category = new Category();
//        category.setId(categoryId);
//
//        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
//        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
//        when(modelMapper.map(postDto, Post.class)).thenReturn(new Post());
//        when(postRepo.save(any())).thenReturn(new Post());
//
//        // Act
//        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
//
//        // Assert
//        assertNotNull(createdPost);
//        verify(userRepo, times(1)).findById(userId);
//        verify(categoryRepo, times(1)).findById(categoryId);
//        verify(postRepo, times(1)).save(any());
//    }
//
//    @Test
//    void updatePost_ShouldUpdatePostSuccessfully() {
//        // Arrange
//        PostDto postDto = new PostDto();
//        int postId = 1;
//
//        Post existingPost = new Post();
//        existingPost.setId(postId);
//
//        when(postRepo.findById(postId)).thenReturn(Optional.of(existingPost));
//        when(modelMapper.map(postDto, Post.class)).thenReturn(existingPost);
//        when(postRepo.save(existingPost)).thenReturn(existingPost);
//
//        // Act
//        PostDto updatedPost = postService.updatePost(postDto, postId);
//
//        // Assert
//        assertNotNull(updatedPost);
//        verify(postRepo, times(1)).findById(postId);
//        verify(postRepo, times(1)).save(existingPost);
//    }

    @Test
    void deletePost_ShouldDeletePostSuccessfully() {
        // Arrange
        int postId = 1;

        when(postRepo.findById(postId)).thenReturn(Optional.of(post));

        // Act
        postService.deletePost(postId);

        // Assert
        verify(postRepo, times(1)).findById(postId);
        verify(postRepo, times(1)).delete(post);


    }

    @Test
    void getAllPost_ShouldReturnPostResponse() {
        // Arrange
        int pageSize = 10;
        int pageNo = 0;
        String sortBy = "title";
        String sortDir = "asc";

        List<Post> posts = Collections.singletonList(new Post());
        Page<Post> page = new PageImpl<>(posts);
        when(postRepo.findAll(any(Pageable.class))).thenReturn(page);
        when(modelMapper.map(any(Post.class), eq(PostDto.class))).thenReturn(new PostDto());

        // Act
        PostResponse postResponse = postService.getAllPost(pageSize, pageNo, sortBy, sortDir);

        // Assert
        assertNotNull(postResponse);
        assertEquals(posts.size(), postResponse.getContent().size());
        verify(postRepo, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getPostById_ShouldReturnPostDto() {
        // Arrange
        int postId = 1;

        when(postRepo.findById(postId)).thenReturn(Optional.of(new Post()));
        when(modelMapper.map(any(Post.class), eq(PostDto.class))).thenReturn(new PostDto());

        // Act
        PostDto postDto = postService.getPostById(postId);

        // Assert
        assertNotNull(postDto);
        verify(postRepo, times(1)).findById(postId);
    }

    @Test
    void getPostsByCategory_ShouldReturnListOfPostDto() {
        // Arrange
        int categoryId = 1;

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(new Category()));
        when(postRepo.findByCategory(any(Category.class))).thenReturn(Collections.singletonList(new Post()));
        when(modelMapper.map(any(Post.class), eq(PostDto.class))).thenReturn(new PostDto());

        // Act
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);

        // Assert
        assertNotNull(postDtos);
        assertFalse(postDtos.isEmpty());
        verify(categoryRepo, times(1)).findById(categoryId);
        verify(postRepo, times(1)).findByCategory(any(Category.class));
    }

    @Test
    void getPostsByUser_ShouldReturnListOfPostDto() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.of(new User()));
        when(postRepo.findByUser(any(User.class))).thenReturn(Collections.singletonList(new Post()));
        when(modelMapper.map(any(Post.class), eq(PostDto.class))).thenReturn(new PostDto());

        // Act
        List<PostDto> postDtos = postService.getPostsByUser(userId);

        // Assert
        assertNotNull(postDtos);
        assertFalse(postDtos.isEmpty());
        verify(userRepo, times(1)).findById(userId);
        verify(postRepo, times(1)).findByUser(any(User.class));
    }

    @Test
    void searchPosts_ShouldReturnListOfPostDto() {
        // Arrange
        String keyword = "test";

        when(postRepo.findByTitleContaining(keyword)).thenReturn(Collections.singletonList(new Post()));
        when(modelMapper.map(any(Post.class), eq(PostDto.class))).thenReturn(new PostDto());

        // Act
        List<PostDto> postDtos = postService.searchPosts(keyword);

        // Assert
        assertNotNull(postDtos);
        assertFalse(postDtos.isEmpty());
        verify(postRepo, times(1)).findByTitleContaining(keyword);
    }
}
