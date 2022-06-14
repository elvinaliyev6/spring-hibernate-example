package az.spring.hibernate.dao;

import az.spring.hibernate.model.Employee;

import java.util.List;

public interface EmployeeDao extends CrudDAO<Employee,Long> {

    List<Employee> findByName(String name);

}
