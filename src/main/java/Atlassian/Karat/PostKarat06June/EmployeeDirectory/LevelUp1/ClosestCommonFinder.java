package PostKarat06June.EmployeeDirectory.LevelUp1;

import java.util.*;

public class ClosestCommonFinder {


    public Group getClosestCommon(List<Employee> employeeList) {

        if (employeeList == null || employeeList.isEmpty()) return null;


        Map<Group, Integer> groupReachCount = new HashMap<>();
        Map<Group, Integer> groupDepthCount = new HashMap<>();

        for (Employee employee : employeeList) {

            Set<Group> visited = new HashSet<>();
            Queue<Group> queue = new LinkedList<>(employee.getGroups());
            int level = 0;

            while (!queue.isEmpty()) {

                int size = queue.size();


                while (size-- > 0) {

                    Group current = queue.poll();

                    if (visited.contains(current)) continue;

                    visited.add(current);
                    groupReachCount.put(current, groupReachCount.getOrDefault(current, 0) + 1);
                    groupDepthCount.putIfAbsent(current, level);

                    queue.addAll(current.getParents());
                }

                level++;
            }
        }


        Group answerGroup = null;
        int maxDepth = -1;

        for (Map.Entry<Group, Integer> entry : groupReachCount.entrySet()) {

            Group currentGroup = entry.getKey();
            Integer count = entry.getValue();


            if (count == employeeList.size()) {

                Integer depth = groupDepthCount.getOrDefault(currentGroup, 0);

                if (depth > maxDepth) {

                    maxDepth = depth;
                    answerGroup = currentGroup;
                }
            }
        }

        return answerGroup;
    }


    public static void main(String[] args) {


        // Create groups
        Group engineering = new Group("Engineering");
        Group frontend = new Group("Frontend");
        Group backend = new Group("Backend");
        Group infra = new Group("Infra");
        Group security = new Group("Security");

        // Define DAG relationships
        frontend.addParent(engineering);
        backend.addParent(engineering);
        security.addParent(engineering);
        infra.addParent(frontend);
        infra.addParent(backend);

        // Create employees in different parts of org
        Employee e1 = new Employee("e1", Arrays.asList(frontend));
        Employee e2 = new Employee("e2", Arrays.asList(backend));
        Employee e3 = new Employee("e3", Arrays.asList(infra));

        // Run the service
        ClosestCommonFinder service = new ClosestCommonFinder();
        Group lca = service.getClosestCommon(Arrays.asList(e1, e2, e3));

        // Output result
        if (lca != null) {
            System.out.println("Closest common group: " + lca.getName());
        } else {
            System.out.println("No common group found.");
        }
    }
}
