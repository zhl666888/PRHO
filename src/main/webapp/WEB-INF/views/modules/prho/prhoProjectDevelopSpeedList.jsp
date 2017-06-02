<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目开发速度分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* var val_payPlatform = $('input[name="time"]:checked').val();
			alert(val_payPlatform); */
			$(":radio").click(function(){
				if($(this).val()=="day"){
					$("#time_day").show();
					$("#time_week").hide();
					$("#time_month").hide();
				}
				if($(this).val()=="week"){
					$("#time_week").show();
					$("#time_day").hide();
					$("#time_month").hide();
				}
				if($(this).val()=="month"){
					$("#time_month").show();
					$("#time_day").hide();
					$("#time_week").hide();
				}
			 });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	/* 	function funccc(){
			$dp.$('d121').value=$dp.cal.getP('y')+$dp.cal.getP('W','WW');
			} */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/prho/prhoProjectDevelopSpeed/">项目开发速度分析列表</a></li>
		<%-- <shiro:hasPermission name="prho:prhoProjectInfo:edit"><li><a href="${ctx}/prho/prhoProjectInfo/form">项目信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectDevelopSpeed" action="${ctx}/prho/prhoProjectDevelopSpeed/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width:10px">日</label>
			<input type="radio" checked="checked" name="time" value="day" >
			<%-- <form:radiobutton path="projectname"  /> --%>
			</li>
			<li><label style="width:10px">周</label>
			<input type="radio" name="time" value="week" >
			</li>
			<li><label style="width:10px">月</label>
			<input type="radio" name="time" value="month" >
			</li>
			<li><label>项目名称：</label>
			<form:select path="projectId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="projectname" htmlEscape="false" maxlength="150" class="input-medium"/> --%>
			</li>
			<div id="time_day">
			<li><label style="width:100px">任务完成时间：</label>
			<input name="taskcompletetime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDevelopSpeed.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			</div>
			<div id="time_week" style="display: none">
			<li><label style="width:120px">任务完成时间(周)：</label>
			<input name="taskcompletetime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDevelopSpeed.starttime}" />"
					onclick="WdatePicker({isShowWeek:true,onpicked:function() {$dp.$('d122_1').value=$dp.cal.getP('W','W');$dp.$('d122_2').value=$dp.cal.getP('W','WW');}})"/>
					
			</li>
			<li><label style="width:120px">任务完成时间(周)：</label>
			<input name="taskcompletetime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDevelopSpeed.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			</div>
			<div id="time_month" style="display: none">
			<li><label style="width:120px">任务完成时间(月)：</label>
			<input name="taskcompletetime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDevelopSpeed.starttime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</li>
			<li><label style="width:120px">任务完成时间(月)：</label>
			<input name="taskcompletetime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDevelopSpeed.starttime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</li>
			</div>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li><input id="btnImport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectDevelopSpeed">
			<tr>
				<td>
					${prhoProjectDevelopSpeed.projectName}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>