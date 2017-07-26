<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目任务管理</title>
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
		<li><a href="${ctx}/prho/prhoProjectTask/">项目任务列表</a></li>
		<li class="active"><a href="${ctx}/prho/prhoProjectTask/form?id=${prhoProjectTask.id}">项目任务<shiro:hasPermission name="prho:prhoProjectTask:edit">${not empty prhoProjectTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prho:prhoProjectTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoProjectTask" action="${ctx}/prho/prhoProjectTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">任务负责人：</label>
			<div class="controls">
				<form:select path="taskmanager" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
			<%-- <div class="controls">
				<sys:treeselect id="user" name="user.id" value="${prhoProjectTask.user.id}" labelName="user.name" labelValue="${prhoProjectTask.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div> --%>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="taskname" htmlEscape="false" maxlength="200" class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label">项目名称：</label>  <!-- 保存项目id -->
		<div class="controls">
		<form:select path="projectId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
		</form:select>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划开始时间：</label>
			<div class="controls">
				<input name="tastplanbegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectTask.tastplanbegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划结束时间：</label>
			<div class="controls">
				<input name="taskplanendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectTask.taskplanendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预计用时(小时)：</label>
			<div class="controls">
				<form:input path="expectedhour" htmlEscape="false" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成进度：</label>
			<div class="controls">
				<form:select path="taskcompleteschedule" class="input-select_medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('taskComplete_schedule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				%
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作类型 ：</label>
			<div class="controls">
				<form:select path="worktype" class="input-select_medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务类型 ：</label>
			<div class="controls">
				<form:select path="tasktype" class="input-select_medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('task_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="prho:prhoProjectTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>