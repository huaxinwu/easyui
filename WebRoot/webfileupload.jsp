<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>web file uplaod</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/webupload/webuploader.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/webupload//jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/webupload//webuploader.js"></script>

  </head>
  
  <body>
    <div id="uploader" class="wu-example">
	    <!--用来存放文件信息-->
	    <div id="thelist" class="uploader-list"></div>
		    <div class="btns">
		        <div id="picker">选择文件</div>
		        <button id="ctlBtn" class="btn btn-default">开始上传</button>
		    </div>
		</div>
	</div>
	
<script type="text/javascript">
  $(function(){
   /*init webuploader*/
   var $list=$("#thelist");   //这几个初始化全局的百度文档上没说明，好蛋疼。
   var $btn =$("#ctlBtn");	 //开始上传
   
   var uploader = WebUploader.create({
	
	    // swf文件路径
	    swf: '${pageContext.request.contextPath }/webupload/Uploader.swf',
	
	    // 文件接收服务端。
	    server: '${pageContext.request.contextPath }/UploadServlet',
	
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',
	
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
	    method:'POST',
	});
	
	// 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
	    $list.append( '<div id="' + file.id + '" class="item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<p class="state">等待上传...</p>' +
	    '</div>' );
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress .progress-bar');
	
	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }
	
	    $li.find('p.state').text('上传中');
	
	    $percent.css( 'width', percentage * 100 + '%' );
	});
	// 文件成功、失败处理
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).find('p.state').text('已上传');
	});
	
	uploader.on( 'uploadError', function( file ) {
	    $( '#'+file.id ).find('p.state').text('上传出错');
	});
	
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').fadeOut();
	});
	
	$btn.on( 'click', function() {
        console.log("上传...");
        uploader.upload();
        console.log("上传成功");
   });
	
  });  
	
 </script>
  </body>
</html>
