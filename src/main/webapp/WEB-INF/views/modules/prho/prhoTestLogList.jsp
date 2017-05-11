<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试log管理</title>
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
		<li class="active"><a href="${ctx}/prho/prhoTestLog/">测试log列表</a></li>
		<shiro:hasPermission name="prho:prhoTestLog:edit"><li><a href="${ctx}/prho/prhoTestLog/form">测试log添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoTestLog" action="${ctx}/prho/prhoTestLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>logcode：</label>
				<form:input path="logcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>liabler：</label>
				<form:input path="liabler" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>update_date：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${prhoTestLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="prho:prhoTestLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoTestLog">
			<tr>
				<td><a href="${ctx}/prho/prhoTestLog/form?id=${prhoTestLog.id}">
					<fmt:formatDate value="${prhoTestLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${prhoTestLog.remarks}
				</td>
				<shiro:hasPermission name="prho:prhoTestLog:edit"><td>
    				<a href="${ctx}/prho/prhoTestLog/form?id=${prhoTestLog.id}">修改</a>
					<a href="${ctx}/prho/prhoTestLog/delete?id=${prhoTestLog.id}" onclick="return confirmx('确认要删除该测试log吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>