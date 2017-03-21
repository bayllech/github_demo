<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/repo/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>姓名</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="nameList" var="m" status="vs">
                <tr>
                    <td>${vs.index} : <s:property value="m"></s:property></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>

        <h1><s:property value="user.username" default="xxx"></s:property> </h1>
        <s:if test="name == 'tom'">
            <h1>Tom</h1>
        </s:if>
        <s:elseif test="name == 'jack'">
            <h1>jack</h1>
        </s:elseif>
        <s:else>
            <h1>Hello</h1>
        </s:else>
    </div>


</body>
</html>
