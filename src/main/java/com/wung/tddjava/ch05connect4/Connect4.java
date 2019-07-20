/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

import javax.swing.border.EmptyBorder;
import java.util.Arrays;

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
	
	private String[][] board = new String[ROWS][COLUMNS];
	
	public int getColumns() {
		return COLUMNS;
	}
	public int getRows() {
		return ROWS;
	}
	
	public Connect4() {
		Arrays.stream(board).forEach(row -> Arrays.fill(row, EMPTY));
	}
	
	public int getNumberOfDiscs() {
		return 0;
	}
	
	public int putDiscInColumn(int column) {
		if (column < 1 || column > COLUMNS) {
			throw new RuntimeException("Invalid column : " + column);
		}
		int row = getNumberOfDiscsInColumn(column);
		return row;
	}
	
	public int getNumberOfDiscsInColumn(int column) {
		int sum = 0;
		for (int i = 0; i < ROWS; i++) {
			if (!board[i][column].equalsIgnoreCase(EMPTY)) {
				sum++;
			}
		}
		return sum;
	}
	
}
