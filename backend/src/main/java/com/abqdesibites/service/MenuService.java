package com.abqdesibites.service;

import com.abqdesibites.dto.MenuCategoryDto;
import com.abqdesibites.dto.MenuItemDto;
import com.abqdesibites.repository.MenuCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuCategoryRepository categoryRepository;

    public List<MenuCategoryDto> getAllCategories() {
        return categoryRepository.findAllOrdered().stream()
            .map(category -> new MenuCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getItems().stream()
                    .filter(item -> Boolean.TRUE.equals(item.getIsAvailable()))
                    .map(item -> new MenuItemDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getIsAvailable(),
                        item.getIsWeekend()
                    ))
                    .toList()
            ))
            .toList();
    }
}
