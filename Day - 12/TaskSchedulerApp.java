import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = newTask;
            current = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = newTask;
            current = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }

    public void addAtPosition(int pos, int taskId, String taskName, int priority, String dueDate) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) {
            tail = newTask;
        }
    }

    public void removeById(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head, prev = tail;
        do {
            if (temp.taskId == taskId) {
                if (temp == head) {
                    if (head == tail) {
                        head = tail = current = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                        if (current == temp)
                            current = head;
                    }
                } else {
                    prev.next = temp.next;
                    if (temp == tail) {
                        tail = prev;
                    }
                    if (current == temp)
                        current = temp.next;
                }
                System.out.println("Task removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Task not found.");
    }

    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Current Task -> ID: " + current.taskId + ", Name: " + current.taskName + ", Priority: "
                    + current.priority + ", Due Date: " + current.dueDate);
        }
    }

    public void moveToNextTask() {
        if (current != null) {
            current = current.next;
            viewCurrentTask();
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority
                    + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority
                        + ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks with the given priority.");
        }
    }
}

public class TaskSchedulerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\nTask Scheduler Menu");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            int taskId, priority, pos;
            String taskName, dueDate;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Task ID: ");
                    taskId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    taskName = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    dueDate = sc.nextLine();
                    if (choice == 1)
                        scheduler.addAtBeginning(taskId, taskName, priority, dueDate);
                    else if (choice == 2)
                        scheduler.addAtEnd(taskId, taskName, priority, dueDate);
                    else {
                        System.out.print("Enter Position: ");
                        pos = sc.nextInt();
                        scheduler.addAtPosition(pos, taskId, taskName, priority, dueDate);
                    }
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    taskId = sc.nextInt();
                    scheduler.removeById(taskId);
                    break;
                case 5:
                    scheduler.viewCurrentTask();
                    break;
                case 6:
                    scheduler.moveToNextTask();
                    break;
                case 7:
                    scheduler.displayAllTasks();
                    break;
                case 8:
                    System.out.print("Enter Priority to search: ");
                    priority = sc.nextInt();
                    scheduler.searchByPriority(priority);
                    break;
                case 0:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
