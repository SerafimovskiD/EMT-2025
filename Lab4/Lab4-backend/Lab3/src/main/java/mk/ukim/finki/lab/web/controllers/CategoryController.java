package mk.ukim.finki.lab.web.controllers;

import mk.ukim.finki.lab.model.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @GetMapping
    public Category[] getAllCategories(){
        return Category.values();
    }
}
