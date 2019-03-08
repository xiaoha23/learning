package com.xiaonan.learning.springunittestingwithjunitandmockito.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class SomeBusinessTest {

	@Test
	public void calculateSum_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actual = business.calculateSum(new int[] {1, 2, 3});
		int expect = 6;
		assertEquals(expect, actual);
	}
	
	@Test
	public void calculateSum_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actual = business.calculateSum(new int[] {});
		int expect = 0;
		assertEquals(expect, actual);
	}

	@Test
	public void calculateSum_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actual = business.calculateSum(new int[] {5});
		int expect = 5;
		assertEquals(expect, actual);
	}
}
