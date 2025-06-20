# db-encryption

Sample Spring Boot application demonstrating file upload and decryption using an internal library. It exposes two methods of decryption:

1. Using an `InputStream`.
2. Using a file path (`FileInputStream`).

Send a `POST` request to `/decrypt` with one or more files. The service responds with JSON in the following form:

```json
{
  "files": [
    {
      "fileName": "example.txt",
      "content": "decrypted text"
    }
  ]
}
```

Run the application with `mvn spring-boot:run` and send a `POST` request to `http://localhost:8080/decrypt` with your files.

You can also open `http://localhost:8080/index.html` in your browser to use a basic front‑end
upload form that posts to the same backend.
