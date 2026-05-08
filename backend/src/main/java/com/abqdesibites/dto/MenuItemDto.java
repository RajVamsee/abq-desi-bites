package com.abqdesibites.dto;

import java.math.BigDecimal;

public record MenuItemDto(
    Integer id,
    String name,
    String description,
    BigDecimal price,
    Boolean isAvailable,
    Boolean isWeekend
) {}
