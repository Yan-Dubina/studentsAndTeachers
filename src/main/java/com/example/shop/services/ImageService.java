package com.example.shop.services;

import java.io.File;
import java.util.List;

public interface ImageService {
   File getImageForProduct(Long Id);
   List<File> getImagesForProducts(List<Long> ids);
}
