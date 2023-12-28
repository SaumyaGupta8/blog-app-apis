//package com.blog.repositories;
//
//import com.blog.entities.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class UserRepoTest {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @BeforeEach
//    void setUp() {
//
//        User user = User.builder()
//                .id(1)
//                .name("John Doe")
//                .email("john.doe@example.com")
//                .about("About John")
//                .password("hashedPassword")  // This should be the hashed password
//                .build();
//
//        testEntityManager.persist(user);
//    }
//
//    @Test
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void findById_thenReturnUser() {
//        Optional<User> user = Optional.of(userRepo.findById(1).get());
//
//        assertEquals(user.get().getId(), 1);
//        assertEquals(user.get().getEmail(), "john.doe@example.com");
//    }
//}