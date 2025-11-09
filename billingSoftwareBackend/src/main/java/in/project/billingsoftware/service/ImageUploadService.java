package in.project.billingsoftware.service;

import in.project.billingsoftware.io.CategoryRequest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageUploadService {

    // 🔑 Replace with your own API key from https://api.imgbb.com/
    private static final String IMGBB_API_KEY = "4efb103877fa490731fb37ccb2cf2417";

    /**
     * Uploads image to imgbb.com and returns the public URL.
     */
    public String uploadImage(MultipartFile image, CategoryRequest req) {
        if (image == null || image.isEmpty()) {
            return null; // no image uploaded
        }

        try {
            // Construct upload endpoint
            String uploadUrl = "https://api.imgbb.com/1/upload?key=" + IMGBB_API_KEY;

            // HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Prepare multipart body
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Send POST request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, requestEntity, Map.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Image upload failed with status: " + response.getStatusCode());
            }

            // Extract the image URL from JSON response
            Map<String, Object> responseBody = response.getBody();
            Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
            String imageUrl = (String) data.get("url");

            System.out.println("✅ Uploaded image to imgbb: " + imageUrl);
            return imageUrl; // Return public image URL

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image to imgbb: " + e.getMessage(), e);
        }
    }

    /**
     * imgbb doesn't support delete by URL without 'delete_url' (from upload response).
     * This method will just log a message.
     */
    public void deleteImage(String imagePath) {
        System.out.println("⚠️ Deletion is not supported for imgbb public URLs. Store 'delete_url' if needed.");
    }
}
