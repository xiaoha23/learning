package com.xiaonan.learning.springunittestingwithjunitandmockito.business;

import static org.junit.Assert.*;

import org.junit.Test;

import com.xiaonan.learning.springunittestingwithjunitandmockito.data.SomeDataService;

class SomeDataServiceStub implements SomeDataService{

	@Override
	public int[] retrieveAllData() {
		return new int[] {1, 2, 3};
	}
	
}

class SomeDataServiceStubEmpty implements SomeDataService{

	@Override
	public int[] retrieveAllData() {
		return new int[] {};
	}
	
}

class SomeDataServiceStubOneElement implements SomeDataService{

	@Override
	public int[] retrieveAllData() {
		return new int[] {5};
	}
	
}
public class SomeBusinessStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStub());
		int actual = business.calculateSumUsingDataService();
		int expect = 6;
		assertEquals(expect, actual);
	}
	
	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStubEmpty());
		int actual = business.calculateSumUsingDataService();
		int expect = 0;
		assertEquals(expect, actual);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStubOneElement());
		int actual = business.calculateSumUsingDataService();
		int expect = 5;
		assertEquals(expect, actual);
	}
}
