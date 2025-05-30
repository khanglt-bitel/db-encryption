package com.example.decryption;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DecryptionController {

    private final DecryptionService service;

    public DecryptionController(DecryptionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping(value = "/decrypt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String decrypt(@RequestParam("file") MultipartFile file,
                          @RequestParam(defaultValue = "stream") String mode,
                          Model model) throws IOException {
        String result;
        if ("path".equalsIgnoreCase(mode)) {
            result = service.decryptUsingFilePath(file);
        } else {
            result = service.decryptUsingStream(file);
        }
        model.addAttribute("decrypted", result);
        return "result";
    }
}
