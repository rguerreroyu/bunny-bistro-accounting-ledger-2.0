package com.accounting.data;

import com.accounting.models.Transaction;
import java.time.LocalDate;
import java.util.List;

public interface TransactionDao {
    List<Transaction> getAll();

    Transaction getById(int id);

    Transaction create(Transaction transaction);

    boolean update(Transaction transaction);

    boolean delete(int id);

    List<Transaction> getDeposits();

    List<Transaction> getPayments();
    List<Transaction> search(String descriptionContains,
                             String vendorContains,
                             Double minAmount,
                             Double maxAmount,
                             Boolean depositFilter);

}
