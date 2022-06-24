package az.spring.jdbc.dao.impl;

import az.spring.jdbc.dao.EmployeeDao;
import az.spring.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Employee employee) {
        String query = "insert into employee(name,surname,age,salary) values(:name,:surname,:age,:salary)";
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);

        jdbcTemplate.update(query, parameters);
    }

    @Override
    public void uptade(Employee employee) {
        String query = "UPDATE employee set name = :name,surname = :surname,age = :age,salary = :salary where id = :id";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void delete(int id) {
        String query = "delete from employee where id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String query = "select * from employee where id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        Employee employee = jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;

    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "select * from employee";
        List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        return employees;
    }

    @Override
    public long count() {
        String query = "select count(*) from employee";
        long count = jdbcTemplate.queryForObject(query, new MapSqlParameterSource(), Long.class);
        return count;

    }

}
