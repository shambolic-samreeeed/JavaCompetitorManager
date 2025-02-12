package org.portfolio.competitormanager.test;

import org.junit.Test;
import org.portfolio.competitormanager.connection.ConnectionToDB;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionToDBTest {

    /**
     * Tests whether the connection to the database is sucessful.
     */
    @Test
    public void testConnectionToDB() {
        try(Connection connection = ConnectionToDB.getConnection()){
            assertNotNull(connection, "Connection can not be null");
            assertFalse("Connection is closed, it should be open.", connection.isClosed());
        }catch(SQLException | ClassNotFoundException e){
            fail("Exception thrown during the database connection." + e.getMessage());
        }
    }
}
