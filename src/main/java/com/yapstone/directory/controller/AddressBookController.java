package com.yapstone.directory.controller;

import com.yapstone.directory.model.Contact;
import com.yapstone.directory.services.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.yapstone.directory.utils.AddressBookConstants.*;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/contacts")
    public List<Contact> findAllContactsBySpecs(
            @RequestParam(value = CONTACT_NUMBER, required = false) String contactNumber,
            @RequestParam(value = EMAIL_ID, required = false) String emailId,
            @RequestParam(value = ADDRESS, required = false) String address,
            @RequestParam(value = FIRST_NAME, required = false) String firstName,
            @RequestParam(value = LAST_NAME, required = false) String lastName
    ) {

        Contact filter = new Contact();
        filter.setAddress(address);
        filter.setEmailId(emailId);
        filter.setContactNumber(contactNumber);
        filter.setLastName(lastName);
        filter.setFirstName(firstName);
        return addressBookService.allContacts(filter);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> findContact(@PathVariable Long id) {
        Contact contact = addressBookService.findContactById(id);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact savedContact = addressBookService.createContact(contact);
        return ResponseEntity.status(HttpStatus.OK).body(savedContact);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        Contact modifiedContact = addressBookService.updateContactById(contact, id);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedContact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Map<String, Object>> deleteContact(@PathVariable Long id) {
        addressBookService.deleteContactById(id);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", id);
        result.put("deleted", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
