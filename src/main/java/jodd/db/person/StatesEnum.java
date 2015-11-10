package jodd.db.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jodd.db.DbSession;
import jodd.db.oom.DbOomQuery;
import jodd.db.oom.sqlgen.DbSqlBuilder;

public enum StatesEnum implements Serializable {

	
	
	/**
	 * 10:分配项目负责人，登记采购活动	 
	 * */
	S_10,
	
	/**
	 * 12:审核采购形式
	 * */
	S_12,
	// 需求公示阶段流程开始
	
	/** 需求公示文件 */
	S_17,
	
	/** 需求公示审核 */
	S_18,
	
	/** 需求公示日程 */
	S_19,
	
	/** 发布需求公示公告 */
	S_20,
	
	// 需求公示阶段流程结束

	/** 发布需求公示结果 */
	S_21,
	
	
	/**
	 * 25:委托协议，原来为S_13，	 
	 * */
	S_25,
	
	/**
	 * 26:采购需求，原来为S_16，	 
	 * */
	S_26,
	
	
	
	/**
	 * 30	预备招标文件
	 */
	S_30,
	
	/**
	 * 31	预备备案审核
	 */
	S_31,
	
	/**
	 * 32:   预备招标日程，原来为S_20
	 */
	S_32,
	
	/**
	 * 40	发布预备采购公告
	 */
	S_40,
	
	/**
	 * 50	发布论证会公告
	 */
	S_50,
	
	/**
	 * 52	正式招标文件
	 */
	S_52,
	
	/**
	 * 54	中心备案审核
	 */
	S_54,
	
	/**
	 * 56	财政局审核
	 */
	S_56,
	/**
	 * 57	发改审核
	 */
	S_57,
	
	/**
	 * 55	正式招标日程
	 */
	S_55,
	
	/**
	 * 60	发布采购公告
	 */
	S_60,
	
	/**
	 * 70	报名中
	 */
	S_70,
	
	/**
	 * 80	待开标
	 */
	S_80,
	
	/**
	 * 90	待评标
	 */
	S_90,
	
	/**
	 * 100	待定标
	 */
	S_100,
	
	/**
	 * 110	评标公告
	 */
	S_110,
	
	/**
	 * 120	待中标公告
	 */
	S_120,
	/**
	 * 125	中标公告审核中
	 */
	S_125,
	/**
	 * 130	合同备案
	 */
	S_130,
	
