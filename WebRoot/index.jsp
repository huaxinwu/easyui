<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui crud demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- 引入EasyUI的样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.3/themes/default/easyui.css" type="text/css"/>
  	<!-- 引入EasyUI的图标样式文件-->
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.3/themes/icon.css" type="text/css"/>
    <!-- 引入JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.3/jquery.min.js"></script>
    <!-- 引入EasyUI -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    
  </head>
  
  <body>
  	<!-- 表格 -->
    <table id="myTable" 
    	class="easyui-datagrid"
    	width="800px"
    	height="250px"
    	url="UserServlet?flag=query"
    	toolbar="#myToolbar"
    	pagination="true"
    	rownumbers="true"
    	fitColumns="true"
    	singleSelect="true"
    	striped="true"
    	pageSize="5"
    	pageList="[3,5,8,10]">
    	<thead>
    		<tr>
    			<th field="uId" width="50" data-options="hidden:true" align="center">编号</th>
                <th field="userName" width="50" align="center">姓名</th>
                <th field="password" width="50" align="center">密码</th>
                <th field="userSex" width="50" align="center">性别</th>
                <th field="userAge" width="50" align="center">年龄</th>
                <th field="education" width="50" align="center">学历</th>
                <th field="address" width="50" align="center">地址</th>
    		</tr>
    	</thead>
    </table>
    <!-- 工具栏 -->
    <div id="myToolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
    </div>
    <!-- 弹出框  -->
    <div id="myDialog" class="easyui-dialog" style="width:400px;height:300px;padding:10px 85px;"
        closed="true" buttons="#myButtons">
        <div class="ftitle">用户信息</div>
        <form id="myForm" method="post" novalidate>
        	<div class="fitem">
		        <label>姓名:</label>
		        <input name="userName" class="easyui-validatebox"  required="true" />
		    </div>
		    <div class="fitem">
                <label>密码:</label>
                <input name="password" class="easyui-validatebox" required="true" />
            </div>
            <div class="fitem">
                <label>性别:</label>
                <input name="userSex" class="easyui-validatebox" required="true"/>
            </div>
            <div class="fitem">
                <label>年龄:</label>
                <input name="userAge" class="easyui-validatebox" required="true"/>
            </div>
            <div class="fitem">
                <label>学历:</label>
                <input name="education" class="easyui-validatebox" required="true"/>
            </div>
            <div class="fitem">
                <label>地址:</label>
                <input name="address" class="easyui-validatebox" required="true"/>
            </div>
        </form>
	</div>
    <!-- 按钮 -->
    <div id="myButtons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#myDialog').dialog('close')">取消</a>
    </div>
  </body>
  <script type="text/javascript">
  	/** 请求路径 */
  	var url;
  	/**
  	 * 新增用户信息
  	 */
  	function addUser(){
  		$("#myDialog").dialog("open").dialog("setTitle","新增");
        $("#myForm").form("clear");
        url = "UserServlet?flag=add";
  	}
  	/**
  	 * 修改用户信息
  	 */
  	function editUser(){
  		var row = $("#myTable").datagrid("getSelected");
  		/** 如果有数据 ,就进行修改  */
  		if(row){
  			$("#myDialog").dialog("open").dialog("setTitle","修改");
  			$("#myForm").form("load",row);
  			url = "UserServlet?flag=update&uId="+row.uId;
  		}
  	}
  	/**
  	 * 删除用户信息
  	 */
  	 function removeUser(){
  	 	var row = $("#myTable").datagrid("getSelected");
  	 	/** 如果有数据 ,就进行删除  */
  	 	if(row){
  	 		$.messager.confirm("确认","您确定要删除吗？",function(r){
  	 			if(r){
  	 				$.post("UserServlet?flag=delete",{uId:row.uId},function(result){
  	 					if(result.success){
  	 						$.messager.show({
  	 							title:"提示",
  	 							msg:result.message
  	 						});
  	 						/** 重新加载表格数据  */
  	 						$("#myTable").datagrid("reload");
  	 					}else{
  	 						$.messager.show({
  	 							title:"提示",
  	 							msg:result.message
  	 						});
  	 					}
  	 				},"json");
  	 			}
  	 		});
  	 	}
  	 }
  	 /**
  	 * 保存用户信息
  	 */
  	 function saveUser(){
  	 	$("#myForm").form("submit",{
  	 		url:url,
  	 		onSubmit:function(){
  	 			return $(this).form("validate");
  	 		},
  	 		success:function(result){
  	 			/** 转成json对象  */
  	 			var result = eval('('+result+')');
  	 			if(result.success){
  	 				$.messager.show({
  	 					title:"提示",
  	 					msg:result.message
  	 				});
  	 				/** 关闭弹出框  */
  	 				$("#myDialog").dialog("close");
  	 				$("#myTable").datagrid("reload");
  	 			}else{
  	 				$.messager.show({
  	 					title:"提示",
  	 					msg:result.message
  	 				});
  	 			}
  	 		},
  	 	});
  	 }
  	 
  	 /**
  	 * 搜索用户信息
  	 */
  	 function doSearch(){
  	 	$("#myTable").dategrid("load",{
  	 		userName:$("#userName").val(),
  	 		education:$("#education").val()
  	 	});
  	 }
  </script>
</html>
