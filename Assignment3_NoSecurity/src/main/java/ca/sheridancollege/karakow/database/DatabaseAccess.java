package ca.sheridancollege.karakow.database;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.karakow.beans.Department;
import ca.sheridancollege.karakow.beans.Employee;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertEmployee(Long EMPLOYEE_ID, String FIRST_NAME, 
			String LAST_NAME, String EMAIL, String PHONE_NUMBER,
			Date HIRE_DATE, String JOB_ID, Double SALARY, 
			Double COMMISSION_PCT, Long MANAGER_ID, Long DEPARTMENT_ID) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, "
				+ "LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, "
				+ "SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)"
				+ "VALUES(:EMPLOYEE_ID, :FIRST_NAME, :LAST_NAME, :EMAIL, "
				+ ":PHONE_NUMBER, :HIRE_DATE, :JOB_ID, :SALARY, "
				+ ":COMMISSION_PCT, :MANAGER_ID, :DEPARTMENT_ID)";
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);
		namedParameters.addValue("FIRST_NAME", FIRST_NAME);
		namedParameters.addValue("LAST_NAME", LAST_NAME);
		namedParameters.addValue("EMAIL", EMAIL);
		namedParameters.addValue("PHONE_NUMBER", PHONE_NUMBER);
		namedParameters.addValue("HIRE_DATE", HIRE_DATE);
		namedParameters.addValue("JOB_ID", JOB_ID);
		namedParameters.addValue("SALARY", SALARY);
		namedParameters.addValue("COMMISSION_PCT", COMMISSION_PCT);
		namedParameters.addValue("MANAGER_ID", MANAGER_ID);
		namedParameters.addValue("DEPARTMENT_ID", DEPARTMENT_ID);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if (rowsAffected > 0) {
			System.out.println("Inserted employee into database");
		}
	}
	
	
	public void updateEmployee(Long EMPLOYEE_ID, Long MANAGER_ID) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "UPDATE Employees SET MANAGER_ID=:MANAGER_ID "
				+ "WHERE EMPLOYEE_ID=:EMPLOYEE_ID";
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);
		namedParameters.addValue("MANAGER_ID", MANAGER_ID);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0) {
			System.out.println("Employee updated in the database.");
		}
	}
	
	public void deleteEmployee(Long EMPLOYEE_ID) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM Employees WHERE EMPLOYEE_ID=:EMPLOYEE_ID";
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0) {
			System.out.println("Employee deleted from the database.");
		}
		
		
	}
	
	public List<Employee> getEmployees() {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM Employees";
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}
	
	public List<Employee> getEmployeesByID(Long EMPLOYEE_ID) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM Employees WHERE EMPLOYEE_ID=:EMPLOYEE_ID";
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}
	
	public List<Department> getDepartments() {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM Employees";
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<Department>(Department.class));
		
	}
	
	
	
}
