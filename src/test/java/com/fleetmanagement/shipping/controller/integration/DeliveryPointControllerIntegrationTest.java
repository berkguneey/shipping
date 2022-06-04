package com.fleetmanagement.shipping.controller.integration;

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
class DeliveryPointControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		// First created data.
		this.mockMvc.perform(
				post("/api/v0/delivery-points")
                .content("{\"name\": \"TEST\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	@Test
	public void testGetDeliveryPoints_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/delivery-points")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testDeleteDeliveryPoint_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				delete("/api/v0/delivery-points/4")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}

	@Test
	public void testCreateDeliveryPoint_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/delivery-points")
                .content("{\"name\": \"TEST2\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}
	
	@Test
	public void testCreateDeliveryPoint_Return400_AlreadyExists() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/delivery-points")
                .content("{\"name\": \"TEST\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testGetDeliveryPointById_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/delivery-points/4")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testGetDeliveryPointById_Return404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/delivery-points/99")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}

}
