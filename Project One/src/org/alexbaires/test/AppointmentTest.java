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
    @DisplayName("Testing Appointment ID Requirements - Not longer than 10 characters, not NULL or empty")
    @Order(1)
    public void testAppointmentIDRequirements() {
        // Test Appointment ID longer than 10 characters
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment("123456789101", currentDate,
                    "Consultation visit"));
        // Test a NULL Appointment ID
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment(null, currentDate,
                    "Consultation visit"));
        // Test an empty Appointment ID
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment("", currentDate,
                    "Consultation visit"));
    }

    @Test
    @DisplayName("Appointment ID shall not be updatable")
    @Order(2)
    public void testAppointmentIDNotUpdatable() {
        appointment = new Appointment ("1", currentDate,
                "Consultation");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> appointment.setAppointmentID("2"));
    }

    @Test
    @DisplayName("IMPORTANT: Testing that Appointment cannot be made for Past Date")
    @Order(3)
    public void testAppointmentDateNotInPast() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2024,Calendar.JULY,30);
            Date testDate = calendar.getTime();
            appointment = new Appointment("1", testDate, " Consultation");
        });
    }

    @Test
    @DisplayName("Testing Date Requirements - Must have date, cannot be NULL")
    @Order(4)
    public void testAppointmentDateRequirements() {
        // Testing that each date object has a date
        Date testDate = new Date();
        appointment = new Appointment("1", testDate, "Consultation");
        Assertions.assertNotNull(appointment.getAppointmentDate());
        Assertions.assertSame(testDate, appointment.getAppointmentDate());
        // Testing that Date is not NULL
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment("1",
                null, " Consultation"));
    }

    @Test
    @DisplayName("Testing Appointment Date Description - No longer than 50 characters, cannot be NULL or empty")
    @Order(5)
    public void testAppointmentDateDescriptionRequirements() {
        // Test that Appointment Description is no longer than 50 characters
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment("1", new Date(),
                "Consultation for the installation of a new roof on home"));
        // Test a NULL Appointment Description field
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment ("1", new Date(),
                null));
        // Test a empty Appointment Description field
        Assertions.assertThrows(RuntimeException.class, () -> appointment = new Appointment("1", new Date(),
                ""));
    }
}
