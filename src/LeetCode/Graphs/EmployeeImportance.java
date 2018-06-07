//https://leetcode.com/problems/employee-importance/description/
package LeetCode.Graphs;

import java.util.*;

public class EmployeeImportance {

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

    class Solution {
        public int getImportance(List<Employee> employees, int id) {
            Map<Integer, Employee> map = new HashMap<>();
            for (Employee employee : employees) {
                map.put(employee.id, employee);
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(id);
            int importance = 0;

            while(!q.isEmpty()){
                Employee temp = map.get(q.poll());
                importance+=temp.importance;
                q.addAll(temp.subordinates);
            }

            return importance;
        }
    }
}
