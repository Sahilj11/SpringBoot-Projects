package com.sahil.fileupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sahil.fileupload.security.service.UserLogin;
import com.sahil.fileupload.storageconfig.StorageException;
import com.sahil.fileupload.storageconfig.StorageFileNotFound;
import com.sahil.fileupload.storageconfig.StorageProperties;

import jakarta.security.auth.message.AuthException;

/**
 * FileStorageImpl
 */
@Service
@Scope("prototype")
public class FileStorageImpl implements StorageService {

    private  Path rootLocation;

    public FileStorageImpl(StorageProperties storageProperties) {
        if (storageProperties.getLocation().trim().length() == 0) {

        }
        UserLogin userLogin = new UserLogin();
        try {
            String loggedInUser = userLogin.getUserName();
            String newLoc = storageProperties.getLocation()+loggedInUser;
            storageProperties.setLocation(newLoc);
            this.rootLocation = Paths.get(storageProperties.getLocation());
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to find files");
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            System.out.println(file.toString());
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Could not read the file " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFound("Could not read the file "+ filename);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty");
            }
            Path destination = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            System.out.println(destination.toString());
            try (InputStream is = file.getInputStream()) {
                Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new StorageException("Failed to store");
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            throw new StorageException("Failed to create directories");
        }
    }

}
