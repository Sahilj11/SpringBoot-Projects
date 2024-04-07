package com.sahil.fileupload.controller;

import java.io.IOException;

import org.hibernate.mapping.List;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

/**
 * FileController
 */
@RestController
@RequestMapping(path = "/file-api")
public class FileController {

    private StorageService storageService;

    @GetMapping(path = "/")
    public ResponseEntity<String> listFiles() throws IOException {
        List<String> uris = storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder.fromMethodName(FileController.class, "getfile", path.getFileName()
                        .toString().build().toUri().toString()).collect(Collectors.toList()));
        return ResponseEntity.ok(uris);
    }

    @GetMapping(path = "/{filename:.+}")
    public ResponseEntity<Resource> getfile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);

    }

    @PostMapping(path = "/")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "Your file has been uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";

    }
}
