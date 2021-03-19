package fpt.tracnghiem.model;

public class MyCounter {
	private Integer index;

	
	
	public MyCounter(Integer index) {
		super();
		this.index = index;
	}
	
	public MyCounter() {
		this.index = 1;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public Integer getIndexAndIncrease() {
		return index++;
	}
	
	public Integer getIndexAndDecrease() {
		return index--;
	}
	
}
