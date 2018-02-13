<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Ghost Game</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="author" content="Daniel Ruiz">

    <link href="http://fonts.googleapis.com/css?family=Kotta+One|Cantarell:400,700" rel="stylesheet" type="text/css">

    <!-- Not required: presentational-only.css only contains CSS for prettifying the demo -->
    <link rel="stylesheet" href="<c:url value="/resources/css/presentational-only.css" />">

    <!-- responsive-full-background-image.css stylesheet contains the code you want -->
    <link rel="stylesheet" href="<c:url value="/resources/css/responsive-full-background-image.css" />">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

    <!-- jQuery Modal -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

    <script type="text/javascript">
        function formHandler() {
            // get the 'play' value from the form
            var play = $('#playInput').val();
            if (play ==='') {
                $('#playInput').focus();
                return;
            }
            $('#letter').val(play);
            $('#gameForm').submit();
        }
        $( document ).ready(function() {
            // Setting the focus to play field text...
            $('#playInput').focus();
            // Presing 'Enter' while in that file triggers the 'click' on the 'playButton'...
            $('#playInput').keypress(function(event){
                if(event.keyCode == 13){
                    $('#playButton').click();
                }
            });

            //If some error, open dialog
            var text = $('#errorText').text();
            if (text.trim()) {
                $('#ex1').modal();
            }

            //When close dialog, clear fields ...
            $('#ex1').on($.modal.BEFORE_CLOSE, function(event, modal) {
                $('#word').text("");
                $('#playInput').val("");
                $('#playInput').focus();
            });
        });
    </script>

</head>
<body>
<nav class="navbar" id="top">
    <div class="inner">
        <p style="margin-top: 6px;"><strong><spring:message code="home.game" /></strong></p>
    </div>
</nav>

<form:form id="gameForm" action="/ghost" method="post" commandName="form" style="display: none;">
    <form:input path="letter" type="hidden" id="letter" name="letter" />
</form:form>

<header class="container">
    <section class="content">
        <h1 id="word">${gameResponse.string}</h1>
        <p class="sub-title"><strong><spring:message code="home.enter" /></strong> <br /><spring:message code="home.press" /></p>
        <input id="playInput" maxlength="1" type="text" value="${gameResponse.play}" />
        <p><a class="button" id="playButton" href="#" onclick="formHandler();"><spring:message code="home.button" /></a></p>

        <!-- Modal HTML embedded directly into document -->
        <div id="ex1" class="modal">
            <p style="color: #464646; margin: 5px; text-align: justify; word-wrap: break-word;" id="errorText">${gameResponse.message}</p>
        </div>

    </section>
</header>
</body>
</html>