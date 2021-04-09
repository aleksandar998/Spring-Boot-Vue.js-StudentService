package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.StudentDto;
import it.engineering.student.service.entity.City;
import it.engineering.student.service.entity.Student;
import it.engineering.student.service.exception.EntityNotPresent;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.mapper.StudentMapper;
import it.engineering.student.service.repository.CityRepository;
import it.engineering.student.service.repository.StudentRepository;
import it.engineering.student.service.service.StudentService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CityRepository cityRepository;
    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CityRepository cityRepository) {
        this.studentRepository = studentRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(entity -> {
            return studentMapper.toStudentDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto save(StudentDto studentDto) throws ExistEntityException {
        Optional<Student> student = studentRepository.findById(studentDto.getStudentId());
        if (student.isPresent()){
            throw new ExistEntityException(student.get(), "Student already exists!");
        }
        Student s = studentMapper.toStudent(studentDto);
        Optional<City> city = cityRepository.findById(studentDto.getCity().getCityId());
        if (city.isPresent()){
            s.setCity(city.get());
        }
        return studentMapper.toStudentDto(studentRepository.save(s));
    }

    @Override
    public StudentDto update(StudentDto studentDto) throws EntityNotPresent {
        Optional<Student> student = studentRepository.findById(studentDto.getStudentId());
        if (!student.isPresent()){
            throw new EntityNotPresent(studentDto.getStudentId(), "Student does not exists!");
        }
        Student s = studentMapper.toStudent(studentDto);
        Optional<City> city = cityRepository.findById(studentDto.getCity().getCityId());
        if (city.isPresent()){
            s.setCity(city.get());
        }
        return studentMapper.toStudentDto(studentRepository.save(s));
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            return Optional.of(studentMapper.toStudentDto(student.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<StudentDto> findByPage(Pageable pageable) {
        Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toStudentDto);
        return entites;
    }
}
