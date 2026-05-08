package com.abqdesibites.repository;

import com.abqdesibites.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {

    @Query("SELECT c FROM MenuCategory c ORDER BY c.sortOrder ASC")
    List<MenuCategory> findAllOrdered();
}
