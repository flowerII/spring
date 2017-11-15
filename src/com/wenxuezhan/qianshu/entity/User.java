package com.wenxuezhan.qianshu.entity;
/**
 * @author qianshu
 *
 * @date   2017年10月6日
 */
public class User {
	
	private int user_id;
	
	private String user_account;
	
	private String user_password;
	
	private String user_name;
	
	private String user_description;
	
	private int user_role;
	
	private int user_sno;

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the user_account
	 */
	public String getUser_account() {
		return user_account;
	}

	/**
	 * @param user_account the user_account to set
	 */
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	/**
	 * @return the user_password
	 */
	public String getUser_password() {
		return user_password;
	}

	/**
	 * @param user_password the user_password to set
	 */
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the user_description
	 */
	public String getUser_description() {
		return user_description;
	}

	/**
	 * @param user_description the user_description to set
	 */
	public void setUser_description(String user_description) {
		this.user_description = user_description;
	}

	/**
	 * @return the user_role
	 */
	public int getUser_role() {
		return user_role;
	}

	/**
	 * @param user_role the user_role to set
	 */
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	/**
	 * @return the user_sno
	 */
	public int getUser_sno() {
		return user_sno;
	}

	/**
	 * @param user_sno the user_sno to set
	 */
	public void setUser_sno(int user_sno) {
		this.user_sno = user_sno;
	}

}
