package com.meiduimall.service.sms.model;

/**
 * Created by yangchang on 2017/5/16.
 */
public class MemberOpenID {

	private DataBean data;
	private String msg;
	private int status;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static class DataBean {
		private String memId;
		private String memLoginName;
		private String memNickName;
		private String memPhone;
		private String memPoint;
		private String wxOpenId;

		public String getMemId() {
			return memId;
		}

		public void setMemId(String memId) {
			this.memId = memId;
		}

		public String getMemLoginName() {
			return memLoginName;
		}

		public void setMemLoginName(String memLoginName) {
			this.memLoginName = memLoginName;
		}

		public String getMemNickName() {
			return memNickName;
		}

		public void setMemNickName(String memNickName) {
			this.memNickName = memNickName;
		}

		public String getMemPhone() {
			return memPhone;
		}

		public void setMemPhone(String memPhone) {
			this.memPhone = memPhone;
		}

		public String getMemPoint() {
			return memPoint;
		}

		public void setMemPoint(String memPoint) {
			this.memPoint = memPoint;
		}

		public String getWxOpenId() {
			return wxOpenId;
		}

		public void setWxOpenId(String wxOpenId) {
			this.wxOpenId = wxOpenId;
		}
	}
}
