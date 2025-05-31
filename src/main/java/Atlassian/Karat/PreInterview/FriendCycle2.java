package PreInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle2 {


    public static void friendCycle2(String[] employees, String[] friendships) {


        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, String> empDeptMap = new HashMap<>();
        Map<String, Integer> deptEmpCount = new HashMap<>();


        for (String employee : employees) {

            String[] record = employee.split(", ");
            String empId = record[0];
            String dept = record[2];

            empDeptMap.put(empId, dept);
            adjacencyList.put(empId, new ArrayList<>());
            deptEmpCount.put(dept, deptEmpCount.getOrDefault(empId, 0) + 1);

        }


        for (String friendship : friendships) {

            String[] record = friendship.split(", ");
            String emp1 = record[0];
            String emp2 = record[1];

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

                crossDeptEmpCount.put(dept, crossDeptEmpCount.getOrDefault(dept,0) + 1);
            }
        }


        for (Map.Entry<String, Integer> entry : deptEmpCount.entrySet()) {

            String dept = entry.getKey();
            Integer empCount = entry.getValue();


            Integer crossDeptCount = crossDeptEmpCount.getOrDefault(dept, 0);

            System.out.println(dept + " : " + crossDeptCount + " of " + empCount);
        }
    }


    public static void main(String[] args) {



        String[] employees = {"1, Bill, Engineer", "2, Joe, HR", "3, Sally, Engineer", "4, Richard, Business", "6, Tom, Engineer"};


        String[] friendships = {"1, 2", "1, 3", "3, 4"};

        friendCycle2(employees, friendships);
    }
}
