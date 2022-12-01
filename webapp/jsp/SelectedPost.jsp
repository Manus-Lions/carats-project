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
<%@ include file="../CSS/selectedPost.css"%></style>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<%@page import="java.time.LocalDateTime"%>

<%

Map<Integer, String> imagePath = (Map<Integer, String>  ) request.getAttribute(FinalConstants.RISULTATI_RICERCA_IMMAGINI);

List<Post> posts = (List<Post>) request.getAttribute(FinalConstants.INPUT_POSTS);
String messaggio = (String) request.getAttribute(FinalConstants.MESSAGGIO1);



//if (posts.size == 0) {nessun post} else { tutto ci� che segue
	
Utente utente = (Utente) session.getAttribute(FinalConstants.INPUT_UTENTE);

boolean segnalino = false;

if (posts.get(0).getAutore().getId() == utente.getId()) {
	segnalino = true;
}


%>

</head>
<body>

	<%
	String BaseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String LoginUrl = BaseUrl + "/jsp/Login.jsp";

	
	
	%>

	<!--BACK GROUND-->
	<div class="container">
		<header id="hex-grid">
			<div class="light"></div>
			<div class="grid"
				style="background: url(<%= BaseUrl%>/risorse/foto/sfondodinamico/grid.svg)repeat; background-size: 500px;">

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
							<a href="HomeServlet">HOME</a> 
							<a href="MyPostServlet">MY POST</a> 
							<i class="fa-sharp fa-solid fa-magnifying-glass fa-xl"></i>
							<form action="SortByUserServlet">
								<input class="serchBar" type="text" placeholder="Cerca"
									name="<%=FinalConstants.CERCA_UTENTE%>">
							</form>
							<a href="LoginServlet" class = "logout">LOGOUT</a> 
						</div>

			
					</div>
				</nav>


				<% if(request.getAttribute(FinalConstants.MESSAGGIO1) != null){ %>
				<%=messaggio%>
				<% } %>
				
				<%
				if(posts != null){
				for (Post p : posts) {
				%>
					<div class="postBody" data-aos="zoom-in-up" data-aos-delay="50"
						data-aos-duration="1000">
						<h2 class = "golden-text"><%=p.getAutore().getNome()+ " " + p.getAutore().getCognome()%></h2>
						<br>
						<h5 class = "data-post">    
							<%=DateUtils.convertToDateFormat(p.getData())%>
						</h5>
						<br><br><br>
						<h3><%=p.getTitolo()%></h3>
						
						
						<span class="likeUnlike"> 
						<%if(messaggio != null){ %>
						<%=messaggio%>
						<%} %>
							<form action="AddPostLike"  method = "get" class="addLike">
								<button  <% if (segnalino) {%>style = "pointer-events:none;"<%} %>
								
								class="like-btn" name = "likeBtn" value = "<%=p.getId()%>,true">
									<div class="content0">
										<span class="heart0"></span>
										<span class="text0"></span>
										
										<% 
											int likeCount = 0;
											for(Likes l : p.getListaLikePost()) {
												if (l.getGoodBad()) {
													likeCount++;
												}
											}
										%>
										<span class="numb0"><%=likeCount%></span>
									</div>
								</button>		
							<!-- DISLIKE -->				    
								<button <% if (segnalino) {%>style = "pointer-events:none;"<%} %>
								 class="dLike-btn" name = "dislikeBtn" value = "<%=p.getId()%>,false">
									<div class="content1">
										<span class="heart1"></span>
										<span class="text1"></span>
										<% 
											int dislikeCount = 0;
											for(Likes l : p.getListaLikePost()) {
												if (!l.getGoodBad()) {
													dislikeCount++;
												}
											}
										%>
										<span class="numb1"><%=dislikeCount%></span>
									</div>
								</button>
							</form>
						</span>
							<%
							if (imagePath != null && imagePath.get(p.getId()) != null) {
							%>
								<div class="imgPost">
									<img src="<%=imagePath.get(p.getId())%>">
								</div>
								<p>
									<%=p.getTesto()%>
								</p>
						<div class="sottoTitolo">
						<div class = "modifica-btn">
							<%
								if(segnalino){ %>
							<form id="modifica" action="PostServlet" method="get">
								<input type="text" name="selectPost" value="<%= p.getId()%>"
									hidden>
								<button class="btn-5">
									<span></span><span></span><span></span><span></span>Modifica
								</button>
							</form>
							<%  } %>
						</div>
						<div class = "delete-btn">
							<%
						
								if (segnalino) { %>
							<form action="PostServlet" method="get">
								<input type="text" name="<%=FinalConstants.INPUT_DELETE%>"
									value="<%= p.getId()%>" hidden>
								<button class="btn-5">
									<span></span><span></span><span></span><span></span>Cancella
								</button>
							</form>
							<%  } %>
						</div>
					</div>
				</div>
							<%
							}
							%>
				<%
				}
				}
				%>
				

					<!--
				
				 BARRA SINISTRA -->

					
					<nav class="navSx">
        <%							
        if (request.getAttribute(FinalConstants.INPUT_COMMENTI) != null) {
            List<Commento> commenti = (List<Commento>) request.getAttribute(FinalConstants.INPUT_COMMENTI);
            for (Commento c: commenti) {
                %>
                <div class="sideCommento">
                    <div class="titoloCommento">
                            <h2 class="golden-text"><%=c.getCommenter().getNome() + "  " + c.getCommenter().getCognome()%></h2>
                            <p class = "data-com"> <%=DateUtils.convertToDateFormat(c.getData())%><p>
                        </div>
                        <div class="testoCommento">
                            <p>
                                <%=c.getTesto()%>
                            </p>
                        </div>
                </div>
                <%
            }
        }
    %>
    </nav>

					<!-- BARRA DESTRA -->

					<nav class="navDx">
						<ul class="side">
						
						<!-- MODIFICA POST -->
						<% if (segnalino) {%>

							<li class="postInvio" data-aos="zoom-in-up" data-aos-delay="50"
								data-aos-duration="1000" style="visibility: hidden" id="modificaForm">

								<form method="get" action="ModificaServlet">
									<input type="text" name="<%=FinalConstants.INPUT_TITOLOPOST%>"
										value="<%=posts.get(0).getTitolo()%>" id="titolo">
									<br>
									<br>
									<textarea id="testo" style="height: 250px;" name="<%=FinalConstants.INPUT_TESTOPOST%>"><%=posts.get(0).getTesto()%></textarea>
									<input type="text" name="<%=FinalConstants.INPUT_USR%>"
										value="<%= posts.get(0).getId()%>" hidden> 
										<br> 
										<button class="btn-5">
										<span></span><span></span><span></span><span></span>Modifica
									</button>
								</form>
							</li>
						<%} %>
						
						
						
							<!-- SCRIVI COMMENTO -->
							
							<% if (!segnalino) {%>
							<li class="postInvio">		
				
								<form action="PostServlet" method="get" class="creacom">
								 <input type="text" name="<%=FinalConstants.SIMPLE%>"
										value="<%=posts.get(0).getId()%>" hidden>
									<div>
										<h3>Stai commentando come:</h3>
										<br>
										<h3 class = "golden-text" style = "text-align: center;"><%=utente.getNome() + "  " + utente.getCognome()%></h3>
										<br>
										<br>
										<textarea id="testo"
											name="<%=FinalConstants.INPUT_TESTOCOMMENTO%>"
											style="height: 250px;" placeholder="Scrivi commento..."></textarea>
										<br>
										<br> 
										<button class = "btn-5"><span></span><span></span><span></span><span></span>Pubblica</button>

									</div>
								</form>
							</li>
							<%}%>
						</ul>
					</nav>

					<!-- DIV GRID BACKGROUND -->

				</div>
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
    
    <!-- MOPDIFICA POST JS -->
    
    <script type="text/javascript">
    
    
     let form = document.getElementById("modifica");

     form.addEventListener("submit", function (event) {

        event.preventDefault();
       
        document.getElementById("modificaForm").style.visibility = "visible";

		
       
        })
    </script>
</body>

</html>