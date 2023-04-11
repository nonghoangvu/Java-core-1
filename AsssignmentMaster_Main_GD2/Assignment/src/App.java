import java.util.*;
import java.util.concurrent.*;

public class App {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearLine() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.print("\n\033[1A\033[2K\r");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void loading() {
        clearScreen();
        System.out.print("Loading.");
        clearLine();
        System.out.print("Loading..");
        clearLine();
        System.out.print("Loading...");
        clearLine();
    }

    public static int menu() {
        int choose;
        clearScreen();
        do {
            System.out.println("+---------------------------------------------------+");
            System.out.println("|             \033[0;31mStaffer Management System\033[0m             |");
            System.out.println("+---------------------------------------------------+");
            System.out.println("|   \033[0;31m0\033[0m. Exit                                         |");
            System.out.println("|   \033[0;31m1\033[0m. Enter a list of employees from the keyboard. |");
            System.out.println("|   \033[0;31m2\033[0m. Display the list of employees on the screen. |");
            System.out.println("|   \033[0;31m3\033[0m. Find employee by ID                          |");
            System.out.println("|   \033[0;31m4\033[0m. Delete employee by ID                        |");
            System.out.println("|   \033[0;31m5\033[0m. Update employee information by ID            |");
            System.out.println("|   \033[0;31m6\033[0m. Find employees by salary range               |");
            System.out.println("|   \033[0;31m7\033[0m. Sort employees by last name and first name.  |");
            System.out.println("|   \033[0;31m8\033[0m. Sort employees by salary.                    |");
            System.out.println("|   \033[0;31m9\033[0m. The top 5 employees with the highest salary. |");
            System.out.println("|   \033[0;31m10\033[0m. Fix data                                    |");
            System.out.println("+---------------------------------------------------+");
            try {
                System.out.print("Choose an option: ");
                choose = Integer.parseInt(new Scanner(System.in).nextLine());
                return choose;
            } catch (Exception e) {
                return 1000;
            }
        } while (!(choose >= 0 && choose <= 10));
    }

    public static void main(String[] args) throws Exception {
        // loading();
        Manager manager = new Manager();
        boolean status = true;
        while (status) {
            switch (menu()) {
                case 0:
                    status = false;
                    clearScreen();
                    System.out.println("\033[0;31mExit program!\033[0m");
                    break;
                case 1:
                    manager.inputListEmployees();
                    break;
                case 2:
                    manager.outputListEmployess();
                    break;
                case 3:
                    manager.findEmployees();
                    break;
                case 4:
                    manager.removeEmployeeById();
                    break;
                case 5:
                    manager.updateEmployeeById();
                    break;
                case 6:
                    manager.findBySalary();
                    break;
                case 7:
                    manager.sortEmployeeByFirstName();
                    break;
                case 8:
                    manager.sortEmployeeBySalary();
                    break;
                case 9:
                    manager.topEmployees();
                    break;
                case 10:
                    manager.fixData();
                    break;
                default:
                    System.out.println("\033[0;31mTry again!\033[0m");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }
}
