

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle2 {


    public static void friendCycle2(String[] employees, String[] friends) {

        Map<String, String> empDeptMap = new HashMap<>();
        Map<String, Integer> deptEmpCount = new HashMap<>();
        Map<String, List<String>> adjList = new HashMap<>();


        for (String emp : employees) {

            String[] empl = emp.split(", ");
            String e = empl[0];
            String dept = empl[2];

            empDeptMap.put(e, dept);
            adjList.put(e, new ArrayList<>());
            deptEmpCount.put(dept, deptEmpCount.getOrDefault(dept, 0) + 1);

        }


        for (String f : friends) {

            String[] frnd = f.split(", ");

            String emp1 = frnd[0];
            String emp2 = frnd[1];

            adjList.get(emp1).add(emp2);
            adjList.get(emp2).add(emp1);
        }


        Map<String, Integer> crossDeptEmpCount = new HashMap<>();

        for (String empId : adjList.keySet()) {

            String dept = empDeptMap.get(empId);
            List<String> friendx = adjList.get(empId);


            boolean hasCrossDeptFriend = false;
            for (String friendId : friendx) {

                if (!dept.equals(empDeptMap.get(friendId))) {
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
            int empCount = entry.getValue();

            int crossDeptCount = crossDeptEmpCount.getOrDefault(dept, 0);

            System.out.println(dept + ":" + crossDeptCount + " of " + empCount);
        }

    }


    public static void main(String[] args) {


        String[] employees = {"1, Bill, Engineer", "2, Joe, HR", "3, Sally, Engineer", "4, Richard, Business", "6, Tom, Engineer"};


        String[] friendships = {"1, 2", "1, 3", "3, 4"};

        friendCycle2(employees, friendships);
    }

}