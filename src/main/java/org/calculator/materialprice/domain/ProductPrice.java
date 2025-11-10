package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    private String dimensions;
    private String comment;
    private String steelGrade;
    private String productName;
    private String standard;
    @Column(length = 512)
    private String parameter;
    private String weight;
    private String numberOfPieces;
    private String quantityWeight;
    private String unitOfMeasurement;
    private String price;
    private String note;
    private String linkPhoto;

}


