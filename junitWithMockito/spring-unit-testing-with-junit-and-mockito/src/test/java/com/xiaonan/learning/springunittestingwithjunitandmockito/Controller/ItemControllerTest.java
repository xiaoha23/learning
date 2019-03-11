package com.xiaonan.learning.springunittestingwithjunitandmockito.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.xiaonan.learning.springunittestingwithjunitandmockito.business.ItemBusinessService;
import com.xiaonan.learning.springunittestingwithjunitandmockito.controller.HelloWorldController;
import com.xiaonan.learning.springunittestingwithjunitandmockito.controller.ItemController;
import com.xiaonan.learning.springunittestingwithjunitandmockito.model.Item;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemBusinessService businessService;

	@Test
	public void dummyItemBasicTest() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/dummy-item")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				//.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();
		//assertEquals("Hello World", result.getResponse().getContentAsString());
	}

	
	@Test
	public void itemFromBusinessServiceBasicTest() throws Exception {
		
		when(businessService.retrieveHardcodedItem()).thenReturn(
				new Item(2, "Ball2", 10, 100));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				//.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andExpect(content().json("{id: 2, name:Ball2, price:10, quantity:100}"))
				.andReturn();
		//assertEquals("Hello World", result.getResponse().getContentAsString());
	}
}
