<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nort.symc.perfengg.utils.Constants"%>
<jsp:include page="../common.jsp"></jsp:include>
<script>
var currentPageNumber=1;
</script>
<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Performance Runs</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a href="<%=Constants.machineRoot %>/Flash">Home</a>
					</li>
					<li class="active"><i class="fa fa-edit"></i> Runs</li>
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
						<th>Build number</th>
						<th>Comment</th>
						<th>User</th>
						<th>Status</th>
						<th>Script</th>
						<th>Run Detail</th>
					</tr>
					</thead>
					<c:set var="i" value="10"></c:set>
					<c:forEach var="runObj" items="${runs }">
					<c:if test="${i%10==0 }">
					<tbody id="pageDisp${i }" class="hidden">
					</c:if>
						<tr>
							<td><c:out value="${runObj.getBuildNumber()}"></c:out></td>
							<td><c:out value="${runObj.getComment()}"></c:out></td>
							<td><c:out value="${runObj.getUsers()}"></c:out></td>
							<td><c:out value="${runObj.getStatus()}"></c:out></td>
							<td><c:out value="${runObj.getTest()}"></c:out></td>
							<td><a
								href="testStatus.mdo?testId=<c:out value='${runObj.getBuildNumber()}'/>">Info</a>
								&nbsp;
								<c:if test="${runObj.getStatus()=='SUCCESS'}">
								<a
								href="testResult.mdo?testId=<c:out value='${runObj.getBuildNumber()}'/>">Report</a>
								</c:if>
							</td>
						</tr>
						<c:set var="i" value="${i+1 }"></c:set>
						<c:if test="${i%10==0 }">
					</tbody>
					</c:if>
					</c:forEach>

				</tbody>
				</table>
				</div>

			</div>
			<!-- /.row -->
			<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
			<div style="float:right">
			<ul class="pagination">
			<c:set var="pages" value="${i/10 }"></c:set>
			<c:forEach var="currentPage" begin="1" end="${pages}" step="1" varStatus="loop">
			<li><a href="javascript:showResultPage(<c:out value='${currentPage}'/>)"><c:out value="${currentPage}"/></a></li>
			</c:forEach>
			</ul>
			</div>
			

			<!-- Container Fluid -->
		</div>

		<!-- Page Wrapper -->
	</div>

	<!-- Wrapper -->
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
$("#pageDisp10").toggleClass('hidden');
});
function showResultPage(pageNumber) {
	//alert("Current : " + currentPageNumber +" : Next :" + pageNumber);
	$("#pageDisp"+pageNumber*10).toggleClass('hidden');
	$("#pageDisp"+currentPageNumber*10).toggleClass('hidden');
	currentPageNumber = pageNumber;
}
</script>
</html>