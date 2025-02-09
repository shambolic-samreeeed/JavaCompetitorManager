package org.portfolio.competitormanager.ui;

import org.portfolio.competitormanager.dao.CompetitorDao;
import org.portfolio.competitormanager.dao.impl.CompetitorDaoImpl;
import org.portfolio.competitormanager.model.Competitor;
import org.portfolio.competitormanager.ui.RegisterUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUser extends JFrame {
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton registerButton;
    private JButton loginButton;
    private JFrame frame;

    public LoginUser() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        frame.setContentPane(contentPane);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }

        });

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                openRegistrationWindow();

            }
        });
    }

    private void handleLogin() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        try {
            CompetitorDao competitorDao = new CompetitorDaoImpl();
            Competitor competitor = competitorDao.findByUsername(username);

            if (competitor != null && competitor.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful");

                if (competitor.getRole().equals("admin")) {
                    JOptionPane.showMessageDialog(frame, "You have successfully logged in as admin");
                    dispose();
                    AdminLayout adminLayout = new AdminLayout(username);
                    adminLayout.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "You have successfully logged in as user");
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "database error" + ex.getMessage());
        }
    }

    private void openRegistrationWindow(){
        dispose();
        RegisterUser registerUser = new RegisterUser();
        registerUser.setVisible(true);

    }

}
