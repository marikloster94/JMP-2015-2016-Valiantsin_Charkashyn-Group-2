package com.epam.module.jpa.runner;

import java.util.List;
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
			case 1:{
				Unit unit = addUnit(ctx.getBean(UnitService.class), sc);
				System.out.println(unit.getUnitId());
			}
				break;
			case 2:{
				Project project = addProject(ctx.getBean(ProjectService.class), sc);
				System.out.println(project.getProjectId());
			}
				break;
			case 3:{
				Employee employee = addEmployee(ctx.getBean(EmployeeService.class), ctx.getBean(UnitService.class), ctx.getBean(PersonalService.class), sc);
				System.out.println(employee.getEmployeeID());
			}
				break;
			case 4: {
				System.out.println("Enter unit id for delete");
				int id = sc.nextInt();
				UnitService serv = ctx.getBean(UnitService.class);
				Unit unitForDelete = serv.get(id);
				serv.delete(unitForDelete);
				Unit unit = serv.get(id);
				if(unit == null) {
					System.out.println("Unit was successfully deleted");
				}
			}
				break;
			case 5:{
				System.out.println("Enter project id for delete");
				int id = sc.nextInt();
				ProjectService service = ctx.getBean(ProjectService.class);
				Project project = service.get(id);
				service.delete(project);
				project = service.get(id);
				if(project == null) {
					System.out.println("Project was successfully deleted");
				}
			}
				break;
			case 6:{
				System.out.println("Enter employee id for delete");
				int id = sc.nextInt();
				EmployeeService emplService = ctx.getBean(EmployeeService.class);
				Employee employee = emplService.get(id);
				emplService.delete(employee);
				employee = emplService.get(id);
				if(employee == null) {
					System.out.println("Employee was successfully deleted");
				}
			}
				break;
			case 7:{
				System.out.println("Enter employee id for adding to unit");
				int id = sc.nextInt();
				EmployeeService emplService = ctx.getBean(EmployeeService.class);
				Employee employee = emplService.get(id);
				if(employee == null){
					System.out.println("There is no employee for adding to unit");
					break;
				}
				Unit oldUnit = employee.getUnit();
				System.out.println("Enter unit id for update employee");
				id = sc.nextInt();
				UnitService serv = ctx.getBean(UnitService.class);
				Unit unit = serv.get(id);
				if(unit == null){
					System.out.println("There is no unit with id:"+id+"  Please add unit");
					unit = addUnit( serv, sc);
				}
				employee.setUnit(unit);
				employee = emplService.update(employee);
				if(oldUnit.getUnitId() != employee.getUnit().getUnitId()){
					System.out.println("Person was successfully changed unit");
				}
			}
				break;
			case 8:{
				System.out.println("Enter project id ");
				int id = sc.nextInt();
				ProjectService serv = ctx.getBean(ProjectService.class);
				Project project = serv.get(id);
				if(project == null){
					System.out.println("There is no project with id:"+id);
					break;
				}
				System.out.println("Enter employee id");
				id = sc.nextInt();
				EmployeeService emplService = ctx.getBean(EmployeeService.class);
				Employee employee = emplService.get(id);
				if(employee == null){
					System.out.println("There is no employee with id:"+id);
					break;
				}
				List<Project> projects = employee.getProjects();
				for(Project pr: projects){
					if(pr.getProjectId() == project.getProjectId()){
						System.out.println("This employee works on selected project. Can not add this employee for such project again");
						break;
					}
				}
				int size = projects.size();
				projects.add(project);
				employee = emplService.update(employee);
				if (size != employee.getProjects().size()) {
					System.out.println("Employee was successfully added to the project with id:"+project.getProjectId());
				}
				
			}
				break;
			case 9:{
				System.out.println("Enter unit id for update ");
				int id = sc.nextInt();
				UnitService serv = ctx.getBean(UnitService.class);
				Unit unit = serv.get(id);
				if(unit == null){
					System.out.println("There is no unit with id:"+id);
					break;
				}
				String oldDesc = unit.getDescr();
				System.out.println("Enter unit description for update ");
				String descr = sc.next();
				unit.setDescr(descr);
				unit = serv.update(unit);
				if(!oldDesc.equals(descr) && id == unit.getUnitId()){
					System.out.println("Unit was successfully updated");
				}
				
			}
				break;
			case 10:{
				System.out.println("Enter project id for update ");
				int id = sc.nextInt();
				ProjectService serv = ctx.getBean(ProjectService.class);
				Project project = serv.get(id);
				if(project == null){
					System.out.println("There is no project with id:"+id);
					break;
				}
				String oldDesc = project.getDesc();
				System.out.println("Enter project description for update ");
				String descr = sc.next();
				project.setDesc(descr);
				project = serv.update(project);
				if(!oldDesc.equals(descr) && id == project.getProjectId()){
					System.out.println("Project was successfully updated");
				}
			}
				break;
			case 11:{
				System.out.println("Enter employee id for update");
				int id = sc.nextInt();
				EmployeeService emplService = ctx.getBean(EmployeeService.class);
				Employee employee = emplService.get(id);
				if(employee == null){
					System.out.println("There is no employee with id:"+id);
					break;
				}
				System.out.println("Please enter address info: City");
				String city = sc.next();
				System.out.println("Please enter address info: Street");
				String street = sc.next();
				employee.getAddress().setCity(city);
				employee.getAddress().setStreet(street);
				employee = emplService.update(employee);
				if(employee.getAddress().getCity().equals(city) && employee.getAddress().getStreet().equals(street) && id == employee.getEmployeeID() ){
					System.out.println("Employee was successfully updated");
				}
			}
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
