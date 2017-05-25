<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
				//	document.getElementById('btnSubmit').disabled=true;
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
	<style type="text/css">
	   /* .input-select{width:282px;} */
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/prho/prhoProjectInfo/">项目信息列表</a></li>
		<li class="active"><a href="${ctx}/prho/prhoProjectInfo/form?id=${prhoProjectInfo.id}">项目信息<shiro:hasPermission name="prho:prhoProjectInfo:edit">${not empty prhoProjectInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prho:prhoProjectInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoProjectInfo" action="${ctx}/prho/prhoProjectInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="projectcode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<%-- sys:treeselect id="user" name="user.id" value="${prhoProjectInfo.user.id}" labelName="user.name" labelValue="${prhoProjectInfo.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/> --%>
				<form:select path="userId" class="input-select_xlarge">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectname" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预估工时(小时)：</label>
			<div class="controls">
				<form:input path="estimatehours" htmlEscape="false"  class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目启动日期：</label>
			<div class="controls">
				<input name="projectbegintime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${prhoProjectInfo.projectbegintime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预计结束日期：</label>
			<div class="controls">
				<input name="projectplanendtime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${prhoProjectInfo.projectplanendtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际结束日期：</label>
			<div class="controls">
				<input name="projectendtime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${prhoProjectInfo.projectendtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目状态 ：</label>
			<div class="controls">
				<form:select path="projectstatus" class="input-select_xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="prho:prhoProjectInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>