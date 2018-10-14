<meta http-equiv="cache-control" content="no-cache"/>

<link rel="stylesheet" type="text/css" href="jqueryslidemenu.css" />

<!--[if lte IE 7]>
<style type="text/css">
html .jqueryslidemenu{height: 1%;} /*Holly Hack for IE7 and below*/
</style>
<![endif]-->

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="jqueryslidemenu.js"></script>

<style>
table td,th {
	padding-right: 10px;
	padding-left: 10px;
}

table td.number {
	text-align: right;
}
</style>

<script>
	function isNumber(str) {
		if(str==null || str=='' || isNaN(str))
			return false;
		else
			return true;
	}
	
	function isDate(str) {
		if(str==null || str=='')
			return false;
		var d = str.split('/');
		var date = new Date(d[2],d[0]-1,d[1]);
		if(date.toString()=='Invalid Date' || date.getDate()!=d[1])
			return false;
		else
			return true;
	}
</script>
