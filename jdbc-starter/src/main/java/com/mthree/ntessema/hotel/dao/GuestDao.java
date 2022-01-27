package com.mthree.ntessema.hotel.dao;

import com.mthree.ntessema.hotel.dto.Guest;
import com.mthree.ntessema.hotel.orm.GuestORM;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
public class GuestDao {

    private final DataSource dataSource;

    public Collection<Guest> findAllGuests() throws SQLException {
        try (final Connection connection = dataSource.getConnection()) {
            final Collection<Guest> guests = new HashSet<>();
            final Statement statement = connection.createStatement();
            final ResultSet results = statement.executeQuery("SELECT * FROM guests");
            while(results.next()) {
                final Guest guest = GuestORM.mapToGuest(results);
                guests.add(guest);
            }
            return guests;
        }
    }

    public Guest findGuestById(int id) throws SQLException {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery("SELECT * FROM guests WHERE id = " + id);
            while(rs.next()) {
                final Guest guest = GuestORM.mapToGuest(rs);
                return guest;
            }
        }
        return null;
    }
}
