package com.epam.module.jpa.runner;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.module.jpa.entity.Address;
import com.epam.module.jpa.entity.Employee;
import com.epam.module.jpa.entity.EmployeeStatus;
import com.epam.module.jpa.entity.Personal;
import com.epam.module.jpa.entity.Project;
import com.epam.module.jpa.entity.Unit;
import com.epam.module.jpa.service.EmployeeService;
import com.epam.module.jpa.service.PersonalService;
import com.epam.module.jpa.service.ProjectService;
import com.epam.module.jpa.service.UnitService;


public class AppHelper {

	public static void showMenu() {
		System.out.println("1. Add new unit");
		System.out.println("2. Add new project");
		System.out.println("3. Add new employee");
		System.out.println("4. Delete unit");
		System.out.println("5. Delete project");
		System.out.println("6. Delete employee");
		System.out.println("7. Add employee to unit");
		System.out.println("8. Add employee to project");
		System.out.println("9. Update unit");
		System.out.println("10. Update project");
		System.out.println("11. Update employee");
		System.out.println("12. Exit");
		System.out.println("   What do you want to do?");
	}
	
	public static void menu(ClassPathXmlApplicationContext ctx) {
		boolean menu = true;
		while (menu) {
			showMenu();
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch (value) {
			case 1:
				Unit unit = addUnit(ctx.getBean(UnitService.class), sc);
				System.out.println(unit.getUnitId());
				break;
			case 2:
				Project project = addProject(ctx.getBean(ProjectService.class), sc);
				System.out.println(project.getProjectId());
				break;
			case 3:
				Employee employee = addEmployee(ctx.getBean(EmployeeService.class), ctx.getBean(UnitService.class), ctx.getBean(PersonalService.class), sc);
				System.out.println(employee.getEmployeeID());
				break;
			case 4:
				System.out.println("Enter unit id for delete");
				int id = sc.nextInt();
				UnitService serv = ctx.getBean(UnitService.class);
				Unit unitForDelete = serv.get(id);
				serv.delete(unitForDelete);
				unit = serv.get(id);
				if(unit == null) {
					System.out.println("Unit was successfully deleted");
				}
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				menu = false;
				break;
			default:
				System.out.println("Please, choose another case");
				showMenu();
			}
		}
	}
	
	private static Unit addUnit(UnitService service, Scanner sc) {
		System.out.println("Please enter Description of new unit");
		String descr = sc.next();
		Unit unit = new Unit();
		unit.setDescr(descr);
		return service.add(unit);
	}
	
	private static Project addProject(ProjectService service,  Scanner sc){
		System.out.println("Please enter Description of new project");
		String descr = sc.next();
		Project project = new Project();
		project.setDesc(descr);
		return service.add(project);
	}
	
	private static Employee addEmployee(EmployeeService service, UnitService unitServ, PersonalService personalServ, Scanner sc) {
		System.out.println("Please enter Unit id for new employee");
		int id = sc.nextInt();
		Unit unit = unitServ.get(id);
		if(unit == null){
			System.out.println("There is no such unit. Add unit for continue");
			unit = addUnit(unitServ, sc);
		}
		Employee employee = new Employee();
		System.out.println("Please enter name of employee");
		String name = sc.next();
		System.out.println("Please enter surname of employee");
		String surname = sc.next();
		System.out.println("Please enter address info: City");
		String city = sc.next();
		System.out.println("Please enter address info: Street");
		String street = sc.next();
		System.out.println("Please chhose employee status: 1-full, 2-part");
		int index = sc.nextInt(); 
		switch(index){
		case 1:
			employee.setStatus(EmployeeStatus.FULL_TIME_EMPLOYEE);
			break;
		case 2:
			employee.setStatus(EmployeeStatus.PART_TIME_EMPLOYEE);
			break;
		default:
			employee.setStatus(EmployeeStatus.FULL_TIME_EMPLOYEE);	
		}
		Personal info = new Personal();
		System.out.println("Please enter personal information of employee: passport number");
		info.setPassNumber(sc.next());
		System.out.println("Please enter personal information of employee: age");
		info.setAge(sc.nextInt());
		
		Address address = new Address(); 
		address.setCity(city);
		address.setStreet(street);
		employee.setAddress(address);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setUnit(unit);
		employee = service.add(employee);
		info.setEmployee(employee);
		info = personalServ.add(info);
		return service.get(employee.getEmployeeID());
	}
}
