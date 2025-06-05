package com.example.mycalendar.rest;

import com.example.mycalendar.model.UsersDTO;
import com.example.mycalendar.service.UsersService;
import com.example.mycalendar.util.ReferencedException;
import com.example.mycalendar.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/userss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UsersService usersService;

    public UsersResource(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUserss() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(usersService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody @Valid final UsersDTO usersDTO) {
        final String createdId = usersService.create(usersDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsers(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final UsersDTO usersDTO) {
        usersService.update(id, usersDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable(name = "id") final String id) {
        final ReferencedWarning referencedWarning = usersService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
