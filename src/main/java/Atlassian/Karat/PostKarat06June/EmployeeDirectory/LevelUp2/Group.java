package PostKarat06June.EmployeeDirectory.LevelUp2;

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

    public void removeParent(Group parent) {

        this.parents.remove(parent);
        parent.children.remove(this);
    }

    public String getName() {
        return name;
    }

    public List<Group> getParents() {
        return parents;
    }
}
