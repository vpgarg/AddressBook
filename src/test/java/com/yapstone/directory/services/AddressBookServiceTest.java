package com.yapstone.directory.services;

import com.yapstone.directory.model.Contact;
import com.yapstone.directory.repository.ContactRepository;
import com.yapstone.directory.specs.ContactSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

@RunWith(SpringRunner.class)
public class AddressBookServiceTest {

    @TestConfiguration
    static class AddressBookServiceTestContextConfiguration {

        @Bean
        public AddressBookService addressBookService() {
            return new AddressBookService();
        }
    }

    @Autowired
    private AddressBookService addressBookService;

    @MockBean
    private ContactRepository contactRepository;

    @Test
    public void testCreateContact() {
        Contact contact = getTestContact();
        Mockito.when(contactRepository.save(Mockito.any())).thenReturn(contact);

        Contact savedContact = addressBookService.createContact(contact);
        Assert.assertNotNull(savedContact);
        Assert.assertEquals(new Long(1), savedContact.getId());
        Assert.assertEquals("TestFirstName", savedContact.getFirstName());
        Assert.assertEquals("TestLastName", savedContact.getLastName());
        Assert.assertEquals("00000000", savedContact.getContactNumber());
        Assert.assertEquals("test@test.com", savedContact.getEmailId());
        Assert.assertEquals("TestAddress", savedContact.getAddress());
    }

    @Test
    public void testFindContactById() {
        Mockito.when(contactRepository.findById(anyLong())).thenReturn(Optional.of(getTestContact()));

        Contact contact = addressBookService.findContactById(1L);
        Assert.assertNotNull(contact);
    }

    @Test
    public void testUpdateContactById() {
        Contact contact = getTestContact();
        contact.setLastName("UpdatedLastName");
        Mockito.when(contactRepository.save(Mockito.any())).thenReturn(contact);
        Mockito.when(contactRepository.findById(anyLong())).thenReturn(Optional.of(getTestContact()));

        Contact modifiedContact = addressBookService.updateContactById(getTestContact(), 1L);
        Assert.assertNotNull(modifiedContact);
        Assert.assertEquals("UpdatedLastName", modifiedContact.getLastName());
    }

    @Test
    public void testAllContacts() {
        Contact testContact = getTestContact();
        List<Contact> contactList = new ArrayList<>();
        contactList.add(testContact);
        Mockito.when(contactRepository.findAll(new ContactSpecification(any()))).thenReturn(contactList);

        Contact filter = new Contact();
        List<Contact> contact = addressBookService.allContacts(filter);
        Assert.assertNotNull(contact);
        Assert.assertEquals(1, contact.size());

    }

    private Contact getTestContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("TestFirstName");
        contact.setLastName("TestLastName");
        contact.setContactNumber("00000000");
        contact.setEmailId("test@test.com");
        contact.setAddress("TestAddress");
        return contact;
    }
}
