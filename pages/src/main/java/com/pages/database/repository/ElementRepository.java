package com.pages.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pages.database.entity.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {

}
