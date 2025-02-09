package org.portfolio.competitormanager.ui;

import org.portfolio.competitormanager.dao.CompetitorDao;
import org.portfolio.competitormanager.dao.impl.CompetitorDaoImpl;
import org.portfolio.competitormanager.model.Competitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUser extends JFrame {
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JRadioButton competitorRadioButton;
    private JRadioButton adminRadioButton;
    private JButton registerButton;
    private JButton backToLoginButton;

    public RegisterUser() {
        setTitle("Register");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToLoginPage();
            }
        });

        ButtonGroup radioRoleGroup = new ButtonGroup();
        radioRoleGroup.add(competitorRadioButton);
        radioRoleGroup.add(adminRadioButton);
        competitorRadioButton.setSelected(true);
    }

    private void handleRegister() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String role = competitorRadioButton.isSelected() ? "competitor" : "admin";

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields");
            return;
        }

        try {
            CompetitorDao competitorDao = new CompetitorDaoImpl();
            Competitor competitor = new Competitor(0, username, password, role);
            int result = competitorDao.save(competitor);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Successfully registered");
                dispose();
                new LoginUser().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error registering");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database registering error");
        }
    }

    private void backToLoginPage() {
        dispose();
        new LoginUser().setVisible(true);
    }
}
