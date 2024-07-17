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
import java.util.ArrayList;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ContactService {

    ArrayList<Contact> contactList = new ArrayList<Contact>();

    public void addContact(String firstName, String lastName, String phone, String address) {
        String uniqueID = getHash(firstName, lastName);
        Contact contact = new Contact(uniqueID, firstName, lastName, phone, address);
        // VerifyContact
        // CheckifContactExists
        contactList.add(contact);

    }

    public void verifyContact() {

    }

    /**
     * Adapted from the below stack overflow post:
     *https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
     *
     * @return String randomKey
     */

    protected String getSaltString() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
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

        for (int i = 0; i < bytes.length; ++i) {
            hexString.append(String.format("%02x", bytes[i]));
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
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }
}
