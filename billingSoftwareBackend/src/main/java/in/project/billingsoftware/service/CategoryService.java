package in.project.billingsoftware.service;

import in.project.billingsoftware.io.CategoryRequest;
import in.project.billingsoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

   CategoryResponse add(CategoryRequest request, MultipartFile image);
   List<CategoryResponse> read();
   void delete(String categoryId);

}
