<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Auction House-Update Carving</title>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/css/datepicker3.css" rel="stylesheet">
	<link href="assets/css/styles.css" rel="stylesheet">
	
	<!--Custom Font-->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>
				<a class="navbar-brand" href="#"><span>Auction</span>House</a>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-envelope"></em><span class="label label-danger">15</span>
					</a>
						<ul class="dropdown-menu dropdown-messages">
							<li>
								<div class="dropdown-messages-box"><a href="profile.html" class="pull-left">
									<img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body"><small class="pull-right">3 mins ago</small>
										<a href="#"><strong>John Doe</strong> commented on <strong>your photo</strong>.</a>
									<br /><small class="text-muted">1:24 pm - 25/03/2015</small></div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="dropdown-messages-box"><a href="profile.html" class="pull-left">
									<img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body"><small class="pull-right">1 hour ago</small>
										<a href="#">New message from <strong>Jane Doe</strong>.</a>
									<br /><small class="text-muted">12:27 pm - 25/03/2015</small></div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="all-button"><a href="#">
									<em class="fa fa-inbox"></em> <strong>All Messages</strong>
								</a></div>
							</li>
						</ul>
					</li>
					<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-bell"></em><span class="label label-info">5</span>
					</a>
						<ul class="dropdown-menu dropdown-alerts">
							<li><a href="#">
								<div><em class="fa fa-envelope"></em> 1 New Message
									<span class="pull-right text-muted small">3 mins ago</span></div>
							</a></li>
							<li class="divider"></li>
							<li><a href="#">
								<div><em class="fa fa-heart"></em> 12 New Likes
									<span class="pull-right text-muted small">4 mins ago</span></div>
							</a></li>
							<li class="divider"></li>
							<li><a href="#">
								<div><em class="fa fa-user"></em> 5 New Followers
									<span class="pull-right text-muted small">4 mins ago</span></div>
							</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpi">
				<img src="assets/images/auctionhouse.jpg" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"></div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		
		<ul class="nav menu">
			<li><a href="adminDashboard.jsp"><em class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li><a href="addUsers"><em class="fa fa-user">&nbsp;</em> Add Users</a></li>
			<li><a href="viewUsers"><em class="fa fa-users">&nbsp;</em> View Users</a></li>
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> Add Items <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="addPaintings.jsp">
						<span class="fa fa-arrow-right">&nbsp;</span> Paintings
					</a></li>
					<li><a class="" href="addPhotographicImages.jsp">
						<span class="fa fa-arrow-right">&nbsp;</span>PhotographicImages
					</a></li>
					<li><a class="" href="addSculptures.jsp">
						<span class="fa fa-arrow-right">&nbsp;</span> Sculptures
					</a></li>
					<li><a class="" href="addCarvings.jsp">
						<span class="fa fa-arrow-right">&nbsp;</span> Carvings
					</a></li>
					<li><a class="" href="addDrawings.jsp">
						<span class="fa fa-arrow-right">&nbsp;</span> Drawings
					</a></li>
				</ul>
			</li>
			<li class="parent "><a data-toggle="collapse" href="#sub-item-2">
				<em class="fa fa-navicon">&nbsp;</em> View Items <span data-toggle="collapse" href="#sub-item-2" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-2">
					<li><a class="" href="viewPaintings">
						<span class="fa fa-arrow-right">&nbsp;</span> Paintings
					</a></li>
					<li><a class="" href="viewPhotographicImages">
						<span class="fa fa-arrow-right">&nbsp;</span>PhotographicImages
					</a></li>
					<li><a class="" href="viewSculptures">
						<span class="fa fa-arrow-right">&nbsp;</span> Sculptures
					</a></li>
					<li><a class="" href="viewCarvings">
						<span class="fa fa-arrow-right">&nbsp;</span> Carvings
					</a></li>
					<li><a class="" href="viewDrawings">
						<span class="fa fa-arrow-right">&nbsp;</span> Drawings
					</a></li>
				</ul>
			</li>
			<li><a href="logout"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="adminDashboard.jsp">
					<em class="fa fa-home"></em>
				</a></li>
				
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Auction House Update Carvings Panel</h1>
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				
				<div class="panel panel-default">
					<div class="panel-heading">Update Carvings</div>
					<div class="panel-body">
						<div class="col-lg-offset-3 col-lg-6">
							<form role="form" action="updateCarving" method="POST" enctype="multipart/form-data">
							<input type="hidden" value="${carving.getLotNumber()}" name="lotNumber">
								
								<div class="form-group">
									<label>Artist Name</label>
									<input class="form-control" type="text" placeholder="Enter artist name" name="artistName" value="${carving.getArtistName() }" required="required">
								</div>
								<div class="form-group">
									<label>Year Produced</label>
									<input class="form-control" type="date" name="yearProduced" value="${carving.getItemProducedDate()}" required="required">
								</div>
								<div class="form-group">
									<label>Select Classification</label>
									<select class="form-control" name="classification">
										<option <c:if test="${carving.getClassification()=='landscape'}">selected="selected"</c:if>value="landscape">Landscape</option>
										<option <c:if test="${carving.getClassification()=='seascape'}">selected="selected"</c:if>value="seascape">Seascape</option>
										<option <c:if test="${carving.getClassification()=='potrait'}">selected="selected"</c:if>value="potrait">Potrait</option>
										<option <c:if test="${carving.getClassification()=='figure'}">selected="selected"</c:if>value="figure">Figure</option>
										<option <c:if test="${carving.getClassification()=='still life'}">selected="selected"</c:if>value="still life">Still Life</option>
										<option <c:if test="${carving.getClassification()=='nude'}">selected="selected"</c:if>value="nude">Nude</option>
										<option <c:if test="${carving.getClassification()=='animal'}">selected="selected"</c:if>value="animal">Animal</option>
										<option <c:if test="${carving.getClassification()=='abstract'}">selected="selected"</c:if>value="abstract">Abstract</option>
										<option <c:if test="${carving.getClassification()=='others'}">selected="selected"</c:if>value="others">Others</option>
									</select>
								</div>
								<div class="form-group">
									<label>Description</label>
									<textarea class="form-control" name="description" required="required" placeholder="Enter description">${carving.getDescription()}</textarea>
								</div>
								<div class="form-group">
									<label>Auction Date</label>
									<input class="form-control" type="date" name="auctionDate" value="${carving.getAuctionDate()}" required="required">
								</div>
								<div class="form-group">
									<label>Estimated Price in(&euro;)</label>
									<input class="form-control" type="number" name="estimatedPrice" value="${carving.getEstimated_price()}" required="required">
								</div>
								<div class="form-group">
									<label>Select Material Used</label>
									<select class="form-control" name="materialUsed">
										<option <c:if test="${carving.getMaterialUsed()=='typically'}">selected="selected"</c:if>value="typically">Typically</option>
										<option <c:if test="${carving.getMaterialUsed()=='oak'}">selected="selected"</c:if>value="oak">Oak</option>
										<option <c:if test="${carving.getMaterialUsed()=='beach'}">selected="selected"</c:if>value="beach">Beach</option>
										<option <c:if test="${carving.getMaterialUsed()=='pine'}">selected="selected"</c:if>value="pine">Pine</option>
										<option <c:if test="${carving.getMaterialUsed()=='willow'}">selected="selected"</c:if>value="willow">Willow</option>
										<option <c:if test="${carving.getMaterialUsed()=='others'}">selected="selected"</c:if>value="others">Others</option>
									</select>
								</div>
								
								<div class="form-group">
									<label>Enter Dimension(height)</label>
									<input class="form-control" type="number" name="height" value="${carving.getHeight()}" required="required">
								</div>
								<div class="form-group">
									<label>Enter Dimension(length)</label>
									<input class="form-control" type="number" name="length" value="${carving.getLength()}" required="required">
								</div>
								<div class="form-group">
									<label>Enter Dimension(width)</label>
									<input class="form-control" type="number" name="width" value="${carving.getWidth()}" required="required">
								</div>
								<div class="form-group">
									<label>Enter weight(in Kgs)</label>
									<input class="form-control" type="number" name="weight" value="${carving.getWeight()}" required="required">
								</div>
								<div class="form-group">
									<label>Upload Picture</label>
									<input type="file" name="photo">
								</div>
								<button type="submit" class="btn btn-success">Update</button>
								<button type="reset" class="btn btn-primary">Reset</button>
							
							</form>
								</div>
								
									
									
								</div>
							
						</div>
					</div>
				</div><!-- /.panel-->
			</div><!-- /.col-->
			<div class="col-sm-12">
				<p class="back-link">Auction House <a href="#">Pawan Thapa</a></p>
			</div>
		</div><!-- /.row -->
	</div><!--/.main-->
	
<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/chart.min.js"></script>
	<script src="assets/js/chart-data.js"></script>
	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>
	<script src="assets/js/bootstrap-datepicker.js"></script>
	<script src="assets/js/custom.js"></script>
	
</body>
</html>
