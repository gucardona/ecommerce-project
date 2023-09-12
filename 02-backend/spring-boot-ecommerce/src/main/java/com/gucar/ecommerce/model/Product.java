package com.gucar.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "product" )
@Getter
@Setter
public class Product {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "category_id", nullable = false )
    private ProductCategory category;

    @Column( name = "sku" )
    private String sku;

    @Column( name = "name" )
    private String name;

    @Column( name = "description" )
    private String description;

    @Column( name = "unit_price" )
    private BigDecimal unitPrice;

    @Column( name = "image_url" )
    private String imageURL;

    @Column( name = "active" )
    private Byte active;

    @Column( name = "units_in_stock" )
    private Integer unitsInStock;

    @Column( name = "date_created" )
    @CreationTimestamp
    private Date dateCreated;

    @Column( name = "last_updated" )
    @UpdateTimestamp
    private Date lastUpdated;
}



