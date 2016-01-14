
<%@ page import="java.util.List"%>
<%@ page import="com.nort.symc.perfengg.utils.JMXFiles"%>
<%@ page import="java.util.Arrays"%>

<jsp:include page="common.jsp"></jsp:include>
<div id="page-wrapper">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Performance Runs
				</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a href="/PerfDash">Home</a>
					</li>
					<li class="active"><i class="fa fa-edit"></i> New Run</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-6">
				<h4>Start a new Test</h4>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-6">
				<form role="form" name="scriptSelect" id="scriptSelect"
					action="runScript.mdo" method="post">
					<div class="form-group">
						<label>Select Script</label> <select class="form-control"
							name="script" id="scripts">
							<% 
		List<JMXFiles> files = Arrays.asList(JMXFiles.values());
		for(JMXFiles f : files) out.println("<option value=\""+f+"\">"+f+"</option>");
		%>
						</select>
						<p class="help-block">Select a scenario to run</p>
					</div>
					<div class="form-group">
						<label>Users</label> <input class="form-control" type="text"
							name="users" id="users">
						<p class="help-block">Specify the number of concurrent users</p>
					</div>
					<div class="form-group">
						<label>Duration</label> <input class="form-control" type="text"
							name="duration" id="duration">
						<p class="help-block">Duration of the test in SECONDS</p>
					</div>
					<div class="form-group">
						<label>Comment</label> <input class="form-control" type="text"
							name="comment" id="comment">
						<p class="help-block">Why not say something, that'll help you
							identify this test later. WITHOUT spaces please.</p>
					</div>
					<div class="form-group">
						<label>MWeb Host</label> <input class="form-control"
							name="mWebHost" id="mWebHost" value="manage-int2.norton.com">
						<p class="help-block">MWeb host address to run this test
							against</p>
					</div>
					<div class="form-group">
						<label>SSO Host</label> <input class="form-control" name="ssoHost"
							id="ssoHost" value="login-int2.norton.com">
						<p class="help-block">SSO host address to run this test
							against</p>
					</div>
					<div class="form-group">
						<label>Parameters</label>
						<textarea class="form-control" name="parameters" id="parameters"
							rows="3"></textarea>
						<p class="help-block">Got to specify something else for your
							script? Don't worry we've got you covered.</p>
					</div>
					<button type="submit" class="btn btn-primary">Start Test</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>

			</div>
			<!-- row -->
			<!-- Container Fluid -->
		</div>

		<!-- Page Wrapper -->
	</div>

	<!-- Wrapper -->
</div>

</body>
</html>
