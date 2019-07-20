/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author wung 2019-07-20.
 */
public class Connect4Spec {
	
	private Connect4 connect4;
	
	@Before
	public void before() {
		connect4 =  new Connect4();
	}
	
	@Test
	public void whenInstantiatedThenColumnsIs7() {
		assertEquals(connect4.getColumns(), 7);
	}
	
	@Test
	public void whenInstantiatedThenRowsIs6() {
		assertEquals(connect4.getRows(), 6);
	}
	
	@Test
	public void whenInstantiatedThenBoardIsEmpty() {
		assertThat(connect4.getNumberOfDiscs(), is(0));
	}
	
}
