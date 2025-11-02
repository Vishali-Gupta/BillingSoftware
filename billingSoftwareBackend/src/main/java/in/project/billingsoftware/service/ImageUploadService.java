package in.project.billingsoftware.service;

import in.project.billingsoftware.io.CategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {
    private static final String UPLOAD_DIR = "uploads/";

    /**
     * Saves the image to local 'uploads' folder and returns the file path or URL.
     */
    public String uploadImage(MultipartFile image, CategoryRequest req) {
        if (image == null || image.isEmpty()) {
            return null; // no image uploaded
        }

        try {
            // Create uploads folder if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate a unique filename
            String fileName = req.getName()  + "_" + image.getOriginalFilename();
            fileName = fileName.replace(" ","").trim();

            // Build path and save the file
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, image.getBytes());

            // Return relative path (so browser can access if exposed)
            return "/" + UPLOAD_DIR + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes an image file if it exists.
     */
    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            return; // No image to delete
        }

        try {
            Path path = Paths.get(imagePath);

            // Only delete if file exists and is a regular file
            if (Files.exists(path) && Files.isRegularFile(path)) {
                Files.delete(path);
                System.out.println("Deleted image: " + imagePath);
            } else {
                System.out.println("Image file not found: " + imagePath);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image: " + e.getMessage(), e);
        }
    }
}
