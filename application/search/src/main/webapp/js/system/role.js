 var setting = {
	        check: {
	            enable: true,
	        },
	        data: {
	            simpleData: {
	                enable: true,
	                idKey: "id", // id编号命名 默认  
	                pIdKey: "pid", // 父id编号命名 默认   
	            }
	        }

	    }; 
  $(document).ready(function(){
		//根据用户id 加载菜单
		$.ajax({
			url:ctx+"/menu/slectCheckdMenu.do?type=add",
			type:"post",
			dataType:"json",
			success:function(data){
				$.fn.zTree.init($("#tree"), setting, data);
				var treeObj = $.fn.zTree.getZTreeObj("tree");
			    treeObj.checkAllNodes(true);
			},
		      error: function(XMLHttpRequest, error, errorThrown){  
		      alert(error);  
		      alert(errorThrown);  
		      } 
		})
		
		//接收表单验证通过的事件
		$('#rolefrom').bind('valid.form', function(){
			saveRole();
		});
		
		
	}); 
  
  // 保存角色
  function saveRole(){
	    //获取选中的菜单
	    var ids = "";
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		for(var i=0;i<nodes.length;i++){
			ids+=nodes[i].id+",";
		}
		
	   var rolename= $("#role_name").val();
	   var roledesc  = $("#role_desc").val();
	   var status = $("#status").val();
	   var data= {"role_name":rolename,"role_desc":roledesc,"status":status,"ids":ids};
		
		//保存
		$.ajax({
			url:ctx+"/role/addRole.do",
			type:"post",
			data:data,
			dataType:"json",
			success : function(rt) {
				if(rt){
					alert("新增角色成功");
					window.location.href=ctx+'/role/showRoleList.do';
				}else{
					alert("新增角色失败");
				}
				
			}
		})
  }
  
  
  /**
   * 全选
   */
  function checkAllNodes() {
      var treeObj = $.fn.zTree.getZTreeObj("tree");
      treeObj.checkAllNodes(true);
  }
  
  /**
   * 全部选
   */
  function cancelAllNodes() {
      var treeObj = $.fn.zTree.getZTreeObj("tree");
      treeObj.checkAllNodes(false);
  }
