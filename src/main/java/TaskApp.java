import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Загружаем задачи при старте
        taskManager.loadTaskFromFile("tasks.dat");

        while (true) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Изменить статус задачи задачи");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Показать задачи");
            System.out.println("5. Выйти");

            int choise = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки после ввода числа
            switch (choise) {
                case 1:
                    System.out.println("Введите название задачи");
                    String title = scanner.nextLine();
                    System.out.println("Введите описание задачи");
                    String description = scanner.nextLine();
                    taskManager.addTask(new Task(title, description));
                    break;
                case 2:
                    System.out.println("Введите название задачи для изменения статуса:");
                    String titleToChange = scanner.nextLine();
                    System.out.println("Введите новый статус (1 - выполнено, 0 - не выполнено):");
                    int status = scanner.nextInt();
                    scanner.nextLine(); //Считываем новую строку
                    taskManager.changeTaskStatus(titleToChange, status == 1);
                    break;
                case 3:
                    System.out.println("Введите название удаляемой задачи");
                    String titleToRemove = scanner.nextLine();
                    taskManager.removeTask(titleToRemove);
                    break;
                case 4:
                    for (Task task : taskManager.getTasks()) {
                        System.out.println(task.getTitle() + " - " + (task.isCompleted() ? "Выполнено" : "Не выполнено"));
                    }
                    break;
                case 5:
                    taskManager.saveTaskToFile("tasks.dat");
                    System.out.println("Завершение работы");
                    System.exit(0);
            }
        }
    }
}
