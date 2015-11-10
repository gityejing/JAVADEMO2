package xstream;

import java.util.List;

public class ListBean {
	private String name;
	private List list;
	
	public List getList() {
		return list;
	}
	public ListBean() {
		super();
	}
	public ListBean(String name, List list) {
		super();
		this.name = name;
		this.list = list;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}