	/**
	 * 140	 保证金退费管理
	 */
	S_140,
	/**
	 * 150	已完成
	 */
	S_150;
	private int stateNo=0;
	private String stateName="";
	private String stateFlowName="";
	//构造方法，初始化：设置当前状态No.设置当前状态名字
	private StatesEnum(){
		String name = this.name();
		String[] ns = name.split("_");
		this.stateNo=Integer.valueOf(ns[1]).intValue();
	}
	/**
	 * 根据状态数字获得状态的枚举对象
	 * @param state
	 * @return null表示没有改状态
	 */
	public static StatesEnum getStatesEnum(int state){
		StatesEnum se = null;
		StatesEnum[] ses = StatesEnum.values();
		for (StatesEnum statesEnum : ses) {
			String name = statesEnum.name();
			String[] ns = name.split("_");
			if(Integer.valueOf(ns[1]).intValue()==state){
				se= statesEnum;
				break;
			}
		}
		return se;
	} 
	/**
	 * 获得第一个流程状态
	 * @return
	 */
	public static StatesEnum getStartState(){
		StatesEnum se = null;
		StatesEnum[] ses = StatesEnum.values();
		for (StatesEnum statesEnum : ses) {
			if(statesEnum.ordinal()==0){
				se=statesEnum;
				break;
			}
		}
		return se;
	}
	/**
	 * 获得最后一个流程状态
	 * @return
	 */
	public static StatesEnum getEndState(){
		StatesEnum se = null;
		StatesEnum[] ses = StatesEnum.values();
		for (StatesEnum statesEnum : ses) {
			if(statesEnum.isLast()){
				se=statesEnum;
				break;
			}
		}
		return se;
	}	
	/**
	 * 获得所有状态的枚举对象
	 * @return
	 */
	public static StatesEnum[] getAllStatesEnum(){
		StatesEnum[] ses = StatesEnum.values();
		return ses;
	}	
	/**
	 * 获得所有状态的枚举对象的状态
	 * @return
	 */
	public static Integer[] getAllStatesEnumNo(){
		StatesEnum[] ses = StatesEnum.values();
		List<Integer> gtl = new ArrayList<Integer>();
		for (StatesEnum statesEnum : ses) {
			gtl.add(statesEnum.getStateNo());
		}
		int c=gtl.size();
		Integer [] gts =(Integer [] )gtl.toArray(new Integer[c]);
		return gts;
	}	
	/**
	 * 获得当前状态数字
	 * @return
	 */
	public int getStateNo() {
		return stateNo;
	}	
	/**
	 * 获得当前状态名字
	 * @return
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * 获得当前状态流程名字
	 * @return
	 */
	public String getStateFlowName() {
		return stateFlowName;
	}
	/**
	 * 获得上一个状态
	 * @return
	 */
	public StatesEnum getPrevious(){
		int index = this.ordinal();
		StatesEnum[] ses = StatesEnum.values();
		if(index==0){
			return StatesEnum.getStartState();
		}else{
			return ses[index-1];
		}
	}
	/**
	 * 获得下一个状态
	 * @return
	 */
	public StatesEnum getNext(){
		int index = this.ordinal();
		StatesEnum[] ses = StatesEnum.values();
		if(ses.length==index){
			return StatesEnum.getEndState();
		}else{
			return ses[index+1];
		}
	}
	/**
	 * 获得下一个状态的数字
	 * @return
	 */
	public Integer getNextNo(){
		return this.getNext().getStateNo();
	}
	/**
	 * 判断是否第一个状态
	 * @return
	 */
	public Boolean isFirst(){
		int index = this.ordinal();
		return index==0;
	}	
	/**
	 * 判断是否最后一个状态
	 * @return
	 */
	public Boolean isLast(){
		int index = this.ordinal();
		StatesEnum[] ses = StatesEnum.values();
		return ses.length==index+1;
	}
	/**
	 * 获得大于等于当前状态的所有状态数字
	 * @param isEq  是否包含本身
	 * @return 
	 */
	public Integer [] getGtNo(Boolean isEq){
		List<Integer> gtl=new ArrayList<Integer>();
		int index = this.ordinal();
		StatesEnum[] ses = StatesEnum.values();
		for (StatesEnum statesEnum : ses) {
			if((isEq && statesEnum.ordinal()>=index) || (!isEq && statesEnum.ordinal()>index)){
				gtl.add(statesEnum.getStateNo());
			}
		}
		int c=gtl.size();
		Integer [] gts =(Integer [] )gtl.toArray(new Integer[c]);
		return gts;
	}
	/**
	 * 获得大于当前状态的所有状态数字
	 * @return
	 */
	public Integer []  getGtNo(){
		return getGtNo(false);
	}
	/**
	 * 获得小于等于当前状态的所有状态数字
	 * @param isEq  是否包含本身
	 * @return
	 */
	public Integer [] getLtNo(Boolean isEq){
		List<Integer> ltl=new ArrayList<Integer>();
		int index = this.ordinal();
		StatesEnum[] ses = StatesEnum.values();
		for (StatesEnum statesEnum : ses) {
			if((isEq && statesEnum.ordinal()<=index) || (!isEq && statesEnum.ordinal()<index) ){
				ltl.add(statesEnum.getStateNo());
			}
		}
		int c=ltl.size();
		Integer [] lts =(Integer [] )ltl.toArray(new Integer[c]);
		return lts;
	}	
	/**
	 * 获得小于当前状态的所有状态数字
	 * @return
	 */
	public Integer [] getLtNo(){
		return getLtNo(false);
	}
	public static void main(String[] args){
		StatesEnum[] a = getAllStatesEnum();	
		long id = 1000L;
		for (int i = 0; i < a.length; i++) {
			PassInfo pi = new PassInfo();
			pi.setId(id+i);
			pi.setState(a[i].getStateNo());
			pi.setRefId(1000L);
			pi.setIspass(false);
			insert(pi);
		}
	}
	
	public static void insert(PassInfo pi){
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            DbSqlBuilder b = DbSqlBuilder.sql().insert(PassInfo.class, pi);
            DbOomQuery q = new DbOomQuery(session,b);
            q.executeUpdate();
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
}

