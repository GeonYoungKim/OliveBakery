
<%@page import="kr.ac.controller.board.dto.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
	String cp=request.getContextPath();
	String id=(String)session.getAttribute("id");
	UserVO uservo=(UserVO)request.getAttribute("uservo");
	String name=uservo.getName().replaceAll(" ", "");
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>올리브 베이커리 회원 설정</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=cp%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=cp%>/resources/bootstrap/css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<style type="text/css">
		h5{
			color:#ffffff;
			text-align:right;
			margin-right:100px;		
		}
	</style>
	<%if(id!=null){ %>
		<div class="address-bar"><%=id%>님 반가워요</div>
		<a href="Logout"><h5>로그아웃</h5></a><a href="Main"><h5>메인</h5></a>
	<%}else{ %>
	<a href="MemberShipForm"><h5>회원가입</h5></a><a href="LoginForm"><h5>로그인</h5></a>
	<%} %>
	
	
    <div class="brand">Olive Bakery</div>
    <div class="address-bar">경기 양평군 양평읍 시민로78번길 9-4</div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="Main">Olive Bakery</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="OrderHistory">Order History</a>
                    </li>
                    <li>
                        <a href="AddressBookForm">Address Book</a>
                    </li>
                    <li>
                        <a href="EditUserForm">Edit User</a>
                    </li>
                    <li>
                        <a href="EditPasswordForm">Edit Password</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
                        <strong>회원 정보 수정</strong>
                    </h2>
                    <hr>
                </div>
                <form role="form" action="EditUser">
                    <div class="row"> 
                        <div class="form-group col-lg-12">
                                <label>NAME</label>
                                <input type="text" class="form-control" name="name" value=<%=name %>>
                        </div>
                    </div>
                    <div class="row"> 
                        <div class="form-group col-lg-12">
                                <label>BIRTH</label>
                                <input type="text" class="form-control" name="birth" value=<%=uservo.getBirth() %>>
                        </div>
                    </div>
                    <div class="row"> 
                        <div class="form-group col-lg-12">
                                <label>PHONE NUMBER</label>
                                <input type="text" class="form-control" name="phone" value=<%=uservo.getPhone() %>>
                                
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="form-group col-lg-12">
                            <input type="hidden" name="save" value="contact">
                            <button type="submit" class="btn btn-default">Change</button>
                        </div>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
   </div>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; Your Website 2017</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="<%=cp%>/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=cp%>/resources/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>