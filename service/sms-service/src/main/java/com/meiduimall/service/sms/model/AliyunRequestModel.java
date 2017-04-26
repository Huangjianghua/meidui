/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AliyunRequestModel {

	public class Result {

		@JsonProperty("err_code")
		private String errCode;

		private String model;
		private String success;

		public String getErrCode() {
			return errCode;
		}

		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

	}

	private Result result;

	@JsonProperty("request_id")
	private String requestId;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
