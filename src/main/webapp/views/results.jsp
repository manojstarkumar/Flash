<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common.jsp"></jsp:include>
<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Performance Runs</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a href="/Flash">Home</a>
					</li>
					<li class="active"><i class="fa fa-edit"></i> Runs</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">Jenkins Build number : ${expectedBuild}
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>Build Number</th>
								<th>Comment</th>
								<th>Users</th>
								<th>Duration</th>
								<th>Script</th>
								<th>Status</th>
								<th>Parameters</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="runObj" items="${runDetail }">
								<tr>
									<c:set var="runResult" value="${runObj.getStatus()}"></c:set>
									<td><c:out value="${runObj.getBuildNumber()}"></c:out></td>
									<td><c:out value="${runObj.getComment()}"></c:out></td>
									<td><c:out value="${runObj.getUsers()}"></c:out></td>
									<td><c:out value="${runObj.getDuration()}"></c:out></td>
									<td><c:out value="${runObj.getTest()}"></c:out></td>
									<td><span id="testStatus"><c:out value="${runObj.getStatus()}"></c:out></span></td>
									<td><c:out value="${runObj.getParameters()}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-12">
				<div id="loader">
					The test will continue to Run even after closing the browser.<br>
					You can check the status later from <a
						href='testStatus.mdo?testId=<c:out value="${expectedBuild}"></c:out>'>/apps/Flash/testStatus.mdo?testId=<c:out value="${expectedBuild}"></c:out></a>
					<br />Running Test...<img src="img/loader.gif" />
				</div>
				<div id="status" class="hidden">
				</div>

				<div class="row">
					<div class="col-lg-12">
					<c:choose>
					<c:when test="${runResult=='SUCCESS' }">
						<div id="reportLink"></div>
						</c:when>
						<c:otherwise>
						<div id="reportLink" class="hidden"></div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- row -->
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-12">
				<pre>
<div id="jenkinsOutput" class="scroller">Please wait while we retrieve the execution console
</div>
</pre>
			</div>
			<!-- /.row -->
		</div>



		<!-- Container Fluid -->
	</div>

	<!-- Page Wrapper -->
</div>

<!-- Wrapper -->
</div>
</body>
<script type='text/javascript' src='js/jenkinsJobs.js'></script>
<script type="text/javascript">
$(document).ready(function() {
	get_jenkins_status(${expectedBuild});
});
</script>
</html>