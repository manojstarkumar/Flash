<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.nort.symc.perfengg.utils.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert</title>
</head>
<body>
Custom errors here..
<%=request.getParameter("error") %>
<c:out value="${error }"></c:out>
<c:if test="${error=='queueError'}">
Another job is running in the queue.
<br/>Until we code a queue mechanism that will schedule your job and make your life easier, you have no other option
<br/>But to come back and try after some-time. ;)
<br/>You can check <a href="<%=Constants.jenkinsHome %>">here</a> to get an estimate of when the jobs will complete.
</c:if>
</body>
</html>