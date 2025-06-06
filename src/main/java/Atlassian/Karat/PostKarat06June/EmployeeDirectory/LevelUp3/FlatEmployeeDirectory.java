package PostKarat06June.EmployeeDirectory.LevelUp3;

import java.util.*;

public class FlatEmployeeDirectory {


    Map<String, Group> groups = new HashMap<>();


    public void addGroup(String groupName) {

        groups.putIfAbsent(groupName, new Group(groupName));
    }

    public void addEmployeeToGroup(String employeeId, String groupName) {

        groups.computeIfAbsent(groupName, Group::new).addEmployee(employeeId);
    }


    public Group commonEmployees(List<String> employeeIds) {

        for (Group group : groups.values()) {

            Set<String> employees = group.getEmployees();

            boolean hasAllEmployees = true;

            for (String empId : employeeIds) {

                if (!employees.contains(empId)) {
                    hasAllEmployees = false;
                    break;
                }
            }

            if (hasAllEmployees) return group;
        }

        return null;
    }


    public static void main(String[] args) {



        FlatEmployeeDirectory directory = new FlatEmployeeDirectory();

        directory.addGroup("Engineering");
        directory.addGroup("Security");
        directory.addGroup("Infra");

        directory.addEmployeeToGroup("e1", "Engineering");
        directory.addEmployeeToGroup("e1", "Infra");
        directory.addEmployeeToGroup("e2", "Engineering");
        directory.addEmployeeToGroup("e2", "Security");
        directory.addEmployeeToGroup("e3", "Engineering");
        directory.addEmployeeToGroup("e3", "Infra");
        directory.addEmployeeToGroup("e3", "Security");

        List<String> query = Arrays.asList("e1", "e3");

        Group result = directory.commonEmployees(query);

        if (result != null) {
            System.out.println("Common group: " + result.getName());
        } else {
            System.out.println("No common group found.");
        }
    }
}
