package com.castoldi.custaddr;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.castoldi.custaddr.domain.Address;
import com.castoldi.custaddr.domain.Customer;
import com.castoldi.custaddr.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CustomersAddressesApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;
	
	private ObjectMapper mapper = new ObjectMapper();

	private Customer createCustomer(Long id, List<Address> addresses) {
		Customer customer = new Customer();
		customer.setAddresses(addresses);
		customer.setAge(id.intValue());
		customer.setDocumentId("documentId" + id);
		customer.setId(id);
		customer.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
		customer.setName("name" + id);
		customer.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
		return customer;
	}

	private List<Address> createAddressList(int size) {
		List<Address> addresses = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Address address = new Address();
			address.setZipCode("81540-370");
			address.setNumber(RandomUtils.nextInt());
			addresses.add(address);
		}

		return addresses;
	}

	@Test
	void saveCustomer() throws Exception {
		Customer customer = createCustomer(6L, createAddressList(2));
		when(service.save(any(Customer.class))).thenReturn(customer);

		mockMvc.perform(post("/customer")
				.contentType("application/json")
				.content(mapper.writeValueAsString(customer)))
				.andDo(print())
				.andExpect(content().string(containsString("\"documentId\":\"documentId6\"")))
				.andExpect(status().isCreated());
	}

}
