<!DOCTYPE html>
<%@ page import="com.nort.symc.perfengg.utils.Constants"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Flash - A PerfWork ;)</title>

	<!-- jQuery -->
    <script src="//code.jquery.com/jquery-2.1.4.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!-- Datatables -->
	<script type="text/javascript" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
	
	<!-- Highcharts -->
	<script type="text/javascript" src="//code.highcharts.com/highcharts.js"></script>
	
    <!-- Bootstrap Core CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    
    <!-- Custom JS -->
    <script src="js/jenkinsJobs.js"></script>

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="//oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=Constants.machineRoot %>/Flash">Flash<small style="font-size: 55%;">  A PerfWork ;)</small></a>
                <div class="ribbon-wrapper-green"><div class="ribbon-green">ALPHA</div></div>
            </div>
            <!-- Top Menu Items -->
            
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
				                    <li>
                        <a href="<%=Constants.machineRoot %>/Flash"><i class="fa fa-fw fa-table"></i> Home</a>
                    </li>
                    <li>
                        <a href="<%=Constants.machineRoot %>/Flash/newRun.jsp"><i class="fa fa-fw fa-dashboard"></i> New Run</a>
                    </li>
                    <li>
                        <a href="<%=Constants.machineRoot %>/PerfDash"><i class="fa fa-fw fa-bar-chart-o"></i> Performance Trends</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
