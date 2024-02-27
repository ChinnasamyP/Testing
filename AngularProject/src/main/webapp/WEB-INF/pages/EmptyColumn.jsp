<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empty Column</title>
<link href="<c:url value='css/bootstrap-multiselect.css" rel="stylesheet'/>">
  <link href="<c:url value='css/multi_select.css" rel="stylesheet'/>">
</head>
<body>
<script src="<c:url value='js/bootstrap.min.js'/>"></script>
<script src="<c:url value='js/bootstrap-multiselect.min.js' />"></script>
		<form action="/uploadexcel" method ="POST">
		<c:forEach var="Values" begin = "1" end = "5">  
   		Values: <c:out value="${Values}"/><p>  
		</c:forEach>
		<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2 sub_col slt_prd">
								<div class="form-group">
									<div class="input-group">                       
										<span class="input-group-addon"><span class="glyphicon glyphicon-list-alt"></span></span>
										<select  class="form-control" id="product" multiple="multiple" name="product" >
											<c:forEach items="${Values}" var="product">
									<option value="${product}"><c:out value="${product}" /></option>
								</c:forEach>
										</select>
									</div>
								</div>
							</div>
										</form>
</body>
</html> 