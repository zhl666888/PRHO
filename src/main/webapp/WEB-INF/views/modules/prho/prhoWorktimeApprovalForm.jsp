<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工时审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li class="active"><a href="${ctx}/prho/prhoWorktimeApproval/form?id=${prhoWorktimeApproval.id}">工时审批</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoWorktimeApproval" action="${ctx}/prho/prhoWorktimeApproval/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">人员：</label>
			<div class="controls">
			<input id="staff" name="staff" value="${prhoWorktimeApproval.staff}" type="hidden">
				<form:input path="staffname" value="${prhoWorktimeApproval.staffname}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作日期：</label>
			<div class="controls">
			<input name="worktime" type="text" value="<fmt:formatDate value="${prhoWorktimeApproval.worktime}" pattern="yyyy-MM-dd"/>" readonly="true" htmlEscape="false"  class="input-medium Wdate"/>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label">项目名称：</label>
			<div class="controls">
			<form:input path="projectId" value="${prhoWorktimeApproval.projectName}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
			<form:input path="taskname" value="${prhoWorktimeApproval.taskname}" readonly="true" htmlEscape="false"  class="input-medium "/>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作类型 ：</label>
			<div class="controls">
			<form:input path="jobtype" value="${fns:getDictLabel(prhoWorktimeApproval.jobtype, 'work_type', '')}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工时类型 ：</label>
			<div class="controls">
			<form:input path="workhourstype" value="${fns:getDictLabel(prhoWorktimeApproval.workhourstype, 'workHours_type', '')}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务开始时间：</label>
			<div class="controls">
			<input name="taskstarttime" type="text" value="<fmt:formatDate value="${prhoWorktimeApproval.taskstarttime}" pattern="yyyy-MM-dd"/>" readonly="true" htmlEscape="false"  class="input-medium Wdate"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务结束时间：</label>
			<div class="controls">
			<input name="taskendtime" type="text" value="<fmt:formatDate value="${prhoWorktimeApproval.taskendtime}" pattern="yyyy-MM-dd"/>" readonly="true" htmlEscape="false"  class="input-medium Wdate"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际用时(小时)：</label>
			<div class="controls">
				<form:input path="realhours" htmlEscape="false" readonly="true" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成进度：</label>
			<div class="controls">
			<form:input path="taskcompleteschedule" value="${fns:getDictLabel(prhoWorktimeApproval.taskcompleteschedule, 'taskComplete_schedule', '')}" readonly="true" htmlEscape="false"  class="input-medium "/>
				%
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">工作描述：</label>
			<div class="controls">
				<form:textarea path="workdesc" readonly="true" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">审批状态 ：</label>
			<div class="controls">
				<form:select path="approvalstatus" class="input-select_medium ">
					<%-- <form:option value="" label=""/> --%>
					<form:options items="${fns:getDictList('approval_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核意见：</label>
			<div class="controls">
				<form:textarea path="approvalopinion" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="prho:prhoWorktimeApproval:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
	$(document).ready(function() {
	
	});
	</script>
</body>
</html>