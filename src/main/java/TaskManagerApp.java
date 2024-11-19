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
        // Создаём главное окно
        JFrame frame = new JFrame("Менеджер задач");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);

        // Панель для задач
        JTextArea taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);

        // Обновление списка задач
        Runnable updateTaskList = () -> {
            StringBuilder taskList = new StringBuilder();
            for (Task task : taskManager.getTasks()) {
                taskList.append(task.getTitle())
                        .append(" - ")
                        .append(task.isCompleted() ? "Выполнено" : "Не выполнено")
                        .append("\n");
            }
            taskArea.setText(taskList.toString());
        };

        // Кнопка добавления задач
        JButton addTaskButton = new JButton("Добавить задачу");
        addTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Введите название задачи:");
            if (title == null || title.trim().isEmpty()) return;

            String description = JOptionPane.showInputDialog(frame, "Введите описание задачи:");
            if (description == null || description.trim().isEmpty()) return;

            taskManager.addTask((new Task(title, description)));
            updateTaskList.run();
        });

        // Кнопка удаления задачи
        JButton removeTaskButton = new JButton("Удалить задачу");
        removeTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Введите название задачи для удаления:");
            if (title == null || title.trim().isEmpty()) return;

            taskManager.removeTask(title);
            updateTaskList.run();
        });

        // Кнопка изменения статуса задачи
        JButton changeTaskButton = new JButton("Изменить статус задачи");
        changeTaskButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Введите название задачи для изменения статуса:");
            if (title == null || title.trim().isEmpty()) return;

            int result = JOptionPane.showConfirmDialog(frame, "Пометить как выполнено?", "Изменение статуса",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                taskManager.changeTaskStatus(title, true);
            } else if (result == JOptionPane.NO_OPTION) {
                taskManager.changeTaskStatus(title, false);
            }
            updateTaskList.run();
        });

        //Кнопка сохранения задач
        JButton saveButton = new JButton("Сохранить задачи");
        saveButton.addActionListener(e -> {
            taskManager.saveTaskToFile("tasks.dat");
            JOptionPane.showMessageDialog(frame, "Задачи сохранены!");
        });

        // Размещение кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1,5,5));
        buttonPanel.add(addTaskButton);
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(changeTaskButton);
        buttonPanel.add(saveButton);

        // Размещение компонентов
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        // Отображение списка задач при старте
        updateTaskList.run();

        // Показываем окно
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerApp::new);
    }
}
