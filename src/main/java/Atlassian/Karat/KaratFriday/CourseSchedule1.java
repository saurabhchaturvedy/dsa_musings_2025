package KaratFriday;

import java.util.*;

public class CourseSchedule1 {


    public static Map<List<String>, List<String>> getStudentSharedCourses(List<String[]> studentCoursePairs) {

        Map<String, Set<String>> studentToCourses = new HashMap<>();


        for (String[] studentCoursePair : studentCoursePairs) {

            String student = studentCoursePair[0];
            String course = studentCoursePair[1];


            studentToCourses.computeIfAbsent(student, k -> new HashSet<>()).add(course);
        }


        List<String> students = new ArrayList<>(studentToCourses.keySet());
        Map<List<String>, List<String>> result = new HashMap<>();

        for (int i = 0; i < students.size(); i++) {

            for (int j = i + 1; j < students.size(); j++) {

                String s1 = students.get(i);
                String s2 = students.get(j);

                Set<String> courses1 = studentToCourses.get(s1);
                Set<String> courses2 = studentToCourses.get(s2);

                Set<String> shared = new HashSet<>(courses1);
                shared.retainAll(courses2);

                List<String> key = Arrays.asList(s1, s2);
                result.put(key, new ArrayList<>(shared));
            }
        }

        return result;
    }


    public static void main(String[] args) {



        List<String[]> studentCoursePairs1 = Arrays.asList(
                new String[]{"58", "Software Design"},
                new String[]{"58", "Linear Algebra"},
                new String[]{"94", "Art History"},
                new String[]{"94", "Operating Systems"},
                new String[]{"17", "Software Design"},
                new String[]{"58", "Mechanics"},
                new String[]{"58", "Economics"},
                new String[]{"17", "Linear Algebra"},
                new String[]{"17", "Political Science"},
                new String[]{"94", "Economics"},
                new String[]{"25", "Economics"}
        );

        Map<List<String>, List<String>> output = getStudentSharedCourses(studentCoursePairs1);

        // Print the output
        for (Map.Entry<List<String>, List<String>> entry : output.entrySet()) {
            List<String> pair = entry.getKey();
            List<String> courses = entry.getValue();
            System.out.println(pair + ": " + courses);
        }
    }
}
