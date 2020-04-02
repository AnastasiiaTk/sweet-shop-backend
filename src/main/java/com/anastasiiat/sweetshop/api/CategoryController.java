package com.anastasiiat.sweetshop.api;

import com.anastasiiat.sweetshop.error.InvalidCategoryOperationException;
import com.anastasiiat.sweetshop.persistence.entity.Category;
import com.anastasiiat.sweetshop.service.CategoryService;
import com.anastasiiat.sweetshop.validator.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryValidator categoryValidator;

    @GetMapping("/api/getCategories")
    public @ResponseBody
    Iterable<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/admin/getRootCategories")
    public @ResponseBody
    Iterable<Category> getRootCategories() {
        return categoryService.getParentCategories();
    }

    @PostMapping(value = "/admin/saveCategory", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/admin/findSubcategories/{categoryId}")
    public @ResponseBody
    Iterable<Category> findSubcategories(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.findSubcategories(categoryId);
    }

    @RequestMapping(value = "/admin/deleteCategory", method = RequestMethod.POST)
    public void deleteCategory(@RequestBody Category category) throws InvalidCategoryOperationException {
        categoryValidator.validateToDelete(category.getCategoryId());
        categoryService.deleteCategory(category.getCategoryId());
    }
}
