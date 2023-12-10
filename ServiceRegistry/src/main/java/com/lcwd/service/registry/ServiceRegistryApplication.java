package com.lcwd.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);

		List<Employee> employeeList=new ArrayList<>();
		//Map<String,Employee> highestSalaryByDepy=
		//List<String> dept=new ArrayList<>();
		Map<String,Employee> deptMax=new HashMap<>();
		for (Employee emp: employeeList
			 ) {
			for(Map.Entry<String,Employee> entry:deptMax.entrySet()){
				String Key=entry.getKey();
				Employee value=entry.getValue();
				if(emp.dipartment.equals(Key)){
					if(emp.sal>value.sal){
						deptMax.put(Key,emp);
					}
				}

			}
		}
	}


}
