package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.persistence.repository.ProductRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository productRepository;

    @Value("${resources.location}")
    private String resourcesLocation;

    private final Character SEPARATOR = '.';

    public Iterable<Product> getAllProducts() {
        return productRepository.findAllByOrderByName();
    }

    public Iterable<Product> findByСategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public Product findProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public String buildUniquePhotoPath(String originalName) {
        int lastDot = originalName.lastIndexOf(SEPARATOR);
        StringBuilder result = new StringBuilder();
        result.append(originalName.substring(0, lastDot));
        result.append(new Date().getTime());
        result.append(originalName.substring(lastDot));
        return result.toString();
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public void saveImageToStorage(MultipartFile file, Product editableProduct) {
        makeResourceDirIfNeeded();
        if (!file.isEmpty()) {
            File fileToSave = createFileToSave(file, editableProduct);
            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                logger.error("ERROR during saving of the image: ", e);
            }
        }
    }

    public byte[] getProductImageContent(Product product) {
        byte[] result = null;
        try {
            Path path = Paths.get(resourcesLocation, product.getPhotoPath());
            File file = new File(path.toUri());
            result = IOUtils.toByteArray(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("ERROR during getting image content: ", e);
        }
        return result;
    }

    private void makeResourceDirIfNeeded() {
        File directory = new File(resourcesLocation);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private File createFileToSave(MultipartFile file, Product editableProduct) {
        String photoPath = buildUniquePhotoPath(file.getOriginalFilename());
        editableProduct.setPhotoPath(photoPath);
        Path fullPath = Paths.get(resourcesLocation, photoPath);
        File fileToSave = new File(fullPath.toUri());
        try {
            fileToSave.createNewFile();
        } catch (IOException e) {
            logger.error("ERROR during the file creation: ", e);
        }
        return fileToSave;
    }
}
