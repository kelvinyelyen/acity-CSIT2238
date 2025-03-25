import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MySQLGuiApp extends JFrame {
    private JTextField nameField, emailField;
    private JTextArea resultArea;
    private Connection connection;

    public MySQLGuiApp() {
        setTitle("MySQL Java GUI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UI Panel for input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> insertData());
        inputPanel.add(insertButton);

        JButton fetchButton = new JButton("Fetch Users");
        fetchButton.addActionListener(e -> fetchData());
        inputPanel.add(fetchButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area
        resultArea = new JTextArea();
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Connect to MySQL
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root"; // Change if needed
        String password = ""; // Set MySQL password if required

        try {
            // Explicitly load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, user, password);
            resultArea.setText("Connected to MySQL\n");
        } catch (ClassNotFoundException e) {
            resultArea.setText("JDBC Driver not found. Ensure mysql-connector-java is in classpath.");
        } catch (SQLException e) {
            resultArea.setText("Connection failed: " + e.getMessage());
        }
    }

    private void insertData() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty()) {
            resultArea.setText("Please enter both name and email.");
            return;
        }

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            resultArea.setText("User inserted successfully.");
        } catch (SQLException e) {
            resultArea.setText("Insert failed: " + e.getMessage());
        }
    }

    private void fetchData() {
        String query = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            StringBuilder result = new StringBuilder("Users:\n");
            while (rs.next()) {
                result.append(rs.getInt("id")).append(". ")
                        .append(rs.getString("name")).append(" - ")
                        .append(rs.getString("email")).append("\n");
            }

            resultArea.setText(result.toString());
        } catch (SQLException e) {
            resultArea.setText("Fetch failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MySQLGuiApp().setVisible(true));
    }
}
