<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.social.ProgettoFinaleSocial.utils.FinalConstants"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
<head>
<meta charset="utf-8">
<title>login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
<%@ include file="../CSS/login.css"%></style>


</head>
<body>


	<%
	String BaseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String LoginUrl = BaseUrl + "/jsp/Login.jsp";

	String messaggio = (String) request.getAttribute(FinalConstants.MESSAGGIO);
	%>



	<!-- BACKGROND-->
	<div class="container">
		<header id="hex-grid">
			<div class="light"></div>
			<div class="grid" style = "background: url(<%= BaseUrl%>/risorse/foto/sfondodinamico/grid.svg); background-size: 500px;">
				<div class="intestazione" >
					<img
						src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png"
						class="logo1" alt="logo leone"> 
					<img
						src="<%=BaseUrl%>/risorse/foto/logo/sbgold-logo1.png"
						class="logo2" alt="Carati"> 
					<img
						src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png"
						class="logo3" alt="logo leone">
				</div>


				<!-- PAGINA DI LOGN-->
				<div class="login-box">



					<h2>Login</h2>
					<form action= "<%=request.getContextPath() + "/LoginServlet"%>" method="get">
						<div class="user-box">
							<input type="text" name="<%=FinalConstants.INPUT_USR%>"
								required=""> <label for="">Username</label>
						</div>

						<div class="user-box">
							<input type="password" name="<%=FinalConstants.INPUT_PASS%>"
								required=""> <label for="">Password</label>
						</div>

						<%
						if (messaggio != null) {
						%>
						<p style="color: red; text-align: center;"><%=messaggio%></p>
						<%
						}
						%>




						<div>
							<button class="btn-5">
								<span></span> <span></span> <span></span> <span></span> Accedi
							</button>

							<div>
								<a
									href="<%=BaseUrl%>/jsp/Register.jsp"
									class="forgot__pass">Registrati</a>
							</div>
							<br> <br>
							<div>
								<a href="#" class="forgot__pass">Password dimenticata?</a>
							</div>
					</form>
				</div>


			</div>
	</div>
	<section></section>
	<!-- SCROLL BAR-->
	<div id="progressbar"></div>
	<div id="scrollpath"></div>
	<div id="percentuale"></div>




	<!-- CODICE DI BACKGROUND-->
	<script type="text/javascript">
            const light = document.querySelector(".light");
            const grid = document.querySelector("#hex-grid");

            grid.addEventListener("mousemove", (e)=>{
                light.style.left =  e.clientX + "px"; /*'${e.clientX}px';*/
                light.style.top = e.clientY + "px"; /* '${e.clientX}px'; */
            });
        </script>
	<script src="../JS/scripts.js"></script>


	<!--CODICE DISCROLLBAR GOLD-->
	<script type="text/javascript">
            var progressbar = document.getElementById("progressbar")
            var percentuale = document.getElementById("percentuale")

            var totalHeinght = document.body.scrollHeight - window.innerHeight;
            window.onscroll = function(){
                var progress  = (window.pageYOffset / totalHeinght)*100;
                progressbar.style.height = progress + "%";
                
            }
        </script>

</body>
</html>