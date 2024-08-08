/*
 * PROJECT ONE
 * TaskService.java
 *
 * Creates and stores individual tasks with their details.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-11-24
 */

package org.alexbaires.main;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class TaskService {

    HashMap<String, Task> taskList = new HashMap<>();

    public void addTask(String taskName, String taskDescription) {
        String taskID = getHash(taskName);
        Task task = new Task(taskID, taskName, taskDescription);
        checkExistingTask(task.getTaskID());
        taskList.put(taskID, task);
    }

    public void deleteTask(String taskID) {
        taskList.remove(taskID);
    }

    public void checkExistingTask(String taskID) {
        if(taskList.containsKey(taskID)) {
            throw new RuntimeException("Task already exists");
        }
    }

    // Retrieves the task list
    public HashMap<String, Task> getTaskList() {
        return taskList;
    }

    public Task retrieveTask(String taskID) {
        return taskList.get(taskID);
    }

    public void updateTaskName(String taskID, String newTaskName) {
        Task task = retrieveTask(taskID);
        task.setTaskName(newTaskName);
    }

    public void updateTaskDescription(String taskID, String newTaskDescription) {
        Task task = retrieveTask(taskID);
        task.setTaskDescription(newTaskDescription);
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
