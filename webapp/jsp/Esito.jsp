<%@page import="java.util.Map"%>
<%@page import="com.social.ProgettoFinaleSocial.utils.DateUtils"%>
<%@page import="com.social.ProgettoFinaleSocial.utils.FinalConstants"%>
<%@page import="com.social.ProgettoFinaleSocial.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />

<style>
<%@include file="../CSS/home.css"%></style>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<%@page import="java.time.LocalDateTime"%>

</head>
<body>
	<%
	String esito = (String) request.getAttribute(FinalConstants.MESSAGGIO);
	%>

	<%
	String BaseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String LoginUrl = BaseUrl + "/jsp/Login.jsp";
	%>

	<!--BACK GROUND-->
	<div class="container">
		<header id="hex-grid">
			<div class="light"></div>
			<div class="grid"
				style="background: url(<%=BaseUrl%>/risorse/foto/sfondodinamico/grid.svg) repeat; background-size: 500px;">

				<!-- NAVBAR CENTRALE -->

				<nav>
					<div class="navBar">
						<div class="indicator"></div>
						<!--  	<div class="logo">
							<img src="<=BaseUrl%>/risorse/foto/logo/sbgold-logo.svg" alt="logo" class="logoImg">
						</div> -->

						<!-- 
						<div class="logoSx">
							<img src="<=BaseUrl%>/risorse/foto/logo/lion-logo.png"
								alt="logo" class="logoImgSx">
						</div>
						<div class="logoDx">
							<img src="<=BaseUrl%>/risorse/foto/logo/lion-logo.png"
								alt="logo" class="logoImgDx">
						</div>
						-->

						<div class="navBarMenu">
							<a href="HomeServlet">HOME</a> <a href="MyPostServlet">MY
								POST</a> <i class="fa-sharp fa-solid fa-magnifying-glass fa-xl"></i>
							<form action="SortByUserServlet">
								<input class="serchBar" type="text" placeholder="Cerca"
									name="<%=FinalConstants.CERCA_UTENTE%>">
							</form>
							<a href="LoginServlet" class="logout">LOGOUT</a>
						</div>
						<div class="Post">
						<%=esito%>
						</div>

					</div>
				</nav>
				<nav class="navSx">
					<div class="logoSx">
						<img src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png" alt="logo"
							class="logoImgSx">
					</div>
					<div class="logoSxCar">
						<img src="<%=BaseUrl%>/risorse/foto/logo/sbgold-logo1.png"
							alt="logo" class="logoImgSx">
					</div>
				</nav>

				<!-- BARRA DESTRA -->

				<nav class="navDx">
					<div class="logoDx">
						<img src="<%=BaseUrl%>/risorse/foto/logo/lion-logo.png" alt="logo"
							class="logoImgDx">
					</div>
					<div class="logoDxCar">
						<img src="<%=BaseUrl%>/risorse/foto/logo/sbgold-logo1.png"
							alt="logo" class="logoImgSx">
					</div>
				</nav>

				<!-- DIV GRID BACKGROUND -->

			</div>
		</header>
	</div>

	<!-- SCROLL BAR -->

	<div id="progressbar" style="z-index: 444;"></div>
	<div id="scrollpath"></div>
	<div id="percentuale"></div>

	<!-- ANIMAZIONI -->

	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>
        AOS.init();
    </script>

	<!-- BACKGROUND -->

	<script type="text/javascript">
        const light = document.querySelector(".light");
        const grid = document.querySelector("#hex-grid");

        grid.addEventListener("mousemove", (e) => {
            light.style.left = e.clientX + "px"; /*'${e.clientX}px';*/
            light.style.top = e.clientY + "px"; /* '${e.clientX}px'; */
        });
    </script>

	<script type="text/javascript">
        var progressbar = document.getElementById("progressbar")
        var percentuale = document.getElementById("percentuale")

        var totalHeinght = document.body.scrollHeight - window.innerHeight;
        window.onscroll = function () {
            var progress = (window.pageYOffset / totalHeinght) * 100;
            progressbar.style.height = progress + "%";
            /* percentuale.innerHTML = "" + Math.round(progress) + "%";*/
        }
    </script>

</body>
</html>