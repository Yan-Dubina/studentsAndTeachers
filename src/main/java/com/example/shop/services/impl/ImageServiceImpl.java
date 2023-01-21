package com.example.shop.services.impl;

import com.example.shop.domain.Image;
import com.example.shop.repository.ImageRepository;
import com.example.shop.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository repository;

    public File getImageForProduct(Long id){
        return repository.getImageByProductId(id).getImage();
    }

    public List<File> getImagesForProducts(List<Long> ids) {
        return repository.getAllByProductIdIn(ids).stream().map(Image::getImage).collect(Collectors.toList());
    }
}
