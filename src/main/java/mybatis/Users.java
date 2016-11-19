package mybatis;

public class Users {
	
	private Long id;
	private String username;
	private String userpwd;
	private Integer flag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", userpwd="
				+ userpwd + ", flag=" + flag + "]";
	}
	
}
