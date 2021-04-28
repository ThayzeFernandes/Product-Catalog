package com.productCatalog.repositories.specifications;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.productCatalog.models.Product;

public class ProductSpecification {


    public static Specification<Product> Productfilter(String q, BigDecimal min_price, BigDecimal max_price) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(q)){
            	Predicate namePredicate = criteriaBuilder.equal(root.get("name") , q);
                Predicate descriptionPredicate = criteriaBuilder.equal(root.get("description") , q);
            	predicates.add(criteriaBuilder.or(namePredicate, descriptionPredicate));
            }
            if(!ObjectUtils.isEmpty(max_price)) {
            	predicates.add(criteriaBuilder.le(root.get("price") , max_price));
            }
            if(!ObjectUtils.isEmpty(min_price)) {
            	predicates.add(criteriaBuilder.ge(root.get("price") , min_price));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
