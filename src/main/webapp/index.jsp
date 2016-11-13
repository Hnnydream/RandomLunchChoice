<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="import" href="/bower_components/paper-header-panel/paper-header-panel.html">
	<link rel="import" href="/bower_components/paper-toolbar/paper-toolbar.html">
	<link rel="import" href="/bower_components/iron-flex-layout/iron-flex-layout.html">
	<link rel="import" href="/bower_components/paper-button/paper-button.html">
	<script  src="/bower_components/jquery/dist/jquery.min.js"></script>
	<title>Random Lunch Choice</title>
</head>
<body  class="fullbleed vertical layout">
	<!-- paper-header-panel must have an explicit height -->
	<paper-header-panel class="flex"> 
		<paper-toolbar>
			<div>Random Lunch Choice</div>
			<span class="flex"></span>
      <paper-icon-button icon="search"></paper-icon-button>
		</paper-toolbar>
	</paper-header-panel>
		<div id="restaurantList">
			<table>
				<tr><th>Name</th></tr>
				<tr><td></td></tr>
			</table>
		</div>
		<br />
		<div  id="getChoice"><paper-button raised id="getChoice">Get Your Chioce</paper-button></div>
		<div><p>Your random choice: <br /></p></div>
		<div><h2 id="yourchoice" style="color:red;"></h2></div>
		<script src="js/main.js"></script>
</body>
</html>