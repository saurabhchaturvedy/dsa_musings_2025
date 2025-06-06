package PostKarat06June.EmployeeDirectory.LevelUp3;

import java.util.HashSet;
import java.util.Set;

public class Group {


    String name;
    Set<String> employees = new HashSet<>();

    public Group(String name) {
        this.name = name;
    }


    public void addEmployee(String employeeId) {

        this.employees.add(employeeId);
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmployees() {
        return employees;
    }
}
