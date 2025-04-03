import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class App extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "davinci";

    private JTextField idField, nameField;
    private JTextArea outputArea;

    public App() {
        setTitle("Student Database Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> insertStudent());
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> deleteStudent());
        inputPanel.add(deleteButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private Connection connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            outputArea.append("✓ Successfully connected to database\n");
            return conn;
        } catch (SQLException e) {
            outputArea.append("✗ Connection failed: " + e.getMessage() + "\n");
            throw e;
        }
    }

    private void insertStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();

            String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
            try (Connection conn = connect();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.executeUpdate();
                outputArea.append("✓ Student " + name + " (ID: " + id + ") added successfully\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("✗ Invalid ID format. Please enter a number.\n");
        } catch (SQLException e) {
            outputArea.append("✗ Database error: " + e.getMessage() + "\n");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());

            String sql = "DELETE FROM students WHERE id = ?";
            try (Connection conn = connect();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    outputArea.append("✓ Student with ID " + id + " deleted successfully\n");
                } else {
                    outputArea.append("✗ No student found with ID " + id + "\n");
                }
            }
        } catch (NumberFormatException e) {
            outputArea.append("✗ Invalid ID format. Please enter a number.\n");
        } catch (SQLException e) {
            outputArea.append("✗ Database error: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new App();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}