				<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
				pageEncoding="ISO-8859-1"%>
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<!DOCTYPE html>
				<html lang="en">
				<head>
					<meta http-equiv="content-type" content="text/html; charset=utf-8" />
					<meta name="viewport" content="width=device-width, initial-scale=1">
					<meta name="description" content="This is social network html5 template available in themeforest......" />
					<meta name="keywords" content="Social Network, Social Media, Make Friends, Newsfeed, Profile Page" />
					<meta name="robots" content="index, follow" />
					<title>Friend Finder Site</title>

				    <!-- Stylesheets
				    ================================================= -->
				    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
				    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
				    <link href="<c:url value='/static/css/ionicons.min.css' />" rel="stylesheet"></link>
				    <link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet"></link>
				    <!--Google Webfont-->
				    <link href='https://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700' rel='stylesheet' type='text/css'>
				    <!--Favicon-->
				    <link rel="shortcut icon" type="image/png" href="/static/images/fav.png"/>
				</head>
				<body ng-app="myApp">

				    <!-- Header
				    ================================================= -->
				    <header id="header" class="lazy-load">
				    	<nav class="navbar navbar-default navbar-fixed-top menu">
				    		<div class="container">

				    			<!-- Brand and toggle get grouped for better mobile display -->
				    			<div class="navbar-header">
				    				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				    					<span class="sr-only">Toggle navigation</span>
				    					<span class="icon-bar"></span>
				    					<span class="icon-bar"></span>
				    					<span class="icon-bar"></span>
				    				</button>
				    				<a class="navbar-brand" href="index.html"><img src=<c:url value='/static/images/logo.png' />" alt="logo" /></a>
				    			</div>

				    			<!-- Collect the nav links, forms, and other content for toggling -->
				    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				    				<ul class="nav navbar-nav navbar-right main-menu">
				    					<li class="dropdown"><a href="index.html">Home</a></li>
				    					<li class="dropdown">
				    						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Newsfeed <span><img src="/static/images/down-arrow.png" alt="" /></span></a>
				    						<ul class="dropdown-menu newsfeed-home">
				    							<li><a href="newsfeed.html">Newsfeed</a></li>
				    							<li><a href="newsfeed-people-nearby.html">Poeple Nearly</a></li>
				    							<li><a href="newsfeed-friends.html">My friends</a></li>
				    							<li><a href="newsfeed-messages.html">Chatroom</a></li>
				    							<li><a href="newsfeed-images.html">Images</a></li>
				    							<li><a href="newsfeed-videos.html">Videos</a></li>
				    						</ul>
				    					</li>
				    					<li class="dropdown">
				    						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Timeline <span><img src="/static/images/down-arrow.png" alt="" /></span></a>
				    						<ul class="dropdown-menu login">
				    							<li><a href="timeline.html">Timeline</a></li>
				    							<li><a href="timeline-about.html">Timeline About</a></li>
				    							<li><a href="timeline-album.html">Timeline Album</a></li>
				    							<li><a href="timeline-friends.html">Timeline Friends</a></li>
				    						</ul>
				    					</li>
				    					<li class="dropdown">
				    						<a href="#" class="dropdown-toggle pages" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">All Pages <span><img src="/static/images/down-arrow.png" alt="" /></span></a>
				    						<ul class="dropdown-menu page-list">
				    							<li><a href="index.html">Landing Page</a></li>
				    							<li><a href="newsfeed.html">Newsfeed</a></li>
				    							<li><a href="newsfeed-people-nearby.html">Poeple Nearly</a></li>
				    							<li><a href="newsfeed-friends.html">My friends</a></li>
				    							<li><a href="newsfeed-messages.html">Chatroom</a></li>
				    							<li><a href="newsfeed-images.html">Images</a></li>
				    							<li><a href="newsfeed-videos.html">Videos</a></li>
				    							<li><a href="timeline.html">Timeline</a></li>
				    							<li><a href="timeline-about.html">Timeline About</a></li>
				    							<li><a href="timeline-album.html">Timeline Album</a></li>
				    							<li><a href="timeline-friends.html">Timeline Friends</a></li>
				    							<li><a href="contact.html">Contact Us</a></li>
				    						</ul>
				    					</li>
				    					<li class="dropdown"><a href="contact.html">Contact</a></li>
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

				    <!-- Top Banner
				    ================================================= -->
				    <section id="banner">
				    	<div class="container" ng-controller="UserController as ctrl">

				        <!-- Sign Up Form
				        ================================================= -->
				        <div class="sign-up-form">
				        	<a href="index.html" class="logo"><img src="/static/images/logo.png" alt="Friend Finder"/></a>
				        	<h2 class="text-white">Find My Friends</h2>
				        	<div class="line-divider"></div>
				        	<div class="form-wrapper">
				        		<p class="signup-text">Signup now and meet awesome people around the world</p>

				        		<form ng-submit="ctrl.submit()" name="myForm">
				        			<fieldset class="form-group">
				        				<input type="text"
				        				class="form-control" id="example-name" placeholder="Enter name" ng-model="ctrl.user.name" name="name"
				        				required ng-minlength="3">
				        				<div class="has-error" ng-show="myForm.$dirty">
				        					<span ng-show="myForm.name.$error.required">This is a
				        						required field</span> <span ng-show="myForm.name.$error.minlength">Minimum
				        						length required is 3</span> <span ng-show="myForm.name.$invalid">This
				        						field is invalid </span>
				        						 </span>
				        					</div>

				        				</fieldset>
				        				<fieldset class="form-group">
				        					<input type="email" class="form-control" id="example-email" placeholder="Enter email" ng-model="ctrl.user.email" name="email"
				        					required>
				        					<div class="has-error" ng-show="myForm.$dirty">
				        						<span ng-show="myForm.email.$error.required">This is a
				        							required field</span> <span ng-show="myForm.email.$invalid">This
				        							field is invalid </span>
				        						</div>

				        					</fieldset>
				        					<fieldset class="form-group">
				        						<input type="password" class="form-control" id="example-password" placeholder="Enter a password" ng-model="ctrl.user.password"
				        						name="password" required>
				        					</fieldset>
				        					<div class="has-error" ng-show="myForm.$dirty"></div>
				        				</div>
				        				</fieldset>


				        			<p>By signning up you agree to the terms</p>
				        			<button type="submit" class="btn-secondary"
				        			ng-disabled="myForm.$invalid" />
				        			Get Started
				        		</button>
				        		</form>
				        	</div>
				        	<a href="#">Already have an account?</a>
				        	<img class="form-shadow" src="/static/images/bottom-shadow.png" alt="" />
				        </div><!-- Sign Up Form End -->

				        <svg class="arrows hidden-xs hidden-sm">
				        	<path class="a1" d="M0 0 L30 32 L60 0"></path>
				        	<path class="a2" d="M0 20 L30 52 L60 20"></path>
				        	<path class="a3" d="M0 40 L30 72 L60 40"></path>
				        </svg>
				    </div>
				</section>

				    <!-- Features Section
				    ================================================= -->
				    <section id="features">
				    	<div class="container wrapper">
				    		<h1 class="section-title slideDown">social herd</h1>
				    		<div class="row slideUp">
				    			<div class="feature-item col-md-2 col-sm-6 col-xs-6 col-md-offset-2">
				    				<div class="feature-icon"><i class="icon ion-person-add"></i></div>
				    				<h3>Make Friends</h3>
				    			</div>
				    			<div class="feature-item col-md-2 col-sm-6 col-xs-6">
				    				<div class="feature-icon"><i class="icon ion-images"></i></div>
				    				<h3>Publish Posts</h3>
				    			</div>
				    			<div class="feature-item col-md-2 col-sm-6 col-xs-6">
				    				<div class="feature-icon"><i class="icon ion-chatbox-working"></i></div>
				    				<h3>Private Chats</h3>
				    			</div>
				    			<div class="feature-item col-md-2 col-sm-6 col-xs-6">
				    				<div class="feature-icon"><i class="icon ion-compose"></i></div>
				    				<h3>Create Polls</h3>
				    			</div>
				    		</div>
				    		<h2 class="sub-title">find awesome people like you</h2>
				    		<div id="incremental-counter" data-value="101242"></div>
				    		<p>People Already Signed Up</p>
				    		<img src="/static/images/face-map.png" alt="" class="img-responsive face-map slideUp hidden-sm hidden-xs" />
				    	</div>

				    </section>

				    <!-- Download Section
				    ================================================= -->
				    <section id="app-download">
				    	<div class="container wrapper">
				    		<h1 class="section-title slideDown">download</h1>
				    		<ul class="app-btn list-inline slideUp">
				    			<li><button class="btn-secondary"><img src="/static/images/app-store.png" alt="App Store" /></button></li>
				    			<li><button class="btn-secondary"><img src="/static/images/google-play.png" alt="Google Play" /></button></li>
				    		</ul>
				    		<h2 class="sub-title">stay connected anytime, anywhere</h2>
				    		<img src="/static/images/iPhone.png" alt="iPhone" class="img-responsive" />
				    	</div>
				    </section>

				    <!-- Image Divider
				    ================================================= -->
				    <div class="img-divider hidden-sm hidden-xs"></div>

				    <!-- Facts Section
				    ================================================= -->
				    <section id="site-facts">
				    	<div class="container wrapper">
				    		<div class="circle">
				    			<ul class="facts-list">
				    				<li>
				    					<div class="fact-icon"><i class="icon ion-ios-people-outline"></i></div>
				    					<h3 class="text-white">1,01,242</h3>
				    					<p>People registered</p>
				    				</li>
				    				<li>
				    					<div class="fact-icon"><i class="icon ion-images"></i></div>
				    					<h3 class="text-white">21,01,242</h3>
				    					<p>Posts published</p>
				    				</li>
				    				<li>
				    					<div class="fact-icon"><i class="icon ion-checkmark-round"></i></div>
				    					<h3 class="text-white">41,242</h3>
				    					<p>People online</p>
				    				</li>
				    			</ul>
				    		</div>
				    	</div>
				    </section>

				    <!-- Live Feed Section
				    ================================================= -->
				    <section id="live-feed">
				    	<div class="container wrapper">
				    		<h1 class="section-title slideDown">live feed</h1>
				    		<ul class="online-users list-inline slideUp">
				    			<li><a href="#" title="Alexis Clark"><img src="/static/images/users/user-5.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    			<li><a href="#" title="James Carter"><img src="/static/images/users/user-6.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    			<li><a href="#" title="Robert Cook"><img src="/static/images/users/user-7.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    			<li><a href="#" title="Richard Bell"><img src="/static/images/users/user-8.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    			<li><a href="#" title="Anna Young"><img src="/static/images/users/user-9.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    			<li><a href="#" title="Julia Cox"><img src="/static/images/users/user-10.jpg" alt="" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
				    		</ul>
				    		<h2 class="sub-title">see what’s happening now</h2>
				    		<div class="row">
				    			<div class="col-md-4 col-sm-6 col-md-offset-2">
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-1.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Sarah</a> just posted a photo from Moscow</p>
				    						<p class="text-muted">20 Secs ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-4.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">John</a> Published a post from Sydney</p>
				    						<p class="text-muted">1 min ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-10.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Julia</a> Updated her status from London</p>
				    						<p class="text-muted">5 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-3.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Sophia</a> Share a photo from Virginia</p>
				    						<p class="text-muted">10 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-2.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Linda</a> just posted a photo from Toronto</p>
				    						<p class="text-muted">20 mins ago</p>
				    					</div>
				    				</div>
				    			</div>
				    			<div class="col-md-4 col-sm-6">
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-17.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Nora</a> Shared an article from Ohio</p>
				    						<p class="text-muted">22 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-18.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Addison</a> Created a poll from Barcelona</p>
				    						<p class="text-muted">23 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-11.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Diana</a> Posted a video from Captown</p>
				    						<p class="text-muted">27 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-1.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Sarah</a> Shared friend's post from Moscow</p>
				    						<p class="text-muted">30 mins ago</p>
				    					</div>
				    				</div>
				    				<div class="feed-item">
				    					<img src="/static/images/users/user-16.jpg" alt="user" class="img-responsive profile-photo-sm" />
				    					<div class="live-activity">
				    						<p><a href="#" class="profile-link">Emma</a> Started a new job at Torronto</p>
				    						<p class="text-muted">33 mins ago</p>
				    					</div>
				    				</div>
				    			</div>
				    		</div>
				    	</div>
				    </section>

				    <!-- Footer
				    ================================================= -->
				    <footer id="footer">
				    	<div class="container">
				    		<div class="row">
				    			<div class="footer-wrapper">
				    				<div class="col-md-3 col-sm-3">
				    					<a href=""><img src="/static/images/logo-black.png" alt="" class="footer-logo" /></a>
				    					<ul class="list-inline social-icons">
				    						<li><a href="#"><i class="icon ion-social-facebook"></i></a></li>
				    						<li><a href="#"><i class="icon ion-social-twitter"></i></a></li>
				    						<li><a href="#"><i class="icon ion-social-googleplus"></i></a></li>
				    						<li><a href="#"><i class="icon ion-social-github"></i></a></li>
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
				    						<li><i class="icon ion-ios-email-outline"></i>pham@cpp.edu</li>
				    						<li><i class="icon ion-ios-location-outline"></i>3801 West Temple Avenue Pomona, CA 91768</li>
				    					</ul>
				    				</div>
				    			</div>
				    		</div>
				    	</div>
				    	<div class="copyright">
				    		<p>Copyright @CS580 2017. All rights reserved</p>
				    	</div>
				    </footer>

				    <!--preloader-->
				    <div id="spinner-wrapper">
				    	<div class="spinner"></div>
				    </div>

				    <!-- Scripts
				    ================================================= -->
				    <script src="js/jquery-3.1.1.min.js"></script>
				    <script src="js/bootstrap.min.js"></script>
				    <script src="js/jquery.appear.min.js"></script>
				    <script src="js/jquery.incremental-counter.js"></script>
				    <script src="js/script.js"></script>
				    <script
				    src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


				    <script
				    src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
				    <script src="<c:url value='/static/js/app.js' />"></script>
				    <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
				    <script src="<c:url value='/static/js/service/user_service.js' />"></script>
				    <script src="<c:url value='/static/js/jquery.incremental-counter.js' />"></script>
				    <script src="<c:url value='/static/js/jquery-3.1.1.min.js' />"></script>
				    <script src="<c:url value='/static/js/jquery.appear.min.js' />"></script>
				    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
				    <script src="<c:url value='/static/js/script.js' />"></script>
				</body>
				</html>