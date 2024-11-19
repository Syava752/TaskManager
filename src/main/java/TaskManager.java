import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(String title){
        Task taskToRemove = null;
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(title)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("������ \"" + title + "\" �������.");
        } else {
            System.out.println("������ � ��������� \"" + title + "\" �� �������.");
        }
    }

    public void changeTaskStatus(String title, boolean isCompleted){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(title)){
                task.setCompleted(isCompleted);
                System.out.println("������ ������ \"" + title + "\" ������ �� " + (isCompleted ? "���������" : "�� ���������") + ".");
                return;
            }
        }
        System.out.println("������ � ��������� \"" + title + "\" �� �������.");
    }

    public List<Task> getTasks(){
        return tasks;
    }

    // ����� ��� ���������� ����� � ����
    public void saveTaskToFile (String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(tasks); // ��������� ������ �����
        } catch (IOException e){
            System.out.println("������ ��� ���������� �����: " + e.getMessage());
        }
    }

    // ����� ��� �������� ����� �� �����
    public void loadTaskFromFile (String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            tasks = (List<Task>) ois.readObject(); // ��������� ������ �����
            System.out.println("������ ������� ��������� �� �����");
        } catch (IOException | ClassNotFoundException e){
            System.out.println("������ ��� �������� �����: " + e.getMessage());
            tasks = new ArrayList<>(); // ������ ������ ������, ���� ��������� ������
        }
    }
}
