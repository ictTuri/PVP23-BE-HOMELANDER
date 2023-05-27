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

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "additional_attributes", schema = "properties_ms")
public class AdditionalAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "furnished")
    private Boolean furnished;
    @Column(name = "typology")
    private String typology;
    @Column(name = "type")
    private String type;
    @Column(name = "appliances")
    private Set<String> appliances;
    @Column(name = "images")
    private Set<UUID> images;
}
