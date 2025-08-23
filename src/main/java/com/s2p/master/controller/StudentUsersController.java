package com.s2p.master.controller;

import com.s2p.master.dto.StudentUsersDto;
import com.s2p.master.service.StudentUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student-users")
public class StudentUsersController {

    @Autowired
    private StudentUsersService studentUsersService;

    @PostMapping
    public ResponseEntity<StudentUsersDto> createStudentUser(@RequestBody StudentUsersDto studentUsersDto) {
        return ResponseEntity.ok(studentUsersService.createStudentUser(studentUsersDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentUsersDto> getStudentUserById(@PathVariable("id") UUID id) {
        StudentUsersDto dto = studentUsersService.getStudentUserById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentUsersDto>> getAllStudentUsers() {
        return ResponseEntity.ok(studentUsersService.getAllStudentUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentUsersDto> updateStudentUser(@PathVariable("id") UUID id, @RequestBody StudentUsersDto studentUsersDto) {
        StudentUsersDto updated = studentUsersService.updateStudentUser(id, studentUsersDto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentUser(@PathVariable("id") UUID id) {
        studentUsersService.deleteStudentUser(id);
        return ResponseEntity.noContent().build();
    }
}
