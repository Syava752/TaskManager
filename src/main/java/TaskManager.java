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
            System.out.println("Задача \"" + title + "\" удалена.");
        } else {
            System.out.println("Задача с названием \"" + title + "\" не найдена.");
        }
    }

    public void changeTaskStatus(String title, boolean isCompleted){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(title)){
                task.setCompleted(isCompleted);
                System.out.println("Статус задачи \"" + title + "\" изменён на " + (isCompleted ? "выполнено" : "не выполнено") + ".");
                return;
            }
        }
        System.out.println("Задача с названием \"" + title + "\" не найдена.");
    }

    public List<Task> getTasks(){
        return tasks;
    }

    // Метод для сохранения задач в файл
    public void saveTaskToFile (String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(tasks); // Сохраняем список задач
        } catch (IOException e){
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    // Метод для загрузки задач из файла
    public void loadTaskFromFile (String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            tasks = (List<Task>) ois.readObject(); // Загружаем список задач
            System.out.println("Задачи успешно загружены из файла");
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
            tasks = new ArrayList<>(); // Создаём пустой список, если произошла ошибка
        }
    }
}
