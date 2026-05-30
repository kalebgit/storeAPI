package com.kal.category_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    @JsonProperty("category_id")
    Integer category_id;

    @Column(name = "category")
    @JsonProperty("category")
    String category;

    @Column(name = "tag")
    @JsonProperty("tag")
    String tag;

    @Column(name = "status")
    @JsonProperty("status")
    Integer status;
}
