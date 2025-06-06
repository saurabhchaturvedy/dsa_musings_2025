package PostKarat06June.EmployeeDirectory;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create groups
        Group root = new Group("Engineering", null);
        Group frontend = new Group("Frontend", root);
        Group backend = new Group("Backend", root);
        Group infra = new Group("Infra", backend);

        // Create employees
        Employee e1 = new Employee("e1", frontend);
        Employee e2 = new Employee("e2", backend);
        Employee e3 = new Employee("e3", infra);

        // Add to directory
        EmployeeHierarchyService directory = new EmployeeHierarchyService();
        directory.addEmployee(e1);
        directory.addEmployee(e2);
        directory.addEmployee(e3);

        // Use service to find closest common group
        ClosestCommonFinder service = new ClosestCommonFinder();
        Group commonGroup = service.getLCA(Arrays.asList(e1, e2, e3));

        System.out.println("Closest common group: " + (commonGroup != null ? commonGroup.getName() : "None"));
    }
}
