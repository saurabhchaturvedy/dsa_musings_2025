package KaratFriday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle2 {


    public static void friendsCycle2(String[] employees, String[] friendships) {


        Map<String, String> empDeptMap = new HashMap<>();
        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> deptEmpCount = new HashMap<>();


        for (String employee : employees) {

            String[] employeeRecord = employee.split(", ");
            String empId = employeeRecord[0];
            String dept = employeeRecord[2];

            empDeptMap.put(empId, dept);
            adjacencyList.put(empId, new ArrayList<>());
            deptEmpCount.put(dept, deptEmpCount.getOrDefault(dept, 0) + 1);
        }


        for (String friendship : friendships) {

            String[] friendshipRecord = friendship.split(", ");

            String emp1 = friendshipRecord[0];
            String emp2 = friendshipRecord[1];

            adjacencyList.get(emp1).add(emp2);
            adjacencyList.get(emp2).add(emp1);
        }


        Map<String, Integer> crossDeptEmpCount = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {

            String empId = entry.getKey();
            String dept = empDeptMap.get(empId);
            List<String> friends = adjacencyList.get(empId);

            boolean hasCrossDeptFriend = false;

            for (String friend : friends) {

                if (!dept.equals(empDeptMap.get(friend))) {
                    hasCrossDeptFriend = true;
                    break;

                }
            }

            if (hasCrossDeptFriend) {

                crossDeptEmpCount.put(dept, crossDeptEmpCount.getOrDefault(dept, 0) + 1);
            }
        }


        for (Map.Entry<String, Integer> entry : deptEmpCount.entrySet()) {

            String dept = entry.getKey();
            Integer count = entry.getValue();

            Integer crossDeptCount = crossDeptEmpCount.getOrDefault(dept, 0);

            System.out.println(dept + " : " + crossDeptCount + " of " + count);
        }

    }

    public static void main(String[] args) {


        String[] employees = {"1, Bill, Engineer", "2, Joe, HR", "3, Sally, Engineer", "4, Richard, Business", "6, Tom, Engineer"};


        String[] friendships = {"1, 2", "1, 3", "3, 4"};

        friendsCycle2(employees, friendships);
    }
}
