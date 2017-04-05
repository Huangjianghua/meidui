  var setting = {
	        check: {
	            enable: true,
	            chkStyle: "radio"
	        },
	        data: {
	            simpleData: {
	                enable: true,
	                idKey: "id", // id编号命名 默认  
	                pIdKey: "pid", // 父id编号命名 默认   
	            }
	        },
	        callback: {
				onClick: onClick
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
			},
		      error: function(XMLHttpRequest, error, errorThrown){  
		      alert(error);  
		      alert(errorThrown);  
		      } 
		})
		
      /**
       * 删除菜单
       */
	  $("#deleteMenus").click(function (){
		  if(!confirm("确定删除选择的菜单项?")){
				return false;
		  }
		  //获得选中的菜单id
		  var treeObj = $.fn.zTree.getZTreeObj("tree");
		  var nodes = treeObj.getCheckedNodes(true);
		  if(nodes.length==0){
			  alert("请选中要删除的菜单");
			  return false ;
		  }
		  var isParent = nodes[0].isParent;
		  //如果是父节点，提示先删除子节点
		  if(isParent==true){
			  alert("请先删除子节点");
			  return ;
		  }
		  
		 //根据menuId删除节点
		 var menuId  = nodes[0].id;
		 
		 $.ajax({
			 url:ctx+"/menu/deleteMenu.do?menuId="+menuId,
			 type:"post",
			 success:function(data){
			    alert("删除成功");
			 }
		 });
		 
	  })
		
	}); 
  
  
  /**
   * 选择菜单
   * @param event
   * @param treeId
   * @param treeNode
   * @param clickFlag
   */
  function onClick(event, treeId, treeNode, clickFlag){
  	var zTree = $.fn.zTree.getZTreeObj("ztree");
  	//zTree.expandNode(treeNode);
  	var url = ctx+"/menu/toAddOrUpdatePage.do?id="+treeNode.id;
  	$("#iframeMenuEdit").attr("src",url);
  }
  

