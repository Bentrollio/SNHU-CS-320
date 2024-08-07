/*
 * PROJECT ONE
 * AppointmentService.java
 *
 * Creates and stores individual appointments with their details.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-4-24
 */
package org.alexbaires.main;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Date;

public class AppointmentService {
    HashMap<String, Appointment> appointmentList = new HashMap<>();

    public void addAppointment(Date appointmentDate, String appointmentDescription) {
        String appointmentID = getHash(appointmentDate.toString());
        Appointment appointment = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        checkDuplicateAppointment(appointment.getAppointmentID());
        appointmentList.put(appointmentID, appointment);
    }

    public void deleteAppointment(String appointmentID) {
        appointmentList.remove(appointmentID);
    }

    public void checkDuplicateAppointment(String appointmentID) {
        if(appointmentList.containsKey(appointmentID)) {
            throw new RuntimeException("Appointment already exists");
        }
    }

    // Retrieves the appointment list
    public HashMap<String, Appointment> getAppointmentList() {
        return appointmentList;
    }

    public Appointment getAppointment(String appointmentID) { return appointmentList.get(appointmentID); }

    public void updateAppointmentDate(String appointmentID, Date newAppointmentDate) {
        Appointment appointment = getAppointment(appointmentID);
        appointment.setAppointmentDate(newAppointmentDate);
    }

    public void updateAppointmentDescription(String appointmentID, String newAppointmentDescription) {
        Appointment appointment = getAppointment(appointmentID);
        appointment.setAppointmentDescription(newAppointmentDescription);
    }

    /**
     * bytesToHex
     *
     * @param bytes - A byte array that will be converted into a HexString
     * @return hex - A string containing the HexString converted message as output.
     */
    protected String bytesToHex(byte[] bytes) {
        String hex;

        // Converts bytes array into HexString object
        StringBuilder hexString = new StringBuilder();

        for (byte aByte : bytes) {
            hexString.append(String.format("%02X", aByte));
        }
        hex = hexString.toString();
        return hex;
    }

    /**
     * getHash
     *
     * Adapted from:
     * https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm#:~:text=You%20can%20generate%20the%20message,digest%20using%20the%20digest%20method.
     *
     * @param input - String to be converted into a SHA-256 checksum. Function only retrieves the first 10 digits.
     * @return checkSum - A string containing the HexString converted message as output.
     */
    protected String getHash(String input) {
        String checksum = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            checksum = bytesToHex(digest);

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return checksum.substring(0, 10);
    }
}
