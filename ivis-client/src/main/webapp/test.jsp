<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 06.07.15
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
  <!-- Define a function that will be executed once the data is received. -->
  <script> function test(data) { alert(data); } </script>

  <!-- Request sent via a script tag, once finished apiStatus(data) will be executed. -->
  <script src="http://localhost:8080/ivis/mock/jsonp?callback=test"></script>
  <script>Spring</script>
</head>
<body>
asdfasdfasdf
</body>
</html>
