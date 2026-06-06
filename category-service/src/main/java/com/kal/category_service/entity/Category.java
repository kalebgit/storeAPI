package com.kal.category_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
    name = "category",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_category_category", columnNames = "category"),
        @UniqueConstraint(name = "ux_category_tag", columnNames = "tag")
    }
)
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category")
    private String category;

    @Column(name = "tag")
    private String tag;

    @Column(name = "status")
    private Integer status;
}
