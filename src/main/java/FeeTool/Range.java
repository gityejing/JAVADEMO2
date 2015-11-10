package FeeTool;

/*** 区间定义 */
public class Range {
	private int level; // 区间索引
	private int sx; // 上限
	private int xx; // 下限
	private float rate; // 费率
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSx() {
		return sx;
	}
	public void setSx(int sx) {
		this.sx = sx;
	}
	public int getXx() {
		return xx;
	}
	public void setXx(int xx) {
		this.xx = xx;
	}
	public Range(int dc, int xx, int sx, float rate) {
		super();
		this.level = dc;
		this.xx = xx;
		this.sx = sx;
		this.rate = rate;
	}
	public Range() {
		super();
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
}
