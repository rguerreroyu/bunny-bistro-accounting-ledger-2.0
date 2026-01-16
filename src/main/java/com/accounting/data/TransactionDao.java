package com.accounting.data;

import com.accounting.models.Transaction;

public interface TransactionDao {
    public void update(int transactionId, Transaction transaction);
    public void delete(int transactionId);

}
