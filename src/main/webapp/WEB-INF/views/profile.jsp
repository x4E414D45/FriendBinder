<%--
    Document   : profile.jsp
    Created on : Jan 30, 2017, 2:55:43 PM
    Author     : Jack
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="This is social network" />
		<meta name="keywords" content="Social Network, Social Media, Make Friends, Newsfeed, Profile Page" />
		<meta name="robots" content="index, follow" />
		<title>About Me | Learn Detail About Me</title>

    <!-- Stylesheets
    ================================================= -->
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap-theme.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap-social.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/ionicons.min.css' />" rel="stylesheet" />
    <link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/emoji.css' />" rel="stylesheet"/>    
    <!--Google Webfont-->
		<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700' rel='stylesheet' type='text/css'>
    <!--Favicon-->
    <link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
	</head>
  <body>

    <!-- Header
    ================================================= -->
		<header id="header">
      <nav class="navbar navbar-inverse navbar-fixed-top menu">
        <div class="container">

          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html" id="rafiki">Rafikie</a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
              <li class=""><a href="index.html">Home</a></li>
              <li class="active">
                <a href="#" class="" role="button" aria-haspopup="true" aria-expanded="false">Profile</a>
              </li>
              <li class="">
                <a href="#" class="" role="button" aria-haspopup="true" aria-expanded="false">Friends</a>
              </li>
            </ul>
            <form class="navbar-form navbar-right hidden-sm">
              <div class="form-group">
                <i class="icon ion-android-search"></i>
                <input type="text" class="form-control" placeholder="Search friends, photos, videos">
              </div>
            </form>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
      </nav>
    </header>
    <!--Header End-->
		<div id="editModal" class="modal fade" role="dialog">
	      <div class="modal-dialog">
	        <!-- Modal content-->
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	                <h4 class="modal-title">Edit Your Information</h4>
	            </div>
	            <div class="modal-body" style="padding: 0px 30px 0px 30px">
				<div class="row">
					<form class="form-horizontal">
						<div class="form-group">
								<label for="yourself" class="col-sm-2 control-label">About Yourself</label>
								<div class="col-sm-10">
										<textarea class="form-control" id="yourself" name="yourself" rows="5"></textarea>
								</div>
						</div>
						<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Full Name</label>
								<div class="col-sm-10">
										<input type="text" class="form-control" id="name" name="name" placeholder="Enter Your Full Name">
								</div>
						</div>
						<div class="form-group has-feedback">
							<label for="date" class="col-sm-2 control-label">Date of Birth</label>
													<div class="col-sm-4">
															<input type="text" class="form-control" id="date" name="date" placeholder="Date">
								<span class="glyphicon glyphicon-calendar form-control-feedback" aria-hidden="true"></span>
													</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-success active">
										<input type="radio" name="options" id="option1" autocomplete="off" checked>Female
									</label>
									<label class="btn btn-danger">
										<input type="radio" name="options" id="option2" autocomplete="off">Male
									</label>
								</div>
								</div>
						</div>
						<div class="form-group">
							<label for="telnum" class="col-xs-12 col-sm-2 control-label">Contact Tel.</label>
							<div class="col-xs-5 col-sm-4 col-md-3">
									<div class="input-group">
											<div class="input-group-addon">(</div>
													<input type="tel" class="form-control" id="areacode" name="areacode" placeholder="Area code">
											<div class="input-group-addon">)</div>
									</div>
							</div>
							<div class="col-xs-7 col-sm-6 col-md-7">
									<input type="tel" class="form-control" id="telnum" name="telnum" placeholder="Tel. number">
							</div>
					</div>
					<div class="form-group">
							<label for="emailid" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
									<input type="email" class="form-control" id="emailid" name="emailid" placeholder="Email">
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Relationship</label>
						<div class="col-sm-10">
							<label class="radio-inline">
																<input type="radio" name="number" value="">Single
														</label>
							<label class="radio-inline">
																<input type="radio" name="number" value="">In a relationship
														</label>
							<label class="radio-inline">
																<input type="radio" name="number" value="">Marriaged
														</label>
							</div>
					</div>
					<div class="form-group">
							<label for="education" class="col-sm-2 control-label">Education Level</label>
							<div class="col-sm-10">
									<input type="education" class="form-control" id="education" name="education" placeholder="Education">
							</div>
					</div>
					<div class="form-group">
							<label for="careerid" class="col-sm-2 control-label">Career</label>
							<div class="col-sm-10">
									<input type="career" class="form-control" id="careerid" name="careerid" placeholder="Career">
							</div>
					</div>
					<div class="form-group">
							<label for="locationid" class="col-sm-2 control-label">Location</label>
							<div class="col-sm-10">
									<input type="location" class="form-control" id="locationid" name="locationid" placeholder="Location">
							</div>
					</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Interests</label>
							<div class="col-sm-10">
								<label class="checkbox-inline">
	                                <input type="checkbox" name="number" value="">Reading
	                            </label>
								<label class="checkbox-inline">
	                                <input type="checkbox" name="number" value="">Travelling
	                            </label>
								<label class="checkbox-inline">
	                                <input type="checkbox" name="number" value="">Playing Sports
	                            </label>
								<label class="checkbox-inline">
	                                <input type="checkbox" name="number" value="">Cooking
	                            </label>
								<label class="checkbox-inline">
	                                <input type="checkbox" name="number" value="">Playing Games
	                            </label>
	            	</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">

									<label class="checkbox-inline">
		                                <input type="checkbox" name="number" value="">Studying
		                            </label>
									<label class="checkbox-inline">
									        <input type="checkbox" name="number" value="">Sleeping
									</label>
								</div>
						</div>
						<div class="form-group">
								<label for="language" class="col-sm-2 control-label">Language</label>
								<div class="col-sm-10">
										<input type="language" class="form-control" id="language" name="language" placeholder="Language">
								</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10 col-sm-offset-2">
	              <button type="submit" class="btn btn-primary">Update</button>
								<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Cancel</button>
	            </div>
						</div>
					</form>
					</div>
	      </div>
	    </div>
			</div>
	  </div>

    <div class="container">

      <!-- Timeline
      ================================================= -->
      <div class="timeline">
        <div class="timeline-cover">

          <!--Timeline Menu for Large Screens-->
          <div class="timeline-nav-bar hidden-sm hidden-xs">
            <div class="row">
              <div class="col-md-3">
                <div class="profile-info">
                  <img src="images/users/user-1.jpg" alt="" class="img-responsive profile-photo" />
                  <h3>Thong Pham</h3>
                  <p class="text-muted">Software Engineer</p>
                </div>
              </div>
              <div class="col-md-9">
                <ul class="list-inline profile-menu">
                  <li><a href=""></a></li>
                  <li><a href=""></a></li>
                  <li><a href=""></a></li>
                </ul>
                <ul class="follow-me list-inline">
									<li><button class="btn-primary" id="editInfo">Edit</button></li>
									<li><button class="btn-primary" id="editInfo">Log Out</button></li>
                </ul>
              </div>
            </div>
          </div><!--Timeline Menu for Large Screens End-->

          <!--Timeline Menu for Small Screens-->
          <div class="navbar-mobile hidden-lg hidden-md">
            <div class="profile-info">
              <img src="images/users/user-1.jpg" alt="" class="img-responsive profile-photo" />
              <h4>Thong Pham</h4>
              <p class="text-muted">Software Engineer</p>
            </div>
            <div class="mobile-menu">
              <ul class="list-inline">
								<li><a href=""></a></li>
								<li><a href=""></a></li>
								<li><a href=""></a></li>
              </ul>
              <button class="btn-primary" id="editInfo">Edit</button>
							<button class="btn-primary" id="editInfo">Log Out</button>
            </div>
          </div><!--Timeline Menu for Small Screens End-->

        </div>
        <div id="page-contents">
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-7">

              <!-- About
              ================================================= -->
              <div class="about-profile">
                <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-information-outline icon-in-title"></i>About Yourself</h4>
                  <p>I am a full-stack web developer with interest on AI and machine learning.</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-information-outline icon-in-title"></i>Date of Birth</h4>
                  <p>07/25/1988</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-transgender icon-in-title"></i>Gender</h4>
                  <p>Male</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-telephone icon-in-title"></i>Contact</h4>
                  <p>+17149499561</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-email icon-in-title"></i>Email</h4>
                  <p>phminthong88@gmail.com</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-information-outline icon-in-title"></i>Relationship</h4>
                  <p>Single</p>
                </div>
								<div class="about-content-block">
                  <h4 class="grey"><i class="ion-university icon-in-title"></i>Education Level</h4>
                  <p>Software Engineer</p>
                </div>
                <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-briefcase-outline icon-in-title"></i>Career</h4>
                  <p>Software Engineer</p>
                </div>
                <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-location-outline icon-in-title"></i>Location</h4>
                  <p>West Covina, CA</p>
                  <div class="google-maps">
                    <div id="map" class="map"></div>
                  </div>
                </div>
                <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-heart-outline icon-in-title"></i>Interests</h4>
                  <ul class="interests list-inline">
                    <li><span class="int-icons" title="Bycycle riding"><i class="icon ion-android-bicycle"></i></span></li>
                    <li><span class="int-icons" title="Photography"><i class="icon ion-ios-camera"></i></span></li>
                    <li><span class="int-icons" title="Shopping"><i class="icon ion-android-cart"></i></span></li>
                    <li><span class="int-icons" title="Traveling"><i class="icon ion-android-plane"></i></span></li>
                    <li><span class="int-icons" title="Eating"><i class="icon ion-android-restaurant"></i></span></li>
                  </ul>
                </div>
                <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-chatbubble-outline icon-in-title"></i>Language</h4>
                    <ul>
                      <li><a href="">Vietnamese</a></li>
                      <li><a href="">English</a></li>
                    </ul>
                </div>
              </div>
            </div>
            <div class="col-md-2 static">
              <div id="sticky-sidebar">

              </div>
            </div>
          </div>
        </div>
      </div>

    </div>


    <!-- Footer
    ================================================= -->
    <footer id="footer">
      <div class="container">
      	<div class="row">
          <div class="footer-wrapper">
            <div class="col-md-3 col-sm-3">
              <a href="" id="rafiki">Rafikie</a>
              <ul class="list-inline social-icons">
              	<li><a href="#"><i class="icon ion-social-facebook"></i></a></li>
              	<li><a href="#"><i class="icon ion-social-twitter"></i></a></li>
              	<li><a href="#"><i class="icon ion-social-googleplus"></i></a></li>
              	<li><a href="#"><i class="icon ion-social-linkedin"></i></a></li>
              </ul>
            </div>
            <div class="col-md-2 col-sm-2">
              <h6>For individuals</h6>
              <ul class="footer-links">
                <li><a href="">Signup</a></li>
                <li><a href="">login</a></li>
                <li><a href="">Explore</a></li>
                <li><a href="">Finder app</a></li>
                <li><a href="">Features</a></li>
                <li><a href="">Language settings</a></li>
              </ul>
            </div>
            <div class="col-md-2 col-sm-2">
              <h6>About</h6>
              <ul class="footer-links">
                <li><a href="">About us</a></li>
                <li><a href="">Contact us</a></li>
                <li><a href="">Privacy Policy</a></li>
                <li><a href="">Terms</a></li>
                <li><a href="">Help</a></li>
              </ul>
            </div>
            <div class="col-md-3 col-sm-3">
              <h6>Contact Us</h6>
                <ul class="contact">
                	<li><i class="icon ion-ios-telephone-outline"></i>+1 (714) 949 9561</li>
                	<li><i class="icon ion-ios-email-outline"></i>info@cpp.com</li>
                  <li><i class="icon ion-ios-location-outline"></i>3801 W Temple Ave, Pomona, CA 91768</li>
                </ul>
            </div>
          </div>
      	</div>
      </div>
      <div class="copyright">
        <p>x4E414D45 Team © 2017. All rights reserved</p>
      </div>
		</footer>

    <!--preloader-->
    <div id="spinner-wrapper">
      <div class="spinner"></div>
    </div>

    <!-- Scripts
    ================================================= -->
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/angular.min.js' />"></script>
    <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
    <script src="<c:url value='/static/js/service/user_service.js' />"></script>
    <script src="<c:url value='/static/js/jquery.incremental-counter.js' />"></script>
    <script src="<c:url value='/static/js/jquery.min.js' />"></script>
    <script src="<c:url value='/static/js/jquery.appear.min.js' />"></script>
    <script src="<c:url value='/static/js/jquery.scrollbar.min.js' />"></script>
    <script src="<c:url value='/static/js/jquery.sticky-kit.min.js' />"></script>
    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/static/js/script.js' />"></script>
		<script>
	        $(document).ready(function(){
				 	$("#editInfo").click(function(){
	                $("#editModal").modal('show');
	            });
	        });
	    </script>
  </body>
</html>
