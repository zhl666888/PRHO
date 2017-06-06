<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人开发速度分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* var val_payPlatform = $('input[name="time"]:checked').val();
			alert(val_payPlatform); */
 			var radioval=$("#rid").val();
			if(radioval){
 		        $("input[type='radio'][name=time][value='"+radioval+"']").attr("checked",true);
 		       radioClick(radioval);
 			} 
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/prho/prhoPersonalDevelopSpeed/export");
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
	/* 	function funccc(){
			$dp.$('d121').value=$dp.cal.getP('y')+$dp.cal.getP('W','WW');
			} */
			function radioClick(time){
				if(time=="day"){
					$("#time_day").show();
					$("#time_week").hide();
					$("#time_month").hide();
				}
				if(time=="week"){
					$("#time_week").show();
					$("#time_day").hide();
					$("#time_month").hide();
				}
				if(time=="month"){
					$("#time_month").show();
					$("#time_day").hide();
					$("#time_week").hide();
				}
				var radioval = $('input[name="time"]:checked').val();
				$("#radioval").val(radioval);
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/prho/prhoPersonalDevelopSpeed/">个人开发速度分析列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoPersonalDevelopSpeed" action="${ctx}/prho/prhoPersonalDevelopSpeed/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		  <input id="radioval" name="radioval" value="day" type="hidden"/>
		  <input type="hidden" id="rid" value="${time}">
			<li><label style="width:10px">日</label>
			<input type="radio" checked="checked" name="time" value="day" onchange="radioClick('day')" >
			<%-- <form:radiobutton path="projectname"  /> --%>
			</li>
			<li><label style="width:10px" >周</label>
			<input type="radio" name="time" value="week"  onchange="radioClick('week')" >
			</li>
			<li><label style="width:10px">月</label>
			<input type="radio" name="time" value="month" onchange="radioClick('month')" >
			</li>
			<li><label>项目名称：</label>
			<form:select path="projectId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllProjectName()}" itemLabel="projectname" itemValue="id" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="projectname" htmlEscape="false" maxlength="150" class="input-medium"/> --%>
			</li>
			<li><label>人员名称：</label>
			<form:select path="staffid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<div id="time_day">
			<li><label style="width:100px">任务完成时间：</label>
			<input name="daytime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoPersonalDevelopSpeed.daytime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			</div>
			<div id="time_week" style="display: none">
			<li><label style="width:140px">任务完成起始时间(周)：</label>
			<input name="weekstarttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoPersonalDevelopSpeed.weekstarttime}" />"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true})"/>
					
			</li>
			<li><label style="width:140px">任务完成结束时间(周)：</label>
			<input name="weekendtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoPersonalDevelopSpeed.weekendtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true});"/>
			</li>
			</div>
			<div id="time_month" style="display: none">
			<li><label style="width:140px">任务完成起始时间(月)：</label>
			<input name="monthstarttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoPersonalDevelopSpeed.monthstarttime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM'});"/>
			</li>
			<li><label style="width:140px">任务完成结束时间(月)：</label>
			<input name="monthendtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoPersonalDevelopSpeed.monthendtime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM'});"/>
			</li>
			</div>
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
				<th>项目名称</th>
				<th>人员</th>
				<th>预估工时</th>
				<th>任务完成时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoPersonalDevelopSpeed">
			<tr>
				<td>
					${prhoPersonalDevelopSpeed.projectName}
				</td>
				<td>
					${prhoPersonalDevelopSpeed.staffid}
				</td>
				<td>
					${prhoPersonalDevelopSpeed.expectedhour}
				</td>
				<td>
				
				<c:choose>
				<c:when test="${prhoPersonalDevelopSpeed.radioval=='day'}">
				<fmt:formatDate value="${prhoPersonalDevelopSpeed.taskcompletetime}" pattern="yyyy-MM-dd"/>
				</c:when>
				<c:when test="${prhoPersonalDevelopSpeed.radioval=='month'}">
					${prhoPersonalDevelopSpeed.monthfw}
				</c:when>
				<c:when test="${prhoPersonalDevelopSpeed.radioval=='week'}">
					${prhoPersonalDevelopSpeed.weekfw}
				</c:when>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>