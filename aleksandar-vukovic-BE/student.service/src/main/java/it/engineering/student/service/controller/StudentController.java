package it.engineering.student.service.controller;

import it.engineering.student.service.dto.StudentDto;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<StudentDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id){
        Optional<StudentDto> studentDto = studentService.findById(id);
        if (studentDto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student id!");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@RequestBody StudentDto studentDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentDto));
        } catch (ExistEntityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id,
                                                       @RequestBody StudentDto studentDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.update(studentDto));
        } catch (ExistEntityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted!");
    }

    @GetMapping("/pageable")
    public @ResponseBody ResponseEntity<Page<StudentDto>> getPages(@PathVariable Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findByPage(pageable));
    }
}
