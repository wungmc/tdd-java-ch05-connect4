/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 7 列 * 6 行的棋盘游戏：
 * - 两个玩家，分为红色和绿色
 * - 轮流将碟片从列顶放入，碟片将落入最低一个没有碟片的空格中，依次循环
 * - 先实现在水平、垂直或斜线方向上将 4 个自己的碟片连接起来的玩家获胜。
 *
 * @author wung 2019-07-20.
 */
public class Connect4 {
	
	private static final int COLUMNS = 7;
	private static final int ROWS = 6;
	private static final String EMPTY = " ";
	
	private static final String RED = "R";
	private static final String GREEN = "G";
	
	private String[][] board = new String[ROWS][COLUMNS];
	private String currentPlayer = RED;
	private String winner = "";
	
	private PrintStream out;
	
	public int getColumns() {
		return COLUMNS;
	}
	public int getRows() {
		return ROWS;
	}
	
	public Connect4(PrintStream out) {
		this.out = out;
		Arrays.stream(board).forEach(row -> Arrays.fill(row, EMPTY));
	}
	
	public String getCurrentPlayer() {
		out.println("Player " + currentPlayer + " turn");
		return currentPlayer;
	}
	
	public int getNumberOfDiscs() {
		return IntStream.range(0, COLUMNS)
				.map(this::getNumberOfDiscsInColumn)
				.sum();
	}
	
	public int putDiscInColumn(int column) {
		checkColumn(column);
		int row = getNumberOfDiscsInColumn(column);
		checkPositionWhenInsert(row, column);
		board[row][column] = currentPlayer;
		printBoard();
		
		if (isWin(row, column)) {
			winner = currentPlayer;
		}
		
		switchPlayer();
		return row;
	}
	
	private void checkColumn(int column) {
		if (column < 0 || column >= COLUMNS) {
			throw new RuntimeException("Invalid column : " + column);
		}
	}
	
	private void checkPositionWhenInsert(int row, int column) {
		if (row >= ROWS) {
			throw new RuntimeException(column + " is full");
		}
	}
	
	public int getNumberOfDiscsInColumn(int column) {
		return (int)IntStream.range(0, ROWS)
				.filter(row -> !board[row][column].equalsIgnoreCase(EMPTY))
				.count();
	}
	
	private void switchPlayer() {
		currentPlayer = currentPlayer.equals(RED) ? GREEN : RED;
	}
	
	private void printBoard() {
		for (int row = ROWS - 1; row >= 0; row--) {
			StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
			Stream.of(board[row]).forEachOrdered(stringJoiner::add);
			out.println(stringJoiner);
		}
	}
	
	public boolean isFinished() {
		return getNumberOfDiscs() == ROWS * COLUMNS;
	}
	
	public String getWinner() {
		return winner;
	}
	
	private boolean isWin(int row, int column) {
		Pattern pattern = Pattern.compile(".*" + currentPlayer + "{4}");
		
		// 垂直线
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r <= row; r++) {
			sb.append(board[r][column]);
		}
		return pattern.matcher(sb.toString()).matches();
	}
	
}
