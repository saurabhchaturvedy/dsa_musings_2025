package PostKarat06June.EmployeeDirectory;

import java.util.ArrayList;
import java.util.List;

public class Group {


    String name;
    Group parent;
    List<Group> children = new ArrayList<>();


    public Group(String name, Group parent) {
        this.name = name;
        this.parent = parent;

        if (parent != null) {

            parent.addChild(this);
        }
    }

    public String getName() {
        return name;
    }

    public Group getParent() {
        return parent;
    }

    public List<Group> getChildren() {
        return children;
    }

    public void setChildren(List<Group> children) {
        this.children = children;
    }


    public void addChild(Group child) {

        children.add(child);
    }
}
