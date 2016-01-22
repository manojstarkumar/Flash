<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nort.symc.perfengg.utils.Constants"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<jsp:include page="../common.jsp"></jsp:include>
<div id="page-wrapper">
	<%
		URL u = new URL(Constants.machineRoot + Constants.reportsLink
				+ request.getParameter("testId") + "/archive/JThreads.png");
		HttpURLConnection huc = (HttpURLConnection) u.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		if (huc.getResponseCode() != 200) {
			response.sendRedirect("errorPage.jsp?error=Invalid testId");
		}
	%>
	
	<script type="text/javascript">
	$(document).ready(function() {
    $('#example').DataTable( {
        "ajax": '<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/csv.data',
		"bPaginate": false,
		"bInfo": false
    } );
} );
</script>
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Performance Runs</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a href="/PerfDash">Home</a>
					</li>
					<li class="active"><i class="fa fa-edit"></i> New Run</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
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
				<h4>Charts</h4>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-6">
				<h4>Active Threads Over Time</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JThreads.png" />
			</div>
			<div class="col-lg-6">
				<h4>BytesThroughputOverTime</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JBytes.png" />
			</div>
		</div>
		<!-- row -->
		<br />
		<br />
		<div class="row">
			<div class="col-lg-6">
			<h4>TransactionsPerSecond</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JTransactions.png" />
				
			</div>
			<div class="col-lg-6">
				<h4>ResponseTimesOverTime</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JResponse.png" />
			</div>
		</div>
		<!-- row -->
		<br />
		<br />
		<div class="row">
			<div class="col-lg-6">
				<h4>ThroughputVsThreads</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JThroughput.png" />
			</div>
			<div class="col-lg-6">
				<h4>TimesVsThreads</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JTimes.png" />
			</div>
		</div>
		<!-- row -->
		<br />
		<br />
		<!-- <div class="row">
			<div class="col-lg-6">
				<h4>HitsPerSecond</h4>
				<img
					src="<%=Constants.machineRoot%><%=Constants.reportsLink%><%=request.getParameter("testId")%>/archive/JHits.png" />
			</div>
		</div> -->
		<!-- row -->
		<div class="row">
			<div class="col-lg-12">
			&nbsp;
						</div>
		</div>
		<!-- row -->
				<div class="row">
			<div class="col-lg-12">
			<h4>Detailed Report</h4>
						</div>
		</div>
		<!-- row -->

		<div class="row">
			<div class="col-lg-12">
			<div class="table-responsive">
<table id="example" class="table table-hover table-striped">
        <thead>
            <tr>
                <th>Label</th>
                <th># Samples</th>
                <th>Average</th>
                <th>Min.</th>
                <th>Max</th>
                <th>90% Line</th>
				<th>Std. Dev.</th>
				<th>Error %</th>
				<th>Throughput</th>
				<th>KB/sec</th>
				<th>Avg. Bytes</th>
            </tr>
        </thead>
    </table>
	</div>
	</div>
		</div>
		<!-- row -->
		<br/>
		<br/>
	</div>

	<!-- Page Wrapper -->
</div>

<!-- Wrapper -->
</div>




</body>

</html>