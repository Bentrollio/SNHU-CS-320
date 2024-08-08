/* Alex Baires - SNHU CS-320
 *
 * PROJECT ONE
 * TaskTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 * The task object shall have a required unique task ID String that cannot be longer than 10 characters.
 * The task ID shall not be null and shall not be updatable.
 * The task object shall have a required name String field that cannot be longer than 20 characters.
 * The name field shall not be null.
 *The task object shall have a required description String field that cannot be longer than 50 characters.
 * The description field shall not be null.
 */

package org.alexbaires.test;

import org.alexbaires.main.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

public class TaskTest {
    private static Task task;

    @Test
    @DisplayName("Testing Task ID Requirements - Not longer than 10 characters, not NULL or empty")
    public void testTaskIDRequirements() {
        // Test Task ID longer than 10 characters
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("123456789101",
                "Opening", "Opens an individual contact"));
        // Test a NULL Task ID
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task(null, "Opening",
                "Opens an individual contact"));
        // Test an empty Task ID
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("", "Opening",
                "Opens an individual contact"));
    }

    @Test
    @DisplayName("Task ID shall not be updatable")
    public void testTaskIDNotUpdatable() {
        task = new Task ("1","Open e-mail", "Opens g-mail application");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> task.setTaskID("2"));
    }

    @Test
    @DisplayName("Testing Task Name Requirements - Not longer than 20 characters, not NULL or empty")
    public void testTaskNameRequirements() {
        // Test that Task Name is not longer than 20 characters
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("1",
                "Opening a brand new contact in the application", "Opens an individual contact"));
        // Test a NULL Task Name field
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("1", null,
                "Adding Ivan as a new contact"));
        // Test an empty Task Name field
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("1", "",
                "Adding Ivan as a new contact"));
    }

    @Test
    @DisplayName("Testing Task Description Requirements - Not longer than 50 characters, not NULL or empty")
    public void testTaskDescriptionRequirements() {
        // Test that Task Description is not longer than 50 characters
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task("1",
                "Add Ivan as Contact", "Adds Ivan the dog as a contact " +
                "inside of Alex's application"));
        // Test a NULL Task Description field
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task ("1",
                "Add Ivan as Contact", null));
        // Test an empty Task Description field
        Assertions.assertThrows(RuntimeException.class, () -> task = new Task ("1",
                "Add Ivan as Contact", ""));
    }
}
