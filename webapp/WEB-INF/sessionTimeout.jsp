<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if(session.getAttribute("login_user_session")==null||session.getAttribute("login_user_session")==""){
		out.println("<script langue='javascript'>");
		out.println("alert(\"登录超时，请重新登录！\");");
		out.println("top.location.href='"+basePath+"/backend/index.jsp'");
		out.println("</script>"); 
	}
 %>

