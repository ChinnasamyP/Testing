<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FileUpload</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<script type="text/javascript">
	function showAlert(){
		alert(${values});
		}
</script>

<body>
	<div class="container ">
	<div class="row p-5 m-5">
	<form action="/uploadexcel" method ="POST" enctype="multipart/form-data">
			<div class="col-lg-4 p-2">
				<input type="file" name="file">
			</div>
			<div class="col-lg-4">
				<button type="submit" class="btn btn-primary p-2">SAVE</button>
			</div>
			
	</form>
	</div>
	</div>
</body>
</html>