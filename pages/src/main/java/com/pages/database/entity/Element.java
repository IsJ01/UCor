package com.pages.database.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "elements")
public class Element implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Long parentId = -1L;

    private Long pageId;

    @Column(nullable = false)
    private String tag;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "elementId", cascade = CascadeType.ALL)
    private List<ElementProperties> properties;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentId", cascade = CascadeType.ALL)
    private List<Element> children;

}
