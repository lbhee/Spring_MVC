package ncontroller;

import vo.Emp;

public class lombokController {

	public Emp lombokBody() {
		Emp emp = new Emp();
		emp.setEmpno(100);
		emp.setEname("홍길동");
		System.out.println(emp);
		
		return emp;
	}
}
