package com.nsikakthompson.SprintbootIntro.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = repository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Taken");
        } else {
            repository.save(student);
        }

    }

    public void deleteStudent(Long id) {
       boolean exist = repository.existsById(id);
       if(!exist){
           throw new IllegalStateException("Student with id " + id + " Does not exit");
       }
       repository.deleteById(id);
    }
}
