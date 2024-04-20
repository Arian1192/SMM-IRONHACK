package com.ssm.systemmeetmanagement;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.PermissionRepository;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SystemMeetManagementApplication {

	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SystemMeetManagementApplication.class, args);
	}


	@Value("${app.test.enabled}")
	private boolean isTestRunning;

	@Bean
	CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository){
		if(isTestRunning){
			return args -> {};
		}else{
			return args -> {
				/* CREATE PERMISSIONS */
				PermissionEntity permissionCreate = new PermissionEntity("CREATE");
				PermissionEntity permissionRead = new PermissionEntity("READ");
				PermissionEntity permissionUpdate = new PermissionEntity("UPDATE");
				PermissionEntity permissionDelete = new PermissionEntity("DELETE");
				permissionRepository.saveAll(Arrays.asList(permissionCreate, permissionRead, permissionUpdate, permissionDelete));

				/* CREATE ROLES */
				Role sysAdminRole = new Role("SYSADMIN");
				sysAdminRole.setPermissionEntityList(Set.of(permissionCreate, permissionRead, permissionUpdate, permissionDelete));
				Role adminRole = new Role("ADMIN");
				adminRole.setPermissionEntityList(Set.of(permissionRead, permissionCreate, permissionUpdate));
				Role userRole = new Role("USER");
				userRole.setPermissionEntityList(Set.of(permissionRead, permissionUpdate));
				roleRepository.saveAll(Arrays.asList(sysAdminRole, adminRole, userRole));

				Set<Role> roles = new HashSet<>();
				roles.add(sysAdminRole);

				/* CREATE SYSADMIN */
				User sysAdminUser = new User("Arian",
						"Collaso",
						"arian.collaso.rodriguez@gmail.com",
						passwordEncoder.encode("40S4r3dder"),
						roles
				);

				userRepository.save(sysAdminUser);

			};
		}
	}
}
