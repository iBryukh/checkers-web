<%--
  Created by IntelliJ IDEA.
  User: oleh_kurpiak
  Date: 26.05.16
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/api/storage/" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"><br />
        Project: <input type="text" name="project"><br />
        Version <input type="text" name="version"><br />
        File name: <input type="text" name="file_name"> <br />
        <input type="submit">
    </form>
</body>
</html>
