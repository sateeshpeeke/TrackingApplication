<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='css/fullcalendar.min.css' rel='stylesheet' />
<link href='css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='js/moment.min.js'></script>
<script src='js/jquery.min.js'></script>
<script src='js/fullcalendar.min.js'></script>

<script>
	$(document).ready(function() {
		var leaves;
		var calendar = $('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			selectable : false,
			selectHelper : true,
			select : function(id,start, end, title) {
				calendar.fullCalendar('renderEvent', {
					id   :  id,
					title : title,
					start : start,
					end : end
				}, true // make the event "stick"
				);
				calendar.fullCalendar('unselect');
			},
			editable : true,
			events : function(start, end, timezone, callback) {
				$.ajax({
					url : '${pageContext.request.contextPath}/leavecalender',

					success : function(data) {
						console.log(data);
						var events = [];
						leaves = JSON.parse(data);
						
						events.push({
							id    :leaves.id,
							title : leaves.title,
							start : leaves.start,
							end : leaves.end
						}); 

						/*  $.each(JSON.parse(data), function(index) {
						 	 leaves=JSON.parse(data);
						      events.push({
						         title: leaves.title,
						         start:leaves.start,
						         end:  leaves.end
						     }); 
						 });  */
						callback(events);

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus + " - " + errorThrown);
					}
				});
			}
		});
	});
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>