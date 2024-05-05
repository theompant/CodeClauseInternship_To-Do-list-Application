import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Enum for task priority
enum Priority {
    HIGH, MEDIUM, LOW
}

// Class to represent a task
class Task {
    String description;
    Priority priority;
    Date dueDate;
    boolean completed;

    // Constructor
    public Task(String description, Priority priority, Date dueDate) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false; // Initially not completed
    }

    // Method to mark a task as complete
    public void markCompleted() {
        this.completed = true;
    }

    // Method to display task information
    @Override
    public String toString() {
        return String.format("Task: %s, Priority: %s, Due Date: %s, Completed: %s", 
                             description, priority, dueDate, completed);
    }
}

public class ToDoList {
    private static List<Task> tasks = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    listTasks();
                    break;
                case 5:
                    System.out.println("Exiting To-Do List...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ... (showMenu, addTask, editTask methods remain similar, but now work with Task objects) ...

    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static void showMenu() {
        System.out.println("\nTo-Do List Options:");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Remove Task");
        System.out.println("4. List Tasks");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
    
        System.out.print("Enter task priority (HIGH, MEDIUM, LOW): ");
        Priority priority;
        try {
            priority = Priority.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Please choose from HIGH, MEDIUM, or LOW.");
            return; // Exit addTask if priority is invalid
        }
    
        System.out.print("Enter due date (dd/MM/yyyy): ");
        Date dueDate;
        try {
            dueDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return; // Exit addTask if date is invalid
        }
    
        Task newTask = new Task(description, priority, dueDate);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    private static void editTask() {
        listTasks();
        System.out.print("Enter the number of the task to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 1 && index <= tasks.size()) {
            Task taskToEdit = tasks.get(index - 1); 

            System.out.print("Enter new description (or press Enter to keep): ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                taskToEdit.description = newDescription;
            }

            // Similar logic for editing priority and due date...

            System.out.println("Task edited successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void removeTask() {
        listTasks();
        System.out.print("Enter the number of the task to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 1 && index <= tasks.size()) {
            tasks.remove(index - 1); // Efficient removal from LinkedList
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your To-Do List is empty.");
        } else {
            System.out.println("\nTo-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i)); // Utilizes Task's toString method
            }
        }
    }
}