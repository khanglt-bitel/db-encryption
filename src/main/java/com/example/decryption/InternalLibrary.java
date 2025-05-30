package com.example.decryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InternalLibrary {

    // Decrypt by input stream (dummy implementation)
    public String decrypt(InputStream in) throws IOException {
        // In a real scenario, decrypt the data from InputStream
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }

    // Decrypt by file path using FileInputStream
    public String decrypt(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return decrypt(fis);
        }
    }
}
