<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试log管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/prho/prhoTestLog/">测试log列表</a></li>
		<li class="active"><a href="${ctx}/prho/prhoTestLog/form?id=${prhoTestLog.id}">测试log<shiro:hasPermission name="prho:prhoTestLog:edit">${not empty prhoTestLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prho:prhoTestLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoTestLog" action="${ctx}/prho/prhoTestLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">logcode：</label>
			<div class="controls">
				<form:input path="logcode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">experimentid：</label>
			<div class="controls">
				<form:input path="experimentid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">problemfeedback：</label>
			<div class="controls">
				<form:input path="problemfeedback" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">problemstatus：</label>
			<div class="controls">
				<form:input path="problemstatus" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">infourl：</label>
			<div class="controls">
				<form:hidden id="infourl" path="infourl" htmlEscape="false" maxlength="300" class="input-xlarge"/>
				<sys:ckfinder input="infourl" type="files" uploadPath="/prho/prhoTestLog" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">liabler：</label>
			<div class="controls">
				<form:input path="liabler" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">updater_by：</label>
			<div class="controls">
				<form:input path="updaterBy" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="prho:prhoTestLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>