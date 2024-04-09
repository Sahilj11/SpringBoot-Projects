package com.sahil.fileupload.service;

import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.swing.text.DefaultEditorKit.CopyAction;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sahil.fileupload.storageconfig.StorageException;
import com.sahil.fileupload.storageconfig.StorageFileNotFound;
import com.sahil.fileupload.storageconfig.StorageProperties;

/**
 * FileStorageImpl
 */
@Service
public class FileStorageImpl implements StorageService {

    private final Path rootLocation;

    public FileStorageImpl(StorageProperties storageProperties) {
        if (storageProperties.getLocation().trim().length() == 0) {

        }
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Path load(String filename) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty");
            }
            Path destination = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try (InputStream is = file.getInputStream()) {
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

}
