package PostKarat06June.EmployeeDirectory.LevelUp1;

import java.util.ArrayList;
import java.util.List;

public class Group {


    String name;
    List<Group> parents = new ArrayList<>();
    List<Group> children = new ArrayList<>();


    public Group(String name) {
        this.name = name;
    }


    public void addParent(Group parent) {

        this.parents.add(parent);
        parent.children.add(this);
    }

    public List<Group> getParents() {
        return parents;
    }

    public String getName() {
        return name;
    }
}
