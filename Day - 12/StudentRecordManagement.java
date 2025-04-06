import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private Student head;

    public void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
        } else {
            Student temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newStudent;
        }
    }

    public void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 0) {
            System.out.println("Invalid position");
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        if (pos == 1) {
            newStudent.next = head;
            head = newStudent;
            return;
        }
        Student temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRollNumber(int roll) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.rollNumber == roll) {
            head = head.next;
            System.out.println("Record deleted.");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Record not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Record deleted.");
        }
    }

    public void searchByRollNumber(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Record Found:");
                System.out.println("Roll: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Record not found.");
    }

    public void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Record not found.");
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No records to display.");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();
        int choice;

        do {
            System.out.println("\nStudent Record Management");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Roll Number");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Update Grade by Roll Number");
            System.out.println("7. Display All");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    if (choice == 1) {
                        list.addAtBeginning(roll, name, age, grade);
                    } else if (choice == 2) {
                        list.addAtEnd(roll, name, age, grade);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        list.addAtPosition(pos, roll, name, age, grade);
                    }
                    break;
                case 4:
                    System.out.print("Enter Roll Number to Delete: ");
                    int delRoll = sc.nextInt();
                    list.deleteByRollNumber(delRoll);
                    break;
                case 5:
                    System.out.print("Enter Roll Number to Search: ");
                    int searchRoll = sc.nextInt();
                    list.searchByRollNumber(searchRoll);
                    break;
                case 6:
                    System.out.print("Enter Roll Number to Update Grade: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();
                    list.updateGrade(updateRoll, newGrade);
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 0:
                    System.out.println("Exiting Program.");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice != 0);
    }
}
