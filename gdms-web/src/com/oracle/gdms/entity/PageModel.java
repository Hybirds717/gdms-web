package com.oracle.gdms.entity;

import java.util.List;

public class PageModel<T> {

	private int current; // 当前页码
	private int total; // 总页数
	private List<T> data; // 数据集

	public static final int ROWS = 5;
	public static final int MAXCOUNT = 5;	// 页码导航个数

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public String getNav() {
		StringBuilder sb = new StringBuilder();
		int a = current - MAXCOUNT / 2;
		a = a < 1 ? 1 : a;
		int b = current + MAXCOUNT / 2;
		b = b < MAXCOUNT ? MAXCOUNT : b;
		b = b > total ? total : b;
		a = a - ( MAXCOUNT - ( b - a ) -1 );
		a = a < 1 ? 1 : a;
		for ( int i = a; i <= b; i ++ ) {
			if ( i == current ) {
				sb.append("<span class='current'>" + i + "</span>");
			} else {
				sb.append("<a href='javascript:changesearch("+i+")'>"        + i +        "</a>");
			}
		}
		return sb.toString();
	}
	
}
