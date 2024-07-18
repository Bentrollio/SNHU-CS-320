/*
 * Contact.java
 *
 * Contains Contact Object information.
 *
 * Alex Baires
 * SNHU CS-320
 * 7-21-24
 */

package org.snhu.alexanderbaires;

public class Contact {

    String contactID;
    String firstName;
    String lastName;
    String phoneNumber;
    String mailingAddress;

    public Contact(String contactID, String firstName, String lastName, String phoneNumber, String mailingAddress) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mailingAddress = mailingAddress;
    }

    // Retrieves contactID
    public String getContactID() {
        return contactID;
    }

    // Updates and retrieves the customer's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void verifyFirstName() {
        if(this.firstName == null || this.firstName.isBlank()) {
            throw new RuntimeException("First name is null or empty");
        }
    }

    // Updates and retrieves the customer's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void verifyLastName() {
        if(this.lastName == null || this.lastName.isBlank()) {
            throw new RuntimeException("Last name is null or empty");
        }
    }

    // Updates and retrieves the customer's phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void verifyPhoneNumber() {
        if(this.phoneNumber == null || this.phoneNumber.isBlank()) {
            throw new RuntimeException("Phone number is null or empty");
        }

        if (this.phoneNumber.length() != 10) {
            throw new RuntimeException("Phone number length must be 10 digits");
        }
    }

    // Updates and retrieves the customer's mailing address
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void verifyMailingAddress() {
        if(this.mailingAddress == null || this.mailingAddress.isBlank()) {
            throw new RuntimeException("Mailing address is null or empty");
        }
    }

    // Prints object for verification purposes
    public String toString() {
        return contactID + "\n" + firstName + " " + lastName + "\n" +
                phoneNumber + "\n" + mailingAddress;
    }
}
