package com.xiaonan.learning.springunittestingwithjunitandmockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;


public class ListMockTest {

	List<String> mock = mock(List.class);
	
	@Test
	public void test() {
		
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}
	
	@Test
	public void returnWithParameters() {
		when(mock.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals(null, mock.get(1));
	}
	
	@Test
	public void returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}
	
	@Test
	public void verificationBasics() {
		
		String value1 = mock.get(0);
		String value2 = mock.get(1);
		
		//verification
		verify(mock).get(0);
		
		verify(mock, times(2)).get(anyInt());
		verify(mock, atLeast(1)).get(anyInt());
		verify(mock, atLeastOnce()).get(anyInt());
		verify(mock, atMost(2)).get(anyInt());
		verify(mock, never()).get(2);		
	}
	
	@Test
	public void argumentCapturing() {
		mock.add("SomeString");		
		//verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());
		assertEquals("SomeString", captor.getValue());
	}
	
	@Test
	public void argumentCapturingMultiple() {
		mock.add("SomeString1");		
		mock.add("SomeString2");		

		//verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();
		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));		
	}
	
	@Test
	public void mocking() {
		ArrayList arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));//null
		System.out.println(arrayListMock.size());//0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());//size is till 0, since mock is not a real object
		
		when(arrayListMock.size()).thenReturn(5); // then size() will return 5 because it was called so
		System.out.println(arrayListMock.size());//it's 5
		}
	
	@Test
	public void spying() {
		//spying is a real object
		ArrayList arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0));//will throw exception if nothing added before 
		System.out.println(arrayListSpy.size());//1
		arrayListSpy.add("Test1");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());//3
		
		when(arrayListSpy.size()).thenReturn(5);  
		System.out.println(arrayListSpy.size());
		
		arrayListSpy.add("Test3");
		System.out.println(arrayListSpy.size());// it's still 5, because it was told to return 5 by being stubbed, then
		//we lost control of spy.
		
		verify(arrayListSpy).add("Test3");
		}
}
