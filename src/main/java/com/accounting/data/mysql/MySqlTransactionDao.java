package com.accounting.data.mysql;

import com.accounting.models.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MySqlTransactionDao {

    public MySqlTransactionDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        {
            String sql = "SELECT * FROM bunnybistroledger.transactions";
            List<Transaction> transactions = new ArrayList<>();

            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);


                ResultSet row = statement.executeQuery();


                while (row.next()) {
                    transactions.add(mapRow(row));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }
    }
    @Override
    public List<Transaction> getAllDeposits() {
        {
            String sql = "SELECT * FROM bunnybistroledger.transactions WHERE is_deposit = TRUE";
            List<Transaction> transactions = new ArrayList<>();

            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);


                ResultSet row = statement.executeQuery();


                while (row.next()) {
                    transactions.add(mapRow(row));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }
    }
    @Override
    public List<Transaction> getAllPayments() {
        {
            String sql = "SELECT * FROM bunnybistroledger.transactions WHERE is_deposit = FALSE";
            List<Transaction> transactions = new ArrayList<>();

            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);


                ResultSet row = statement.executeQuery();


                while (row.next()) {
                    transactions.add(mapRow(row));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }
    }

    public Transaction getById(int key_id)
    {
        String sql = "SELECT * FROM bunnybistroledger.transactions WHERE key_id = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, key_id);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

}
