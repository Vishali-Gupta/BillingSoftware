package in.project.billingsoftware.controller;

import in.project.billingsoftware.io.CategoryRequest;
import in.project.billingsoftware.io.CategoryResponse;
import in.project.billingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    // For Images, I am currently using local storage

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
        return   categoryService.add(request);
    }

    @GetMapping
    public List<CategoryResponse> fetchCategories(){
        return  categoryService.read();
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryId){
        try{
            categoryService.delete(categoryId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found: " + categoryId);
        }
    }
}

