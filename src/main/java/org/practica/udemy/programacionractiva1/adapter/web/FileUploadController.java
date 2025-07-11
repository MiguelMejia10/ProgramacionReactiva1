package org.practica.udemy.programacionractiva1.adapter.web;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    public static final String UPLOAD_DIR = "uploads/";

    @GetMapping
    public String uploadForm(){
        return "upload-form";
    }

    @PostMapping
    public Mono<String> handleUpload(@RequestPart("file") FilePart filePart) {
        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                return Mono.error(new RuntimeException("No se pudo crear la carpeta de subida", e));
            }
        }

        Path path = uploadPath.resolve(filePart.filename());
        return filePart.transferTo(path)
                .thenReturn("redirect:/upload");
    }



    @GetMapping("/files")
    @ResponseBody
    public Flux<String> listFiles(){
        try{
            return Flux.fromStream(Files.list(Paths.get(UPLOAD_DIR)))
                    .map(Path::getFileName)
                    .map(Path::toString);
        } catch (IOException e) {
            return Flux.just("Error al listar archivos");        }
    }

}
