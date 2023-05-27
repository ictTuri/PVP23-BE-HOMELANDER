package com.codeonmars.usersms.model.user;

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

import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "users_ms")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "superuser")
    private Boolean superuser;
    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tenants_slots", referencedColumnName = "id")
    private TenantsSlots tenantsSlots;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "properties_slots", referencedColumnName = "id")
    private PropertiesSlots propertiesSlots;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    /* SUPPORTING METHODS*/

    public void setTenantsSlots(TenantsSlots tenantsSlots) {
        this.tenantsSlots = Objects.requireNonNullElseGet(tenantsSlots, () -> new TenantsSlots(null, 1, 0));
    }

    public void setPropertiesSlots(PropertiesSlots propertiesSlots) {
        this.propertiesSlots = Objects.requireNonNullElseGet(propertiesSlots, () -> new PropertiesSlots(null, 1, 0));
    }

    public UserEntity initializeData() {
        this.setSuperuser(Boolean.FALSE);
        this.setPropertiesSlots(null);
        this.setTenantsSlots(null);
        return this;
    }

    public UserEntity increasePropertySlot() {
        this.getPropertiesSlots().increaseSlot();
        return this;
    }

    public UserEntity decreasePropertySlot() {
        this.getPropertiesSlots().decreaseSlot();
        return this;
    }
}
