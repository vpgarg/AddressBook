package com.yapstone.directory.services;

import com.yapstone.directory.exception.ContactNotFoundException;
import com.yapstone.directory.model.Contact;
import com.yapstone.directory.repository.ContactRepository;
import com.yapstone.directory.specs.ContactSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class AddressBookService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> allContacts(Contact filter) {
        Specification<Contact> spec = new ContactSpecification(filter);
        return contactRepository.findAll(spec);
    }

    public Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
    }

    public void deleteContactById(Long id) {
        try {
            contactRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ContactNotFoundException(id);
        }
    }

    public Contact updateContactById(Contact contact, Long id) {

        return contactRepository.findById(id)
                .map(c -> {
                    if (StringUtils.hasLength(contact.getFirstName())) {
                        c.setFirstName(contact.getFirstName());
                    }

                    if (StringUtils.hasLength(contact.getLastName())) {
                        c.setLastName(contact.getLastName());
                    }

                    if (StringUtils.hasLength(contact.getContactNumber())) {
                        c.setContactNumber(contact.getContactNumber());
                    }

                    if (StringUtils.hasLength(contact.getEmailId())) {
                        c.setEmailId(contact.getEmailId());
                    }

                    if (StringUtils.hasLength(contact.getAddress())) {
                        c.setAddress(contact.getAddress());
                    }

                    return contactRepository.save(c);

                })
                .orElseGet(() -> {
                    throw new ContactNotFoundException(id);
                });

    }
}
