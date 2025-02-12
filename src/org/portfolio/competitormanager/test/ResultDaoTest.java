package org.portfolio.competitormanager.test;

import org.junit.jupiter.api.Test;
import org.portfolio.competitormanager.model.Result;

import static org.junit.jupiter.api.Assertions.*;

public class ResultDaoTest {

    @Test
    public void testCalculateAverageScore_validScores() {

        Result result = new Result(21, 13, "user", "1,4,2", 0.0);


        double expectedAverage = (1+ 4 + 2) / 3.0;
        assertEquals(expectedAverage, result.getAverageScore(), "Average score should be calculated correctly");
    }

    @Test
    public void testCalculateAverageScore_singleScore() {
        // Create a Result object with a single score
        Result result = new Result(21, 13, "samreed", "5", 0.0);

        // Validate the average score
        double expectedAverage = 5.0;
        assertEquals(expectedAverage, result.getAverageScore(), "Average score should be the same as the single score");
    }


    @Test
    public void testConstructor_correctlySetsFields() {
        // Create a Result object with known values
        Result result = new Result(21, 13, "samreed", "5", 0.0);

        // Validate that the constructor sets the fields correctly
        assertEquals(21, result.getResult_id());
        assertEquals(13, result.getUser_id());
        assertEquals("samreed", result.getUsername());
        assertEquals("5", result.getScore());
        assertEquals(5.0, result.getAverageScore(), 0.001);
    }
}
