<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/prho/prhoProjectHoursSatistic/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
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
		<li class="active"><a href="${ctx}/prho/prhoProjectHoursSatistic/">项目工时统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectHoursStatics" action="${ctx}/prho/prhoProjectHoursSatistic/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		 <ul class="ul-form">
			<li><label>项目名称：</label>
			<form:select path="projectid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>编号</th> -->
				<th>人员名称</th>
				<th>项目名称</th>
				<th>投入工时</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectHoursSatistic">
			<tr>
				<%-- <td>
					${prhoProjectHoursSatistic.id}
				</td> --%>
				<td>
					${prhoProjectHoursSatistic.staff}
				</td>
				<td>
					${prhoProjectHoursSatistic.projectname}
				</td>
				<td>
					${prhoProjectHoursSatistic.realhours}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>