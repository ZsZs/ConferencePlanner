<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<jsp:useBean id='eventHelper' scope='session' class='com.agilerenovation.conference.event.web.ConferenceEventHelper' type="com.agilerenovation.conference.event.web.ConferenceEventHelper" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submitted Papers</title>
</head>
<body>
   <h1>Conference Configuration</h1>
   
   <h3>Conference: <c:out value="${eventHelper.conferenceName}"/></h3>
   <h4><c:out value="${eventHelper.conference.earliestStart}"/> - <c:out value="${eventHelper.conference.earliestEnd}"/></h4>
   
   <h3>Days</h3>
   <table>
   <tr>
      <td>name</td>
      <td>earliest start</td>
      <td>earliest end</td>
    </tr>
   <c:forEach items="${eventHelper.days}" var="event">
      <tr>
         <td><c:out value="${event.name}"/></td>
         <td><c:out value="${event.earliestStart}"/></td>
         <td><c:out value="${event.earliestEnd}"/></td>
       </tr>
   </c:forEach>
   </table>
   
   <h3>Tracks</h3>
   <table>
   <tr>
      <td>name</td>
      <td>earliest start</td>
      <td>earliest end</td>
    </tr>
   <c:forEach items="${eventHelper.tracks}" var="event">
      <tr>
         <td><c:out value="${event.name}"/></td>
         <td><c:out value="${event.earliestStart}"/></td>
         <td><c:out value="${event.earliestEnd}"/></td>
       </tr>
   </c:forEach>
   </table>
   
</body>
</html>