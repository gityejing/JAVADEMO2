package FeeTool;

/**
 * Created by Administrator on 2015/4/17.
 */
public class RangeConfig {
    private boolean eqr = false; //为true表示包尾不包头，false为包头不包尾
    public RangeConfig() {}
    public RangeConfig(boolean eqr) {
        this.eqr = eqr;
    }
    public boolean isEqr() {
        return eqr;
    }
}
