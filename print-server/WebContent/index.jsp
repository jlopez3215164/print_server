<%@page import="com.pos.print.server.entity.DeviceEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.pos.print.server.servelt.PoolConfigLoadServlet" %>
    <%@ page import="com.pos.print.server.entity.DeviceEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>XETUX / XS-PRINT-SERVER / INFO</title>
	</head>
	<body>
		<h2 align="center">XS-PRINT-SERVER INFO / CONFIG</h2>
		<table  border="1" align="center">
			<tr>
				<td>VERSION_PRINT-SERVER: </td>
				<td><%=PoolConfigLoadServlet.XS_PRINT_SERVER_VERSION%></td>
			</tr>
			<tr>
				<td>REAL_PATH_POOL: </td>
				<td><A HREF="api/Pool/Path"><%=PoolConfigLoadServlet.REAL_PATH_POOL%></A></td>
			</tr>
			<tr>
				<td>SOCKET_TIMEOUT: </td>
				<td><%=PoolConfigLoadServlet.SOCKET_TIMEOUT%></td>
			</tr>
			<tr>
				<td>THEARD_INTERVAL: </td>
				<td><%=PoolConfigLoadServlet.THEARD_INTERVAL%></td>
			</tr>
			<tr>
				<td>THEARD_INTERVAL_LIST: </td>
				<td><%=PoolConfigLoadServlet.THEARD_INTERVAL_LIST%></td>
			</tr>
			<tr>
				<td>BACKUP: </td>
				<td><%=PoolConfigLoadServlet.BACKUP%></td>
			</tr>
		</table>
		</br></br>
		<table  border="1" align="center">
			<thead>
				<th>xDevice</th>
				<th>xLabel</th>
				<th>xNameSystem</th>
				<th>xIP</th>	
				<th>xPort</th>	
			</thead>
			<tr>
				<td colspan="5">
				<A HREF="http://localhost:8090/xspos/api/PrintServer/Config">http://localhost:8090/xspos/api/PrintServer/Config</A>
				 </td>
				
			</tr>
			<%
			for (DeviceEntity dev: PoolConfigLoadServlet.conf.getPrinters()){%>
			<tr>
				<td><%=dev.getxDevice()%> </td>
				<td><%=dev.getxLabel()%></td>
				<td><%=dev.getxNameSystem()%></td>
				<td><%=dev.getxIp()%></td>
				<td><%=dev.getxPort()%></td>
			</tr>
			<%}%>
		</table>
	</body>
</html>