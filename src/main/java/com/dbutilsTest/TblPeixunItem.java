package com.dbutilsTest;

import java.util.Date;

/**
 * 培训事项
 * @author yejing
 *
 */
public class TblPeixunItem {
	private Long fsn;
	private String fitemName; // 培训名称
	private String fpeixunKecheng;// 培训课程
	private Date fstartTime; // 报名开始时间
	private Date fendTime;// 报名结束时间
	private String fpeixunUilt;// 主持培训单位
	private String fpeixunZhuanYe;// 培训专业
	private String fpeixunAddress;// 培训地点
	private Date fpeixunStart; // 培训开始时间
	private Date fpeixunEnd;// 培训结束时间
	
	public TblPeixunItem() {
		super();
	}
	public TblPeixunItem(Long fsn, String fitemName, Date fstartTime,
			Date fendTime, String fpeixunUilt, String fpeixunZhuanYe,
			Date fpeixunStart, Date fpeixunEnd) {
		super();
		this.fsn = fsn;
		this.fitemName = fitemName;
		this.fstartTime = fstartTime;
		this.fendTime = fendTime;
		this.fpeixunUilt = fpeixunUilt;
		this.fpeixunZhuanYe = fpeixunZhuanYe;
		this.fpeixunStart = fpeixunStart;
		this.fpeixunEnd = fpeixunEnd;
	}
	
	public Long getFsn() {
		return fsn;
	}
	public void setFsn(Long fsn) {
		this.fsn = fsn;
	}
	
	public String getFitemName() {
		return fitemName;
	}
	public void setFitemName(String fitemName) {
		this.fitemName = fitemName;
	}
	
	public Date getFstartTime() {
		return fstartTime;
	}
	public void setFstartTime(Date fstartTime) {
		this.fstartTime = fstartTime;
	}
	
	public Date getFendTime() {
		return fendTime;
	}
	public void setFendTime(Date fendTime) {
		this.fendTime = fendTime;
	}
	
	public String getFpeixunUilt() {
		return fpeixunUilt;
	}
	public void setFpeixunUilt(String fpeixunUilt) {
		this.fpeixunUilt = fpeixunUilt;
	}
	
	public String getFpeixunZhuanYe() {
		return fpeixunZhuanYe;
	}
	public void setFpeixunZhuanYe(String fpeixunZhuanYe) {
		this.fpeixunZhuanYe = fpeixunZhuanYe;
	}
	
	public Date getFpeixunStart() {
		return fpeixunStart;
	}
	public void setFpeixunStart(Date fpeixunStart) {
		this.fpeixunStart = fpeixunStart;
	}
	
	public Date getFpeixunEnd() {
		return fpeixunEnd;
	}
	public void setFpeixunEnd(Date fpeixunEnd) {
		this.fpeixunEnd = fpeixunEnd;
	}
	
	public String getFpeixunAddress() {
		return fpeixunAddress;
	}
	public void setFpeixunAddress(String fpeixunAddress) {
		this.fpeixunAddress = fpeixunAddress;
	}
	
	public String getFpeixunKecheng() {
		return fpeixunKecheng;
	}
	public void setFpeixunKecheng(String fpeixunKecheng) {
		this.fpeixunKecheng = fpeixunKecheng;
	}
	
	
	
	
	
}
