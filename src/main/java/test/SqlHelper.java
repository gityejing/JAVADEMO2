package test;

import java.util.ArrayList;
import java.util.List;

public class SqlHelper {
	
	private StringBuilder select = new StringBuilder("");
	private StringBuilder from = new StringBuilder("");
	private StringBuilder where = new StringBuilder("");
	private StringBuilder orderby = new StringBuilder("");
	List<Object> params = new ArrayList<Object>();
	
	public SqlHelper() {
		super();
	}

	public StringBuilder getSelect() {
		return select;
	}

	public void setSelect(StringBuilder select) {
		this.select = select;
	}

	public StringBuilder getFrom() {
		return from;
	}

	public void setFrom(StringBuilder from) {
		this.from = from;
	}

	public StringBuilder getWhere() {
		return where;
	}

	public void setWhere(StringBuilder where) {
		this.where = where;
	}

	public StringBuilder getOrderby() {
		return orderby;
	}

	public void setOrderby(StringBuilder orderby) {
		this.orderby = orderby;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
	
	public SqlHelper createSqlHelper(){
		SqlHelper obj = new SqlHelper();
		obj.select = new StringBuilder("");
		obj.from = new StringBuilder("");
		obj.where = new StringBuilder("");
		obj.orderby = new StringBuilder("");
		obj.params = new ArrayList<Object>();
		return obj;
	}
	
	
	public SqlHelper addFilterCondition(String condition){
		
		return this;
	}
	
	
}
