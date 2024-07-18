/*
 * ContactService.java
 *
 * Creates and stores individual contacts with their details.
 *
 * Alex Baires
 * SNHU CS-320
 * 7-21-24
 */

package org.snhu.alexanderbaires;
import java.util.Collection;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ContactService {

    HashMap<String, Contact> contactList = new HashMap<>();

    public void addContact(String firstName, String lastName, String phone, String address) {
        String uniqueID = getHash(firstName, lastName);
        Contact contact = new Contact(uniqueID, firstName, lastName, phone, address);
        verifyContact(contact);
        checkExistingContacts(contact.getContactID());
        contactList.put(uniqueID, contact);
    }

    public void deleteContact(String uniqueID) {
        contactList.remove(uniqueID);
    }

    public void checkExistingContacts(String key) {
        if(contactList.containsKey(key)) {
            throw new RuntimeException("Contact already exists");
        }
    }

    public void verifyContact(Contact contact) {
        contact.verifyFirstName();
        contact.verifyLastName();
        contact.verifyMailingAddress();
        contact.verifyPhoneNumber();
    }

    public HashMap<String, Contact> getContactList() {
        return contactList;
    }

    public Contact retrieveContact(String uniqueID) {
        return contactList.get(uniqueID);
    }

    public void updateFirstName(String uniqueID, String firstName) {
        Contact temp = contactList.get(uniqueID);
        temp.setFirstName(firstName);
    }

    public void updateLastName(String uniqueID, String lastName) {
        Contact temp = contactList.get(uniqueID);
        temp.setLastName(lastName);
    }

    public void updateMailingAddress(String uniqueID, String mailingAddress) {
        Contact temp = contactList.get(uniqueID);
        temp.setMailingAddress(mailingAddress);
    }

    public void updatePhoneNumber(String uniqueID, String phoneNumber) {
        Contact temp = contactList.get(uniqueID);
        temp.setPhoneNumber(phoneNumber);
    }

    /**
     * bytesToHex
     *
     * @param bytes - A byte array that will be converted into a HexString
     * @return hex - A string containing the HexString converted message as output.
     */
    protected String bytesToHex(byte[] bytes) {
        String hex;

        // Converts byte array into HexString object
        StringBuilder hexString = new StringBuilder();

        for (byte aByte : bytes) {
            hexString.append(String.format("%02x", aByte));
        }
        hex = hexString.toString();
        return hex;
    }

    protected String getHash(String firstName, String lastName) {
        String combinedString = firstName + lastName;
        String checkSum = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(combinedString.getBytes());
            byte[] digest = md.digest();

            checkSum = bytesToHex(digest);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return checkSum.substring(0, 10);
    }

    // FIXME: This function, while not in the parameters, is used for verification. Can be deleted in final
    public void printContactList() {
        for (HashMap.Entry entry : contactList.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
