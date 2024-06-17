<%-- 
    Document   : insert
    Created on : Aug 19, 2017, 9:43:53 AM
    Author     : cyclingbd007
--%>

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
					<li><a href="item_list.html">Item</a></li>
					<li class="active"><a href="inventory_list.html">Inventory
							<span class="sr-only">(current)</span>
					</a></li>
					<li><a href="order_list.html">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h1 align="center">Insert Inventory</h1>
	<s:if test="sm != null">
		<font color="green"> <s:property value="sm" />
		</font>
	</s:if>
	<s:if test="em != null">
		<font color="red"> <s:property value="em" />
		</font>
	</s:if>
	<s:form action="insertInventory" method="post">
		<s:select label="Item" name="inventory.itemId" headerKey="0"
			headerValue="Select Item" list="%{itemList}" listKey="%{id}" listValue="%{id+' - '+name}" />
		<s:textfield name="inventory.qty" label="Inventory Qty" />
		<s:select label="Inventory Type" name="inventory.type"
			list="{'T','W'}" />
		<s:submit value="Save"></s:submit>
	</s:form>

	<a href="inventory_list.html">Back</a>
</body>
</html>
