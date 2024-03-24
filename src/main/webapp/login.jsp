	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html>
	<head>
	  <!-- Required meta tags -->
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <title>Login</title>
	
	  <link rel="stylesheet" href="css/style.css">
	</head>
	
	<body>
	  <div class="container-scroller">
	    <div class="container-fluid page-body-wrapper full-page-wrapper">
	      <div class="content-wrapper d-flex align-items-center auth px-0">
	        <div class="row w-100 mx-0">
	          <div class="col-lg-4 mx-auto">
	            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
	              <center>
	              <h4>Please Enter Your Email And Password</h4></center>
	              <!-- <h6 class="font-weight-light">Sign in to continue.</h6> -->
	              <form action="Login" method="post" class="pt-3">
	                <div class="form-group">
	                  <input type="email" name="email" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Email" required>
	                </br>
	                  <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password" required>
	                </div>
	                <CENter>
	                <div class="mt-3">
	                  <input class="btn btn-block btn-lg font-weight-medium auth-form-btn"  type="submit" value="SIGN IN">
	                </div></CENter>
	 
	                <div class="text-center mt-4 font-weight-light">
	                  Don't have an account? <a href="register.jsp" class="text-primary">Create</a>
	                </div>
	              </form>
	            </div>
	          </div>
	        </div>
	      </div>
	
	    </div>
	
	  </div>
	
	</body>
	
	</html>