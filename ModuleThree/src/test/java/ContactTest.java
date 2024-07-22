/* Alex Baires - SNHU CS-320
 * ContactTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The contact object shall have a required unique contact ID string that cannot be longer than 10 characters.
 * The contact ID shall not be null and shall not be updatable.
 * The contact object shall have a required firstName String field that cannot be longer than 10 characters.
 * The firstName field shall not be null.
 * The contact object shall have a required lastName String field that cannot be longer than 10 characters.
 * The lastName field shall not be null.
 * The contact object shall have a required phone String field that must be exactly 10 digits.
 * The phone field shall not be null.
 * The contact object shall have a required address field that must be no longer than 30 characters.
 * The address field shall not be null.
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import org.snhu.alexanderbaires.Contact;

public class ContactTest {
    private static Contact contact;

    @Test
    @DisplayName("Unique Contact ID String should not be longer than 10 characters")
    public void testUniqueContactID() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("12345678901",
                    "Ivan", "Baires", "8008675309", "123 Sesame Street");
        });
    }

    @Test
    @DisplayName("Unique Contact ID should not be NULL")
    public void testUniqueContactIDNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact(null,
                    "Ivan", "Baires", "8008675309", "123 Sesame Street");
        });
    }

    @Test
    @DisplayName("First Name String Field cannot be longer than 10 characters")
    public void testFirstName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bartholomew", "Simpson", "8005557246", "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("First Name cannot be NULL")
    public void testFirstNameNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    null, "Simpson", "8005557246", "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("Last Name String Field cannot be longer than 10 characters")
    public void testLastName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bart", "Simpsonsons", "8005557246", "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("Last Name cannot be NULL")
    public void testLastNameNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bart", null, "8005557246", "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("Phone Number String Field must have exactly 10 characters")
    public void testPhoneNumber() {
        contact = new Contact("1",
                "Bart", "Simpson", "8005557246", "742 Evergreen Terrace");

        Assertions.assertThrows(RuntimeException.class, () -> { contact.setPhoneNumber("80055572461234");
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact.setPhoneNumber("1");
        });
    }

    @Test
    @DisplayName("Phone Number cannot be NULL")
    public void testPhoneNumberNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bart", "Simpson", null, "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("Phone Number can only have Numbers")
    public void testPhoneNumberChars() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bart", "Simpson", "800555BOOT", "742 Evergreen Terrace");
        });
    }

    @Test
    @DisplayName("Address String Field must have no more than 30 characters")
    public void testAddress() {
        Assertions.assertThrows(RuntimeException.class, () -> {contact = new Contact("1",
                "Bart", "Simpson", "8005557246", "742 Evergreen Terrace Springfield, Unknown, USA");
        });
    }

    @Test
    @DisplayName("Address field cannot be NULL")
    public void testAddressNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contact = new Contact("1",
                    "Bart", "Simpson", "8005557246", null);
        });
    }


}