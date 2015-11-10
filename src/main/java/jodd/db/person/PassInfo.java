package jodd.db.person;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

@DbTable("passInfo")
public class PassInfo {
	@DbColumn("id")
	private Long id;
	@DbColumn("state")
	private Integer state;
	@DbColumn("ispass")
	private boolean ispass;
	@DbColumn("refId")
	private Long refId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public boolean isIspass() {
		return ispass;
	}
	public void setIspass(boolean ispass) {
		this.ispass = ispass;
	}
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
}
