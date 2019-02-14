<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1> This is my test Page!</h1>
<form method="get" action=modetview.html>
	
	Author : 
	<input type="text" name="author">
	Title : 
	<input type="text" name="postTitle">
	<%-- ${keyword}  --%>	
				
		<div class="btn-group">
     		<c:forEach var="img" items="${urls}" >                                                                   	
            		<img height ="" width = "" src="${img.url}">
                 <input type="radio" class="chosen_image" name="chosen_image" value="${img.url}">
        		</c:forEach>
        	</div>
	
	
	Text:
	<textarea rows="60" cols="100" name="postText"></textarea> 
	<!-- email: <input type="text" name="email"> -->

	<!-- <p>Checkout the first article <a href="modetview.html">ICI!</a> -->
	
	<input type="submit" value="create post">

</form>
