package concurrent;

public class Range<T> {
	T index;
	T length;
	
	public Range() {
		super();
	}
	
	public Range(T index, T length) {
		super();
		this.index = index;
		this.length = length;
	}
	public T getIndex() {
		return index;
	}
	public void setIndex(T index) {
		this.index = index;
	}
	public T getLength() {
		return length;
	}
	public void setLength(T length) {
		this.length = length;
	}
}
