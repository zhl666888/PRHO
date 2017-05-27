<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工时审批管理</title>
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
		<li class="active"><a href="${ctx}/prho/prhoWorktimeApproval/">工时审批列表</a></li>
		<%-- <shiro:hasPermission name="prho:prhoProjectHours:edit"><li><a href="${ctx}/prho/prhoProjectHours/form">我的工时添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoWorktimeApproval" action="${ctx}/prho/prhoWorktimeApproval/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
	
			<li><label style="width:100px">工作开始日期：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoWorktimeApproval.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:100px">工作结束日期：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoWorktimeApproval.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>审批状态：</label>
				<form:select path="approvalstatus" class="input-small ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('approval_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
				<li><label>人员：</label>
			<form:select path="staff" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
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
				<th>人员</th>
				<th>工作日期</th>
				<th>项目名称</th>
				<th>任务名称</th>
				<th>工作类型</th>
				<th>工时类型</th>
				<th>任务开始时间</th>
				<th>任务结束时间</th>
				<th>实际用时</th>
				<th>任务完成进度</th>
				<th>工作描述</th>
				<th>审核状态</th>
				<th>审核意见</th>
				<th>审核时间</th>
				<shiro:hasPermission name="prho:prhoWorktimeApproval:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoWorktimeApproval">
			<tr>
				<td>
					${prhoWorktimeApproval.staffname}
				</td>
				<td>
					<fmt:formatDate value="${prhoWorktimeApproval.worktime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${prhoWorktimeApproval.projectName}
				</td>
				<td>
					${prhoWorktimeApproval.taskname}
				</td>
				<td>
					${fns:getDictLabel(prhoWorktimeApproval.jobtype, 'work_type', '')}
				</td>
				<td>
					${fns:getDictLabel(prhoWorktimeApproval.workhourstype, 'workHours_type', '')}
				</td>
				<td>
				<fmt:formatDate value="${prhoWorktimeApproval.taskstarttime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<fmt:formatDate value="${prhoWorktimeApproval.taskendtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${prhoWorktimeApproval.realhours}
				</td>
				<td>
					${fns:getDictLabel(prhoWorktimeApproval.taskcompleteschedule, 'taskComplete_schedule', '')}
				</td>
				<td>
					${prhoWorktimeApproval.workdesc}
				</td>
				<td>
					${fns:getDictLabel(prhoWorktimeApproval.approvalstatus, 'approval_status', '')}
				</td>
				<td>
					${prhoWorktimeApproval.approvalopinion}
				</td>
				<td>
					${prhoWorktimeApproval.approvaltime}
				</td>
				<shiro:hasPermission name="prho:prhoWorktimeApproval:edit"><td>
    				<a href="${ctx}/prho/prhoWorktimeApproval/form?id=${prhoWorktimeApproval.id}">审批</a>
					<%-- <a href="${ctx}/prho/prhoWorktimeApproval/delete?id=${prhoWorktimeApproval.id}" onclick="return confirmx('确认要删除该项目工时吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>