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
					$("#searchForm").attr("action","${ctx}/prho/prhoProjectDailySatistic/export");
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
		<li class="active"><a href="${ctx}/prho/prhoProjectDailySatistic/">日报统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="prhoProjectDailyStatics" action="${ctx}/prho/prhoProjectDailySatistic/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		 <ul class="ul-form">
			<li><label>人员名称：</label>
			 <form:select path="userid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnprho:getAllUser()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select> 
			</li>
			<li><label style="width:100px">任务开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDailyStatics.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:100px">任务结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${prhoProjectDailyStatics.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>姓名</th>
				<th>日期</th>
				<th>星期</th>
				<th>系统名称</th>
				<th>工作描述</th>
				<th>实际工作量(小时)</th>
				<th>加班小时数</th>
				<th>是否加班</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prhoProjectHoursSatistic">
			<tr>
				<%-- <td>
					${prhoProjectHoursSatistic.id}
				</td> --%>
				<td>
					${prhoProjectHoursSatistic.name}
				</td>
				
				<td>
					
						<fmt:formatDate value="${prhoProjectHoursSatistic.workTime}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					${prhoProjectHoursSatistic.weekName}
				</td>
				
				<td>
					${prhoProjectHoursSatistic.projectname}
				</td>
				
				<td>
					${prhoProjectHoursSatistic.workDesc}
				</td>
				
				<td>
					${prhoProjectHoursSatistic.workLoad}
				</td>
				
				<td>
					${prhoProjectHoursSatistic.overTime}
				</td>
				<td>
					${prhoProjectHoursSatistic.ifOverTime}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>