/**
 * 流水账
 */
var runningAccount = {
	config: {
		data_url: "RunningAccount.do",
		export_url: "exportExcel.do",
		sync_url: "syncData.do",
		com_param: "?mchProductName=",
		tel: "tel",
		flow: "flow", 
		fuel: "fuel",
		data_tel: "telRecharge",
		data_flow: "flowRecharge",
		data_fuel: "fuelRecharge"
	},
	// 导出excel
	exportExcel: function(type) {
		window.open(runningAccount.config.export_url + runningAccount.config.com_param + type)
	},
	// 设置样式
	setCss: function() {
		$(".Wdate").css("height", "20").css("margin-top", "0px");
		$("#query_field").css("width", "100px");
	},
	// 查询
	query: function() {
		if(event.keyCode == 13) {
			var dataType = $("#data_type").val();
			var mchProductName = "";
			var url = "";
			if(runningAccount.config.data_tel.indexOf(dataType, 0) != -1) {
				mchProductName = runningAccount.config.data_tel;
				url = runningAccount.config.tel + runningAccount.config.data_url;
			} else if(runningAccount.config.data_flow.indexOf(dataType, 0) != -1) {
				mchProductName = runningAccount.config.data_flow;
				url = runningAccount.config.flow + runningAccount.config.data_url;
			} else if(runningAccount.config.data_fuel.indexOf(dataType, 0) != -1) {
				mchProductName = runningAccount.config.data_fuel;
				url = runningAccount.config.fuel + runningAccount.config.data_url;
			}	
			var startTime = $("#start_time").val();
			var endTime = $("#end_time").val();
			var queryField = $("#query_field").val();
			var queryCondit = $("#query_condit").val();
			url += runningAccount.config.com_param + mchProductName;
			if(queryCondit != "" && queryCondit != null) {
				url += "&" + queryField + "=" + queryCondit;
			}
			if(startTime != "" && startTime != null) {
				url += "&startTime=" + startTime;
			}
			if(endTime != "" && endTime != null) {
				url += "&endTime=" + endTime;
			}
			url += "&queryData=1";
			url = url.replace(/\s+/g, "%20");
			debugger;
			window.location.href = url;
		}
	},
	// 同步数据
	syncDatas: function() {
		$.ajax({
			type: "POST",
			url: runningAccount.config.sync_url,
			dataType: "json",
			success: function(res) {
				res = eval('(' + res + ')');
				alert(res.result_msg);
			}
		});
	},
	// 初始化
	init: function() {
		runningAccount.setCss();
	}
	
		
}


$(function(){
	
	runningAccount.init();
	
})