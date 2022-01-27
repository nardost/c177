package com.mthree.ntessema.hotel.orm;

import com.mthree.ntessema.hotel.dto.Guest;
import com.mthree.ntessema.hotel.dto.State;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class GuestORM {
    public static Guest mapToGuest(ResultSet results) throws SQLException {
        final int id = results.findColumn("id");
        final String firstName = results.getString("first_name");
        final String lastName = results.getString("last_name");
        final LocalDate dateOfBirth = results.getDate("date_of_birth") == null ? null :
                results.getDate("date_of_birth")
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
        final String phone = results.getString("phone");
        final String streetAddress = results.getString("street_address");
        final String city = results.getString("city");
        final State state = State.valueOf(results.getString("state"));
        final int zipCode = results.getInt("zip_code");
        return new Guest(
                id,
                firstName,
                lastName,
                dateOfBirth,
                phone,
                streetAddress,
                city,
                state,zipCode
        );
    }
}
