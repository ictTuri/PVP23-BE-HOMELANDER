package com.codeonmars.usersms.model.user;

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
@Table(name = "properties_slots", schema = "users_ms")
public class PropertiesSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "available")
    private Integer available;
    @Column(name = "used")
    private Integer used;

    public PropertiesSlots increaseSlot() {
        if (available > used) {
            used++;
        }
        return this;
    }

    public PropertiesSlots decreaseSlot() {
        if (used > 0) {
            used--;
        }
        return this;
    }
}
