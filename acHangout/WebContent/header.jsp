
<%
	String login = "Login";
	if (session.getAttribute("name") != null && session.getAttribute("pass") != null) {
		login = "Hello, " + (String) session.getAttribute("name");
	}
%>
<a href="index.jsp"> <img src='Resources/1.png' style='float: left;'
	width='380' height='100'>
</a>

<div style='display: none; padding: 10px;' id='google_translate_element'></div>

<div class="topnav" id="myTopnav">
	<a class="active" href="index.jsp">Home</a> <a href="#contact">Messenger</a>
	<a href="#contact">Help&nbsp;<i class="fa fa-question-circle-o"></i></a>
	&emsp;
	<%
		if (login == "Login") {
	%>
	<a href="signup.jsp" style="float: right">Sign up</a>
	<%
		} else {
	%>

	<a href="logout" style="float: right">Logout</a>
	<%
		}
	%>
	<a href="login.jsp" style="float: right"> <%=login%>
	</a> <a href="#" style="float: right" onclick="open_translate(this)"
		title="Google Translate"><i class="fa fa-globe"></i></a>

	<div class="search-container">
		<form action="search.jsp">
			&emsp; &emsp; &emsp; &emsp; <input type="text" placeholder="Search.."
				name="search">
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>
</div>

<script type="text/javascript">
	function open_translate(elmnt) {
		var a = document.getElementById("google_translate_element");
		if (a.style.display == "") {
			a.style.display = "none";
			elmnt.innerHTML = "<i class='fa fa-globe'></i>";
		} else {
			a.style.display = "";
			elmnt.innerHTML = "<i class='fa fa-times-circle-o'></i>";
		}
	}

	function googleTranslateElementInit() {
		new google.translate.TranslateElement({
			pageLanguage : 'en',
			layout : google.translate.TranslateElement.InlineLayout.SIMPLE
		}, 'google_translate_element');
	}
</script>

<script type="text/javascript"
	src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit">
	
</script>
