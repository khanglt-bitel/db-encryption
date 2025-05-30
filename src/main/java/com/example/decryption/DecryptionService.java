package com.example.decryption;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.decryption.DecryptFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DecryptionService {

    private final InternalLibrary library = new InternalLibrary();

    public String decryptUsingStream(MultipartFile file) throws IOException {
        try (InputStream in = file.getInputStream()) {
            return library.decrypt(in);
        }
    }

    public String decryptUsingFilePath(MultipartFile file) throws IOException {
        // Save to a temp file and pass path to library
        java.io.File temp = java.io.File.createTempFile("upload", null);
        file.transferTo(temp);
        String result = library.decrypt(temp.getAbsolutePath());
        temp.delete();
        return result;
    }

    public List<DecryptFile> decryptFiles(MultipartFile[] files, String mode) throws IOException {
        List<DecryptFile> results = new ArrayList<>();
        for (MultipartFile file : files) {
            String content;
            if ("path".equalsIgnoreCase(mode)) {
                content = decryptUsingFilePath(file);
            } else {
                content = decryptUsingStream(file);
            }
            results.add(new DecryptFile(file.getOriginalFilename(), content));
        }
        return results;
    }
}
