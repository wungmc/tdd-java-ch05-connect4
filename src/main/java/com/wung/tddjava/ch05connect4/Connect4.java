/*
 * Copyright (C), 2011-2019.
 */
package com.wung.tddjava.ch05connect4;

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
	
	public int getColumns() {
		return COLUMNS;
	}
	public int getRows() {
		return ROWS;
	}
	
	public int getNumberOfDiscs() {
		return 0;
	}
	
}
