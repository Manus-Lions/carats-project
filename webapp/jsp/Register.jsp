<%@page import="com.social.ProgettoFinaleSocial.utils.FinalConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<title>Registrazione</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
<%@ include file="../CSS/registrati.css"%></style>

</head>
<body>

	<%   
 
 	String BaseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
 	String LoginUrl = BaseUrl + "/jsp/Login.jsp";
 	
 	String messaggio =  (String) request.getAttribute(FinalConstants.MESSAGGIO);
 	
 %>



	<!-- BACKGROND-->
	<div class="container">
		<header id="hex-grid">
			<div class="light"></div>
			<div class="grid" style = "background: url(<%= BaseUrl%>/risorse/foto/sfondodinamico/grid.svg); background-size: 500px;"></div>
			<div class="intestazione">
				<img src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png"
					class="logo1" alt="Logo leone">
				<!--<img src="../risorse/foto/logo/sbgold-logo.svg" class="logo2" alt="">-->
				<img src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png"
					class="logo3" alt="Logo leone">
			</div>

			<!-- PAGINA DI LOGN-->
			<div class="login-box">

				<%    if(messaggio != null) {
				%>
				<h3 style="color: red;"><%= messaggio %></h3>
				<%
					
		} %>



				<h2 alt="registrati">Registrati</h2>
				<form action="<%= request.getContextPath() + "/RegisterServlet" %>"
					method="GET">
					<br>
					<div class="user-box">
						<input type="text" alt="nome"
							name="codice" required=""> <label
							for="">codice  carati</label>
					</div>
					
					<div class="user-box">
						<input type="email" alt="email"
							name="<%=FinalConstants.INPUT_USR%>" required=""> <label
							for="">Email</label>
					</div>

					<div class="user-box">
						<input type="password" alt="password"
							name="<%=FinalConstants.INPUT_PASS%>" required=""> <label
							for="">Password</label>
					</div>
					<div class="user-box">
						<input type="password" alt="Ripeti password" name="ciao"
							required=""> <label for="">Ripeti password</label>
					</div>
					<div class="user-box">
						<input type="text" alt="nome"
							name="<%=FinalConstants.INPUT_NAME%>" required=""> <label
							for="">Nome</label>
					</div>
					<div class="user-box">
						<input type="text" alt="Cognome"
							name="<%=FinalConstants.INPUT_COGNOME%>" required=""> <label
							for="">Cognome</label>
					</div>




					<button class="btn-5">
						<span></span> <span></span> <span></span> <span></span> Registrati
					</button>
					<div>
						<a href="<%=BaseUrl%>/jsp/Login.jsp" class="forgot__pass">Torna
							al login </a>
					</div>
			</div>
			</form>
	</div>
	</div>
	<section></section>
	<!-- SCROLL BAR-->
	<div id="progressbar"></div>
	<div id="scrollpath"></div>
	<div id="percentuale"></div>
	<section></section>




	<!-- CODICE DI BACKGROUND-->
	<script type="text/javascript">
            const light = document.querySelector(".light");
            const grid = document.querySelector("#hex-grid");

            grid.addEventListener("mousemove", (e)=>{
                light.style.left =  e.clientX + "px"; /*'${e.clientX}px';*/
                light.style.top = e.clientY + "px"; /* '${e.clientX}px'; */
            });
        </script>



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


