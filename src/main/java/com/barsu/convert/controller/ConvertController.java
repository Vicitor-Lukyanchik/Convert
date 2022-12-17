package com.barsu.convert.controller;

import com.barsu.convert.converter.ToPdfConverter;
import com.barsu.convert.entity.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class ConvertController {

    private final ToPdfConverter toPdfConverter;
    private final String APPLICATION_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private final String UPLOAD_DIR = "C:/Users/user/Desktop/PdfFile/convert.pdf";
    private final String FIRST_STUDY_DIR = "C:/Users/user/Desktop/PdfFile/first.docx";
    private final String SECOND_STUDY_DIR = "C:/Users/user/Desktop/PdfFile/second.docx";
    private final String EVENTS_DIR = "C:/Users/user/Desktop/PdfFile/events.docx";

    @GetMapping("/index")
    public String sayHello(@ModelAttribute("convert") Convert convert) {
        return "index";
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@ModelAttribute("convert") Convert convert) throws IOException {
        toPdfConverter.convert(convert);
        File file = new File(UPLOAD_DIR);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE))
                .body(resource);
    }

    @GetMapping(path = "/study")
    public String getStudy(){
        return "study";
    }

    @GetMapping(path = "/study/download/first")
    public ResponseEntity<Resource> downloadFirstStudy() throws IOException {
        File file = new File(FIRST_STUDY_DIR);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(APPLICATION_DOCX))
                .body(resource);
    }

    @GetMapping(path = "/study/download/second")
    public ResponseEntity<Resource> downloadSecondStudy() throws IOException {
        File file = new File(SECOND_STUDY_DIR);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(APPLICATION_DOCX))
                .body(resource);
    }

    @GetMapping(path = "/events")
    public String getEvents(){
        return "events";
    }

    @GetMapping(path = "/events/download")
    public ResponseEntity<Resource> downloadEvents() throws IOException {
        File file = new File(EVENTS_DIR);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(APPLICATION_DOCX))
                .body(resource);
    }
}
