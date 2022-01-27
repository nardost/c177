package com.mthree.ntessema.hotel;

import com.mthree.ntessema.hotel.dao.GuestDao;
import com.mthree.ntessema.hotel.dto.Guest;
import com.mthree.ntessema.hotel.dto.State;
import com.mthree.ntessema.hotel.orm.GuestORM;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;

/**
 * Hotel Software Guild - A JDBC Application
 * author: @nardos
 *
 */
public class HotelApplication {

    private static final String host = System.getenv("DB_HOST");
    private static final String port = System.getenv("DB_PORT");
    private static final String username = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");
    private static final String db = System.getenv("DB");
    // jdbc:mysql://username:password@host:port/db
    private static final String jdbcURL = String.format("jdbc:mysql://%s:%s@%s:%s/%s", username, password, host, port, db);

    public static void main( String[] args ) {
        try {
            final MysqlDataSource dataSource = getDataSource();
            final GuestDao guestDao = new GuestDao(dataSource);
            final Collection<Guest> guests = guestDao.findAllGuests();
            guests.stream().map(g -> g.getFirstName() + " " + g.getLastName()).forEach(System.out::println);
            // handle null
            final Guest g = guestDao.findGuestById(1);
            System.out.printf("%s %s %s", g.getFirstName(), g.getLastName(), g.getPhone());
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    private static MysqlDataSource getDataSource() throws SQLException {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(jdbcURL);
        dataSource.setUseSSL(true);
        dataSource.setAllowPublicKeyRetrieval(true);
        return dataSource;
    }
}
