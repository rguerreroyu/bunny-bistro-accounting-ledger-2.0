package com.accounting.controllers;

import com.accounting.data.TransactionDao;
import com.accounting.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private TransactionDao transactionDao;

    @Autowired
    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    // *** GET ***

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public List<Transaction> getAll() {
        try {
            return transactionDao.getAllTransactions();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when retrieving all transactions.");
        }
    }

    @GetMapping("deposits")
    @PreAuthorize("permitAll()")
    public List<Transaction> getDeposits() {
        try {
            return transactionDao.getAllDeposits();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when retrieving all deposits.");
        }
    }

    @GetMapping("payments")
    @PreAuthorize("permitAll()")
    public List<Transaction> getPayments() {
        try {
            return transactionDao.getAllPayments();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when retrieving all payments.");
        }
    }

    // *** POST ***

    @PostMapping("")
    @PreAuthorize("permitAll()")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        try {
            return transactionDao.create(transaction);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when adding transaction.");
        }
    }

    // *** PUT ***

    @PutMapping("{id}")
    @PreAuthorize("permitAll()")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        try {
            transactionDao.update(id, transaction);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when updating transaction.");
        }
    }


    // *** DELETE ***

    @DeleteMapping("{id}")
    @PreAuthorize("permitAll()")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTransaction(@PathVariable int id) {
        try {
            transactionDao.delete(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Internal server error when deleting transaction.");
        }
    }

}
