package it.engineering.student.service.controller;

import it.engineering.student.service.dto.ProfessorDto;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.service.ProfessorService;
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
@RequestMapping("api/professors")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id){
        Optional<ProfessorDto> professorDto = professorService.findById(id);
        if (professorDto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(professorService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid professor id");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@RequestBody ProfessorDto professorDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
        } catch (ExistEntityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id,
                                                       @RequestBody ProfessorDto professorDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professorService.update(professorDto));
        } catch (ExistEntityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
        professorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted!");
    }

    @GetMapping("/pageable")
    public @ResponseBody ResponseEntity<Page<ProfessorDto>> getPages(@PathVariable Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findByPage(pageable));
    }
}
