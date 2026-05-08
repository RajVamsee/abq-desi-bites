package com.abqdesibites.dto;

import java.util.List;

public record MenuCategoryDto(
    Integer id,
    String name,
    String description,
    List<MenuItemDto> items
) {}
