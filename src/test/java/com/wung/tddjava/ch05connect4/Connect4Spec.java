/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author wung 2019-07-20.
 */
public class Connect4Spec {
	
	private OutputStream out;
	private Connect4 connect4;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Before
	public void before() {
		out = new ByteArrayOutputStream();
		connect4 =  new Connect4(new PrintStream(out));
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
	public void whenDiscInsert7ColumnThenThrowRuntimeException() {
		int column = 7;
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
	
	@Test
	public void whenAskedForCurrentPlayerThenOutputNotice() {
		connect4.getCurrentPlayer();
		assertThat(out.toString(), containsString("Player R turn"));
	}
	
	@Test
	public void whenDiscInsertedThenOutBoard() {
		int column = 1;
		connect4.putDiscInColumn(column);
		System.out.println(out.toString());
		assertThat(out.toString(), containsString("| |R| | | | | |"));
	}
	@Test
	public void whenSecondDiscInsertedThenOutBoard() {
		connect4.putDiscInColumn(1);
		connect4.putDiscInColumn(4);
		
		System.out.println(out.toString());
		assertThat(out.toString(), containsString("| |R| | |G| | |"));
	}
	
	
	@Test
	public void whenGameStartThenIsNotFinished() {
		assertFalse(connect4.isFinished());
	}
	
	@Test
	public void whenBoardIsFullThenIsFinished() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				connect4.putDiscInColumn(col);
			}
		}
		assertTrue("Game must be finished", connect4.isFinished());
		
	}
	
	@Test
	public void when4VertiaclDiscsAreConnectedThenPlayerWin() {
		for (int i = 0; i < 3; i++) {
			connect4.putDiscInColumn(1); // R
			connect4.putDiscInColumn(3); // G
		}
		assertThat(connect4.getWinner(), isEmptyString());
		
		connect4.putDiscInColumn(1); // R
		assertThat(connect4.getWinner(), is("R"));
	}
	
	
	@Test
	public void when4HorizontalDiscsAreConnectedThenPlayerWin() {
		int col;
		for (col = 0; col < 3; col++) {
			connect4.putDiscInColumn(col); // R
			connect4.putDiscInColumn(col); // G
		}
		assertThat(connect4.getWinner(), isEmptyString());
		connect4.putDiscInColumn(col); // R
		assertThat(connect4.getWinner(), is("R"));
		
	}
	
	@Test
	public void when4Diagonal1DiscsAreConnectedThenPlayerWin() {
		int[] cols = new int[] {1, 2, 2, 3, 4, 3, 3, 4, 4, 5, 4};
		for (int i = 0; i < cols.length; i++) {
			connect4.putDiscInColumn(cols[i]);
		}
		assertThat(connect4.getWinner(), is("R"));
	}
	
	@Test
	public void when4Diagonal2DiscsAreConnectedThenPlayerWin() {
		int[] cols = new int[] {3, 4, 2, 3, 2, 2, 1, 1, 1, 1};
		for (int i = 0; i < cols.length; i++) {
			connect4.putDiscInColumn(cols[i]);
		}
		assertThat(connect4.getWinner(), is("G"));
	}
	
}
