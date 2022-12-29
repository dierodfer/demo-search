package com.demo.search.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Size {
    @Id
    private Integer id;
    @ManyToOne
    private Product product;
    @Column(name="back_soon")
    private boolean backSoon;
    private boolean special;
}
