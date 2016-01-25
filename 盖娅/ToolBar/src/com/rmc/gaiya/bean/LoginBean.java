package com.rmc.gaiya.bean;

public class LoginBean {
	public String UserId;
	public String UserName;
	public String PublicKey;
	public String PrivateKey;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPublicKey() {
		return PublicKey;
	}
	public void setPublicKey(String publicKey) {
		PublicKey = publicKey;
	}
	public String getPrivateKey() {
		return PrivateKey;
	}
	public void setPrivateKey(String privateKey) {
		PrivateKey = privateKey;
	}
	@Override
	public String toString() {
		return "LoginBean [UserId=" + UserId + ", UserName=" + UserName + ", PublicKey=" + PublicKey + ", PrivateKey="
				+ PrivateKey + "]";
	}

}
