import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TaskManagerApp {
    private TaskManager taskManager;

    public TaskManagerApp(){
        taskManager = new TaskManager();
        taskManager.loadTaskFromFile("tasks.dat");
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        // ������ ������� ����
        JFrame frame = new JFrame("�������� �����");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);

        // ������ ��� �����
        JTextArea taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);

        // ���������� ������ �����
        Runnable updateTaskList = () -> {
            StringBuilder taskList = new StringBuilder();
            for (Task task : taskManager.getTasks()) {
                taskList.append(task.getTitle())
                        .append(" - ")
                        .append(task.isCompleted() ? "���������" : "�� ���������")
                        .append("\n");
            }
            taskArea.setText(taskList.toString());
        };

        // ������ ���������� �����
        JButton addTaskButton = new JButton("�������� ������");
        addTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "������� �������� ������:");
            if (title == null || title.trim().isEmpty()) return;

            String description = JOptionPane.showInputDialog(frame, "������� �������� ������:");
            if (description == null || description.trim().isEmpty()) return;

            taskManager.addTask((new Task(title, description)));
            updateTaskList.run();
        });

        // ������ �������� ������
        JButton removeTaskButton = new JButton("������� ������");
        removeTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "������� �������� ������ ��� ��������:");
            if (title == null || title.trim().isEmpty()) return;

            taskManager.removeTask(title);
            updateTaskList.run();
        });

        // ������ ��������� ������� ������
        JButton changeTaskButton = new JButton("�������� ������ ������");
        changeTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "������� �������� ������ ��� ��������� �������:");
            if (title == null || title.trim().isEmpty()) return;

            int result = JOptionPane.showConfirmDialog(frame, "�������� ��� ���������?", "��������� �������",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                taskManager.changeTaskStatus(title, true);
            } else if (result == JOptionPane.NO_OPTION) {
                taskManager.changeTaskStatus(title, false);
            }
            updateTaskList.run();
        });

        //������ ���������� �����
        JButton saveButton = new JButton("��������� ������");
        saveButton.addActionListener(e -> {
            taskManager.saveTaskToFile("tasks.dat");
            JOptionPane.showMessageDialog(frame, "������ ���������!");
        });

        // ���������� ������
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1,5,5));
        buttonPanel.add(addTaskButton);
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(changeTaskButton);
        buttonPanel.add(saveButton);

        // ���������� �����������
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        // ����������� ������ ����� ��� ������
        updateTaskList.run();

        // ���������� ����
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerApp::new);
    }
}
