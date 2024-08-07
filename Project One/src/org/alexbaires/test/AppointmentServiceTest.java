/* Alex Baires - SNHU CS-320
 * PROJECT ONE
 * AppointmentServiceTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The appointment service shall be able to add appointments with a unique appointment ID.
 *
 * The appointment service shall be able to delete appointments per appointment ID.
 *
 * Extra Requirement to boost functionality:
 * The following fields are updatable:
 * firstName, lastName, Number, Address
 */
package org.alexbaires.test;

import org.alexbaires.main.AppointmentService;
import org.alexbaires.main.Appointment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

public class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private Date currentDate;

    @BeforeEach
    public void setupAppointmentService() {
        appointmentService = new AppointmentService();
    }

    @BeforeEach
    public void setCurrentDate() {
        Calendar calendar = Calendar.getInstance(); // has current date and time
        this.currentDate = calendar.getTime();
    }

    @Test
    @DisplayName("Should add an appointment")
    public void testAddAppointment() {
        appointmentService.addAppointment(currentDate, "Dental Exam");
        assertFalse(appointmentService.getAppointmentList().isEmpty());
        assertEquals(1, appointmentService.getAppointmentList().size());
    }

    @Test
    @DisplayName("New appointment should have an ID generated")
    public void testAppointmentIDGeneration() {
        appointmentService.addAppointment(currentDate, "Dental Exam");
        String appointmentIDTest = getAppointmentIDByDate(appointmentService, currentDate);
        assertFalse(appointmentService.getAppointment(appointmentIDTest).getAppointmentID().isEmpty());
    }

    @Test
    @DisplayName("Testing whether generated ID is unique")
    public void testAppointmentIDUnique() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2024,Calendar.AUGUST,25);
        Date testDate = calendar.getTime();
        appointmentService.addAppointment(testDate, "Dinner with Dana");
        String firstUniqueIDTest = getAppointmentIDByDate(appointmentService, testDate);

        calendar.set(2024,Calendar.AUGUST,30);
        Date testDate2 = calendar.getTime();
        appointmentService.addAppointment(testDate2, "Ivan's vet visit");
        String secondUniqueIDTest = getAppointmentIDByDate(appointmentService, testDate2);
        assertNotEquals(firstUniqueIDTest, secondUniqueIDTest);
    }

    @Test
    @DisplayName("Testing whether multiple appointments are stored")
    public void testAddMultipleAppointments() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2024,Calendar.AUGUST,28);
        Date testDate = calendar.getTime();
        appointmentService.addAppointment(testDate, "Dinner with Dana");

        calendar.set(2024,Calendar.AUGUST,30);
        Date testDate2 = calendar.getTime();
        appointmentService.addAppointment(testDate2, "Ivan's vet visit");

        assertEquals(2, appointmentService.getAppointmentList().size());

        calendar.set(2024,Calendar.AUGUST,25);
        Date testDate3 = calendar.getTime();
        appointmentService.addAppointment(testDate3, "Quitting College");
        assertEquals(3, appointmentService.getAppointmentList().size());
    }

    @Test
    @DisplayName("Testing whether appointments can be retrieved")
    public void testRetrieveAppointment() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2024,Calendar.AUGUST,28);
        Date testDate = calendar.getTime();
        appointmentService.addAppointment(testDate, "Dinner with Dana");
        String appointmentIDTest = getAppointmentIDByDate(appointmentService, testDate);

        // Control object
        Appointment appointment = new Appointment(appointmentIDTest, testDate, "Dinner with Dana");
        // If both objects parameters match, object can be retrieved from list
        assertEquals(appointment.getAppointmentID(), appointmentService.getAppointment(appointmentIDTest).getAppointmentID());
        assertEquals(appointment.getAppointmentDate(), appointmentService.getAppointment(appointmentIDTest).getAppointmentDate());
        assertEquals(appointment.getAppointmentDescription(), appointmentService.getAppointment(appointmentIDTest).getAppointmentDescription());
        assertTrue(appointmentService.getAppointmentList().containsKey(appointmentIDTest));

        calendar.set(2024,Calendar.AUGUST,30);
        Date testDate2 = calendar.getTime();
        appointmentService.addAppointment(testDate2,"Ivan's vet visit");
        String appointmentIDTest2 = getAppointmentIDByDate(appointmentService, testDate2);

        // Control object
        Appointment appointment2 = new Appointment(appointmentIDTest2, testDate2, "Ivan's vet visit");
        // If both objects parameters match, object can be retrieved from list
        assertEquals(appointment2.getAppointmentID(), appointmentService.getAppointment(appointmentIDTest2).getAppointmentID());
        assertEquals(appointment2.getAppointmentDate(), appointmentService.getAppointment(appointmentIDTest2).getAppointmentDate());
        assertEquals(appointment2.getAppointmentDescription(), appointmentService.getAppointment(appointmentIDTest2).getAppointmentDescription());
        assertTrue(appointmentService.getAppointmentList().containsKey(appointmentIDTest2));
    }

    @Test
    @DisplayName("Should Delete an Appointment")
    public void testDeleteAppointment() {
        appointmentService.addAppointment(currentDate, "Meeting with Chris");
        String locatedAppointment = getAppointmentIDByDate(appointmentService, currentDate);
        appointmentService.deleteAppointment(locatedAppointment);
        assertTrue(appointmentService.getAppointmentList().isEmpty());
        assertEquals(0, appointmentService.getAppointmentList().size());
    }

    /**
     * My previous method was inconsistent with multiple entries, so I created a wrapper function that iterates
     * over the Appointment Service - Appointment List hash map checking each ID key before locating
     * the date value and returning the associated ID.
     */
    private String getAppointmentIDByDate(AppointmentService appointmentService, Date date) {
        for (String id : appointmentService.getAppointmentList().keySet()) {
            if (appointmentService.getAppointmentList().get(id).getAppointmentDate().equals(date)) {
                return id;
            }
        }
        throw new AssertionError("Appointment ID not found for date: " + date);
    }
}
