package com.demo.search.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Size {
    @Id
    private Integer id;
    @ManyToOne
    private Product product;
    @Column(name="back_soon")
    private Boolean backSoon;
    private Boolean special;
    @OneToMany(mappedBy = "size")
    @Builder.Default
    private List<Stock> stocks = List.of();
    @CreationTimestamp
    private LocalDateTime dateCreation;
    @UpdateTimestamp
    private LocalDateTime dateModification;

    public boolean isVisible(){
        return this.getBackSoon() || this.hasPositiveStock();
    }

    public Boolean hasPositiveStock(){
        return this.stocks.stream().anyMatch(stock -> stock.getQuantity() > 0);
    }
}
