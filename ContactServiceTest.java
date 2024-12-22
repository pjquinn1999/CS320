package cs320milestone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    
    private ContactService contactService;
    
    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }
    
    @Test
    void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("1234567890"));
    }
    
    @Test
    void testAddDuplicateContact() {
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Oak St");
        
        contactService.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(contact2));
    }
    
    @Test
    void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }
    
    @Test
    void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> 
            contactService.deleteContact("1234567890"));
    }
    
    @Test
    void testUpdateContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        
        contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Oak St");
        
        Contact updatedContact = contactService.getContact("1234567890");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("0987654321", updatedContact.getPhone());
        assertEquals("456 Oak St", updatedContact.getAddress());
    }
    
    @Test
    void testUpdateNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> 
            contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Oak St"));
    }
}