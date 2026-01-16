package com.accounting.data.mysql;

import org.springframework.stereotype.Component;
import com.accounting.data.TransactionDao;
import com.accounting.models.Transaction;


import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class MySqlTransactionDao implements TransactionDao {
    public MySqlTransactionDao(DataSource dataSource) {
        super();
    }

    @Override
    public Transaction create(Transaction transaction) {
        String sql = """
                INSERT INTO transactions (description, vendor, amount, deposit)
                VALUES (?, ?, ?, ?);
                """;

        boolean deposit = transaction.getAmount() >= 0;
        double absAmount = Math.abs(transaction.getAmount());

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, transaction.getDescription());
            ps.setString(2, transaction.getVendor());
            ps.setDouble(3, absAmount);
            ps.setBoolean(4, deposit);

            ps.executeUpdate();

            int newId = 0;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    newId = keys.getInt(1);
                }
            }


            LocalDateTime dt = (null != transaction.getDateAndTime()) ? transaction.getDateAndTime() : LocalDateTime.now();

            return new Transaction(newId, dt, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction getById(int transactionId) {
        String sql = """
                SELECT transaction_id, description, vendor, amount, deposit
                FROM transactions
                WHERE transaction_id = ?;
                """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transactionId);

            try (ResultSet row = ps.executeQuery()) {
                if (row.next()) {
                    return mapRow(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
