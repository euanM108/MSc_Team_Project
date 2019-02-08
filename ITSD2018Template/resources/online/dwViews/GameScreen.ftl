<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
	<!DOCTYPE html>
<html lang="en">
<head>
<title>Home Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
}

/* Style the body */
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

/* Header/logo Title */
.header {
  padding: 80px;
  text-align: center;
  background: #1abc9c;
  color: white;
}

/* Increase the font size of the heading */
.header h1 {
  font-size: 40px;
}

/* Sticky navbar - toggles between relative and fixed, depending on the scroll position. It is positioned relative until a given offset position is met in the viewport - then it "sticks" in place (like position:fixed). The sticky value is not supported in IE or Edge 15 and earlier versions. However, for these versions the navbar will inherit default position */
.navbar {
  overflow: hidden;
  background-color: #333;
  position: sticky;
  position: -webkit-sticky;
  top: 0;
}

/* Style the navigation bar links */
.navbar a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}




/* Change color on hover */
.navbar a:hover {
  background-color: #ddd;
  color: black;
}

/* Active/current link */
.navbar a.active {
  background-color: #666;
  color: white;
}

/* Column container */
.row {  
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
}

/* Create two unequal columns that sits next to each other */
/* Sidebar/left column */
.side {
  -ms-flex: 30%; /* IE10 */
  flex: 30%;
  background-color: #f1f1f1;
  padding: 20px;
}

/* Main column */
.main {   
  -ms-flex: 70%; /* IE10 */
  flex: 70%;
  background-color: white;
  padding: 20px;
}

/* Fake image, just for this example */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

.card {

  /* Add shadows to create the "card" effect */
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 35%;
  /* 15 pixel rounded corners */
  border-radius: 15px; 
  background: #1abc9c;
  font-family: Helvetica;
}

/* On mouse-over, add a deeper shadow */
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

/* Padding inside the card container */
.container {
  padding: 9px 16px;
}

.cat-buttons{
	display: block;
	width: 100%;
}


img {
  border-radius: 15px 15px 0 0;
}

                .card-container {
                    display: grid;
                    grid-template-columns: 1fr 1fr 1fr;
                    grid-template-rows: 1fr 1fr;
                    grid-template-areas: ". . ."". . .";
                    grid-template-areas:
                        "a b c"
                        "a d e";
                }

                .card {

                    /* Add shadows to create the "card" effect */
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                    transition: 0.3s;
                    width: 90%;
                    /* 15 pixel rounded corners */
                    border-radius: 15px;
                    margin: 5% 5% 5% 5%;
                    background: #1abc9c;
                    font-family: Helvetica;
                }

                .playing-card-1 {
                    grid-area: a;
                    align-self: center;

                }

                .playing-card-2 {
                    grid-area: b;
                    align-self: center;
                    text-align: center;
                }


                .playing-card-3 {
                    grid-area: c;
                    align-self: center;
                    text-align: center;

                }


                .playing-card-4 {
                    grid-area: d;
                    align-self: center;
                    text-align: center;

                }


                .playing-card-5 {
                    grid-area: e;
                    align-self: center;
                    text-align: center;

                }

                table.cat-table {
                    background-color: white;
                    text-align: center;
                    width: 100%;
                    border-collapse: collapse;
                }

                table.cat-table td,
                table.cat-table th {
                    border: 1px solid #AAAAAA;
                    padding: 3px 2px;
                }

                table.cat-table tbody td {
                    font-size: 13px;
                }

                table.cat-table tfoot td {
                    font-size: 14px;
                }




}

