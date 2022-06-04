package com.fleetmanagement.shipping.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
class BagControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		// First created data.
		this.mockMvc.perform(
				post("/api/v0/bags")
                .content("{\"barcode\": \"C625800\",\"deliveryPointId\": 1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	@Test
	public void testGetBags_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/bags")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testDeleteBag_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				delete("/api/v0/bags/C625800")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}

	@Test
	public void testCreateBag_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/bags")
                .content("{\"barcode\": \"C999900\",\"deliveryPointId\": 1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}
	
	@Test
	public void testCreateBag_Return400_InvalidFormat() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/bags")
                .content("{\"barcode\": \"C62580099\",\"deliveryPointId\": 1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testCreateBag_Return400_AlreadyExists() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/bags")
                .content("{\"barcode\": \"C625800\",\"deliveryPointId\": 1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testUpdateBag_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				put("/api/v0/bags/C625800")
				.content("{\"state\": 2}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testUpdateBag_Return404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				put("/api/v0/bags/C725801")
				.content("{\"state\": 2}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}
	
	@Test
	public void testGetBagByBarcode_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/bags/C625800")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testGetBagByBarcode_Return404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/bags/C725801")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}

}
