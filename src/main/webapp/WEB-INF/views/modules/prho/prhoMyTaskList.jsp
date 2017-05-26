<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/prho/prhoMyTask/">我的任务列表</a></li>
		<%-- <shiro:hasPermission name="prho:prhoMyTask:edit"><li><a href="${ctx}/prho/prhoMyTask/form">我的任务添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoMyTask" action="${ctx}/prho/prhoMyTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
			<form:select path="projectId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
					<li><label style="width:100px">计划开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoMyTask.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:100px">计划结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoMyTask.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>任务状态：</label>
				<form:select path="taskstatus" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务负责人</th>
				<th>任务名称</th>
				<th>计划开始时间</th>
				<th>计划结束时间</th>
				<th>预计用时</th>
				<th>所属项目</th>
				<th>任务完成进度</th>
				<th>任务完成时间</th>
				<th>任务类型</th>
				<th>工作类型</th>
				<shiro:hasPermission name="prho:prhoMyTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoMyTask">
			<tr>
				<td>
					${prhoMyTask.userName}
				</td>
				<td>
					${prhoMyTask.taskname}
				</td>
				<td>
					<fmt:formatDate value="${prhoMyTask.tastplanbegintime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${prhoMyTask.taskplanendtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${prhoMyTask.expectedhour}
				</td>
				<td>
					${prhoMyTask.projectName}
				</td>
				<td>
					${fns:getDictLabel(prhoMyTask.taskcompleteschedule, 'taskComplete_schedule', '')}
				</td>
				<td>
					<fmt:formatDate value="${prhoMyTask.taskcompletetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(prhoMyTask.tasktype, 'task_type', '')}
				</td>
				<td>
					${fns:getDictLabel(prhoMyTask.worktype, 'work_type', '')}
				</td>
				<shiro:hasPermission name="prho:prhoMyTask:edit"><td>
				<a href="${ctx}/prho/prhoProjectHours/saveMyHours?pmtid=${prhoMyTask.id}">工时填报</a>
    				<%-- <a href="${ctx}/prho/prhoMyTask/form?id=${prhoProjectTask.id}">修改</a>
					<a href="${ctx}/prho/prhoMyTask/delete?id=${prhoProjectTask.id}" onclick="return confirmx('确认要删除该项目任务吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>