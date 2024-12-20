package com.pages.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pages.database.entity.ElementProperties;

@Repository
public interface ElementPropertiesRepository extends JpaRepository<ElementProperties, Long> {
    
}
