package com.xiaonan.learning.springunittestingwithjunitandmockito.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.xiaonan.learning.springunittestingwithjunitandmockito.data.ItemRepository;
import com.xiaonan.learning.springunittestingwithjunitandmockito.data.SomeDataService;
import com.xiaonan.learning.springunittestingwithjunitandmockito.model.Item;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {

	/*SomeBusinessImpl business = new SomeBusinessImpl();
	SomeDataService dataServiceMock = mock(SomeDataService.class);
	*/
	
	@InjectMocks
	private ItemBusinessService business; 
	
	@Mock
	private ItemRepository repository;
	
	/*@Before
	public void before() {
		business.setSomeDataService(dataServiceMock);
	}*/	

	@Test
	public void retrieveAllItems_basic() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Item(2, "Ball2", 10, 100), 
				new Item(3, "Ball3", 30, 300)));
		List<Item> items = business.retrieveAllItems();
		assertEquals(1000, items.get(0).getValue());
		assertEquals(9000, items.get(1).getValue());
	}
	
	
}
