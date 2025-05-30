package com.example.decryption;

import java.io.IOException;
import java.util.List;
import com.example.decryption.DecryptFile;
import com.example.decryption.ResponseDecrypt;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DecryptionController {

    private final DecryptionService service;

    public DecryptionController(DecryptionService service) {
        this.service = service;
    }

    @PostMapping(value = "/decrypt",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDecrypt decrypt(@RequestParam("files") MultipartFile[] files,
                                   @RequestParam(defaultValue = "stream") String mode) throws IOException {
        List<DecryptFile> results = service.decryptFiles(files, mode);
        return new ResponseDecrypt(results);
    }
}
