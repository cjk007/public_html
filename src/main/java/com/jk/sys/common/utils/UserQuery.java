package com.jk.sys.common.utils;

import java.util.Map;

import com.jk.sys.domain.UserType;

public class UserQuery extends Query {
	private UserType userType;
	
	public UserQuery(Map<String, Object> params) {
		super(params);
		userType = (UserType)params.get("userType");
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
