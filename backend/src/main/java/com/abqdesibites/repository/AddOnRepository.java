package com.abqdesibites.repository;

import com.abqdesibites.model.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddOnRepository extends JpaRepository<AddOn, Integer> {
    List<AddOn> findByIsAvailableTrue();
}
