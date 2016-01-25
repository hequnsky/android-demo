package com.rmc.gaiya.bean;

public class App {
	private int State;

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public MsgContent getParam() {
		return Param;
	}

	public void setParam(MsgContent param) {
		Param = param;
	}

	private MsgContent Param;

	@Override
	public String toString() {
		return "App [State=" + State + ", Param=" + Param + "]";
	}

}

class MsgContent {
	private String ErrorName;
	private String Content;

	public String getErrorName() {
		return ErrorName;
	}

	public void setErrorName(String errorName) {
		ErrorName = errorName;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "MsgContent [ErrorName=" + ErrorName + ", Content=" + Content + "]";
	}

}
