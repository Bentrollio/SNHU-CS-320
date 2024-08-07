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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    public void setupTaskService() {
        taskService = new TaskService();
    }

    @Test
    @DisplayName("Should add a task")
    public void testAddTask() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        assertFalse(taskService.getTaskList().isEmpty());
        assertEquals(1, taskService.getTaskList().size());
    }

    @Test
    @DisplayName("New task should have a unique ID generated")
    public void testWhetherTaskHasID() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String taskIDTest = taskService.getTaskList().keySet().iterator().next();
        assertFalse(taskService.getTask(taskIDTest).getTaskID().isEmpty());
    }

    @Test
    @DisplayName("Testing whether generated ID is unique")
    public void testUniqueTaskID() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String firstUniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.addTask("Texts", "Opens text messages");
        String secondUniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        assertNotEquals(firstUniqueIDTest, secondUniqueIDTest);
    }

    @Test
    @DisplayName("Should avoid duplicate tasks")
    public void testDuplicateTasks() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("Open e-mail", "Opens g-mail application");
        });
    }

    @Test
    @DisplayName("Testing whether multiple tasks are stored")
    public void testAddMultipleTasks() {

        taskService.addTask("Pay a Payee", "Pay electric bill");
        taskService.addTask("Work", "Attend general meeting");
        assertEquals(2, taskService.getTaskList().size());

        taskService.addTask("Dog", "Give pain medicine");
        assertEquals(3, taskService.getTaskList().size());
    }

    @Test
    @DisplayName("Should Delete a task")
    public void testDeleteTask() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String uniqueIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.deleteTask(uniqueIDTest);
        assertTrue(taskService.getTaskList().isEmpty());
        assertEquals(0, taskService.getTaskList().size());
    }

    @Test
    @DisplayName("Should not create a task when task name is NULL")
    public void testRunTimeExceptionNullName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask(null, "Opens g-mail application");
        });
    }

    @Test
    @DisplayName("Should not create a task when task description is NULL")
    public void testRunTimeExceptionNullDescription() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.addTask("open e-mail", null);
        });
    }

    @Test
    @DisplayName("Task service shall update task name")
    public void testUpdateTaskName() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String taskIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.updateTaskName(taskIDTest,"Open mail app");
        assertEquals("Open mail app", taskService.getTask(taskIDTest).getTaskName());
    }

    @Test
    @DisplayName("Task service shall update task name")
    public void testUpdateTaskDescription() {
        taskService.addTask("Open e-mail", "Opens g-mail application");
        String taskIDTest = taskService.getTaskList().keySet().iterator().next();
        taskService.updateTaskDescription(taskIDTest,"Opens Apple Mail app");
        assertEquals("Opens Apple Mail app", taskService.getTask(taskIDTest).getTaskDescription());
    }
}
