/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package employemanagementsystem;

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeManagementSystem extends JFrame implements ActionListener {
    // GUI components
    private JLabel nameLabel, ageLabel, salaryLabel;
    private JTextField nameTextField, ageTextField, salaryTextField;
    private JButton addButton;
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/emp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    
    public EmployeManagementSystem() {
        // Set up the GUI
        setTitle("Employee Management System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create GUI components
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        salaryLabel = new JLabel("Salary:");
        
        nameTextField = new JTextField();
        ageTextField = new JTextField();
        salaryTextField = new JTextField();
        
        addButton = new JButton("Add Employee");
        addButton.addActionListener(this);
        
        // Set layout manager
        setLayout(new GridLayout(4, 2));
        
        // Add components to the frame
        add(nameLabel);
        add(nameTextField);
        add(ageLabel);
        add(ageTextField);
        add(salaryLabel);
        add(salaryTextField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            EmployeManagementSystem ems = new EmployeManagementSystem();
            ems.setVisible(true);
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            double salary = Double.parseDouble(salaryTextField.getText());
            
            // Save employee to the database
            saveEmployee(name, age, salary);
            
            // Clear input fields
            nameTextField.setText("");
            ageTextField.setText("");
            salaryTextField.setText("");
            
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
        }
    }
    
    private void saveEmployee(String name, int age, double salary) {
        try {
            // Create database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Prepare the SQL statement
            String sql = "INSERT INTO empl (name, age, salary) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setDouble(3, salary);
            
            // Execute the statement
            statement.executeUpdate();
            
            // Close resources
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving employee!");
        }
    }
}


//public class EmployeManagementSystem {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}
