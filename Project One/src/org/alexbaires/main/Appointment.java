/*
 * PROJECT ONE
 * Appointment.java
 *
 * Contains Appointment Object information.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-4-24
 */

package org.alexbaires.main;

import java.util.Date;
import java.util.Calendar;

public class Appointment {
    final String appointmentID;
    Date appointmentDate = new Date();
    String appointmentDescription;

    public Appointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentDescription = appointmentDescription;

        verifyAppointmentID();
        verifyAppointmentDate();
        verifyAppointmentDescription();
    }

    public void setAppointmentID(String appointmentID) {
        throw new UnsupportedOperationException("Task ID Not updatable");
    }

    // Retrieves Appointment ID
    public String getAppointmentID() {
        return appointmentID;
    }

    // Verifies that taskID is neither null, blank nor longer than 10 characters
    public void verifyAppointmentID() {
        if (this.appointmentID == null || this.appointmentID.isEmpty()) {
            throw new RuntimeException("Unique ID string is null or empty");
        }

        if (this.appointmentID.length() > 10) {
            throw new RuntimeException("Unique ID string is too long, cannot be longer than 10 characters");
        }
    }

    // Appointment date Setter
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
        verifyAppointmentDate();
    }

    // Appointment date getter
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    // Appointment date verification
    public void verifyAppointmentDate() {
        if (this.appointmentDate == null) {
            throw new RuntimeException("Unique Date cannot be null");
        }

        // Adds a buffer because this function creates a new date that is after any date
        // generated by the appointment service, thus triggering the throw block.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -1); // 1 second should be a sufficient buffer
        Date bufferDate = calendar.getTime();

        if (this.appointmentDate.before(bufferDate)) {
            throw new RuntimeException("Unique Date cannot be before current date");
        }
    }

    // Appointment description setter
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
        verifyAppointmentDescription();
    }

    // Appointment description getter
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    // Verifies that taskDescription is neither null, blank nor longer than 50 characters
    public void verifyAppointmentDescription() {
        if (this.appointmentDescription == null || this.appointmentDescription.isEmpty()) {
            throw new RuntimeException("Appointment description is null or empty");
        }

        if (this.appointmentDescription.length() > 50) {
            throw new RuntimeException("Appointment description is too long, cannot be longer than 50 characters");
        }
    }

}
