package com.demo.search.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {
    @Id
    private Integer id;
    private Integer sequence;
}
