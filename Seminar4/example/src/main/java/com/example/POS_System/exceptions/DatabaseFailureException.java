package com.example.POS_System.exceptions;

/**
 * Thrown when a database access error occurs.
 */
public class DatabaseFailureException extends RuntimeException {
    public DatabaseFailureException(String itemId) {
        super("Error: Database connection failure for item identifier: "+ itemId);
    }
}