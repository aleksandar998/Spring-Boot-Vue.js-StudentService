package it.engineering.student.service.controller;

import it.engineering.student.service.dto.ExamRegistrationDto;
import it.engineering.student.service.service.ExamRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/registration")
public class ExamRegistrationController {
    @Autowired
    private ExamRegistrationService examRegistrationService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<ExamRegistrationDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamRegistrationDto examRegistrationDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.save(examRegistrationDto));
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
