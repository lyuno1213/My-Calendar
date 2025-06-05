package com.example.mycalendar.rest;

import com.example.mycalendar.model.ContentsDTO;
import com.example.mycalendar.service.ContentsService;
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
@RequestMapping(value = "/api/contentss", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContentsResource {

    private final ContentsService contentsService;

    public ContentsResource(final ContentsService contentsService) {
        this.contentsService = contentsService;
    }

    @GetMapping
    public ResponseEntity<List<ContentsDTO>> getAllContentss() {
        return ResponseEntity.ok(contentsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentsDTO> getContents(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(contentsService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createContents(
            @RequestBody @Valid final ContentsDTO contentsDTO) {
        final String createdId = contentsService.create(contentsDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContents(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final ContentsDTO contentsDTO) {
        contentsService.update(id, contentsDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContents(@PathVariable(name = "id") final String id) {
        contentsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
