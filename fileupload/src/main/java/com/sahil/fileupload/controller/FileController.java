package com.sahil.fileupload.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sahil.fileupload.service.StorageService;

import lombok.AllArgsConstructor;

/**
 * FileController
 */
@RestController
@RequestMapping(path = "/file-api")
@AllArgsConstructor
public class FileController {

    private final StorageService storageService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<String>> listFiles() throws IOException {
        List<String> uris = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                        "getfile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(uris);
    }

    @GetMapping(path = "/{filename:.+}")
    public ResponseEntity<Resource> getfile(@PathVariable String filename) {

        try {
            Resource file = storageService.loadAsResource(filename);
            if (file == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return "File uploaded";
    }

}
