<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的工时管理</title>
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
		<li class="active"><a href="${ctx}/prho/prhoProjectHours/">我的工时列表</a></li>
		<shiro:hasPermission name="prho:prhoProjectHours:edit"><li><a href="${ctx}/prho/prhoProjectHours/form">我的工时添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectHours" action="${ctx}/prho/prhoProjectHours/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>项目名称：</label>
		<form:select path="projectId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getPersonalProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
				</li>
			<li><label style="width:100px">任务开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectHours.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:100px">任务结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectHours.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>审批状态：</label>
				<form:select path="approvalstatus" class="input-small ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('approval_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>工作日期</th>
				<th>项目名称</th>
				<th>任务名称</th>
				<th>工作类型</th>
				<th>工时类型</th>
				<th>任务开始时间</th>
				<th>任务结束时间</th>
				<th>实际用时</th>
				<th>任务完成进度</th>
				<th>项目负责人</th>
				<th>工作描述</th>
				<th>审核状态</th>
				<th>审核意见</th>
				<th>审核人员</th>
				<shiro:hasPermission name="prho:prhoProjectHours:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectHours">
			<tr>
				<td><a href="${ctx}/prho/prhoProjectHours/form?id=${prhoProjectHours.id}">
					<fmt:formatDate value="${prhoProjectHours.worktime}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					${prhoProjectHours.projectName}
				</td>
				<td>
					${prhoProjectHours.taskname}
				</td>
				<td>
					${fns:getDictLabel(prhoProjectHours.jobtype, 'work_type', '')}
				</td>
				<td>
					${fns:getDictLabel(prhoProjectHours.workhourstype, 'workHours_type', '')}
				</td>
				<td>
				<fmt:formatDate value="${prhoProjectHours.taskstarttime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<fmt:formatDate value="${prhoProjectHours.taskendtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${prhoProjectHours.realhours}
				</td>
				<td>
					${fns:getDictLabel(prhoProjectHours.taskcompleteschedule, 'taskComplete_schedule', '')}
				</td>
				<td>
					${prhoProjectHours.projectmanager}
				</td>
				<td>
					${prhoProjectHours.workdesc}
				</td>
				<td>
					${fns:getDictLabel(prhoProjectHours.approvalstatus, 'approval_status', '')}
				</td>
				<td>
					${prhoProjectHours.approvalopinion}
				</td>
				<td>
					${prhoProjectHours.approvalopinion}
				</td>
				<shiro:hasPermission name="prho:prhoProjectHours:edit"><td>
    				<a href="${ctx}/prho/prhoProjectHours/form?id=${prhoProjectHours.id}">修改</a>
					<a href="${ctx}/prho/prhoProjectHours/delete?id=${prhoProjectHours.id}" onclick="return confirmx('确认要删除该项目工时吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>