package it.engineering.student.service.controller;

import it.engineering.student.service.dto.ExamDto;
import it.engineering.student.service.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<ExamDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(examService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamDto examDto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
    }
}
