package org.portfolio.competitormanager.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.portfolio.competitormanager.dao.QuestionDao;
import org.portfolio.competitormanager.dao.impl.QuestionDaoImpl;
import org.portfolio.competitormanager.model.Questions;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDaoTest {

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() throws Exception {
        questionDao = new QuestionDaoImpl();
    }

    @Test
    public void testSaveQuestion() {
        Questions questions = new Questions(
                0, "Who is messi?", "Footballer", "Basketballer", "Singer", "Actor", 1, "Beginner"
        );

        // Test the save operation
        assertDoesNotThrow(() -> {
            int result = questionDao.save(questions);
            assertTrue(result > 0, "Question should be saved properly");
        });
    }


    @Test
    public void testFindByDifficulty() {
        // Test find by difficulty
        assertDoesNotThrow(() -> {
            List<Questions> retrievedQuestions = questionDao.findByDifficulty("Beginner");
            assertFalse(retrievedQuestions.isEmpty(), "There should be at least one question with 'Beginner' difficulty");

            // Ensure the first question is retrieved correctly
            Questions retrievedQuestion = retrievedQuestions.get(0);
            assertEquals("Lionel Messi joined which french football club in 2021?", retrievedQuestion.getQuestionText());
            assertEquals("Paris Saint Germain", retrievedQuestion.getOption3());
            assertEquals(3, retrievedQuestion.getCorrectOption());
        });
    }



    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        Questions updatedQuestion = new Questions(
                0,
                "Where is messi?",
                "In his house", "I have no idea", "Anywhere", "Sleeping with his worldcup",
                4, "Intermediate"
        );

        // Test update
        assertDoesNotThrow(() -> {
            int result = questionDao.update(updatedQuestion, 35);
            assertTrue(result > 0, "Question should be updated successfully");
        });
    }

    @Test
    public void testFindAll() throws SQLException, ClassNotFoundException {
        assertDoesNotThrow(() -> {
            List<Questions> questions = questionDao.findAll();
            assertNotNull(questions, "List of questions should not be null");
            assertFalse(questions.isEmpty(), "There should be at least one question in the database");
        });
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        assertDoesNotThrow(() -> {
            int result = questionDao.delete(33);
            assertTrue(result > 0, "Question should be deleted successfully");
        });
    }
}
