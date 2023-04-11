import java.util.Scanner;

public class Employee {
    private String stafferId;
    private String name;
    private Double salary;

    public Employee() {
    }

    public Employee(String stafferId, String name, Double salary) {
        this.stafferId = stafferId;
        this.name = name;
        this.salary = salary;
    }

    public String getStafferId() {
        return stafferId;
    }

    public void setStafferId(String stafferId) {
        this.stafferId = stafferId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void inputEmployee() {
        Scanner sc = new Scanner(System.in);
        String names = "^[A-Z][a-z]*\\s[A-Z][a-z]*(\\s[A-Z][a-z]*){0,2}$";
        System.out.print("Enter ID: ");
        this.stafferId = sc.nextLine();
        while (true) {
            System.out.print("Enter name: ");
            this.name = sc.nextLine();
            if (this.name.matches(names)) {
                break;
            } else {
                System.out.println("\033[0;31mInvalid name\033[0m");
            }
        }
        while (true) {
            try {
                System.out.print("Enter salary: ");
                this.salary = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("\033[0;31mTry again!\033[0m");
            }
        }
    }

    public String getFirstName() {
        return this.name.substring(this.name.lastIndexOf(" ") + 1);
    }

    public void outputInformation() {
        System.out.printf("\033[0;34m%-15s \033[0;36m%-25s \033[0;32m%-15s\033[0m\n", stafferId.toUpperCase(),
                name.toUpperCase(),
                salary);
    }

    @Override
    public String toString() {
        return String.format("\033[0;34m%-15s \033[0;36m%-25s \033[0;32m%-15s\033[0m\n", stafferId.toUpperCase(),
                name.toUpperCase(),
                salary);
    }
}
