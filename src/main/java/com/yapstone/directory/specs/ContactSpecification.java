package com.yapstone.directory.specs;

import com.yapstone.directory.model.Contact;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.yapstone.directory.utils.AddressBookConstants.*;

public class ContactSpecification implements Specification<Contact> {

    private Contact filter;

    public ContactSpecification(Contact filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate predicate = cb.disjunction();

        if (StringUtils.hasLength(filter.getFirstName())) {
            predicate.getExpressions().add(cb.like(cb.upper(root.get(FIRST_NAME)), "%" + filter.getFirstName().toUpperCase() + "%"));
        }

        if (StringUtils.hasLength(filter.getLastName())) {
            predicate.getExpressions().add(cb.like(cb.upper(root.get(LAST_NAME)), "%" + filter.getLastName().toUpperCase() + "%"));
        }

        if (StringUtils.hasLength(filter.getAddress())) {
            predicate.getExpressions().add(cb.like(cb.upper(root.get(ADDRESS)), "%" + filter.getAddress().toUpperCase() + "%"));
        }

        if (StringUtils.hasLength(filter.getEmailId())) {
            predicate.getExpressions().add(cb.like(cb.upper(root.get(EMAIL_ID)), "%" + filter.getEmailId().toUpperCase() + "%"));
        }

        if (StringUtils.hasLength(filter.getContactNumber())) {
            predicate.getExpressions().add(cb.like(cb.upper(root.get(CONTACT_NUMBER)), "%" + filter.getContactNumber().toUpperCase() + "%"));
        }

        return predicate.getExpressions().size() > 0 ? predicate : null;
    }

}
