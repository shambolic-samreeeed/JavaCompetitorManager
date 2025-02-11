package org.portfolio.competitormanager.ui;

import org.portfolio.competitormanager.model.Result;
import org.portfolio.competitormanager.dao.ResultDao;
import org.portfolio.competitormanager.dao.impl.ResultDaoImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JFrame {
    private String username;

    private JPanel contentPane;
    private JLabel homeLabel;
    private JButton playButton;
    private JButton leaderboardsButton;
    private JButton exitButton;


    public HomePage(String username) {
        this.username = username;

        initComponents();

        setTitle("Home Page - " + username);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(contentPane);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playHandler(e);
            }
        });

        leaderboardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLeaderboards();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the program
            }
        });

        setVisible(true);  // Make the window visible
    }

    private void playHandler(ActionEvent e) {
        CompetitorLayout competitorLayout = new CompetitorLayout(username);
        competitorLayout.setVisible(true);
        dispose(); // Close the home page
    }

    private void showLeaderboards() {
        // Fetch leaderboard data and display it
        ResultDao resultDao = new ResultDaoImpl();
        try {
            List<Result> results = resultDao.findAll(); // Get leaderboard data
            StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");

            for (Result result : results) {
                leaderboard.append(result.getUsername()).append(" - ").append(result.getScore()).append("\n");
            }

            JOptionPane.showMessageDialog(this, leaderboard.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error loading leaderboard: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // You can also ensure the form file's initComponents is present
    private void initComponents() {
        // The code that IntelliJ generates from the .form file goes here
        // Usually this is automatically called, so you don't need to add it manually
    }
}
