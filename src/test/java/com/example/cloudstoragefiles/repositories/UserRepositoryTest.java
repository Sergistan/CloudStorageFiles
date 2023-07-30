package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils;

import static com.example.cloudstoragefiles.TestData.USERNAME_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User(RandomUtils.nextLong(), "Random_login", "Random_password", null);
        userRepository.save(user);
    }

    @Test
    void findUserByLogin() {
        assertEquals("Random_login", userRepository.findUserByLogin("Random_login").getLogin());
    }

    @Test
    void notFindUserByLogin() {
        assertNull(userRepository.findUserByLogin(USERNAME_2));
    }
}
