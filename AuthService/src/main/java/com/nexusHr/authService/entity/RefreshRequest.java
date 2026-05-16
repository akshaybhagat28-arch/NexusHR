package com.nexusHr.authService.entity;


public class RefreshRequest {
	
	private String refreshToken;

	public RefreshRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}
	

	public RefreshRequest() {
		super();
	}


	public String getRefreshToken() {
		return refreshToken;
	}


	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



}