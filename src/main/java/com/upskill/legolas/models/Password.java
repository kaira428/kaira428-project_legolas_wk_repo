package com.upskill.legolas.models;

public class Password {

    public Password(String currentPassword, String newPassword, String reEnterNewPassword) {
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.reEnterNewPassword = reEnterNewPassword;
	}

	public Password() {};
	
	private String currentPassword;
	private String newPassword;
	private String reEnterNewPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReEnterNewPassword() {
		return reEnterNewPassword;
	}
	public void setReEnterNewPassword(String reEnterNewPassword) {
		this.reEnterNewPassword = reEnterNewPassword;
	}
}
