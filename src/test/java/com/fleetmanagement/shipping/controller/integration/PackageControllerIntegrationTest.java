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
class PackageControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		// First created data.
		this.mockMvc.perform(
				post("/api/v0/packages")
                .content("{\"barcode\": \"P6988000121\", \"deliveryPointId\": 1, \"weight\": 17}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	@Test
	public void testGetPackages_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/packages")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testDeletePackage_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				delete("/api/v0/packages/P6988000121")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}

	@Test
	public void testCreatePackage_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/packages")
                .content("{\"barcode\": \"P6988000122\", \"deliveryPointId\": 1, \"weight\": 17}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk()
                );
	}
	
	@Test
	public void testCreatePackage_Return400_InvalidFormat() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/packages")
                .content("{\"barcode\": \"P7988000\", \"deliveryPointId\": 1, \"weight\": 17}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testCreatePackage_Return400_AlreadyExists() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/packages")
                .content("{\"barcode\": \"P6988000121\", \"deliveryPointId\": 1, \"weight\": 17}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isBadRequest()
                );
	}
	
	@Test
	public void testUpdatePackage_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				put("/api/v0/packages/P6988000121")
				.content("{\"state\": 2}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testUpdatePackage_Return404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				put("/api/v0/packages/P9988000131")
				.content("{\"state\": 2}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}
	
	@Test
	public void testGetPackageByBarcode_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/packages/P6988000121")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testGetPackageByBarcode_Return404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				get("/api/v0/packages/P9988000131")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}

}
