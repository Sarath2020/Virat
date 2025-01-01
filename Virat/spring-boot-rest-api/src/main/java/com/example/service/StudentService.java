package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.Student;
import com.example.exception.StudentNotFoundException;
import com.example.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    public StudentDTO saveStudent(StudentDTO studentDto) {
        studentDto.setDbTimeStamp(LocalDateTime.now());
        Student student = modelMapper.map(studentDto, Student.class);
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id : " + id));
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public StudentDTO updateStudent(StudentDTO studentDto) {
        return Optional.ofNullable(studentDto)
                .map(dto -> modelMapper.map(dto, Student.class))
                .map(studentRepository::save)
                .map(entity -> modelMapper.map(entity, StudentDTO.class))
                .orElse(null); // or throw an exception or handle the null case appropriately
    }

    public List<Student> getAllStudentsByIds(List<Long> ids) {
        return studentRepository.findAllById(ids);
    }


    public List<String> getCircuitsFromFile(MultipartFile file) throws Exception {
        log.info("getCircuitsFromFile : Start");
        String data;
        Set<String> circuitIds = new LinkedHashSet<>();
        List<String> circuitIdsList = new ArrayList<>();
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            inputStream = file.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (StringUtils.hasText((data = bufferedReader.readLine()))) {
                circuitIds.add(data);
            }
            circuitIdsList = circuitIds.stream().filter(StringUtils::hasText).toList();
            log.info("getCircuitsFromFile : End");
        } catch (Exception e) {
            log.error("Error while getCircuitsFromFile", e);
            throw new Exception("Error while getting circuits from file");
        }
        return circuitIdsList;
    }

    public List<String> getCircuitsFromFile2(MultipartFile file) throws Exception {
        log.info("getCircuitsFromFile :: Start");
        Set<String> circuitIds = new LinkedHashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (StringUtils.hasText(bufferedReader.readLine()))
                circuitIds.add(bufferedReader.readLine());
        } catch (Exception e) {
            log.error("Error while getCircuitsFromFile", e);
            throw new Exception("Error while getting circuits from file", e);
        }
        log.info("getCircuitsFromFile :: End");
        return new ArrayList<>(circuitIds);
    }

    public List<String> getCircuitsFromFile3(MultipartFile file) throws Exception {
        log.info("getCircuitsFromFile : Start");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return bufferedReader.lines()
                    .filter(StringUtils::hasText)
                    .distinct()
                    .toList();
        } catch (Exception e) {
            log.error("Error while getCircuitsFromFile", e);
            throw new Exception("Error while getting circuits from file", e);
        } finally {
            log.info("getCircuitsFromFile : End");
        }
    }






}
