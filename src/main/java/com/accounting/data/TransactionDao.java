package com.accounting.data;

import com.accounting.models.Transaction;

import java.util.List;

public interface TransactionDao {
    public List<Transaction> getAllTransactions();
    public List<Transaction> getAllDeposits();
    public List<Transaction> getAllPayments();
    public void update(int transactionId, Transaction transaction);
    public void delete(int transactionId);

}
