package com.accounting.data.mysql;

import com.accounting.data.TransactionDao;
import com.accounting.models.Transaction;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

@Component
public class MySqlTransactionDao extends MySqlDaoBase implements TransactionDao {

    public MySqlTransactionDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public void update(int transactionId, Transaction transaction) {
        String sql = """
                UPDATE transactions
                SET transaction_time = ?, description = ?, vendor = ?, amount = ?, is_deposit = ?
                WHERE key_id = ?;""";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(transaction.getDateAndTime()));
            ps.setString(2, transaction.getDescription());
            ps.setString(3, transaction.getVendor());
            ps.setDouble(4, transaction.getAmount());
            ps.setBoolean(5, transaction.isDeposit());
            ps.setInt(6, transactionId);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int transactionId) {
        String sql = """
                DELETE FROM transactions
                WHERE key_id = ?;""";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transactionId);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction mapRow(ResultSet row) throws SQLException {
        int transactionId = row.getInt("key_id");
        LocalDateTime dateTime = row.getTimestamp("transaction_time").toLocalDateTime();
        String description = row.getString("description");
        String vendor = row.getString("vendor");
        double amount = row.getDouble("amount");
        boolean isDeposit = row.getBoolean("is_deposit");

        Transaction transaction = new Transaction(transactionId, dateTime, description, vendor, amount, isDeposit);

        return transaction;
    }

}

