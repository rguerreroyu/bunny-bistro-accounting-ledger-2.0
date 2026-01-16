package com.accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Bunny Bistro Spring Boot application.
 * <p>
 * Bootstraps the application using Spring Boot's auto-configuration & component scanning.
 * </p>
 */
@SpringBootApplication
public class BunnyBistroAccountingLedgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BunnyBistroAccountingLedgerApplication.class, args);
    }
}