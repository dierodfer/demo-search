package com.demo.search.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Product {
    @Id
    private Integer id;
    private Integer sequence;
}
