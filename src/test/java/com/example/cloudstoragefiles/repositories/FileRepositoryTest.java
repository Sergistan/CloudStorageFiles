package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    private User savedUser;
    private File savedFile;

    @BeforeEach
    void setUp() {
        User user = new User(RandomUtils.nextLong(), "Random_login", "Random_password", null);
        savedUser = userRepository.save(user);

        File file = new File(RandomUtils.nextLong(), "Random_filename", LocalDateTime.now(), RandomUtils.nextLong(), "".getBytes(), savedUser);
        savedFile = fileRepository.save(file);
    }

    @Test
    void deleteByUserAndFilename() {
        File beforeDelete = fileRepository.findByUserAndFilename(savedUser, savedFile.getFilename());
        assertNotNull(beforeDelete);
        int deletedRows = fileRepository.deleteByUserAndFilename(savedUser, savedFile.getFilename());
        Assert.assertEquals(deletedRows, 1);
    }

    @Test
    void findByUserAndFilename() {
        assertEquals(savedFile, fileRepository.findByUserAndFilename(savedUser, savedFile.getFilename()));
    }

    @Test
    void editFileNameByUser() {
        File file = new File(RandomUtils.nextLong(), "Rename_filename", LocalDateTime.now(), RandomUtils.nextLong(), "".getBytes(), savedUser);
        File savedFile = fileRepository.save(file);

        int updatedRowCount = fileRepository.setNewFilenameByUserAndFilename("New_name", savedUser, savedFile.getFilename());
        assertEquals(updatedRowCount, 1);

        Optional<File> afterEditName = fileRepository.findById(savedFile.getId());
        assertTrue(afterEditName.isPresent());

        assertEquals("New_name", afterEditName.get().getFilename());
    }

    @Test
    void findAllByUser() {
        assertEquals(List.of(savedFile), fileRepository.findAllByUser(savedUser));
    }
}
