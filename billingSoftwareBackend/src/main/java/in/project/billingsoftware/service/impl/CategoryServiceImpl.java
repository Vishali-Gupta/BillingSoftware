package in.project.billingsoftware.service.impl;

import in.project.billingsoftware.entity.CategoryEntity;
import in.project.billingsoftware.io.CategoryRequest;
import in.project.billingsoftware.io.CategoryResponse;
import in.project.billingsoftware.repository.CategoryRepository;
import in.project.billingsoftware.service.CategoryService;
import in.project.billingsoftware.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public  class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageUploadService imageUploadService;

    @Override
    public CategoryResponse add(CategoryRequest req, MultipartFile image){
        CategoryEntity newCategory =  ConvertToEntity(req);
        // Delegate image upload to separate service
        String imagePath = imageUploadService.uploadImage(image, req);
        newCategory.setImgUrl(imagePath);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity))
                .collect(Collectors.toList());

    }
    @Override
    public void delete(String categoryId){
       CategoryEntity existingCategory =  categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found" +   categoryId));
        imageUploadService.deleteImage(existingCategory.getImgUrl());
       categoryRepository.delete(existingCategory);

    }

    private CategoryEntity ConvertToEntity(CategoryRequest req){

        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(req.getName())
                .bgColor(req.getBgColor())
                .description(req.getDescription())
                .build();
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder().categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .imageUrl(newCategory.getImgUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }

}
