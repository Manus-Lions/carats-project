<%@page import="java.time.LocalDateTime"%>
<%@page import="com.social.ProgettoFinaleSocial.utils.FinalConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<% int Autore_id =  (int) request.getAttribute(FinalConstants.INPUT_IDAUTORE);  %>

</head>
<body>

<form action="<%= request.getContextPath() + "/AddPostServlet" %>" method="GET">
                <div class="">
                    <input type="email" alt="email"name="<%=FinalConstants.INPUT_TITOLOPOST%>" required="">
                    <label for=""> Titolo</label>
                </div>

                <div class="">
                    <input type="text"alt="password" name="<%=FinalConstants.INPUT_TESTOPOST%>" required="" >
                    <label for="">Testo</label>
                </div>
               
              
               
                
                
              
           
                
                <input type="submit"> 
                
              </form>
		
		


</form>

<textarea name="<%=FinalConstants.INPUT_TESTOPOST%>" form="usrform">Inserisci testo...</textarea>

BENVENUTO!




</body>
</html>