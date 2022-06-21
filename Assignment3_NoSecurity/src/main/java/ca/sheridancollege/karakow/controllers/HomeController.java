package ca.sheridancollege.karakow.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.karakow.beans.Department;
import ca.sheridancollege.karakow.beans.Employee;
import ca.sheridancollege.karakow.database.DatabaseAccess;

@Controller
public class HomeController {

	
	@Autowired
	DatabaseAccess da;
	
	@GetMapping("/")
	public String goHome(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "index";
	}
	
	@GetMapping("/insertEmployee")
	public String goInsert(Model model) {
	
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		model.addAttribute("departmentList", da.getDepartments());
		
		return "insert";
	}
	
	@GetMapping("/updateEmployee/{EMPLOYEE_ID}")
	public String updateEmployee(Model model,
			@PathVariable Long EMPLOYEE_ID,
			@PathVariable Long MANAGER_ID) {
		
		Employee employee = da.getEmployeesByID(EMPLOYEE_ID).get(0);
		
		da.updateEmployee(EMPLOYEE_ID, MANAGER_ID);
		
		model.addAttribute("employee", employee);
		model.addAttribute("employeeList", da.getEmployees());
	
		return "update";
	}
	
	@GetMapping("/deleteEmployee/{EMPLOYEE_ID}")
	public String deleteEmployee(Model model,
			@PathVariable Long EMPLOYEE_ID) {
		
		da.deleteEmployee(EMPLOYEE_ID);
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "delete";
	}
	
	@GetMapping("/viewEmployees")
	public String viewEmployees(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "view";
	}
	
	@PostMapping("/insertEmployee")
	public String insertEmployee(Model model,
			@RequestParam Long EMPLOYEE_ID, 
			@RequestParam String FIRST_NAME, 
			@RequestParam String LAST_NAME, 
			@RequestParam String EMAIL, 
			@RequestParam String PHONE_NUMBER,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date HIRE_DATE, 
			@RequestParam String JOB_ID, 
			@RequestParam Double SALARY, 
			@RequestParam Double COMMISSION_PCT, 
			@RequestParam Long MANAGER_ID, 
			@RequestParam Long DEPARTMENT_ID,
			@ModelAttribute Employee employee,
			@ModelAttribute Department department) {
		
		
		da.insertEmployee(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, 
				EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, 
				COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID);
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		model.addAttribute("department", department);
		model.addAttribute("departmentList", da.getDepartments());
		
		return "insert";
	}
	
	
	
	
}
