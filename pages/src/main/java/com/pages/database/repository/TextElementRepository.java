package com.pages.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pages.database.entity.TextElement;

@Repository
public interface TextElementRepository extends JpaRepository<TextElement, Long>{

}
