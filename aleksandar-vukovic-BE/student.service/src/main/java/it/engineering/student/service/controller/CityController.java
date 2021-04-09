package it.engineering.student.service.controller;

import it.engineering.student.service.dto.CityDto;
import it.engineering.student.service.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<CityDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
    }
}
