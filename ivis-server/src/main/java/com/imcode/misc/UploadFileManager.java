package com.imcode.misc;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 08.12.15.
 */

//todo make it thread safe
public class UploadFileManager {
//    private final Principal user;
    private final Path tempFilePath;
    private final Map<String, Path> files = new HashMap<>();

    public UploadFileManager() {
        this(null);
    }

    public UploadFileManager(Principal user) {
        String userPathName = user == null ? "Anonimus" : user.getName();

        tempFilePath = Paths.get("upload/" + userPathName);
        if (!Files.exists(tempFilePath)) {
            try {
                Files.createDirectories(tempFilePath);
            } catch (IOException e) {
                handleException(e);
            }
        }
    }

    public String put(MultipartFile multipartFile) {
        Path file = null;

        try {
            file = Files.createTempFile(tempFilePath, "", "");
            multipartFile.transferTo(file.toFile());
        } catch (IOException e) {
            handleException(e);
        }

        if (file != null) {
            String id = file.getFileName().toString();

            if (!files.containsKey(id)) {
                files.put(id, file);
            } else {
                throw new RuntimeException("file \"" + id + "\" is already exist!");
            }
            return id;
        }

        return null;
    }

    public List<String> putAll(Iterable<MultipartFile> multipartFiles) {
        List<String> result = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            result.add(put(multipartFile));
        }

        return result;
    }

    public Path getFile(String id) {
        Path path = files.get(id);

        if (path == null) {
            path = tempFilePath.resolve(id);
            files.put(id, path);
        }

        if (Files.notExists(path)) {
            files.remove(id);
            return null;
        }

        return path;
    }

    public void delete(String id) {
        Path file = files.remove(id);

        if (file == null) {
            throw new RuntimeException("file \"" + id + "\" not managed!");
        }

        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            handleException(e);
        }
    }

    public boolean contains(String id) {
        return files.containsKey(id);
    }

    protected void handleException(Exception e) {
        //todo think about how to handle all exceptions
        throw new RuntimeException(e);
    }

@PostConstruct
    public void init() {

    }
}
