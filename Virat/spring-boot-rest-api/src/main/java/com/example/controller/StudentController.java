package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.entity.Student;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("saveStudent")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody @Valid StudentDTO studentDto) {
        StudentDTO studentDTO = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);

    }

    @GetMapping("getAllStudents")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getStudentByLastName/{name}")
    public List<Student> getStudent(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

    @PostMapping("updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDto) {
        return studentService.updateStudent(studentDto);
    }

    @PostMapping("getAllStudentsByIds")
    public List<Student> getAllStudentsByIds(@RequestBody List<Long> ids) {
        return studentService.getAllStudentsByIds(ids);
    }

    @PostMapping("/readCircuits")
    public List<String> readCircuits(@RequestPart(value = "file", required = false)MultipartFile file) throws Exception {
        return studentService.getCircuitsFromFile3(file);
    }

}
