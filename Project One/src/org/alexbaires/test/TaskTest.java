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
    @DisplayName("Unique Task ID String should not be longer than 10 characters")
    public void testUniqueTaskId() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task("123456789101", "Opening", "Opens" +
                    " an individual contact");
        });
    }

    @Test
    @DisplayName("Task ID should not be null or empty")
    public void testUniqueTaskIdNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task(null, "Opening", "Opens an individual contact");
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task("", "Opening", "Opens an individual contact");
        });
    }

    @Test
    @DisplayName("Test that task ID is not updatable")
    public void testUpdateTaskID() {
        task = new Task ("1","Open e-mail", "Opens g-mail application");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            task.setTaskID("2");
        });
    }

    @Test
    @DisplayName("Task name string field should not be longer than 20 characters")
    public void testUniqueTaskNameString() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task("1", "Opening a brand new contact in the application", "Opens an individual contact");
        });
    }

    @Test
    @DisplayName("Task name shall not be NULL")
    public void testUniqueTaskNameNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task("1", null, "Adding Ivan as a new contact");
        });
    }

    @Test
    @DisplayName("Task description field shall not be longer than 50 characters")
    public void testUniqueTaskDescription() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            task = new Task("1", "Add Ivan as Contact", "Adds Ivan the dog as a contact" +
                    "inside of Alex's application");
        });
    }

    @Test
    @DisplayName("The task description field shall not be null")
    public void testUniqueTaskDescriptionNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Task ("1", "Add Ivan as Contact", null);
        });
    }


}
