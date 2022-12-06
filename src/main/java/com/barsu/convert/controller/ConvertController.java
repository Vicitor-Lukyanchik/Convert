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
    private final String UPLOAD_DIR = "C:/Users/user/Desktop/PdfFile/convert.pdf";

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
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE)).body(resource);
    }

}
