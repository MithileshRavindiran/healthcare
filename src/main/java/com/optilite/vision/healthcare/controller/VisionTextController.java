package com.optilite.vision.healthcare.controller;

import com.optilite.vision.healthcare.service.VisionTextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/visiontext")
public class VisionTextController {

    private final VisionTextService visionTextService;

    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadVisionText(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(visionTextService.extractVisionText(file));
    }

    @GetMapping
    public String getText() {
        return "Hello World";
    }
}
