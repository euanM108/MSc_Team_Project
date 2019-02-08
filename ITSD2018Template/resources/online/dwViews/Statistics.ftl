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
<title>Game Statistics</title>
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
  padding: 60px;
  text-align: center;
  background: #1abc9c;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
}


h1 {
  position: relative;
  text-transform: uppercase;
  letter-spacing: 3px;
  font-size: 5vw;
  font-weight: 900;
  text-decoration: none;
  color: white;
  display: inline-block;
  background-size: 120% 100%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  -moz-background-clip: text;
  -moz-text-fill-color: transparent;
  -ms-background-clip: text;
  -ms-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
  background-image: linear-gradient(45deg, 
                    #7794ff, 
                    #44107A,
                    #FF1361,
                    #FFF800);
  animation: .8s shake infinite alternate;
}

@keyframes shake {
  0% { transform: skewX(-15deg); }
  5% { transform: skewX(15deg); }
  10% { transform: skewX(-15deg); }
  15% { transform: skewX(15deg); }
  20% { transform: skewX(0deg); }
  100% { transform: skewX(0deg); }  
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

/*css for the table*/

.statsTable{
    padding-top: 50px;
    width:70%;
    margin-left:22%;
    margin-right:15%;
}

table.blueTable {
  font-family: Verdana, sans-serif;
  background: linear-gradient(45deg, #1abc9c, #5f2c82);
  overflow: hidden;
  max-width: 600px;
  width: 200%;
  border-radius: 16px;
  position: relative;
  text-align: center;
  border-collapse: collapse;
  border-spacing: 1;
}

table.blueTable td, table.blueTable th {
  border: 
}

table.blueTable tbody td {
  font-size: 14px;
  color: white;
}

table.blueTable thead {
  background: #1ABC9C;
  background: -moz-linear-gradient(top, #53cdb5 0%, #31c2a6 66%, #1ABC9C 100%);
  background: -webkit-linear-gradient(top, #53cdb5 0%, #31c2a6 66%, #1ABC9C 100%);
  background: linear-gradient(to bottom, #53cdb5 0%, #31c2a6 66%, #1ABC9C 100%);
  border-bottom: 0px solid #444444;
}

table.blueTable thead th {
    height: 60px;
    background: linear-gradient(#1abc9c, #000000);
    font-size: 20px;
    color: white;
    font-family: Verdana, sans-serif;
  }

tbody tr {
    height: 48px;
    border-bottom: 1px solid #e3f1d5;
    &:last-child {
      border: 0;
    }
  }

</style>
</head>
<body>

<div class="header">
  <h1>Statistics</h1>

</div>

<div class="navbar">
  <a href="/toptrumps">Home</a>

</div>

<div class="statsTable">
    <table class="blueTable">
        <thead>
            <tr>
            <th>Statistic</th>
            <th>Value</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <td>Total Number of Games</td>
            <td id="noGames">Number of Games</td>
            </tr>
            <tr>
            <td>Number of Computer Wins</td>
            <td id="AIWins">Number of AI Wins</td>
            </tr>
            <tr>
            <td>Number of Human Wins</td>
            <td id="humanWins">Number of Human Wins</td>
            </tr>
            <tr>
            <td>Average Number of Draws</td>
            <td id="avgDraws">Ave Number of Draws</td>
            </tr>
            <tr>
            <td>Largest Rounds in One Game</td>
            <td id="bigRound">Largest Round</td>
            </tr>
        </tbody>
    </table>
</div>

</div>



</body>
</html>

		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				// helloJSONList();
				// helloWord("Student");

                getNumberOfGames();
                getNumberOfAIWins();
                getNumberOfHumanWins();
                getNumberOfAverageDraws();
                getLargestRoundsInOneGame();




			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

            ///////******** Game Stats *******/////////
            function getNumberOfGames(){
			    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTotalGames	");
                if (!xhr) {
  					alert("CORS not supported");
				}
			    xhr.send();
			    xhr.onload = function(e){
		  		    var responseText = xhr.response;
		  		    document.getElementById("noGames").innerHTML = responseText;
		  	    } 
		    }

            function getNumberOfAIWins(){
			    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCompWins	");
                if (!xhr) {
  					alert("CORS not supported");
				}
			    xhr.send();
			    xhr.onload = function(e){
		  		    var responseText = xhr.response;
		  		    document.getElementById("AIWins").innerHTML = responseText;
		  	    } 
		    }

            function getNumberOfHumanWins(){
			    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanWins	");
                if (!xhr) {
  					alert("CORS not supported");
				}
			    xhr.send();
			    xhr.onload = function(e){
		  		    var responseText = xhr.response;
		  		    document.getElementById("humanWins").innerHTML = responseText;
		  	    } 
		    }

            function getNumberOfAverageDraws(){
			    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAveDraws	");
                if (!xhr) {
  					alert("CORS not supported");
				}
			    xhr.send();
			    xhr.onload = function(e){
		  		    var responseText = xhr.response;
		  		    document.getElementById("avgDraws").innerHTML = responseText;
		  	    } 
		    }

            function getLargestRoundsInOneGame(){
			    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getBigRound	");
                if (!xhr) {
  					alert("CORS not supported");
				}
			    xhr.send();
			    xhr.onload = function(e){
		  		    var responseText = xhr.response;
		  		    document.getElementById("bigRound").innerHTML = responseText;
		  	    } 
		    }
            ////////******* Games Stats End *******///////////
		
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