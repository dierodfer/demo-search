package com.demo.search.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {
    @Id
    private Integer id;
    private Integer sequence;
    @OneToMany(mappedBy = "product")
    private List<Size> sizes;
    @CreationTimestamp
    private LocalDateTime dateCreation;
    @UpdateTimestamp
    private LocalDateTime dateModification;

    public Boolean isVisible(){
        if(sizes.stream().noneMatch(Size::getSpecial)){ //First & zero condition
            return sizes.stream().anyMatch(Size::isVisible);
        }else{ //Second Condition
            boolean existNoSpecialStock = sizes.stream().anyMatch(size -> !size.getSpecial() && size.isVisible());
            boolean existSpecialStock = sizes.stream().anyMatch(size -> size.getSpecial() && size.isVisible());
            return existSpecialStock && existNoSpecialStock;
        }
    }
}
