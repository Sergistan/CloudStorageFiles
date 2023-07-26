package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.example.cloudstoragefiles.TestData.*;
import static junit.framework.TestCase.assertTrue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        fileRepository.deleteAll();
        userRepository.save(USER_1);
        fileRepository.save(FILE_1);
        fileRepository.save(TO_RENAME_FILE);
    }

    @Test
    void deleteByUserAndFilename() {
        File beforeDelete = fileRepository.findByUserAndFilename(USER_1, FILENAME_1);
        assertNotNull(beforeDelete);
        fileRepository.deleteByUserAndFilename(USER_1, FILENAME_1);
        File afterDelete = fileRepository.findByUserAndFilename(USER_1, FILENAME_1);
        assertNull(afterDelete);
    }

    @Test
    void findByUserAndFilename() {
        assertEquals(FILE_1, fileRepository.findByUserAndFilename(USER_1, FILENAME_1));
    }

    @Test
    void editFileNameByUser() {
        Optional<File> beforeEditName = fileRepository.findById(FILE_ID_TO_RENAME);
        assertTrue(beforeEditName.isPresent());
        assertEquals(TO_RENAME_FILENAME, beforeEditName.get().getFilename());
        fileRepository.setNewFilenameByUserAndFilename(NEW_FILENAME, USER_1, TO_RENAME_FILENAME);
        Optional<File> afterEditName = fileRepository.findById(FILE_ID_TO_RENAME);
        assertTrue(afterEditName.isPresent());
        assertEquals(NEW_FILENAME, afterEditName.get().getFilename());
    }

    @Test
    void findAllByUser() {
        assertEquals(List.of(FILE_1, TO_RENAME_FILENAME), fileRepository.findAllByUser(USER_1));
    }
}
