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
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        post.setAddedDate(new Date());

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAddedDate(new Date());

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Post Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageSize, Integer pageNo, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


//        if (sortDir.equalsIgnoreCase("asc")) {
//            sort = Sort.by(sortBy).ascending();
//        }
//        else if (sortDir.equalsIgnoreCase("dsc")) {
//            sort = Sort.by(sortBy).descending();
//        }
//        else {
//            throw new ResourceNotFoundException("Sorting", "this sorting param", 404);
//        }


        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
//        List<Post> posts  = (List<Post>) this.postRepo.findAll(pageable); this is not working throwing classCastException
//        List<Post> posts = this.postRepo.findAll(pageable).getContent();  this is one line implementation of those 2 lines

        List<PostDto> postDtos= posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pageable.getPageSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post> posts = this.postRepo.findByTitleContaining(keyword);

        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();
    }
}
