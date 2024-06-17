<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<style type="text/css">
body {
	text-align: center;
}

table {
	border-collapse: collapse;
	margin: 0 auto;
}

th, td {
	padding: 10px;
}

input {
	padding: 10px;
}

tfoot {
	text-align: center;
}
.label {
    color: #000;
}
</style>
</head>
<body>
	<h1 align="center" style="color: green">OBS Test</h1>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="item_list.html">Item <span class="sr-only">(current)</span></a></li>
					<li><a href="inventory_list.html">Inventory</a></li>
					<li><a href="order_list.html">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h1 align="center">Edit Item</h1>
	<s:if test="sm != null">
		<font color="green"> <s:property value="sm" />
		</font>
	</s:if>
	<s:if test="em != null">
		<font color="red"> <s:property value="em" />
		</font>
	</s:if>
	<s:form action="updateItem" method="post">
		<s:hidden value="%{item.id}" name="item.id" />
		<s:textfield name="item.name" label="Item Name" />
		<s:textfield name="item.price" label="Item Price" />
		<s:submit value="Update"></s:submit>
	</s:form>
	
	<a href="item_list.html">Back</a>
</body>
</html>
