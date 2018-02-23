function GetRequestPara() {
	var url = location.search; // 获取url中"?"符后的字串
	var RequestPara = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (let i = 0; i < strs.length; i++) {
			RequestPara[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return RequestPara;
}

function ajaxrequest(){
	let request = GetRequestPara();
	// console.log(request['name']);
	if(request['page']==null){
		request['page'] = "1";
	}
	if(request['sort']==null){
		request['sort'] = "yearasc";
	}

	let parameter = {
	// name : request['name'],
	// password : request['password']
		page: request['page'],
		sort: request['sort'],
		title: request['title'],
		year: request['year'],
		director: request['director'],
		star_name: request['star_name'],
		genre: request['genre'],
		title_first_character: request['title_first_character'],
	}
	

	$.ajax({

		type : "GET",

		url : "/Movie_Website/movie",

		data : parameter,

		dataType : "json", 

		success : function(value) {

			// console.log(value);
			for(var i=0; i< value.length;i++)
			{
				
				$('#movielist').append(`
				<div class="row featurette">
               <div class="col-md-5">
               <img class="featurette-image img-fluid mx-auto" src="images/2.jpg" width="280" height="280"   alt="Generic placeholder image">
               </div>
               <div class="col-md-7 order-md-2">
               <h2 class="featurette-heading"><font size="2" face="Verdana">Title:&nbsp&nbsp  <a onclick="moviedetails(event)" href="#">${value[i].title}</a><br><span class="text-muted">ID:&nbsp&nbsp${value[i].movieid}<br>
               Year:&nbsp&nbsp${value[i].year}<br>Director:&nbsp&nbsp${value[i].director}<br>Stars:&nbsp&nbsp${value[i].stars}<br>Genres:&nbsp&nbsp${value[i].genres}<br> 
				RATE:&nbsp&nbsp${(value[i].rate).toFixed(1)}
                <br>
				Price:  &nbsp&nbsp $15.99<br>
				<br>
				</h2>
				<button type="button" class="btn btn-primary btn-lg " onclick="add_to_cart('${value[i].movieid}','${value[i].title}')" aria-haspopup="true" aria-expanded="false">Add to Cart</button>
               <p class="lead"></p>
          </div> 
            </div>

			<hr class="featurette-divider">
				
				`);
				$('#Total_Pages').html("Total Pages: "+value[0].total_pages);
				let tdArr = $("#mytable").children();
				for (let i = 0; i < tdArr.length; i++) {
					if(i!=0 & i!=8){
						if($(tdArr[i]).children(':first').html()>value[0].total_pages || $(tdArr[i]).children(':first').html()<1){
							$(tdArr[i]).children(':first').hide();
						}else{
							$(tdArr[i]).children(':first').show();
						}
					}else if(i==0){
						if( Number.parseInt($("#Current_Page").html()) <= 1){
							$(tdArr[0]).children(':first').hide();
						}else{
							$(tdArr[0]).children(':first').show();
						}
					}else if(i==8){
						if( Number.parseInt($("#Current_Page").html()) >= value[0].total_pages ){
							$(tdArr[8]).children(':first').hide();
						}else{
							$(tdArr[8]).children(':first').show();
						}
					}
				}
				
				
				
				
			}

		}

	});

}


function add_to_cart(movieid,title){
		

	var temp = prompt("Please enter the amount(From 1 to 5):", "1");
	if(temp == "1"||"2"||"3"||"4"||"5"){
		var amount = temp;
	}
	
	// console.log(movieid,title);
	var movie_info = {
			id: movieid,
			title: title,
			amount: amount
	}
	
	$.ajax({

		type : "GET",

		url : "/Movie_Website/cart",

		data : movie_info,

		dataType : "json", 

		success : function(value) {

		}
	});

}
function moviedetails(event){
	
	event.preventDefault();
	let title = $(event.target).html();
	url= "Movie_List.html?title_first_character="+title;
	console.log(url);
	window.history.replaceState(null,null,url);
	$("#movielist").children().remove();
	ajaxrequest();
    
	$("#mytable").css('display','none');
	$("#Total_Pages").css('display','none');
	$("#Current_Page").css('display','none');
	$("#sort").css('display','none');
	$("#footer").css('display','none');
}



