<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="1024kb"%>
<%
	String webPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Page.</title>
	<link rel="stylesheet" type="text/css" href="<%=webPath%>/View/common/Flexigrid-master/css/flexigrid.pack.css" />
	<script type="text/javascript" src="<%=webPath%>/View/common/Flexigrid-master/js/jquery-1.6.3.js"></script>
	<script type="text/javascript" src="<%=webPath%>/View/common/Flexigrid-master/js/flexigrid.pack.js"></script>
	<script type="text/javascript">
        var webpath = "<%=webPath%>";
        var $jQuery = $;
    </script>
	<script type="text/javascript" src="<%=webPath%>/View/search.js"></script>
</head>

<body>
	<div style="align:center;margin:0 auto;width:800px">
		<div align="center" style="margin-top:20px">
		<form id="queryForm" name="queryForm">
			<table>
				<tr>
					<th align="center" colspan="2">Search conditions.</th>
				</tr>
				<tr>
					<td align="right">id:</td>
					<td align="left"><input type="text" id="idPara" name="idPara" /></td>
				</tr>
				<tr>
					<td align="right">name:</td>
					<td align="left"><input type="text" id="namePara" name="namePara" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<span><input type="button" id="queryBtn" value="查询"></span>
						<span><input type="reset" id="resetBtn" value="重置"></span></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><font size="2">Please input your conditions to have a search, <br/>and the result will showed below.</font></td>
				</tr>
			</table>
		</form>
		</div>
		<!-- 考虑到FLEXIGRID对IE浏览器的兼容，这里需要为Table加上div标签，否则在IE中会产生JS错误。 -->
		<div id="divTable">
			<table id="gcibTable" style="display: none;"></table>
		</div>
	</div>
</body>
</html>