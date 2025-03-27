import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class TaskManagerGUI extends JFrame {
    private JTextField nameField;
    private JComboBox<String> categoryBox;
    private JCheckBox completedCheck;
    private JTable table;
    private DefaultTableModel tableModel;

    public TaskManagerGUI() {
        setTitle("Task Manager Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Form", createFormPanel());
        tabbedPane.add("Table", createTablePanel());

        setJMenuBar(createMenuBar());
        add(tabbedPane);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Task Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Category:"));
        categoryBox = new JComboBox<>(new String[] { "Work", "Personal", "Other" });
        panel.add(categoryBox);

        panel.add(new JLabel("Completed:"));
        completedCheck = new JCheckBox();
        panel.add(completedCheck);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addEntry());
        panel.add(addButton);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new String[] { "Task Name", "Category", "Completed" }, 0);
        table = new JTable(tableModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Tasks");
        saveItem.addActionListener(e -> saveData());
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load Tasks");
        loadItem.addActionListener(e -> loadData());
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);
        return menuBar;
    }

    private void addEntry() {
        String name = nameField.getText();
        String category = (String) categoryBox.getSelectedItem();
        String completed = completedCheck.isSelected() ? "Yes" : "No";

        if (!name.isEmpty()) {
            tableModel.addRow(new Object[] { name, category, completed });
            nameField.setText("");
            completedCheck.setSelected(false);
        }
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.csv"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2) + "\n");
            }
            JOptionPane.showMessageDialog(this, "Tasks saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"))) {
            tableModel.setRowCount(0);
            String line;
            while ((line = reader.readLine()) != null) {
                tableModel.addRow(line.split(","));
            }
            JOptionPane.showMessageDialog(this, "Tasks loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerGUI().setVisible(true));
    }
}