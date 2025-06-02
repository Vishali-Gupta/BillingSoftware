package in.project.billingsoftware.service;

import in.project.billingsoftware.io.CategoryRequest;
import in.project.billingsoftware.io.CategoryResponse;

import java.util.List;

public interface CategoryService {

   CategoryResponse add(CategoryRequest request);
   List<CategoryResponse> read();
   void delete(String categoryId);

}
