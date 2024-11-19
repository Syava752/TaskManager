import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // ��������� ������ ��� ������
        taskManager.loadTaskFromFile("tasks.dat");

        while (true) {
            System.out.println("1. �������� ������");
            System.out.println("2. �������� ������ ������ ������");
            System.out.println("3. ������� ������");
            System.out.println("4. �������� ������");
            System.out.println("5. �����");

            int choise = scanner.nextInt();
            scanner.nextLine(); // ���������� ����� ������ ����� ����� �����
            switch (choise) {
                case 1:
                    System.out.println("������� �������� ������");
                    String title = scanner.nextLine();
                    System.out.println("������� �������� ������");
                    String description = scanner.nextLine();
                    taskManager.addTask(new Task(title, description));
                    break;
                case 2:
                    System.out.println("������� �������� ������ ��� ��������� �������:");
                    String titleToChange = scanner.nextLine();
                    System.out.println("������� ����� ������ (1 - ���������, 0 - �� ���������):");
                    int status = scanner.nextInt();
                    scanner.nextLine(); //��������� ����� ������
                    taskManager.changeTaskStatus(titleToChange, status == 1);
                    break;
                case 3:
                    System.out.println("������� �������� ��������� ������");
                    String titleToRemove = scanner.nextLine();
                    taskManager.removeTask(titleToRemove);
                    break;
                case 4:
                    for (Task task : taskManager.getTasks()) {
                        System.out.println(task.getTitle() + " - " + (task.isCompleted() ? "���������" : "�� ���������"));
                    }
                    break;
                case 5:
                    taskManager.saveTaskToFile("tasks.dat");
                    System.out.println("���������� ������");
                    System.exit(0);
            }
        }
    }
}
