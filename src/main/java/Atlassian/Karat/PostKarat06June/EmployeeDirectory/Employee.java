package PostKarat06June.EmployeeDirectory;

public class Employee {



    String id;
    Group group;

    public Employee(String id, Group group) {
        this.id = id;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }
}
