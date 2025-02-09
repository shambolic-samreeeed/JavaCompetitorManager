package org.portfolio.competitormanager.ui;

import org.portfolio.competitormanager.dao.QuestionDao;
import org.portfolio.competitormanager.dao.impl.QuestionDaoImpl;
import org.portfolio.competitormanager.model.Questions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class AdminLayout extends JFrame {
    private JTextField questionTextField, option1Field, option2Field, option3Field, option4Field;
    private JComboBox<String> difficultyComboBox;
    private JButton addButton, updateButton, deleteButton;
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private QuestionDao questionDao;
    private int selectedQuestionId = -1;
    private ButtonGroup correctOptionGroup;
    private JRadioButton correctOption1, correctOption2, correctOption3, correctOption4;

    public AdminLayout(String adminName) {
        questionDao = new QuestionDaoImpl();
        setTitle("Admin Panel - Question Management");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🔹 HEADER PANEL (Admin Welcome Label)
        JPanel headerPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + adminName + " - Admin Panel");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(welcomeLabel);
        add(headerPanel, BorderLayout.NORTH);

        // 🔹 FORM PANEL
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Question:"), gbc);
        gbc.gridx = 1;
        questionTextField = new JTextField(25);
        formPanel.add(questionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Option 1:"), gbc);
        gbc.gridx = 1;
        option1Field = new JTextField(20);
        formPanel.add(option1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Option 2:"), gbc);
        gbc.gridx = 1;
        option2Field = new JTextField(20);
        formPanel.add(option2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Option 3:"), gbc);
        gbc.gridx = 1;
        option3Field = new JTextField(20);
        formPanel.add(option3Field, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Option 4:"), gbc);
        gbc.gridx = 1;
        option4Field = new JTextField(20);
        formPanel.add(option4Field, gbc);

        // Correct Option Radio Buttons
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Correct Option:"), gbc);

        JPanel correctOptionPanel = new JPanel();
        correctOption1 = new JRadioButton("1");
        correctOption2 = new JRadioButton("2");
        correctOption3 = new JRadioButton("3");
        correctOption4 = new JRadioButton("4");

        correctOptionGroup = new ButtonGroup();
        correctOptionGroup.add(correctOption1);
        correctOptionGroup.add(correctOption2);
        correctOptionGroup.add(correctOption3);
        correctOptionGroup.add(correctOption4);

        correctOptionPanel.add(correctOption1);
        correctOptionPanel.add(correctOption2);
        correctOptionPanel.add(correctOption3);
        correctOptionPanel.add(correctOption4);

        gbc.gridx = 1;
        formPanel.add(correctOptionPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Difficulty:"), gbc);
        gbc.gridx = 1;
        difficultyComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Advanced"});
        formPanel.add(difficultyComboBox, gbc);

        add(formPanel, BorderLayout.WEST);

        // 🔹 BUTTON PANEL (Properly Positioned)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Better button spacing

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔹 TABLE PANEL (For Displaying Questions)
        tableModel = new DefaultTableModel(new String[]{"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Correct", "Difficulty"}, 0);
        questionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(questionTable);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane, BorderLayout.CENTER);

        // Load Questions from Database
        loadQuestions();

        // Button Actions
        addButton.addActionListener(this::handleAdd);
        updateButton.addActionListener(this::handleUpdate);
        deleteButton.addActionListener(e1 -> {
            try {
                handleDelete(e1);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // Table Selection Listener
        questionTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = questionTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedQuestionId = (int) tableModel.getValueAt(selectedRow, 0);
                questionTextField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                option1Field.setText(tableModel.getValueAt(selectedRow, 2).toString());
                option2Field.setText(tableModel.getValueAt(selectedRow, 3).toString());
                option3Field.setText(tableModel.getValueAt(selectedRow, 4).toString());
                option4Field.setText(tableModel.getValueAt(selectedRow, 5).toString());
                String correctOption = tableModel.getValueAt(selectedRow, 6).toString();
                correctOption1.setSelected("1".equals(correctOption));
                correctOption2.setSelected("2".equals(correctOption));
                correctOption3.setSelected("3".equals(correctOption));
                correctOption4.setSelected("4".equals(correctOption));
                difficultyComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 7).toString());
            }
        });

        setVisible(true);
    }

    // Load questions from database into JTable
    private void loadQuestions() {
        try {
            tableModel.setRowCount(0);
            List<Questions> questionsList = questionDao.findAll();
            for (Questions q : questionsList) {
                tableModel.addRow(new Object[]{q.getQuestionId(), q.getQuestionText(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getCorrectOption(), q.getDifficulty()});
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading questions: " + e.getMessage());
        }
    }

    // Add a new question
    private void handleAdd(ActionEvent e) {
        try {
            int correctOption = 0;
            if (correctOption1.isSelected()) correctOption = 1;
            else if (correctOption2.isSelected()) correctOption = 2;
            else if (correctOption3.isSelected()) correctOption = 3;
            else if (correctOption4.isSelected()) correctOption = 4;

            Questions newQuestion = new Questions(0, questionTextField.getText(), option1Field.getText(), option2Field.getText(),
                    option3Field.getText(), option4Field.getText(), correctOption, difficultyComboBox.getSelectedItem().toString());

            int result = questionDao.save(newQuestion);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Question Added Successfully!");
                clearFields();
                loadQuestions();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding question: " + ex.getMessage());
        }
    }

    // Update an existing question
    private void handleUpdate(ActionEvent e) {
        if (selectedQuestionId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a question to update.");
            return;
        }

        try {
            int correctOption = 0;
            if (correctOption1.isSelected()) correctOption = 1;
            else if (correctOption2.isSelected()) correctOption = 2;
            else if (correctOption3.isSelected()) correctOption = 3;
            else if (correctOption4.isSelected()) correctOption = 4;

            Questions updatedQuestion = new Questions(selectedQuestionId, questionTextField.getText(), option1Field.getText(), option2Field.getText(),
                    option3Field.getText(), option4Field.getText(), correctOption, difficultyComboBox.getSelectedItem().toString());

            int result = questionDao.update(updatedQuestion, selectedQuestionId);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Question Updated Successfully!");
                clearFields();
                loadQuestions();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating question: " + ex.getMessage());
        }
    }

    // Delete a selected question
    private void handleDelete(ActionEvent e) throws SQLException, ClassNotFoundException {
        if (selectedQuestionId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a question to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            questionDao.delete(selectedQuestionId);
            JOptionPane.showMessageDialog(this, "Question Deleted Successfully!");
            clearFields();
            loadQuestions();
        }
    }

    // Clear all input fields and reset radio buttons
    private void clearFields() {
        questionTextField.setText("");
        option1Field.setText("");
        option2Field.setText("");
        option3Field.setText("");
        option4Field.setText("");
        correctOptionGroup.clearSelection();
        difficultyComboBox.setSelectedIndex(0);
    }
}
