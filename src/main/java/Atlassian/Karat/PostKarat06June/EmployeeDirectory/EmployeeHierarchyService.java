package PostKarat06June.EmployeeDirectory;

import java.util.HashMap;
import java.util.Map;

public class EmployeeHierarchyService {


    Map<String, Employee> employeeMap = new HashMap<>();


    public void addEmployee(Employee employee) {

        employeeMap.put(employee.getId(), employee);
    }

    public Employee getEmployee(String id) {

        return employeeMap.get(id);
    }
}
