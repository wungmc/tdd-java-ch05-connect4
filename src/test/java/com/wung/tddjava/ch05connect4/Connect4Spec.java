/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author wung 2019-07-20.
 */
public class Connect4Spec {
	
	private Connect4 connect4;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
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
	
	
	@Test
	public void whenDiscOutsideBoardThenThrowRuntimeException() {
		int column = -1;
		expected.expect(RuntimeException.class);
		expected.expectMessage("Invalid column : " + column);
		connect4.putDiscInColumn(column);
	}
	
	@Test
	public void whenDiscInsertedInEmptyColumnThenPositionIsZero() {
		int column = 1;
		assertThat(connect4.putDiscInColumn(column), is(0));
	}
	
	@Test
	public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
		int column = 1;
		connect4.putDiscInColumn(column);
		assertThat(connect4.putDiscInColumn(column), is(1));
	}
	
	@Test
	public void whenDiscInsertedInColumnThenNumberOfDiscsIncreases() {
		int column = 1;
		connect4.putDiscInColumn(column);
		connect4.putDiscInColumn(column);
		assertThat(connect4.getNumberOfDiscs(), is(2));
	}
	
	@Test
	public void whenDiscInsertFullColumnThenThrowRuntimeException() {
		int column = 2;
		for (int row = 0; row < 6; row++) {
			connect4.putDiscInColumn(column);
		}
		expected.expect(RuntimeException.class);
		expected.expectMessage(column + " is full");
		connect4.putDiscInColumn(column);
		
	}
	
	// 假定总是红方先来
	@Test
	public void whenFirstPlayerPlayThenDiscColorIsR() {
		assertThat(connect4.getCurrentPlayer(), is("R"));
	}
	
	@Test
	public void whenSecondPlayerPlayThenDiscColorIsG() {
		int column = 1;
		connect4.putDiscInColumn(column);
		assertThat(connect4.getCurrentPlayer(), is("G"));
	}
	
}
