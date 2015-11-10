package com.dbutilsTest;

/**
 * TWebinfo entity. @author MyEclipse Persistence Tools
 */
public class TWebinfo implements java.io.Serializable {

	// Fields

	private Long fid;
	private String fkey;
	private byte[] fvalue;
	private String ftype;
	private String flable;

	// Constructors
	public String getFlable() {
		return flable;
	}

	public void setFlable(String flable) {
		this.flable = flable;
	}

	/** default constructor */
	public TWebinfo() {
	}

	/** minimal constructor */
	public TWebinfo(Long fid) {
		this.fid = fid;
	}

	/** full constructor */
	public TWebinfo(Long fid, String fkey, byte[] fvalue, String ftype) {
		this.fid = fid;
		this.fkey = fkey;
		this.fvalue = fvalue;
		this.ftype = ftype;
	}

	// Property accessors
	public Long getFid() {
		return this.fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getFkey() {
		return this.fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	public byte[] getFvalue() {
		return this.fvalue;
	}

	public void setFvalue(byte[] fvalue) {
		this.fvalue = fvalue;
	}

	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

}