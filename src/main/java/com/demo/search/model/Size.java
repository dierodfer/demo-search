package com.demo.search.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Size {
    @Id
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    @Column(name="back_soon")
    private boolean backSoon;
    private boolean special;
}
