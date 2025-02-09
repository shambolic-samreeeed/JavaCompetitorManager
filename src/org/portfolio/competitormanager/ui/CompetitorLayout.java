package org.portfolio.competitormanager.ui;

import org.portfolio.competitormanager.dao.QuestionDao;
import org.portfolio.competitormanager.dao.ResultDao;
import org.portfolio.competitormanager.dao.impl.QuestionDaoImpl;
import org.portfolio.competitormanager.dao.impl.ResultDaoImpl;
import org.portfolio.competitormanager.model.Questions;
import org.portfolio.competitormanager.model.Result;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CompetitorLayout extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> difficultyComboBox;
    private JButton startQuizButton;
    private JTextArea questionArea;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JLabel scoreLabel;

    private QuestionDao questionDao;
    private ResultDao resultDao;
    private List<Questions> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String username;

    public CompetitorLayout(String username) {
        this.username = username;

        // Initialize DAOs
        questionDao = new QuestionDaoImpl();
        resultDao = new ResultDaoImpl();

        // UI Setup
        setTitle("User Dashboard - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        difficultyComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Advanced"});
        startQuizButton = new JButton("Start Quiz");
        topPanel.add(new JLabel("Select Difficulty:"));
        topPanel.add(difficultyComboBox);
        topPanel.add(startQuizButton);

        questionArea = new JTextArea(5, 50);
        questionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(questionArea);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        // Creating radio buttons
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        // Grouping radio buttons
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        // Adding buttons to panel
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit Answer");
        scoreLabel = new JLabel("Score: 0/5");
        bottomPanel.add(submitButton);
        bottomPanel.add(scoreLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(optionsPanel, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);

        // Action Listeners
        startQuizButton.addActionListener(e -> startQuiz());
        submitButton.addActionListener(e -> submitAnswer());
    }

    private void startQuiz() {
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        try {
            questions = questionDao.findByDifficulty(difficulty);
            if (questions.size() < 5) {
                JOptionPane.showMessageDialog(this, "Not enough questions available for the selected difficulty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentQuestionIndex = 0;
            score = 0;
            displayQuestion();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex < 5) {
            Questions question = questions.get(currentQuestionIndex);
            questionArea.setText("Question " + (currentQuestionIndex + 1) + ":\n" + question.getQuestionText());

            // Update radio button texts
            option1.setText("1. " + question.getOption1());
            option2.setText("2. " + question.getOption2());
            option3.setText("3. " + question.getOption3());
            option4.setText("4. " + question.getOption4());

            // Clear previous selection
            optionsGroup.clearSelection();
        } else {
            endQuiz();
        }
    }

    private void submitAnswer() {
        int selectedOption = -1; // Default if nothing is selected

        if (option1.isSelected()) selectedOption = 1;
        else if (option2.isSelected()) selectedOption = 2;
        else if (option3.isSelected()) selectedOption = 3;
        else if (option4.isSelected()) selectedOption = 4;

        if (selectedOption == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Questions question = questions.get(currentQuestionIndex);
        if (selectedOption == question.getCorrectOption()) {
            score++;
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private void endQuiz() {
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        try {
            Result result = new Result(0, 0, username, score, difficulty);
            resultDao.save(result);

            String summary = "Quiz Summary:\n" +
                    "Username: " + username + "\n" +
                    "Difficulty: " + difficulty + "\n" +
                    "Score: " + score + "/5";
            JOptionPane.showMessageDialog(this, summary, "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
