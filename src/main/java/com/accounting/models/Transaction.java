package com.accounting.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int transactionId;
    private LocalDateTime dateAndTime;
    private String description;
    private String vendor;
    private double amount;
    private boolean isDeposit;

    // *** CONSTRUCTORS ***

    /**
     * Creates a new Transaction instance, initializing all fields.
     * @param dateAndTime The date & time a transaction occurred.
     * @param description A brief description of the transaction.
     * @param vendor The vendor of the transaction.
     * @param amount The value of the transaction.
     */
    public Transaction(int transactionId, LocalDateTime dateAndTime, String description, String vendor, double amount, boolean isDeposit) {
        this.transactionId = transactionId;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    // *** GETTERS ***


    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Returns the date and time of the transaction.
     * @return  {@code LocalDateTime}
     */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Returns the date of the transaction.
     * @return  {@code LocalDate}
     */
    public LocalDate getDate() {
        return dateAndTime.toLocalDate();
    }

    /**
     * Returns the time of the transaction.
     * @return  {@code LocalTime}
     */
    public LocalTime getTime() {
        return dateAndTime.toLocalTime();
    }

    /**
     * Returns the description of the transaction.
     * @return  {@code String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the vendor of the transaction.
     * @return  {@code String}
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Returns the amount of the transaction.
     * @return  {@code double}
     */
    public double getAmount() {
        return amount;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    // *** SETTERS ***

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Sets the date and time.
     * @param dateAndTime The new date and time to be assigned to the transaction.
     */
    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Sets the description.
     * @param description The new description to be assigned to the transaction.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the vendor.
     * @param vendor The new vendor to be assigned to the transaction.
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Sets the amount.
     * @param amount The new amount to be assigned to the transaction.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    // *** OTHER ***

    /**
     * Returns a string in the following format with spaces in between each item for table format:
     * MM/dd/yyyy hh:mm a {description} {vendor} +/-${amount}
     *
     * @return An expanded string of the transaction details.
     */
    @Override
    public String toString() {
        //get formatted amount based on deposit or payment
        String formatAmount;
        if (amount < 0) { //payment
            formatAmount = String.format("-$%.2f", amount * -1);
        }
        else { //deposit
            formatAmount = String.format("+$%.2f", amount);
        }

        //return formatted string
        return String.format("%-11s  %-11s  %-35s  %-25s  %-10s",
                getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                getTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                description, vendor, formatAmount);
    }

    /**
     * Returns a string in the following format for a descriptive version:
     * 🌱 MMM dd, yyyy | hh:mm:ss a
     *      > Description: {description}
     *      > Vendor: {vendor}
     *      > Amount: +/-${amount}
     *
     * @return A descriptive String of the transaction details.
     */
    public String toDescriptiveString() {
        //get formatted amount based on deposit or payment
        String formatAmount;
        if (amount < 0) { //payment
            formatAmount = String.format("-$%.2f", amount * -1);
        }
        else { //deposit
            formatAmount = String.format("+$%.2f", amount);
        }

        //return formatted string
        return String.format("""
                        🌱 %s | %s
                            > Description: %s
                            > Vendor: %s
                            > Amount: %s""",
                getDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                getTime().format(DateTimeFormatter.ofPattern("h:mm:ss a")),
                description, vendor, formatAmount);
    }
}
