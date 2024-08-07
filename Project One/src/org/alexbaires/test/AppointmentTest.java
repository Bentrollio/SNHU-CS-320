/* Alex Baires - SNHU CS-320
 * PROJECT ONE
 * AppointmentTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 * The appointment object shall have a required unique appointment ID string that cannot be longer than 10 characters.
 * The appointment ID shall not be null and shall not be updatable.
 * The appointment object shall have a required appointment Date field.
 * The appointment Date field cannot be in the past. The appointment Date field shall not be null.
 * The appointment object shall have a required description String field that cannot be longer than 50 characters.
 * The description field shall not be null.
 */
package org.alexbaires.test;

import org.alexbaires.main.Appointment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;

public class AppointmentTest {

    private static Appointment appointment;
    private Date currentDate;

    @BeforeEach
    public void setCurrentDate() {
        Calendar calendar = Calendar.getInstance(); // has current date and time
        this.currentDate = calendar.getTime();
    }

    @Test
    @DisplayName("Unique Appointment ID String should not be longer than 10 characters")
    public void testAppointmentIDLength() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment("123456789101", currentDate,
                    "Consultation visit");
        });
    }

    @Test
    @DisplayName("Appointment ID should not be null or empty")
    public void testAppointmentIDNullOrEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment(null, currentDate,
                    "Consultation visit");
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment("", currentDate,
                    "Consultation visit");
        });
    }

    @Test
    @DisplayName("Test that appointment ID is not updatable")
    public void testAppointmentIDNotUpdatable() {
        appointment = new Appointment ("1", currentDate,
                "Consultation");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            appointment.setAppointmentID("2");
        });
    }

    @Test
    @DisplayName("Verifying that an appointment cannot be created for a past date")
    public void testAppointmentDateNotInPast() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2024,Calendar.JULY,30);
            Date testDate = calendar.getTime();
            appointment = new Appointment("1", testDate, " Consultation");;
        });
    }

    @Test
    @DisplayName("Verifying that the date field cannot be null")
    public void testAppointmentDateNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment("1", null, " Consultation");
        });
    }

    @Test
    @DisplayName("Verifying appointment object has a required appointment date field")
    public void testAppointmentHasDate() {
        Date testDate = new Date();
        appointment = new Appointment("1", testDate, "Consultation");
        Assertions.assertTrue(appointment.getAppointmentDate() != null);
        Assertions.assertEquals(testDate == appointment.getAppointmentDate(), true);
    }

    @Test
    @DisplayName("Appointment description field shall not be longer than 50 characters")
    public void testAppointmentDescriptionLength() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment("1", new Date(),
                    "Consultation for the installation of a new roof on home");
        });
    }

    @Test
    @DisplayName("The appointment description field shall not be null or empty")
    public void testAppointmentDescriptionNullOrEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Appointment ("1", new Date(), null);
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            appointment = new Appointment("1", new Date(),
                    "");
        });
    }
}
