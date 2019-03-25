package com.yapstone.directory.config;

import com.yapstone.directory.model.Contact;
import com.yapstone.directory.repository.ContactRepository;
import com.yapstone.directory.utils.AddressBookConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AddressBookConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AddressBookConstants.CONTROLLER_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Initialize the DB with some contacts.
     *
     * @param repository
     * @return
     */
    @Bean
    public CommandLineRunner initDatabase(ContactRepository repository) {
        Contact contact1 = new Contact();
        contact1.setFirstName("James");
        contact1.setLastName("Miller");
        contact1.setContactNumber("11111111");
        contact1.setAddress("California");
        contact1.setEmailId("james@outlook.com");
        repository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Mark");
        contact2.setLastName("Wee");
        contact2.setContactNumber("22222222");
        contact2.setAddress("New York");
        contact2.setEmailId("mark@outlook.com");
        repository.save(contact2);

        Contact contact3 = new Contact();
        contact3.setFirstName("Jason");
        contact3.setLastName("Anthony");
        contact3.setContactNumber("33333333");
        contact3.setAddress("Atlanta");
        contact3.setEmailId("jason@outlook.com");
        repository.save(contact3);

        Contact contact4 = new Contact();
        contact4.setFirstName("Pamela");
        contact4.setLastName("Leo");
        contact4.setContactNumber("44444444");
        contact4.setAddress("London");
        contact4.setEmailId("pamela@outlook.com");
        repository.save(contact4);

        Contact contact5 = new Contact();
        contact5.setFirstName("Richard");
        contact5.setLastName("Tan");
        contact5.setContactNumber("55555555");
        contact5.setAddress("Texas");
        contact5.setEmailId("richard@outlook.com");
        repository.save(contact5);

        return obj -> {
        };
    }

}
