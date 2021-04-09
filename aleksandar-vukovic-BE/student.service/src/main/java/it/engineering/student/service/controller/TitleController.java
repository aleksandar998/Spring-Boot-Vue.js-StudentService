package it.engineering.student.service.controller;

import it.engineering.student.service.dto.TitleDto;
import it.engineering.student.service.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/titles")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<TitleDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findAll());
    }
}
