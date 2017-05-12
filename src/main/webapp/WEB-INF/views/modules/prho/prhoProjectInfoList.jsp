<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
		<li class="active"><a href="${ctx}/prho/prhoProjectInfo/">项目信息列表</a></li>
		<shiro:hasPermission name="prho:prhoProjectInfo:edit"><li><a href="${ctx}/prho/prhoProjectInfo/form">项目信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectInfo" action="${ctx}/prho/prhoProjectInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="projectname" htmlEscape="false" maxlength="150" class="input-medium"/>
			</li>
			<li><label style="width:100px">项目启动日期：</label>
				<input name="projectbegintime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectInfo.projectbegintime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:100px">实际结束日期：</label>
				<input name="projectendtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectInfo.projectendtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>项目状态：</label>
				<form:select path="projectstatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>项目编号</th>
				<th>项目负责人</th>
				<th>项目名称</th>
				<th>项目启动日期</th>
				<th>预计结束日期</th>
				<th>实际结束日期</th>
				<th>项目状态 </th>
				<shiro:hasPermission name="prho:prhoProjectInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectInfo">
			<tr>
				<td><a href="${ctx}/prho/prhoProjectInfo/form?id=${prhoProjectInfo.id}">
					${prhoProjectInfo.projectcode}
				</a></td>
				<td>
					${prhoProjectInfo.user.name}
				</td>
				<td>
					${prhoProjectInfo.projectname}
				</td>
				<td>
					<fmt:formatDate value="${prhoProjectInfo.projectbegintime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${prhoProjectInfo.projectplanendtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${prhoProjectInfo.projectendtime}" pattern="yyyy-MM-dd"/><!-- pattern="yyyy-MM-dd HH:mm:ss" -->
				</td>
				<td>
					${fns:getDictLabel(prhoProjectInfo.projectstatus, '', '')}
				</td>
				<shiro:hasPermission name="prho:prhoProjectInfo:edit"><td>
    				<a href="${ctx}/prho/prhoProjectInfo/form?id=${prhoProjectInfo.id}">修改</a>
					<a href="${ctx}/prho/prhoProjectInfo/delete?id=${prhoProjectInfo.id}" onclick="return confirmx('确认要删除该项目信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>