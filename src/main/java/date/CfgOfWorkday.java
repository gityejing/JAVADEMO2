package date;

import java.io.Serializable;
import java.util.Date;

/**工作日设置表*/
public class CfgOfWorkday implements Serializable {
	private static final long serialVersionUID = 1909683636908218626L;
	private Integer id;
	private Date dateOfYear;/**日期年份*/
	private Integer type;/**1.正常工作日调休;2.休息日调上班 */
	private String remark;/**备注*/
	private Date st;// 开始日期
	private Date en;// 结束日期
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateOfYear() {
		return dateOfYear;
	}

	public void setDateOfYear(Date dateOfYear) {
		this.dateOfYear = dateOfYear;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSt() {
		return st;
	}

	public void setSt(Date st) {
		this.st = st;
	}

	public Date getEn() {
		return en;
	}

	public void setEn(Date en) {
		this.en = en;
	}

	

}



