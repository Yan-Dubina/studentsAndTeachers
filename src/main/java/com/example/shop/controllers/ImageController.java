package com.example.shop.controllers;

import com.example.shop.services.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/all")
    public ResponseEntity<List<byte[]>> getAll(@RequestParam List<Long> ids) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImagesForProducts(ids).stream()
                        .map(this::convertFile)
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "")
    public ResponseEntity<byte[]> getForProduct(@RequestParam Long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(convertFile(imageService.getImageForProduct(id)));
    }

    byte[] convertFile(File file) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (Exception ignored){ }
        return bytes;
    }
}