/* Responsive layout - when the screen is less than 700px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 700px) {
  .row {   
    flex-direction: column;
  }
}

/* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
@media screen and (max-width: 400px) {
  .navbar a {
    float: none;
    width: 100%;
  }
}


#btn-default, #btn-next, #btn-submit{
	margin: 5px;
	display: block;
	padding: 5px;
}

#btn-next, #btn-submit {
	display: none;
}

#roundNum {
	margin: 5px;
	width: 130px;
}
</style>
</head>
<body>

<div class="header">
  <h1>Top Trumps Game</h1>

    <p>Please select the number of opponents below and press <b>begin</b></p>

</div>

<div class="navbar">

  <a href="/toptrumps/">Exit</a>

</div>

				
				<select id="numberOfPlayers" style="font-size: 20px; padding: 10px;">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
					<button id="btn-default" onclick="setNumberOfPlayers(); getDeck(); numberOfPlayers.style.display='none'; this.style.display = 'none';"
					>Begin!</button>
		
				<p id="roundNum"></p>
				<button id="btn-next"; onclick="nextRound(); this.style.display='none';" this.style.display='none';>Next Turn!</button>
				

	
            <div class="card-container">
                <div class="playing-card-1">
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName">Card Name</h2>
                                <img src="#" alt="picture of spaceship">
                                <div class="cat-buttons">
                                    <button id="1" onclick=setCategory(this.id) style="width: 100%; display: block;">Size
                                    </button>
                                    <button id="2" onclick=setCategory(this.id) style="width: 100%; display: block;">Speed
                                    </button>
                                    <button id="3" onclick=setCategory(this.id) style="width: 100%; display: block;">Range
                                    </button>
                                    <button id="4" onclick=setCategory(this.id) style="width: 100%; display: block;">Firepower
                                    </button>
                                    <button id="5" onclick=setCategory(this.id) style="width: 100%; display: block;">Cargo
                                    </button>
                                </div>

                            </div>
                    </div>
                </div>
                <div class="playing-card-2">
                    AI PLAYER 1
                    <div class="card">
                        <div class="container">
                            <h2 id="cardName">Card Name</h2>
                            <img src="#" alt="picture of spaceship">
                            <div class="cat-tables">
                                <table class="cat-table">
                                    <tbody>
                                        <tr>
                                            <td>cell1_1</td>
                                            <td>cell2_1</td>
                                        </tr>
                                        <tr>
                                            <td>cell1_2</td>
                                            <td>cell2_2</td>
                                        </tr>
                                        <tr>
                                            <td>cell1_3</td>
                                            <td>cell2_3</td>
                                        </tr>
                                        <tr>
                                            <td>cell1_4</td>
                                            <td>cell2_4</td>
                                        </tr>
                                        <tr>
                                            <td>cell1_5</td>
                                            <td>cell2_5</td>
                                        </tr>
                                    </tbody>
                                    </tr>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="playing-card-3">
                    AI PLAYER 2
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName">Card Name</h2>
                                <img src="#" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                    <td>cell1_1</td>
                                                    <td>cell2_1</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_2</td>
                                                    <td>cell2_2</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_3</td>
                                                    <td>cell2_3</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_4</td>
                                                    <td>cell2_4</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_5</td>
                                                    <td>cell2_5</td>
                                                </tr>
                                            </tbody>
                                            </tr>
                                        </table>
                                    </div>

                            </div>
                    </div>
                </div>
                <div class="playing-card-4">
                    AI PLAYER 3
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName">Card Name</h2>
                                <img src="#" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                    <td>cell1_1</td>
                                                    <td>cell2_1</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_2</td>
                                                    <td>cell2_2</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_3</td>
                                                    <td>cell2_3</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_4</td>
                                                    <td>cell2_4</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_5</td>
                                                    <td>cell2_5</td>
                                                </tr>
                                            </tbody>
                                            </tr>
                                        </table>
                                    </div>

                            </div>
                    </div>
                </div>
                <div class="playing-card-5">
                    AI PLAYER 4
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName">Card Name</h2>
                                <img src="#" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                    <td>cell1_1</td>
                                                    <td>cell2_1</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_2</td>
                                                    <td>cell2_2</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_3</td>
                                                    <td>cell2_3</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_4</td>
                                                    <td>cell2_4</td>
                                                </tr>
                                                <tr>
                                                    <td>cell1_5</td>
                                                    <td>cell2_5</td>
                                                </tr>
                                            </tbody>
                                            </tr>
                                        </table>
                                    </div>

                            </div>
                    </div>
                </div>
            </div>

				<button id="btn-submit"; onclick="submit(); document.getElementById('btn-next').style.display='block';">Submit you category!</button>




		<script type="text/javascript">
		
		// Method that is called on page load
		function initalize() {
			launchGame();
		
			// --------------------------------------------------------------------------
			// You can call other methods you want to run when the page first loads here
			// --------------------------------------------------------------------------
			
			// For example, lets call our sample methods
			// helloJSONList();
			// helloWord("Student");
				
		}
			
		// -----------------------------------------
		// Add your other Javascript methods Here
		// -----------------------------------------

		var category_selected;//global variable  
				
		function setNumberOfPlayers() {
			// getting numberOfPlayers from dropdown menu and save as variable players
		  	var players = document.getElementById('numberOfPlayers').value;
		  	console.log("The total number of players is " + players)
		  	
		  	//  create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET',
			"http://localhost:7777/toptrumps/numberOfPlayers?Number="+players); // Request type and URL+parameters
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send()
	
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives 
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(responseText);
			};
		  }

		function setCategory(clicked_id) {
			document.getElementById('btn-submit').style.display='block';
			category_selected = clicked_id;
			console.log("this clicked id is: " + category_selected);
		}
			
		function submit(){		  	
		  	//  create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET',
			"http://localhost:7777/toptrumps/submit_category?categoryChoice="+category_selected); // Request type and URL+parameters
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send()
			
			
			
		}
		
        function getDeck(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDeck	");
		  	if(!xhr){
		  		alert("CORS not supported");
		  	}
		  	xhr.send();
		  	xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		distributeDeck();
		  	}  	
		  }
			  
		function distributeDeck(){
	  		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/distributeCards	");
	  		if(!xhr){
		  		alert("CORS not supported");
	  		}
	  		xhr.send();
	 		console.log("distributing deck");
	  		xhr.onload = function(e){
	  			var responseText = xhr.response;
	  			nextRound();			  			
			}
		}
		
			  
		function nextRound(){
			document.getElementById('btn-submit').style.display='none';
			console.log("initiating gameplay");
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/nextRound	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		getRoundNumber();
		  		getCardName();
		  		getCategories();
		  	} 
		}
		  
		
		
		
		
		
		function getRoundNumber(){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNumber ");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("roundNum").innerHTML = "round number is " + responseText;
		  	} 
		}
			  
		function getCategories(){
		  
		  getCat1();
		  getCat2();
		  getCat3();
		  getCat4();
		  getCat5();
		}
			  
	 	function getCat1(){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCat1	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("1").innerHTML = "Size      " +responseText;
		  	} 
		}
			  
		function getCat2(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCat2 ");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("2").innerHTML = "Speed     " +responseText;
		  	} 
		}
		  
		function getCat3(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCat3	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("3").innerHTML = "Range     "+responseText;
		  	} 
		}
		  
		function getCat4(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCat4	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("4").innerHTML = "Firepower " +responseText;
		  	} 
		}
		  	
		function getCat5(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCat5	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("5").innerHTML = "Cargo     "+responseText;
		  	} 
		}
		
		function getCardName(){
		  	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardName	");
			xhr.send();
			xhr.onload = function(e){
		  		var responseText = xhr.response;
		  		document.getElementById("cardName").innerHTML = responseText;
		  	} 
	  	}
			  
		// This is a reusable method for creating a CORS request. Do not edit this.
		function createCORSRequest(method, url) {
			var xhr = new XMLHttpRequest();
			if ("withCredentials" in xhr) {
				// Check if the XMLHttpRequest object has a "withCredentials" property.
				// "withCredentials" only exists on XMLHTTPRequest2 objects.
				xhr.open(method, url, true);

  			} else if (typeof XDomainRequest != "undefined") {
				// Otherwise, check if XDomainRequest.
				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
				xhr = new XDomainRequest();
				xhr.open(method, url);
			 } else {
   				// Otherwise, CORS is not supported by the browser.
   				xhr = null;
			 }
			 return xhr;
		}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
					}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>