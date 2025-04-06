import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int priority;
    int remainingTime;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process tail = null;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (tail == null) {
            tail = newProcess;
            tail.next = tail;
        } else {
            newProcess.next = tail.next;
            tail.next = newProcess;
            tail = newProcess;
        }
    }

    public void executeProcesses() {
        if (tail == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int time = 0;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int completed = 0;
        int processCount = getProcessCount();

        Process current = tail.next;
        while (completed < processCount) {
            if (current.remainingTime > 0) {
                System.out.println(
                        "Executing Process " + current.pid + " (Remaining Time: " + current.remainingTime + ")");
                int executedTime = Math.min(current.remainingTime, timeQuantum);
                current.remainingTime -= executedTime;
                time += executedTime;

                if (current.remainingTime == 0) {
                    int turnAroundTime = time;
                    int waitingTime = turnAroundTime - current.burstTime;
                    totalWaitingTime += waitingTime;
                    totalTurnAroundTime += turnAroundTime;
                    System.out.println("Process " + current.pid + " completed. Turnaround Time: " + turnAroundTime
                            + ", Waiting Time: " + waitingTime);
                    completed++;
                }
            }
            current = current.next;
            displayQueue();
        }

        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double) totalTurnAroundTime / processCount);
    }

    private int getProcessCount() {
        if (tail == null)
            return 0;
        int count = 1;
        Process current = tail.next;
        while (current != tail) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void displayQueue() {
        if (tail == null) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Current Queue:");
        Process current = tail.next;
        do {
            System.out.println("PID: " + current.pid + ", Remaining Time: " + current.remainingTime + ", Priority: "
                    + current.priority);
            current = current.next;
        } while (current != tail.next);
    }
}

public class RoundRobinApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        RoundRobinScheduler scheduler = new RoundRobinScheduler(quantum);

        int choice;
        do {
            System.out.println("\n1. Add Process");
            System.out.println("2. Execute Processes");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burst = sc.nextInt();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    scheduler.addProcess(pid, burst, priority);
                    break;
                case 2:
                    scheduler.executeProcesses();
                    break;
                case 0:
                    System.out.println("Exiting Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
