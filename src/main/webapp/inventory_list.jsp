<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OBS Test</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>
</head>
<body>
	<h1 align="center" style="color: green">OBS Test</h1>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="item_list.html">Item</a></li>
					<li class="active"><a href="inventory_list.html">Inventory <span class="sr-only">(current)</span></a></li>
					<li><a href="order_list.html">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h3 align="center" style="color: blue">Inventory List</h3>
	<p align="center">
		<s:if test="sm != null">
			<font color="green"> <s:property value="sm" />
			</font>
		</s:if>
		<s:if test="em != null">
			<font color="red"> <s:property value="em" />
			</font>
		</s:if>
	</p>
	<a class="btn btn-primary" href="inventory_insert.html" role="button">Add
		Inventory</a>
	<table id="datatable" class="display" style="width: 100%">
		<thead>
			<tr>
				<th colspan="5">Inventory List</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>Item ID</th>
				<th>Qty</th>
				<th>Type</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${inventoryList}">
				<tr>
					<td>${row.id}</td>
					<td>${row.itemId}</td>
					<td>${row.qty}</td>
					<td>${row.type}</td>
					<td>
						<a href="inventory_edit.html?id=${row.id}">Edit</a>
						<a onclick="return confirm('Are you sure to delete this inventory?')"
							href="inventory_delete.html?id=${row.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		new DataTable('#datatable', {
			pagingType : 'simple_numbers',
			columnDefs : [ {
				"defaultContent" : "-",
				"targets" : "_all"
			} ]
		});
	</script>
</body>
</html>
