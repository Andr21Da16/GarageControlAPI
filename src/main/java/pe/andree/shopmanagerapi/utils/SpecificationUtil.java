package pe.andree.shopmanagerapi.utils;

import org.springframework.data.jpa.domain.Specification;


public final class SpecificationUtil {

    private SpecificationUtil() {}

    public static <T> Specification<T> stringLike(
            String field,
            String value
    ) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return null;
            return cb.like(
                    cb.lower(root.get(field)),
                    "%" + value.toLowerCase() + "%"
            );
        };
    }

    public static <T> Specification<T> priceBetween(
            String field,
            Integer min,
            Integer max
    ) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(root.get(field), max);
            if (max == null) return cb.greaterThanOrEqualTo(root.get(field), min);
            return cb.between(root.get(field), min, max);
        };
    }

    public static <T> Specification<T> hasExactPrice(
            String field,
            Integer price
    ) {
        return (root, query, cb) ->
                price == null ? null : cb.equal(root.get(field), price);
    }
}
