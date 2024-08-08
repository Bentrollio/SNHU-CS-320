/* Alex Baires - SNHU CS-320
 * PROJECT ONE
 * TaskServiceTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The task service shall be able to add tasks with a unique ID.
 * The task service shall be able to delete tasks per contact ID.
 * The task service shall be able to task contact fields per contact ID.
 * The following fields are updatable:
 * task name, task description
 */

package org.alexbaires.test;
import org.alexbaires.main.TaskService;
import org.alexbaires.main.Task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    public void setupTaskService() {
        taskService = new TaskService();
    }

    @Test
    @DisplayName("Should Add a Task and Retrieve its Individual Details")
    @Order(1)
    public void testAddAndRetrieveTask() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        assertFalse(taskService.getTaskList().isEmpty());
        assertEquals(1, taskService.getTaskList().size());

        String uniqueID = taskService.getTaskList().keySet().iterator().next();
        Task task = taskService.retrieveTask(uniqueID);
        Assertions.assertNotNull(task);
        assertEquals("Open e-mail", task.getTaskName());
        assertEquals("Opens g-mail application", task.getTaskDescription());
    }

    @Test
    @DisplayName("New Task Should Have a Unique ID Generated")
    @Order(2)
    public void testWhetherTaskHasID() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String taskIDTest = taskService.getTaskList().keySet().iterator().next();
        assertFalse(taskService.retrieveTask(taskIDTest).getTaskID().isEmpty());
    }

    @Test
    @DisplayName("Testing whether Generated ID is Unique")
    @Order(3)
    public void testUniqueTaskID() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String firstUniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.addTask("Texts", "Opens text messages");
        String secondUniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        assertNotEquals(firstUniqueIDTest, secondUniqueIDTest);
    }

    @Test
    @DisplayName("Should Avoid Duplicate Tasks")
    @Order(4)
    public void testDuplicateTasks() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("Open e-mail", "Opens g-mail application");
        });
    }

    @Test
    @DisplayName("Testing whether Multiple Tasks are Stored")
    @Order(5)
    public void testAddMultipleTasks() {

        taskService.addTask("Pay a Payee", "Pay electric bill");
        taskService.addTask("Work", "Attend general meeting");
        assertEquals(2, taskService.getTaskList().size());

        taskService.addTask("Dog", "Give pain medicine");
        assertEquals(3, taskService.getTaskList().size());
    }

    @Test
    @DisplayName("Should Delete a Task")
    @Order(6)
    public void testDeleteTask() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String uniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.deleteTask(uniqueIDTest);
        assertTrue(taskService.getTaskList().isEmpty());
        assertEquals(0, taskService.getTaskList().size());
    }

    @Test
    @DisplayName("Testing Invalid Task Data Error-Handling")
    @Order(7)
    public void testInvalidTaskData() {
        // Testing NULL Task Name
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask(null, "Opens g-mail application");
        });
        // Testing blank Task Name
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("", "Opens g-mail application");
        });

        // Testing NULL Task Description
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("open e-mail", null);
        });
        // Testing blank Task Description
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("open e-mail", "");
        });
    }

    @Test
    @DisplayName("Testing Updatable Task Information")
    @Order(8)
    public void testUpdateTaskInformation() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String taskIDTest = taskService.getTaskList().keySet().iterator().next();

        // Updating Task Name
        taskService.updateTaskName(taskIDTest,"Open mail app");
        assertEquals("Open mail app", taskService.retrieveTask(taskIDTest).getTaskName());

        // Updating Task Description
        taskService.updateTaskDescription(taskIDTest,"Opens Apple Mail app");
        assertEquals("Opens Apple Mail app", taskService.retrieveTask(taskIDTest).getTaskDescription());
    }

    @Test
    @DisplayName("Testing for edge cases")
    @Order(9)
    public void testEdgeCases() {
        char[] data = new char[1000];
        Arrays.fill(data, 'a');
        String str = new String(data);
        // Testing very long input
        assertThrows(RuntimeException.class, () -> {
            taskService.addTask(str, "Opens g-mail application");
        });
        assertThrows(RuntimeException.class, () -> {
            taskService.addTask("Open email", str);
        });
        assertThrows(RuntimeException.class, () -> {
            taskService.addTask(str, str);
        });

        assertNotEquals(1, taskService.getTaskList().size());

        // Testing special characters
        taskService.addTask("!@#$%^&*()!@#$%^&*()", "!@#$%^&*()!@#$%^&*()!@#$%^&*()!@#$%^&*()" +
                "!@#$%^&*()");
        assertNotEquals(0, taskService.getTaskList().size());
    }

}
