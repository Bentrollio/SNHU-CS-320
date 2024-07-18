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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.snhu.alexanderbaires.Contact;

public class ContactTest {
    private Contact contact;



}