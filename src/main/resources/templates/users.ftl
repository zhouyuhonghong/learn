<html>
<head></head>
<body>
<table>
<#list users as user>
<tr>
<td>${user.username} !</td><td>${user.password} !</td>
 </#list>
</table >
</body>
</html>