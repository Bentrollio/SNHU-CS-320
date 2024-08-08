/*
 * PROJECT ONE
 * Task.java
 *
 * Contains Task Object information.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-11-24
 */

package org.alexbaires.main;

public class Task {

    final String taskID;
    String taskName;
    String taskDescription;

    public Task(String taskID, String taskName, String taskDescription) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;

        verifyTaskID();
        verifyTaskName();
        verifyTaskDescription();
    }

    public void setTaskID(String taskID) {
        throw new UnsupportedOperationException("Task ID Not updatable");
    }

    // Retrieves taskID
    public String getTaskID() {
        return taskID;
    }

    // Verifies that taskID is neither null, blank nor longer than 10 characters
    public void verifyTaskID() {
        if (this.taskID == null || this.taskID.isEmpty()) {
            throw new RuntimeException("Task ID string is null or empty");
        }

        if (this.taskID.length() > 10) {
            throw new RuntimeException("Task ID string is too long, cannot be longer than 10 characters");
        }
    }

    // Task name Setter
    public void setTaskName(String taskName) {
        this.taskName = taskName;
        verifyTaskName();
    }

    // Task name Getter
    public String getTaskName() {
        return taskName;
    }

    // Verifies that the task name is neither null, blank nor longer than 10 characters
    public void verifyTaskName() {
        if (this.taskName == null || this.taskName.isEmpty()) {
            throw new RuntimeException("Task name is null or empty");
        }

        if (this.taskName.length() > 20) {
            throw new RuntimeException("Task name is too long, cannot be longer than 20 characters");
        }
    }

    // Task description setter
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        verifyTaskDescription();
    }

    // Task description getter
    public String getTaskDescription() {
        return taskDescription;
    }

    // Verifies that taskDescription is neither null, blank nor longer than 50 characters
    public void verifyTaskDescription() {
        if (this.taskDescription == null || this.taskDescription.isEmpty()) {
            throw new RuntimeException("Task description is null or empty");
        }

        if (this.taskDescription.length() > 50) {
            throw new RuntimeException("Task description is too long, cannot be longer than 50 characters");
        }
    }

}
