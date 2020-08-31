<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="${ href }">
<title>登录</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/dialog-min.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/dialog-min.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body style="background:url(images/bg.jpg); background-repeat: repeat-y;">
	
	<div class="container">
		<div class="line bouncein">
			<div class="xs6 xm4 xs3-move xm4-move">
				<div style="height: 10px;"></div>
				<div class="media media-y margin-big-bottom"></div>
				<form action="admin/user/login.php" method="post" id="loginForm" >
					
					<input type='hidden' id="loginMsg" value="${login_msg}">
					<div style="height: 150px;"></div>
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>后台管理中心</h1>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="name" id="name" value="13396084647"
										placeholder="登录账号" data-validate="required:请填写账号" /> <span
										class="icon icon-phone margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password" value="1234"
										placeholder="登录密码" data-validate="required:请填写密码" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field">
									<input type="text" class="input input-big" name="code" id="code" value="AAAA"
									placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
									
									<img src="admin/user/yzm/code.png" alt="" width="100" height="32"
										class="passcode" style="height: 43px; cursor: pointer;"
										onclick="this.src=this.src+'?'">
								</div>
							</div>
						</div>
						<div style="padding: 30px;">
							<input type="submit" class="button button-block bg-main text-big input-big" value="登录">
						</div>
						
						<div style="padding: 0px 30px 30px;">
							<input type="button" class="button button-block bg-main text-big input-big" value="去注册" id="goReg">
						</div>
					</div>
				</form>
				
				<!-- ------------------------------------------------------------- -->
				
				
				
				
				
				<iframe name="ifr_up" style="display:none"></iframe>
				
				<form action="admin/user" style="display:none" target="ifr_up" name="form1" enctype="multipart/form-data" method="post" id="form1">
			    	<input name="file" type="file" accept="image/gif, image/jpeg, image/png" id="file" /><br/>
			    	<!-- 允许上传的文件类型 gif,png,jpg  也可以用accept="image/*" -->
			    </form>
			    
			    
			    
			    
				
				<form action="admin/user/reg.php" method="post" id="regForm" style="display:none" onsubmit="return check()" >
					<input type='hidden' id="errMsg" value="${err_msg}">
					<input type='hidden' id="msg" value="${msg}">
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>新用户注册</h1>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="name" id="mobile" onblur="testMobile(this)"
										placeholder="手机号码" data-validate="required:请填写手机号码" /> <span
										class="icon icon-phone margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password"
										placeholder="登录密码" data-validate="required:请填写密码" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password2"
										placeholder="确认密码" data-validate="required:请填写确认密码" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="realname"
										placeholder="真实姓名" data-validate="required:请填写真实姓名" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" id="pic" readonly name="slogo" class="input tips" style="width:80%; float:left;" placeholder="单击此处上传照片" />
							        <input type="button" class="button bg-blue margin-left" id="doupload" value="+ 提交上传" >
							        <span id="view"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="idnumber"
										placeholder="身份证号" data-validate="required:请填写身份证号" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<select name="prov" class="place input-big" id="prov" >
									</select>
									<select name="city" class="place input-big" id="city" ></select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="field">
									<input type="text" class="input input-big" name="code" id="yzm"
										placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
									<img src="admin/user/yzm/code.png" alt="" width="100" height="32"
										class="passcode" style="height: 43px; cursor: pointer;"
										onclick="this.src=this.src+'?'">

								</div>
							</div>
						</div>
						<div style="padding: 30px;">
							<input type="submit" class="button button-block bg-main text-big input-big" value="注册" id="reg">
						</div>
						
						<div style="padding: 0px 30px 30px;">
							<input type="button" class="button button-block bg-main text-big input-big" value="去登录" id="goLogin">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$("#goReg, #goLogin").click( function(){	// 为去注册和去登录按钮，注册事件处理
		$("form:not(:eq(1))").slideToggle(); 
	})
	
	function check(){						// 检查输入项是否法，不合法就弹消息提示，并return false
		var v = $("#pic").val();
		if ( v.indexOf("\\") != -1 ){		// 查找内容中是否含有路径分隔符，如果有，就提示需要先提交上传
			qipao("请先提交上传", $("#pic"));
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function testMobile(txt){
		var v = txt.value;
		if ( v == "" ) return;
		if ( v.length != 11 ) {
			qipao("手机号码不合法", $("#mobile"));
			return;
		}
		
		$.ajax({
			
			url : "admin/user/mobile.php",	// 请求的URI
			data: {"mobile" : v},			// 提交的参数数据
			type: "GET",					// 请求方式 			
          	success : function(result){	 	// 成功之后做什么
          		var json = eval(result);	// 把结果变成JSON对象
          		qipao(json.data.message, $("#mobile"));
          		if ( json.data.code != 0 ){
          			$("#mobile").css({"border":"1px solid red"});
          		} else {
          			$("#mobile").css({"border":"1px solid green"});
          		}
          	}
			
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function initProv(target, pid){
		$.ajax({
			
			url : "admin/area/provlist",	// 请求的URI
			data: {"parentid" : pid},		// 提交的参数数据
			type: "GET",					// 请求方式 			
          	success : function(result){	 	// 成功之后做什么
          		var json = eval(result);	// 把结果变成JSON对象
          		var list = json.data;		// 得到对象中的集合
          		
          		target.options.length = 0;	// 清空当前所有选项
          		for ( var i = 0; i < list.length; i ++ ){
          			var o = list[i];
          			var op = new Option( o.name, o.areaid );
          			target.options.add(op);  // 向下拉框增加一个选项
          		}
          		
          	}
			
		});
	}
	
	onload = function(){
		
		initProv(document.getElementById("prov"), 0);
		
		$("#prov").change(function(){
			// 拿到当前选中的省份id，发起新的查询
			var id = $(this).val();
			initProv(document.getElementById("city"), id);
		});
		
		initProv(document.getElementById("city"), 1);
		
		var m = $("#errMsg").val();			// 得到隐藏域中的内容
		if ( m != "" ){
			$("#loginForm").hide();
			$("#regForm").show();
			qipao(m, $("#yzm") );			// 在验证码框的附近弹一个气泡
		}
		
		m = $("#msg").val();	 // 注册成功的消息
		if ( m != "" ){
			qipao(m);			// 注册成功的消息，弹气泡在屏幕中间
		}
		
		m = $("#loginMsg").val();		// 登录的表单中隐藏域的消息
		if ( m != "" ){
			qipao(m, $("#code"));		// 弹气泡
		}
		
		$("#pic").click(function(){
			$("#file").trigger("click");	// 触发文件选择按钮的单击事件
		});
		
		
		$("#file").change( function() { 
			$("#pic").val( $(this).val() )	;
		});
		
		$("#doupload").click(function(){
			var formdata = new FormData(form1);
			// 发异步请求，来完成上传
			$.ajax({
				
				url : "admin/user",				// 请求的URI
				data: formdata,					// 提交的参数数据
				type: "POST",					// 请求方式 			
				async: false,					// 同步请求
   	          	cache: false,					// 不缓存
   	          	contentType: false,				// 是否处理内容类型
   	          	processData: false,				// 是否对数据进行处理
   	          	success : function(result){	 	// 成功之后做什么
   	          		var json = eval(result);	// 把返回的JSON对象接收成js中的JSON对象
   	          		$("#pic").val(json.entity.message);
   	          		qipao(json.entity.data);
   	          	}
				
			});
			
		});
	}
	
</script>

</html>