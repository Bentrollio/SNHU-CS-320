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

    // Updates and retrieves the customer's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    // Updates and retrieves the customer's phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Updates and retrieves the customer's mailing address
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    // Prints object for verification purposes
    public String toString() {
        return contactID + "\n" + firstName + " " + lastName + "\n" +
                phoneNumber + "\n" + mailingAddress;
    }



}
