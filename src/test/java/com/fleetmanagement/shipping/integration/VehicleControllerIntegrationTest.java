package com.fleetmanagement.shipping.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class VehicleControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		// First created data.
		this.mockMvc.perform(
				post("/api/v0/vehicles")
                .content("{\"licensePlate\": \"34XX444\",\"model\": \"HONDA\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	@Test
	public void testGetVehicles_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/vehicles")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testDeleteVehicle_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				delete("/api/v0/vehicles/34XX444")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}

	@Test
	public void testCreateVehicle_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/vehicles")
                .content("{\"licensePlate\": \"34YY555\",\"model\": \"SCANIA\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}
	
	@Test
	public void testCreateVehicle_ReturnIncorrectFormat() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/vehicles")
                .content("{\"licensePlate\": \"34XXX4\",\"model\": \"HONDA\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testCreateVehicle_ReturnAlreadyExists() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/vehicles")
                .content("{\"licensePlate\": \"34XX444\",\"model\": \"HONDA\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testGetVehicleByLicensePlate_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/vehicles/34XX444")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testGetVehicleByLicensePlate_ReturnNoDataFound() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/vehicles/34XX445")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}

}
