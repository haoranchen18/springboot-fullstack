package com.haoran.demo.student;

import com.haoran.demo.student.exception.BadRequestException;
import com.haoran.demo.student.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if (existsEmail) {
            throw new BadRequestException("Email " + student.getEmail() + " taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id "+ studentId +" does not exist!");
        }
        Student studentInDB = studentRepository.getById(studentId);

        if (name != null && name.length() > 0 &&
        !studentInDB.getName().equals(name)) {
            studentInDB.setName(name);
        }

        if (email != null && email.length() > 0 &&
        !studentInDB.getEmail().equals(email)) {
            studentInDB.setEmail(email);
        }
    }
}