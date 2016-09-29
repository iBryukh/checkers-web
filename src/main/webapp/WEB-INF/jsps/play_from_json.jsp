<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkers KMA | From json</title>
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/checkers_game.js"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
    <a href="/">Main</a>
    <textarea id="game_json">{}</textarea>
    <button id="play">Play</button>
<script>
    $(function(){
        $('#play').click(function(){
            play($('#game_json').val());
        });
    });
</script>
</body>
</html>
