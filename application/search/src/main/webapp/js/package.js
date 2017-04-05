	function modifyPackage(type, id) {
			location.href = "udpatePackageIntegral.do?type="+type+"&packageId=" + id;
	}
	
	function deletePackage() {
		var obj = $('input:checkbox[name=cb_oil]:checked');
		if(obj.length==0) {
			alert('至少选择一项进行删除');
		} else {
			var params = "";
			for(var i=0;i<obj.length;i++) {
				params += "|" + obj[i].defaultValue;
			}
			console.log(params)
			$.post("deletePackageIntegral.do", {param:params},function (data,status) {
				if(status == 'success') {
					alert(JSON.parse(data)['result_msg']);
				} else {
					alert('网络错误,请重试!')
				}
			})
		}
	}
	
