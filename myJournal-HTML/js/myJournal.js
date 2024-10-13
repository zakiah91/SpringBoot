
	let mainUrl = "http://localhost:8080";



	function getContent(){
		
		let url = mainUrl + "/journal";
		
		//container
		$("#history").css("display","block");
		$("#write").css("display","none");
		
		//menu
		$("#menu1").removeClass("active");
		$("#menu2").addClass("active");
		
		$.get(url,function(data){
			//console.log(data);
			//console.log(data[0].content);
			//console.log(data.length);
			
			$("#table_content").empty();
			
			let myTable = "<table class=\"table\">";
			
			myTable += "<thead>"+
							"<tr>" +
								"<th class=\"col-md-1\">Action</th>" +
								"<th class=\"col-md-1\">#</th>" +
								"<th class=\"col-md-2\">Date</th>" +
								"<th>Content</th>" +
							"</tr>" +
						"</thead>";
			
			myTable += "<tbody>";
			
			for(let i=0;i<data.length;i++){
				myTable += "<tr id=\""+i+"\">" +
								"<td><button onclick=\"removeCell("+i+")\" class=\"btn btn-warning\">Delete</button></td>"+
								"<td>"+i+"</td>"+
								"<td>"+data[i].date+"</td>" +
								"<td>"+data[i].content+"</td>"+
							"</tr>";
			}
			
			myTable += "</tbody>";
									
			myTable += "</table>";
			
			$("#table_content").append(myTable);
		});
		
	}
	
	
	function viewForm(){
		
		//container
		$("#write").css("display","block");	
		$("#history").css("display","none");
		
		//menu
		$("#menu1").addClass("active");
		$("#menu2").removeClass("active");
	
		console.log(this);
	}
	
	
	function submitForm(){
		
		console.log($("input[type='date']").val());
		
		console.log($("textarea").val());
		
		let path = mainUrl + "/journal/post";
		
		let data = {"date": $("input[type='date']").val(),
		            "content": $("textarea").val()};
		
		$.ajax({
			type: "POST",
			crossDomain: true,
			dataType: "json",
			headers: {
				"Accept" : "application/json"
			},
			url: path,
			data: data,
			success: function(response){
				console.log("OK");
			},
			error: function(error){
				console.log("NG");
			}
		});
		
	}
	
	
	function removeCell(i){
		
		let path = mainUrl + "/journal/delete";
		
		let tRow = $("table tr")[i+1];
		
		console.log(tRow.cells[2].innerHTML);
		
		let data = {"date":tRow.cells[2].innerHTML};
		
		for(let i=0;i<tRow.length;i++){
			console.log(tRow[i]);
		}
		
		$.ajax({
			type: "POST",
			crossDomain: true,
			dataType: "json",
			headers:{
				"Accept": "application/json"
			},
			url: path,
			data: data,
			success: function(response){
				console.log("OK");
			},
			error: function(error){
				console.log("NG");
			}
		});
		
		
		$("#"+i).remove();
	}
	
	
	