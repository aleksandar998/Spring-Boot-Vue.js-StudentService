package it.engineering.student.service.controller;

import it.engineering.student.service.dto.SubjectDto;
import it.engineering.student.service.exception.EntityNotPresent;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.service.SubjectService;
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
@RequestMapping("api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<SubjectDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id){
        Optional<SubjectDto> subjectDto = subjectService.findById(id);
        if (subjectDto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid subject id!");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@RequestBody SubjectDto subjectDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
        } catch (ExistEntityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @RequestBody SubjectDto subjectDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.update(subjectDto));
        } catch (EntityNotPresent ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
        subjectService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Subject deleted!");
    }

    @GetMapping("/{pageable}")
    public @ResponseBody ResponseEntity<Page<SubjectDto>> getPages(@PathVariable Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findByPage(pageable));
    }
}
