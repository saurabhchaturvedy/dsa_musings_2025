package PostKarat06June.EmployeeDirectory.LevelUp2;

import java.util.List;

public class Employee {


    String id;
    List<Group> groups;

    public Employee(String id, List<Group> groups) {
        this.id = id;
        this.groups = groups;
    }


    public String getId() {
        return id;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
