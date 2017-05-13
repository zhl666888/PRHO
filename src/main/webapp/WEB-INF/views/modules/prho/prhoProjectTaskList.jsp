<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目任务管理</title>
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
		<li class="active"><a href="${ctx}/prho/prhoProjectTask/">项目任务列表</a></li>
		<shiro:hasPermission name="prho:prhoProjectTask:edit"><li><a href="${ctx}/prho/prhoProjectTask/form">项目任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectTask" action="${ctx}/prho/prhoProjectTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目负责人：</label>
			<form:select path="taskmanager" class="input-medium">
					<form:option value="" label=""/>
					<%-- <form:options items="${fnslms:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li><label>任务名称：</label>
				<form:input path="taskname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>任务状态：</label>
				<form:input path="taskstatus" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>任务完成进度</th>
				<shiro:hasPermission name="prho:prhoProjectTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectTask">
			<tr>
				<td><a href="${ctx}/prho/prhoProjectTask/form?id=${prhoProjectTask.id}">
					${prhoProjectTask.taskmanager}
				</a></td>
				<td>
					${prhoProjectTask.taskname}
				</td>
				<td>
					<fmt:formatDate value="${prhoProjectTask.tastplanbegintime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${prhoProjectTask.taskplanendtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${prhoProjectTask.expectedhour}
				</td>
				<td>
					${prhoProjectTask.taskcompleteschedule}
				</td>
				<shiro:hasPermission name="prho:prhoProjectTask:edit"><td>
    				<a href="${ctx}/prho/prhoProjectTask/form?id=${prhoProjectTask.id}">修改</a>
					<a href="${ctx}/prho/prhoProjectTask/delete?id=${prhoProjectTask.id}" onclick="return confirmx('确认要删除该项目任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>