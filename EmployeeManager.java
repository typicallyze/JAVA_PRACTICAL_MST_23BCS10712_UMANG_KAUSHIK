import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

public class EmployeeManager {
    private final Map<Integer, String> employeeMap;

    public EmployeeManager() {
        this.employeeMap = new HashMap<>();
    }

    public void addEmployee(int id, String name) {
        employeeMap.put(id, name);
        System.out.println("Employee added successfully.");
    }

    public String getEmployeeNameById(int id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        return employeeMap.get(id);
    }

    public void displayAllEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        System.out.println("\n--- All Employees ---");
        for (Map.Entry<Integer, String> entry : employeeMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Find Employee by ID");
            System.out.println("3. Display All Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Employee Name: ");
                        String name = scanner.nextLine();
                        manager.addEmployee(id, name);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format. Please enter a number.");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter Employee ID to search: ");
                        int searchId = Integer.parseInt(scanner.nextLine());
                        String employeeName = manager.getEmployeeNameById(searchId);
                        System.out.println("Employee Found: " + employeeName);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format. Please enter a number.");
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    manager.displayAllEmployees();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}
