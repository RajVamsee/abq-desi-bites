package com.abqdesibites.controller;

import com.abqdesibites.dto.MenuCategoryDto;
import com.abqdesibites.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuCategoryDto>> getMenu() {
        return ResponseEntity.ok(menuService.getAllCategories());
    }
}
