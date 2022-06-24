package az.spring.jdbc.dao;

import az.spring.jdbc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void insert(Employee employee);

    void uptade(Employee employee);

    void delete(int id);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    long count();
}
