package com.abqdesibites.repository;

import com.abqdesibites.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByCategoryIdOrderBySortOrderAsc(Integer categoryId);

    List<MenuItem> findByIsWeekendTrueOrderBySortOrderAsc();

    List<MenuItem> findByIsAvailableTrueOrderByCategorySortOrderAscSortOrderAsc();
}
