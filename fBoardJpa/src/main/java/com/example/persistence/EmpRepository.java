package com.example.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Employee;

//public interface EmpRepository {//extends CrudRepository<Employee, Integer>{ // class, 자료형
	public interface EmpRepository extends JpaRepository<Employee, Integer>{ // class, 자료형

	@Query(value="SELECT"
			+ "   e.empno 	empno	"
			+ "	  , e.ename ename 	"
			+ "	  , e.job	job 	"
			+ "	  , e.sal	sal	 	"
			+ "	  , d.dname dname  	"
			+ "   FROM emp e INNER JOIN dept d 	"
			+ "   ON e.deptno = d.deptno		"
			, nativeQuery=true)
	List<Map> selectEmp();
	
}
