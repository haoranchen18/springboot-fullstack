package com.haoran.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testSelectExistsEmailExists() {
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.FEMALE
        );
        underTest.save(student);

        boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isTrue();
    }

    @Test
    void testSelectExistsEmailDoesNotExists() {
        String email = "jamila@gmail.com";

        boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isFalse();
    }
}
