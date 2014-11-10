<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Set"%>
<jsp:useBean id='paperHelper' scope='session' class='com.agilerenovation.conference.paper.web.PaperHelper' type="com.agilerenovation.conference.paper.web.PaperHelper" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submitted Papers</title>
</head>
<body>
   <h1>Submitted Papers</h1>
   <table>
   <tr>
      <td>Title</td>
      <td>Length</td>  
    </tr>
   <c:forEach items="${paperHelper.papers}" var="paper">
   <tr>
      <td><c:out value="${paper.title}"/></td>
      <td><c:out value="${paper.length}"/></td>  
    </tr>
   </c:forEach>
   </table>
</body>
</html>