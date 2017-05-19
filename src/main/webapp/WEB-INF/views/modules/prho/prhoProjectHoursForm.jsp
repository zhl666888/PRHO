<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目工时管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var ppiid=$("#ppiid").val();
			 if(ppiid){
				   $("#projectId").find("option[value='"+ppiid+"']").attr("selected", "selected");
				 var projectName=  $('#projectId option:selected').text();
				 $("#s2id_projectId").find(".select2-chosen").html(projectName);
				// alert(projectName);
			} 
			
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
		<li><a href="${ctx}/prho/prhoProjectHours/">项目工时列表</a></li>
		<li class="active"><a href="${ctx}/prho/prhoProjectHours/form?id=${prhoProjectHours.id}">项目工时<shiro:hasPermission name="prho:prhoProjectHours:edit">${not empty prhoProjectHours.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prho:prhoProjectHours:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoProjectHours" action="${ctx}/prho/prhoProjectHours/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">工作日期：</label>
			<div class="controls">
				<input name="worktime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.worktime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
			<form:select id="projectId" path="projectId" class="input-medium">
					<%-- <form:option value="" label=""/> --%>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="taskId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
	
	

		<div class="control-group">
			<label class="control-label">工作类型 ：</label>
			<div class="controls">
				<form:select path="jobtype" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工时类型 ：</label>
			<div class="controls">
				<form:select path="workhourstype" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('workHours_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务开始时间：</label>
			<div class="controls">
				<input name="taskstarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.taskstarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务结束时间：</label>
			<div class="controls">
				<input name="taskendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.taskendtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际用时：</label>
			<div class="controls">
				<form:input path="realhours" htmlEscape="false" class="input-midum "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成进度：</label>
			<div class="controls">
				<form:input path="taskcompleteschedule" htmlEscape="false" maxlength="50" class="input-midum "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<form:select path="projectmanagerId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}"  itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作描述：</label>
			<div class="controls">
				<form:textarea path="workdesc" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">人员：</label>
			<div class="controls">
			<input id="staffId" name="staffId" value="${fns:getUser().id}" type="hidden">
				<form:input path="staff" value="${fns:getUser().name}" readonly="true" htmlEscape="false"  class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="prho:prhoProjectHours:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<input type="hidden" id="ppiid" value="${ppiid}">
	</form:form>
	<script type="text/javascript">
	$(document).ready(function() {
	
	});
	</script>
</body>
</html>