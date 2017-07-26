<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>填报工时</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var pnReadonly=$("#pnReadonly").val();
			if(pnReadonly){
				$("#projectId").attr("readOnly","true");
			}
			var tnReadonly=$("#tnReadonly").val();
			if(pnReadonly){
				$("#taskId").attr("readOnly","true");
			}
			/* var ppiid=$("#ppiid").val();
			 if(ppiid){
				   $("#projectId").find("option[value='"+ppiid+"']").attr("selected", "selected");
				 var projectName=  $('#projectId option:selected').text();
				 $("#s2id_projectId").find(".select2-chosen").html(projectName);
			} */ 
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
		$(function(){
		
			$("#projectId").click(function(){
				var projectId = $("#projectId").val();
				$.ajax( {  
	                type : "post",  
	                url : '${ctx}/prho/prhoProjectHours/getProjectManager',
	                data:{projectId:projectId},
	                success: function(data) {
	                	if(data.userId){
	                		  $("#projectmanagerId").find("option[value='"+data.userId+"']").attr("selected", "selected");
	                		  var projectmanager=  $('#projectmanagerId option:selected').text();
	         				  $("#s2id_projectmanagerId").find(".select2-chosen").html(projectmanager);
	                	}
	                },
	            	error: function(request) {
	                	alert("出错");
	            	}
	            }); 
			});
			$("#taskId").click(function(){
				var taskId = $("#taskId").val();
				$.ajax( {  
	                type : "post",  
	                url : '${ctx}/prho/prhoProjectHours/getWorkType',
	                data:{taskId:taskId},
	                success: function(data) {
	                	if(data.worktype){
	                		  $("#jobtype").find("option[value='"+data.worktype+"']").attr("selected", "selected");
	                		  var jobtype=  $('#jobtype option:selected').text();
	         				  $("#s2id_jobtype").find(".select2-chosen").html(jobtype);
	                	}
	                },
	            	error: function(request) {
	                	alert("出错");
	            	}
	            }); 
			});
		}); 
	</script>
	<style type="text/css">
	.zhl{width:55%;}
	.zhl_left{float:left}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/prho/prhoMyTask/">我的任务</a></li>
		<li class="active"><a href="#">填报工时</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="prhoProjectHours" action="${ctx}/prho/prhoProjectHours/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	<%-- 	<div class="control-group">
			<label class="control-label">工作日期：</label>
			<div class="controls">
				<input name="worktime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.worktime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="control-group">
		<div class="zhl_left">
		<label class="control-label">项目名称：</label>
		<input id="pnReadonly" type="hidden" value="${pnReadonly}">
		<div class="controls zhl">
		<c:choose>
			<c:when test="${''!=prhoProjectHours.projectId&& null!=prhoProjectHours.projectId}">
			<form:select id="projectId"  path="projectId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getPersonalProjectName()}"   itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
				</c:when>
				<c:otherwise>
				<form:select id="projectId" path="projectId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getPersonalProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
				</c:otherwise>
		</c:choose>
			</div>
		</div>
		<div class="zhl_left" style="padding-left: 12px">
			<label class="control-label">任务名称：</label>
			<input id="tnReadonly" type="hidden" value="${tnReadonly}">
			<div class="controls zhl">
			<c:choose>
			<c:when test="${''!=prhoProjectHours.taskId&& null!=prhoProjectHours.taskId}">
			<form:select id="taskId" readonly="readOnly" path="taskId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllTaskName()}"  itemLabel="taskname" itemValue="id" htmlEscape="false"/>
				</form:select>
				</c:when>
				<c:otherwise>
				<form:select id="taskId" path="taskId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllTaskName()}" itemLabel="taskname" itemValue="id" htmlEscape="false"/>
				</form:select>
				</c:otherwise>
				</c:choose>
		</div>
		</div>
		</div>
		<div class="control-group">
		<div class="zhl_left">
			<label class="control-label">工作类型 ：</label>
			<div class="controls zhl">
			<c:choose>
			<c:when test="${''!=prhoProjectHours.jobtypelabel&& null!=prhoProjectHours.jobtypelabel}">
			<input id="jobtype" name="jobtype" value="${prhoProjectHours.jobtype}" type="hidden">
			  <form:input path="jobtypelabel" value="${prhoProjectHours.jobtypelabel}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</c:when>
			<c:otherwise>
				<form:select id="jobtype" path="jobtype" class="input-select_medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('work_type')}"  itemLabel="label" itemValue="value"  htmlEscape="false"/>
				</form:select>
			</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div class="zhl_left" style="padding-left: 12px">
		<label class="control-label">工时类型 ：</label>
			<div class="controls zhl">
				<form:select path="workhourstype" class="input-select_medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('workHours_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</div>
		<div class="control-group">
		<div class="zhl_left">
			<label class="control-label">任务开始时间：</label>
			<div class="controls zhl">
				<input name="taskstarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.taskstarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="zhl_left" style="padding-left: 12px">
		<label class="control-label">任务结束时间：</label>
			<div class="controls zhl">
				<input name="taskendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${prhoProjectHours.taskendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		</div>
		<div class="control-group">
		<div class="zhl_left">
			<label class="control-label">实际用时(小时)：</label>
			<div class="controls zhl">
				<form:input path="realhours" htmlEscape="false" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="zhl_left">
		<label class="control-label">任务完成进度：</label>
			<div class="controls zhl" >
				<form:select path="taskcompleteschedule" class="input-select_medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('taskComplete_schedule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">%</span>
			</div>
		</div>
		</div>
		<div class="control-group">
		<div class="zhl_left">
			<label class="control-label">审批人：</label>
			<div class="controls zhl">
				<form:select path="projectmanagerId" class="input-select_medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}"  itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				</div>
			</div>
		<div class="zhl_left" style="padding-left: 12px">
				<label class="control-label">人员：</label>
			<div class="controls zhl">
			<input id="staffId" name="staffId" value="${fns:getUser().id}" type="hidden">
				<form:input path="staff" value="${fns:getUser().name}" readonly="true" htmlEscape="false"  class="input-medium "/>
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作描述：</label>
			<div class="controls">
				<form:textarea path="workdesc" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
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