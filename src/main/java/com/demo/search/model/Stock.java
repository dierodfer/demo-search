package com.demo.search.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    private Size size;
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime dateCreation;
    @UpdateTimestamp
    private LocalDateTime dateModification;
}
