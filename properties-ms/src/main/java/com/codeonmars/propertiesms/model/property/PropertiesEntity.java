package com.codeonmars.propertiesms.model.property;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "properties", schema = "properties_ms")
public class PropertiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "long_description")
    private String longDescription;
    @Column(name = "size")
    private Double size;
    @Column(name = "unit")
    private String unit;
    @Column(name = "for_sale")
    private Boolean forSale;
    @Column(name = "for_rent")
    private Boolean forRent;
    @Column(name = "price")
    private Double price;
    @Column(name = "year")
    private Integer year;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "additional_attributes", referencedColumnName = "id")
    private AdditionalAttributes additionalAttributes;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "property_address", referencedColumnName = "id")
    private PropertyAddress propertyAddress;

    @Column(name = "rented")
    private Boolean rented;
    @Column(name = "sold")
    private Boolean sold;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private OwnerEntity owner;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    private TenantEntity tenant;

    @Column(name = "creation_date")
    private Instant creationDate;
    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    public PropertiesEntity initializeAddressAndInfo() {
        this.additionalAttributes = new AdditionalAttributes();
        this.propertyAddress = new PropertyAddress();
        return this;
    }
}
