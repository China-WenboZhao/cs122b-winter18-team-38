/**
 * 
 */
$.ajax({

	type : "GET",

	url : "/Movie_Website/cart",

	dataType : "json", 

	success : function(cart_content) {

		//console.log(value);
		for(var i=0; i< cart_content.length;i++)
		{
			
			//amount:${cart_content[i].amount}
			
//			$("#amount").change(function(){
//				
//			});
			$('#content').append(`
			<div class="row featurette">
               <div class="col-md-5">
               
               <img class="featurette-image img-fluid mx-auto" src="images/2.jpg" width="180" height="400"   alt="Generic placeholder image">
               </div>
               <div class="col-md-7 order-md-2">
               <h2 class="featurette-heading"><font size="2" face="Verdana">Title:&nbsp&nbsp  ${cart_content[i].title}<br><span class="text-muted">ID:&nbsp&nbsp${cart_content[i].id}<br>
               
               Quantity:      &nbsp&nbsp      
               <select id="${i}" onchange="choosen_change(${i})" >
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
				<script type="text/javascript">
					$('#${i}').val(${cart_content[i].amount});
				</script>
				<br>
				Price:  &nbsp&nbsp $15.99<br><br>
				</h2>
		
				
				<button type="button" class="btn btn-primary btn-lg " onclick="remove(${i})" aria-haspopup="true" aria-expanded="false"> Remove</button>
				
               <p class="lead"></p>
          </div> 
            </div>

			<hr class="featurette-divider">
			`);
		
		}

	}

});

function choosen_change(index){
	let changed_amount = $('#'+index).val();
//	console.log(value);
	let amount_change = {
			index: index,
			amount: changed_amount
	}
	$.ajax({

		type : "GET",

		url : "/Movie_Website/cart",

		data : amount_change,

		dataType : "json", 

		success : function(value) {

		}
	});
}

function remove(index){
	let amount_zero = {
			index: index,
			amount: "0"
	}
	//console.log("count:"+count);
	$.ajax({

		type : "GET",

		url : "/Movie_Website/cart",

		data : amount_zero,

		dataType : "json", 

		success : function(value) {

		}
	});
	
	location.reload();
	
	
}


