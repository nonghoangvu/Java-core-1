import java.util.*;
import java.util.concurrent.*;

public class Manager {
    ArrayList<Employee> listEmployees = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearLine() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.print("\n\033[1A\033[2K\r");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Y1
    public void inputListEmployees() {
        int count = 0;
        System.out.println("+---------------------------------------------------+");
        System.out.println("|           \033[0;32mEnter a list of employees\033[0m               |");
        System.out.println("+---------------------------------------------------+");
        while (true) {
            count++;
            System.out.println("Staffer " + count);
            Employee employee = new Employee();
            employee.inputEmployee();
            listEmployees.add(employee);
            System.out.print("Dou you want continue? [Y/N]: ");
            if (sc.nextLine().equalsIgnoreCase("N")) {
                System.out.println();
                clearScreen();
                break;
            }
            System.out.println();
        }
    }

    // Y2
    public void outputListEmployess() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|    \033[0;32mDisplay the list of employees on the screen.\033[0m   |");
        System.out.println("+---------------------------------------------------+");
        if (listEmployees.size() > 0) {
            System.out.printf("\033[0;33m%-15s %-25s %-15s\n\033[0m", "Staff ID", "Full Name", "Salary");
            for (Employee std : listEmployees) {
                std.outputInformation();
            }
        } else {
            System.out.print("\033[0;31mNo data yet\033[0m");
        }
        System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
        sc.nextLine();
    }

    // Y3
    public void findEmployees() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                 \033[0;32mFind employee by ID.\033[0m              |");
        System.out.println("+---------------------------------------------------+");
        Boolean check = true;
        while (check) {
            if (listEmployees.size() == 0) {
                System.out.print("\033[0;31mNo data yet\033[0m\n");
                clearLine();
                break;
            } else {
                System.out.print("Staffer ID to find: ");
                String findId = sc.nextLine();
                Boolean found = false;
                for (Employee x : listEmployees) {
                    if (x.getStafferId().equalsIgnoreCase(findId)) {
                        if (!found) {
                            System.out.printf("\n\033[0;33m%-15s %-25s %-15s\n\033[0m", "Staffer ID", "Full Name",
                                    "Salary");
                            found = true;
                        }
                        System.out.println(x);
                    }
                }
                if (!found) {
                    System.out.print("\033[0;31mNo staffer found with ID: " + findId + "\033[0m");
                    clearLine();
                }
                System.out.print("Dou you want continue? [Y/N]: ");
                if (sc.nextLine().equalsIgnoreCase("N")) {
                    System.out.println();
                    clearScreen();
                    check = false;
                    break;
                }
            }
        }
    }

    // Y4
    public void removeEmployeeById() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                 \033[0;32mRemove employee by ID.\033[0m            |");
        System.out.println("+---------------------------------------------------+");
        while (true) {
            if (listEmployees.size() == 0) {
                System.out.print("\033[0;31mNo data yet\033[0m\n");
            } else {
                System.out.print("Staffer ID to delete: ");
                String stafferId = sc.nextLine();
                int initialSize = listEmployees.size();
                listEmployees.removeIf(staffer -> staffer.getStafferId().equalsIgnoreCase(stafferId));
                int finalSize = listEmployees.size();
                if (finalSize < initialSize) {
                    System.out.printf("\033[0;32mRemove success ID: %s\033[0m\n", stafferId.toUpperCase());
                } else {
                    System.out.println("\033[0;31mStaffer ID not found: " + stafferId.toUpperCase() + "\033[0m");
                }
            }
            System.out.print("Do you want to continue? [Y/N]: ");
            if (sc.nextLine().equalsIgnoreCase("N")) {
                System.out.println();
                break;
            }
        }
    }

    // Y5
    public void updateEmployeeById() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|       \033[0;32mUpdate employee information by ID.\033[0m          |");
        System.out.println("+---------------------------------------------------+");
        String staffId, newName;
        while (true) {
            if (listEmployees.size() == 0) {
                System.out.print("\033[0;31mNo data yet\033[0m\n");
            } else {
                System.out.print("Staffer ID: ");
                staffId = sc.nextLine();
                for (Employee y : listEmployees) {
                    if ((y.getStafferId().equalsIgnoreCase(staffId))) {
                        System.out.printf("\033[0;32mStaffer ID found: %s\n\033[0m", staffId.toUpperCase());
                        System.out.printf("\n\033[0;33m%-15s %-25s %-15s\n\033[0m", "Staffer ID", "Full Name",
                                "Salary");
                        break;
                    }
                }
                for (Employee x : listEmployees) {
                    if (x.getStafferId().equalsIgnoreCase(staffId)) {
                        System.out.println(x);
                        String name = "^[A-Z][a-z]*\\s[A-Z][a-z]*(\\s[A-Z][a-z]*){0,2}$";
                        while (true) {
                            System.out.print("New name: ");
                            newName = sc.nextLine();
                            if (newName.matches(name)) {
                                break;
                            } else {
                                System.out.println("\033[0;31mStaffer ID malformed\033[0m");
                            }

                        }
                        double salary;
                        while (true) {
                            try {
                                System.out.print("New salarys: ");
                                salary = Double.parseDouble(sc.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("\033[0;31mTry again!\033[0m");

                            }
                        }
                        x.setName(newName);
                        x.setSalary(salary);
                        System.out.printf("\033[0;32mUpdate success staffer id: %s\n\033[0m",
                                x.getStafferId().toUpperCase());
                    }
                }
            }
            System.out.print("Do you want to continue? [Y/N]: ");
            if (sc.nextLine().equalsIgnoreCase("N")) {
                System.out.println();
                break;
            }
        }
    }

    // Y6
    public void findBySalary() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|           \033[0;32mFind employees by salary range.\033[0m         |");
        System.out.println("+---------------------------------------------------+");
        Double minSalary, maxSalary;
        while (true) {
            if (listEmployees.size() == 0) {
                System.out.print("\033[0;31mNo data yet\033[0m\n");
            } else {
                try {
                    System.out.print("Enter the minimum salary: ");
                    minSalary = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter the maximum salary: ");
                    maxSalary = Double.parseDouble(sc.nextLine());
                    if (minSalary > maxSalary) {
                        System.out.println("\033[0;31mInvalid\033[0m");
                        break;
                    }
                    ArrayList<Employee> fillList = new ArrayList<>();
                    for (Employee employee : listEmployees) {
                        if (employee.getSalary() >= minSalary && employee.getSalary() <= maxSalary) {
                            fillList.add(employee);
                        }
                    }
                    System.out.printf(
                            "\n\033[0;32mList of staffer whose salary are within the range from %.1f to %.1f\033[0m",
                            minSalary,
                            maxSalary);
                    System.out.printf("\n\033[0;33m%-15s %-25s %-15s\n\033[0m", "Staffer ID", "Full Name", "Salary");

                    for (Employee employee : fillList) {
                        System.out.println(employee);
                    }
                } catch (Exception e) {
                    System.out.println("\033[0;31mInvalid salary\033[0m");
                }
            }
            System.out.print("Do you want to continue? [Y/N]: ");
            if (sc.nextLine().equalsIgnoreCase("N")) {
                System.out.println();
                break;
            }
        }
    }

    // Y7
    public void sortEmployeeByFirstName() {
        if (listEmployees.size() == 0) {
            System.out.print("\033[0;31mNo data yet\033[0m");
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        } else {
            listEmployees.sort((emy_1, emy_2) -> {
                return emy_1.getFirstName().compareTo(emy_2.getFirstName());
            });
            System.out.println("+---------------------------------------------------+");
            System.out.println("|               \033[0;32mSort staffer by name\033[0m                |");
            System.out.println("+---------------------------------------------------+");
            for (Employee std : listEmployees) {
                std.outputInformation();
            }
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        }
        return;
    }

    // Y8
    public void sortEmployeeBySalary() {
        if (listEmployees.size() == 0) {
            System.out.print("\033[0;31mNo data yet\033[0m");
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        } else {
            listEmployees.sort((emy_1, emy_2) -> {
                Double emy_01 = emy_1.getSalary();
                Double emy_02 = emy_2.getSalary();
                return emy_01.compareTo(emy_02);
            });
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                \033[0;32mSort staffer by salary\033[0m             |");
            System.out.println("+---------------------------------------------------+");
            for (Employee std : listEmployees) {
                std.outputInformation();
            }
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        }
        return;
    }

    // Y9
    public void topEmployees() {
        if (listEmployees.size() == 0) {
            System.out.print("\033[0;31mNo data yet\033[0m");
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        } else {
            listEmployees.sort((emy_1, emy_2) -> {
                Double emy_01 = emy_1.getSalary();
                Double emy_02 = emy_2.getSalary();
                return emy_02.compareTo(emy_01);
            });
            List<Employee> topFiveEmployee = listEmployees.subList(0, Math.min(listEmployees.size(), 5));
            System.out.println("+---------------------------------------------------+");
            System.out.println("|      \033[0;32mDisplay 5 staffer with highest salary\033[0m        |");
            System.out.println("+---------------------------------------------------+");
            System.out.printf("\n\033[0;33m%-15s %-25s %-15s\n\033[0m", "Staffer ID", "Full Name", "Salary");
            for (Employee employee : topFiveEmployee) {
                System.out.println(employee);
            }
            System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
            sc.nextLine();
        }
        return;
    }

    // Y10
    public String getFullName() {
        Random random = new Random();
        String[] lastName = { "Nguyen", "Tran", "Le", "Nong", "Hoang", "Phan" };
        String[] middleName = { "Thi", "Dinh", "Duc", "The", "Hoang", "Quang" };
        String[] firstName = { "An", "Binh", "Chi", "Dung", "Giang", "Hien", "Hoa", "Hung", "Huong", "Vu", "Nhi",
                "Linh", "Nga", "Tam", "Thao" };
        int lastIndex = random.nextInt(lastName.length);
        int middleIndex = random.nextInt(middleName.length);
        int firstIndex = random.nextInt(firstName.length);
        String fullName = lastName[lastIndex] + " " + middleName[middleIndex] + " " + firstName[firstIndex];
        return fullName;
    }

    public void fixData() {
        Random random = new Random();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|           \033[0;32mAutomatically add data\033[0m                  |");
        System.out.println("+---------------------------------------------------+");
        while (true) {
            try {
                System.out.print("How much data do you want to add?: ");
                int data = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < data; i++) {
                    int id = (int) (Math.random() * 90000) + 10000;
                    double salary = Math.round((random.nextDouble() * 9900 + 100) * 100) / 100.0;
                    listEmployees.add(new Employee("PH" + id, getFullName(), salary));
                }
                System.out.println("\033[0;32mFix Data Success\033[0m");
                System.out.print("\n\033[0;33mPress Enter to continue...\033[0m");
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("\033[0;31mAdd failed data\033[0m");
                System.out.print("Do you want to continue? [Y/N]: ");
                if (sc.nextLine().equalsIgnoreCase("N")) {
                    System.out.println();
                    break;
                }
            }
        }
    }
}
