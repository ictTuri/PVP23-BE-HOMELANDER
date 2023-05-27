package com.codeonmars.propertiesms.model.property;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "property_address", schema = "properties_ms")
public class PropertyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "zone")
    private String zone;
    @Column(name = "address")
    private String address;
    @Column(name = "floor_number")
    private Integer floorNumber;
    @Column(name = "door_number")
    private Integer doorNumber;
    @Column(name = "street")
    private String street;
}
