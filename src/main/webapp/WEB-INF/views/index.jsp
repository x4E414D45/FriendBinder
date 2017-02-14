<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" >
<head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="This is social network site" />
        <meta name="keywords" content="Social Network, Social Media, Make Friends, Newsfeed, Profile Page" />
        <meta name="robots" content="index, follow" />
        <title>Rafiki</title>

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
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700' rel='stylesheet' type='text/css'/>
    <!--Favicon-->
    <link rel="shortcut icon" type="image/png" href="<c:url value='/static/images/favicon.png' />" />
</head>
<body ng-app="myApp" ng-controller="UserController as ctrl" ng-init="ctrl.init()">

    <!-- Header
    ================================================= -->
    <header id="header">
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
                                <a class="navbar-brand" id="rafiki" href="<c:url value='FriendBinder' />" >Rafikie</a>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
                                <ul class="nav navbar-nav navbar-right main-menu" ng-controller="UserController as ctrl">
                                        <form class="form-inline">
                                            <fieldset class="form-group">
                                                <label class="sr-only" for="email">Email Address</label>
                                                <input type="email" class="form-control input-sm" id="example-email" placeholder="Email">
                                            </fieldset>
                                            <fieldset class="form-group">
                                                <label class="sr-only" for="password">Password</label>
                                                <input type="password" class="form-control input-sm" id="example-password" placeholder="Password">
                                            </fieldset>
                                            <fieldset class="checkbox-inline">
                                                <label>
                                                    <input type="checkbox">Remember me
                                                </label>
                                            </fieldset>
                                                <button type="submit" class="btn-primary">Login In</button>
                                                <button type="submit" ng-click="ctrl.showRegister()" class="btn-primary">Register</button>
                                    </form>                                                             
                                </ul>                             
                        </div><!-- /.navbar-collapse -->
                </div><!-- /.container -->
        </nav>
    </header>
    <!--Header End-->

    <!-- Top Banner
    ================================================= -->
    <section id="banner">
        <div class="container" >
        <div class="col-md-5">
        <!-- Sign Up Form
        ================================================= -->
        <div class="sign-up-form"  >            
                <h2 class="text-white">Find My Friends</h2>
                <div class="line-divider"></div>
                <div class="form-wrapper">
                        <p class="signup-text">Signup now and meet awesome people around the world</p>

                        <form  ng-submit="ctrl.submit()" name="myForm">
                          <fieldset class="form-group" ng-class="{ 'has-error has-feedback' : myForm.name.$error.required && !myForm.name.$pristine }">
                            <div>
                              <input type="text" class="form-control" id="name" name="name" placeholder="Enter Your Name"
                              ng-model="ctrl.user.name" required ng-minlength=3>
                              <span ng-show="myForm.name.$error.required && !myForm.name.$pristine">This is required field.</span>
                              <span ng-show="myForm.name.$error.minlength && !myForm.name.$pristine">Minimum required length is 3.</span>
                              </div>
                          </fieldset>
                          <fieldset class="form-group" ng-class="{ 'has-error has-feedback' : myForm.email.$invalid && !myForm.email.$pristine }">
                              <div>
                                  <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                                  ng-model="ctrl.user.email" required>
                                  <span ng-show="myForm.email.$error.required && !myForm.email.$pristine">This is required field.</span>
                                  <span ng-show="(myForm.email.$invalid ||
                                              myForm.email.$error.required) &&
                                              !myForm.email.$pristine">Enter a valid email address.</span>
                              </div>
                          </fieldset>
                          <fieldset class="form-group" ng-class="{ 'has-error has-feedback' : myForm.password.$error.required && !myForm.password.$pristine }">
                              <input type="password" class="form-control" id="password" placeholder="Enter a password" ng-model="ctrl.user.password"
                                            name="password" required>
                          </fieldset>
                          <fieldset class=form-group>
                                <p>By signning up you agree to the terms</p>
                                <button type="submit" class="btn btn-secondary"
                                ng-disabled="myForm.$invalid" />Create Account</button>
                          </fieldset>
                     </form>
                </div>               
        </div><!-- Sign Up Form End -->
    </div>
        <div class="col-sm-7">
        <div class="row row-content">
          <p style="padding:80px;"></p>
        </div>
        <div class="row row-content">
          <p style="padding:80px;"></p>
        </div>
        <div class="row row-content">
          <div class="text-center">
            <h1 style="color: #F0F8FF; font-weight: bold"><span class="typing"></span></h1>
          </div>
        </div>
      </div>
     </div>
</section>

        <!-- Footer
    ================================================= -->
    <footer id="footer">
      <div class="copyright">
        <p>x4E414D45 Team © 2017. All rights reserved</p>
      </div>
    </footer>

    <!--preloader-->
    <div id="spinner-wrapper">
        <div class="spinner">
            
        </div>
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
    <script src="<c:url value='/static/js/typed.min.js' />"></script>
    <script>
    $(document).ready(function(){
        $(".typing").typed({
          strings: ["Are you alone ?", "Are you looking for friends ?", "This site is for you."],
          typeSpeed: 30,
        });
    });
</script>
</body>
</html>
