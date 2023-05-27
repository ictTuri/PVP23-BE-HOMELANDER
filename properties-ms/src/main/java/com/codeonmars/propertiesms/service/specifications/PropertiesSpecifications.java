package com.codeonmars.propertiesms.service.specifications;

import com.codeonmars.propertiesms.model.property.OwnerEntity;
import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.PropertyAddress;
import com.codeonmars.propertiesms.model.property.TenantEntity;
import com.codeonmars.propertiesms.model.property.dto.PriceRange;
import com.codeonmars.propertiesms.model.property.dto.SizeRange;
import com.codeonmars.propertiesms.model.property.dto.YearRange;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class PropertiesSpecifications {

    /* property address entity is joined in queries*/
    private static final String PROPERTY_ADDRESS = "propertyAddress";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String ZONE = "zone";

    /* additional attributes entity is joined in queries*/
    private static final String ADDITIONAL_ATTRIBUTES = "additionalAttributes";
    private static final String TYPOLOGY = "typology";
    private static final String FURNISHED = "furnished";

    /* properties entity attributes*/
    private static final String PRICE = "price";
    private static final String SOLD = "sold";
    private static final String RENTED = "rented";
    private static final String FOR_SALE = "forSale";
    private static final String FOR_RENT = "forRent";
    private static final String SIZE = "size";
    private static final String YEAR = "year";

    private static final String OWNER = "owner";
    private static final String TENANT = "tenant";
    private static final String USERNAME = "username";
    private static final String ACTIVE = "active";



    private PropertiesSpecifications() {
    }

    public static Specification<PropertiesEntity> isSold(Boolean sold) {
        if (sold == null) {
            return noOp();
        }
        if (sold) {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(SOLD))));
        } else {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(SOLD))));
        }
    }

    public static Specification<PropertiesEntity> isRented(Boolean rented) {
        if (rented == null) {
            return noOp();
        }
        if (rented) {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(RENTED))));
        } else {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(RENTED))));
        }
    }

    public static Specification<PropertiesEntity> isForSale(Boolean forSale) {
        if (forSale == null) {
            return noOp();
        }
        if (forSale) {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FOR_SALE))));
        } else {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(FOR_SALE))));
        }
    }

    public static Specification<PropertiesEntity> isForRent(Boolean forRent) {
        if (forRent == null) {
            return noOp();
        }
        if (forRent) {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FOR_RENT))));
        } else {
            return (((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(FOR_RENT))));
        }
    }

    public static Specification<PropertiesEntity> isInCountry(String country) {
        if (country == null || country.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<PropertyAddress, PropertiesEntity> joinParent = root.join(PROPERTY_ADDRESS);
            return criteriaBuilder.like(criteriaBuilder.lower(joinParent.get(COUNTRY)), getLikeOperatorJollyStartEndToLoweCase(country));
        });
    }

    public static Specification<PropertiesEntity> isInCity(String city) {
        if (city == null || city.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<PropertyAddress, PropertiesEntity> joinParent = root.join(PROPERTY_ADDRESS);
            return criteriaBuilder.like(criteriaBuilder.lower(joinParent.get(CITY)), getLikeOperatorJollyStartEndToLoweCase(city));
        });
    }

    public static Specification<PropertiesEntity> isInZone(String zone) {
        if (zone == null || zone.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<PropertyAddress, PropertiesEntity> joinParent = root.join(PROPERTY_ADDRESS);
            return criteriaBuilder.like(criteriaBuilder.lower(joinParent.get(ZONE)), getLikeOperatorJollyStartEndToLoweCase(zone));
        });
    }

    public static Specification<PropertiesEntity> isOfTopology(String typology) {
        if (typology == null || typology.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<PropertyAddress, PropertiesEntity> joinParent = root.join(ADDITIONAL_ATTRIBUTES);
            return criteriaBuilder.like(criteriaBuilder.lower(joinParent.get(TYPOLOGY)), getLikeOperatorJollyStartEndToLoweCase(typology));
        });
    }

    public static Specification<PropertiesEntity> isFurnished(Boolean furnished) {
        if (furnished == null) {
            return noOp();
        }
        if (furnished) {
            return ((root, query, criteriaBuilder) -> {
                Join<PropertyAddress, PropertiesEntity> joinParent = root.join(ADDITIONAL_ATTRIBUTES);
                return criteriaBuilder.isTrue(joinParent.get(FURNISHED));
            });
        } else {
            return ((root, query, criteriaBuilder) -> {
                Join<PropertyAddress, PropertiesEntity> joinParent = root.join(ADDITIONAL_ATTRIBUTES);
                return criteriaBuilder.isFalse(joinParent.get(FURNISHED));
            });
        }
    }

    public static Specification<PropertiesEntity> isInPriceRange(PriceRange priceRange) {
        if (priceRange == null || (priceRange.getFrom() == null && priceRange.getTo() == null)) {
            return noOp();
        } else if (priceRange.getFrom() != null && priceRange.getTo() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), priceRange.getFrom());
        } else if (priceRange.getTo() != null && priceRange.getFrom() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), priceRange.getTo());
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(PRICE), priceRange.getFrom(), priceRange.getTo());
        }
    }

    public static Specification<PropertiesEntity> isInSizeRange(SizeRange sizeRange) {
        if (sizeRange == null || (sizeRange.getFrom() == null && sizeRange.getTo() == null)) {
            return noOp();
        } else if (sizeRange.getFrom() != null && sizeRange.getTo() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(SIZE), sizeRange.getFrom());
        } else if (sizeRange.getTo() != null && sizeRange.getFrom() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(SIZE), sizeRange.getTo());
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(SIZE), sizeRange.getFrom(), sizeRange.getTo());
        }
    }

    public static Specification<PropertiesEntity> isInYearRange(YearRange yearRange) {
        if (yearRange == null || (yearRange.getFrom() == null && yearRange.getTo() == null)) {
            return noOp();
        } else if (yearRange.getFrom() != null && yearRange.getTo() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(YEAR), yearRange.getFrom());
        } else if (yearRange.getTo() != null && yearRange.getFrom() == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(YEAR), yearRange.getTo());
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(YEAR), yearRange.getFrom(), yearRange.getTo());
        }
    }

    public static Specification<PropertiesEntity> hasOwner(String owner){
        if (owner == null || owner.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<OwnerEntity, PropertiesEntity> joinParent = root.join(OWNER);
            return criteriaBuilder.equal(joinParent.get(USERNAME), owner);
        });
    }

    public static Specification<PropertiesEntity> hasTenants(String tenant){
        if (tenant == null || tenant.isBlank()) {
            return noOp();
        }
        return ((root, query, criteriaBuilder) -> {
            Join<TenantEntity, PropertiesEntity> joinParent = root.join(TENANT);
            return criteriaBuilder.equal(joinParent.get(USERNAME), tenant);
        });
    }

    static String getLikeOperatorJollyStartEndToLoweCase(String likeField) {
        String likeOperator = "%" + likeField + "%";
        return likeOperator.toLowerCase();
    }

    static Specification<PropertiesEntity> noOp() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }
}
