package PostKarat06June.EmployeeDirectory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClosestCommonFinder {


    public Group getLCA(List<Employee> employees) {

        if (employees == null || employees.isEmpty()) return null;

        List<List<Group>> paths = new ArrayList<>();

        for (Employee employee : employees) {

            List<Group> path = getPathTillRoot(employee.getGroup());
            Collections.reverse(path);
            paths.add(path);

        }


        Group lca = null;
        int index = 0;

        while (true) {

            Group current = null;

            for (List<Group> path : paths) {

                if (index >= path.size()) {

                    return lca;
                }

                if (current == null) {

                    current = path.get(index);
                } else if (!current.equals(path.get(index))) {

                    return lca;
                }
            }

            lca = current;
            index++;
        }
    }


    public List<Group> getPathTillRoot(Group group) {

        List<Group> path = new ArrayList<>();

        while (group != null) {

            path.add(group);
            group = group.getParent();
        }


        return path;
    }
}
