package PostKarat06June.EmployeeDirectory.LevelUp1;

import java.util.ArrayList;
import java.util.List;

public class Employee {


    String id;
    List<Group> groups = new ArrayList<>();


    public Employee(String id, List<Group> groups) {
        this.id = id;
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
