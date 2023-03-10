package org.project.dao;

import org.project.connection.ConnectionManager;
import org.project.entity.Publisher;
import org.project.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class PublisherDao {
    private final static Logger log = LoggerFactory.getLogger(PublisherDao.class);

    private final static String QUERY = "SELECT * FROM publisher WHERE name = ?";
    private final static String INSERT = "INSERT INTO publisher (name) VALUE (?)";


    public boolean isPresent(String name) throws DaoException {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement(QUERY);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.error("DaoException occurred in " + PublisherDao.class);
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return false;
    }

    public Optional<Publisher> findByName(String name) throws DaoException {
        Optional<Publisher> result;
        Publisher publisher = null;
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                publisher = new Publisher(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            result = Optional.ofNullable(publisher);

        } catch (SQLException exception) {
            log.error("DaoException occurred in " + PublisherDao.class);
            throw new DaoException(exception.getMessage(), exception.getCause());
        }
        return result;
    }

    public boolean insert(Publisher publisher) throws DaoException {
        boolean result = false;
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            Savepoint sp = con.setSavepoint("Save");

            try (PreparedStatement ps = con.prepareStatement(INSERT)) {
                ps.setString(1, publisher.getName());

                int update = ps.executeUpdate();
                result = update != 0;
                con.commit();
            } catch (SQLException e) {
                ConnectionManager.rollback(con, sp);
            }
        } catch (SQLException exception) {
            log.error("DaoException occurred in " + PublisherDao.class);
            throw new DaoException(exception.getMessage(), exception.getCause());
        }
        return result;
    }
}
