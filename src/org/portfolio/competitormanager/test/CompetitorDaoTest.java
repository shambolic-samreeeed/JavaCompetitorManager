package org.portfolio.competitormanager.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.portfolio.competitormanager.dao.CompetitorDao;
import org.portfolio.competitormanager.dao.impl.CompetitorDaoImpl;
import org.portfolio.competitormanager.model.Competitor;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorDaoTest {

    private CompetitorDao competitorDao;

    @BeforeEach
    void setup() {
        competitorDao = new CompetitorDaoImpl();
    }

    @Test
    void testSaveAndFindByUsername() throws SQLException, ClassNotFoundException {
        Competitor competitor = new Competitor("samreedtest", "testingpassword", "admin");

        // Testing save
        assertDoesNotThrow(() -> {
            int result = competitorDao.save(competitor);
            assertTrue(result > 0, "Competitor should be saved successfully");
        });

        // Testing findByUsername
        assertDoesNotThrow(() -> {
            Competitor retrievedCompetitor = competitorDao.findByUsername("samreedtest");
            assertNotNull(retrievedCompetitor, "Competitor should be found");
            assertEquals("samreedtest", retrievedCompetitor.getUsername());
            assertEquals("testingpassword", retrievedCompetitor.getPassword());
            assertEquals("admin", retrievedCompetitor.getRole());
        });
    }

}
