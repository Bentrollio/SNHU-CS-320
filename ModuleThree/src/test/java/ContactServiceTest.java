/* Alex Baires - SNHU CS-320
 * ContactServiceTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The contact service shall be able to add contacts with a unique ID.
 * The contact service shall be able to delete contacts per contact ID.
 * The contact service shall be able to update contact fields per contact ID.
 * The following fields are updatable:
 * firstName, lastName, Number, Address
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.snhu.alexanderbaires.ContactService;

public class ContactServiceTest {

    private static ContactService contactService;

    @BeforeEach
    public void setupContactService() {
        contactService = new ContactService();
    }
    @Test
    @DisplayName("Should Add a Contact")
    public void testAddContact() {
        contactService.addContact("Ivan", "Baires",
                "4078736418", "308 lake hills ln");
        assertFalse(contactService.getContactList().isEmpty());
        assertEquals(1, contactService.getContactList().size());
    }

    @Test
    @DisplayName("New contact should have a unique ID generated")
    public void testWhetherContactHasID() {
        contactService.addContact("Ivan", "Baires",
                "4078736418", "308 lake hills ln");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        assertFalse(contactService.retrieveContact(uniqueIDTest).getContactID().isEmpty());
    }

    @Test
    @DisplayName("Testing whether generated ID is Unique")
    public void testUniqueContactID() {
        contactService.addContact("Aaron", "Judge", "7188675309",
                "Yankee Stadium, NYC");
        String firstUniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.addContact("Luke", "Skywalker", "8009992345", "Imperial City, Coruscant");
        String secondUniqueIDTest = contactService.getContactList().keySet().iterator().next();
        assertNotEquals(firstUniqueIDTest, secondUniqueIDTest);
    }

    @Test
    @DisplayName("Should Avoid Duplicate Contacts")
    public void testAvoidDuplicateContacts() {
        contactService.addContact("Aaron", "Judge", "7188675309",
        "Yankee Stadium, NYC");
        Assertions.assertThrows(RuntimeException.class, () -> {contactService.addContact("Aaron", "Judge", "7188675309",
                "Yankee Stadium, NYC");
        });
    }

    @Test
    @DisplayName("Should Delete a Contact")
    public void testDeleteContact() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.deleteContact(uniqueIDTest);
        assertTrue(contactService.getContactList().isEmpty());
        assertEquals(0, contactService.getContactList().size());
    }

    @Test
    @DisplayName("Should Not Create a Contact When First Name is NULL")
    public void testRunTimeExceptionNullFirstName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact(null, "Baires", "8631234567", "123 Sesame Street");
        });
    }

    @Test
    @DisplayName("Should Not Create a Contact When Last Name is NULL")
    public void testRunTimeExceptionNullLastName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", null, "8631234567", "123 Sesame Street");
        });
    }

    @Test
    @DisplayName("Should Not Create a Contact with a NULL phone number")
    public void testRunTimeExceptionNullPhoneNumber() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", null, "123 Sesame Street");
        });
    }

    @Test
    @DisplayName("Should Not Create a Contact with a NULL address")
    public void testRunTimeExceptionNullAddress() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", "8631234567", null);
        });
    }

    @Test
    @DisplayName("Contact Service Shall Update First Name")
    public void testUpdateFirstName() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.updateFirstName(uniqueIDTest, "Vanya");
        assertEquals("Vanya", contactService.retrieveContact(uniqueIDTest).getFirstName());
    }

    @Test
    @DisplayName("Contact Service Shall Update Last Name")
    public void testUpdateLastName() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.updateLastName(uniqueIDTest, "The Dog");
        assertEquals("The Dog", contactService.retrieveContact(uniqueIDTest).getLastName());
    }

    @Test
    @DisplayName("Contact Service Shall Update Phone Number")
    public void testUpdatePhoneNumber() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.updatePhoneNumber(uniqueIDTest, "5888800000");
        assertEquals("5888800000", contactService.retrieveContact(uniqueIDTest).getPhoneNumber());
    }

    @Test
    @DisplayName("Contact Service Shall Update Mailing Address")
    public void testUpdateMailingAddress() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.updateMailingAddress(uniqueIDTest, "1 Jumbo Ave");
        assertEquals("1 Jumbo Ave", contactService.getContactList().get(uniqueIDTest).getMailingAddress());
    }
}
