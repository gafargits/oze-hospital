package com.oze.hospitalservice;

import com.oze.hospitalservice.repositories.StaffRepository;
import com.oze.hospitalservice.services.StaffService;
import com.oze.hospitalservice.services.StaffServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HospitalServiceApplicationTests extends BaseServiceTest {
	@Mock
	private StaffRepository staffRepository;

	@InjectMocks
	private StaffServiceImpl staffService;

	@Test
	@DisplayName("register_staff_successful")
	void registerStaff_successful() {
		//Given
		String name = "gafar";
		String password = "password";
		var registeredStaff = getRegisteredStaff(name, password);
		Map<String, Object> generatedKeys = Map.of("id", registeredStaff.getId(), "staff_id", registeredStaff.getStaffId(),"registration_date", registeredStaff.getRegistrationDate());

		//When
		when(staffRepository.create(eq(name), anyString())).thenReturn(generatedKeys);
		var response = staffService.registerStaff(name, password);
		//Then
		assertNotNull(response);
		assertThat(response).usingRecursiveComparison().isEqualTo(registeredStaff);
	}

	@Test
	void registerStaff_staffAlreadExist_throwsException() {
		//Given
		String name = "";
		String password = "";
		var registerStaff = getRegisteredStaff(name, password);

	}

}
