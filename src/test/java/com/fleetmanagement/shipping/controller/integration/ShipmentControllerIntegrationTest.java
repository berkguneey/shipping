package com.fleetmanagement.shipping.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class ShipmentControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testTransfer_ReturnSuccess() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/shipments/transfer")
				.content("{\r\n"
						+ "    \"plate\": \"34AAA12\",\r\n"
						+ "    \"route\": [\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 1,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000121\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000122\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000123\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000121\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"C725799\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        },\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 2,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000123\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000124\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000125\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"C725799\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        },\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 3,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000126\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000127\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000128\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000129\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000130\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        }\r\n"
						+ "    ]\r\n"
						+ "}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void testTransfer_ReturnVehicle404_NoDataFound() throws Exception {
		this.mockMvc.perform(
				post("/api/v0/shipments/transfer")
				.content("{\r\n"
						+ "    \"plate\": \"34AAA14\",\r\n"
						+ "    \"route\": [\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 1,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000121\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000122\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P7988000123\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000121\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"C725799\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        },\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 2,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000123\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000124\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P8988000125\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"C725799\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        },\r\n"
						+ "        {\r\n"
						+ "            \"deliveryPoint\": 3,\r\n"
						+ "            \"deliveries\": [\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000126\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000127\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000128\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000129\"\r\n"
						+ "                },\r\n"
						+ "                {\r\n"
						+ "                    \"barcode\": \"P9988000130\"\r\n"
						+ "                }\r\n"
						+ "            ]\r\n"
						+ "        }\r\n"
						+ "    ]\r\n"
						+ "}")
                .contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound()
				);
	}

}
