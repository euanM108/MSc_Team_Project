
<html>

    <head>
        <!-- Web page title -->
        <title>Top Trumps</title>
        
        <!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
        <script src="https://code.jquery.com/jquery-2.1.1.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">
        <link href='https://fonts.googleapis.com/css?family=Atma' rel='stylesheet'>
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
        
        <div class="main-container">

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
#header {
  padding: 80px;
  text-align: center;
  background: #1abc9c;
  color: white;
}

/* Increase the font size of the heading */
#header h1 {
  font-size: 40px;
}

h1 {
  position: relative;
  text-transform: uppercase;
  letter-spacing: 10%;
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
  font-family: sans-serif;
 
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

/* On mouse-over, add a deeper shadow */
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

/* Padding inside the card container */
.container {
  padding: 4% 7%;
}

#cat-buttons{
    display: block;
    width: 100%;
}

img {
  border-radius: 15px 15px 0 0;
  width: 100%;
}

.card-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    grid-template-rows: 1fr;
    grid-template-areas: ". . .";
    grid-template-areas:
        "a b c e f";
}

.card {
    border-top: 0px;
    /* Add shadows to create the "card" effect */
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
    width: 90%;
    /* 15 pixel rounded corners */
    border-radius: 15px;
    margin-left: 5%;
    margin-right: 5%;
    margin-bottom: 5%;
    background: #1abc9c;
    font-family: Helvetica;
}

#playing-card-1 {
    background-color: rgba(55, 142, 125, 0.4);
    display: none;
    grid-area: a;
    align-self: center;
    text-align: center;
    margin-top: 0%;
    
}

#playing-card-2 {
    display: none;
    grid-area: b;
    text-align: center;
    margin-top: 0%;
    margin-bottom: 0%;
}


#playing-card-3 {
    display: none;
    grid-area: c;
    text-align: center;
    margin-top: 0%;
}


#playing-card-4 {
    display: none;
    grid-area: e;
    text-align: center;
    margin-top: 0%;
}

#playing-card-5 {
    display: none;
    grid-area: f;
    text-align: center;
    margin-top: 0%;
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


#btn-submit{
    margin: 5px;
    display: block;
    padding: 5px;
}



#btn-submit, #btn-opponent-submit, #btn-reveal-winner, #btn-next-round {
    display: none;
}

#roundNum {
  display: none;
  transition: 0.3s;
  background: #1abc9c;
  font-family: sans-serif;
font-size: 30px;
  text-align: center;
}

#overlay {
  position: fixed;
  display: none;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0,0,0,0.5);
  z-index: 2;
  cursor: pointer;
}

#win-text {
    font-family: Atma, Arial, Helvetica, sans-serif;
    font-size: 100px;
    text-align: center;
    margin: 10%;
}

#btn-default{
  margin: 5%;
  background: #1abc9c;
  color:#fff;
  border:none;
  position:relative;
  height:20%;
  width: 90%;
  font-size: 50px;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
#btn-default:hover{
  background:#fff;
  color:#1abc9c;
}
#btn-default:before,#btn-default:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1abc9c;
  transition:400ms ease all;
}
#btn-default:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
#btn-default:hover:before,#btn-default:hover:after{
  width:100%;
  transition:800ms ease all;
}


#winning-popup-box {
margin: 10%;
background-color: white;
border-style: dashed;
border-color: red;
}

#btn-finish-game{
display: none;
}
    #column-design{
        background-color: #333;
        font-family: sans-serif;
        color: white;
        width: 50%;
        height: 30%;
        margin-left: 25%;
        margin-top: 0%;
        margin-bottom: 0%;
    }
    
    #HumanCardCount, #P2CardCount, #P3CardCount, #P4CardCount, #P5CardCount{
        margin: 0%;
    }
    
    #roundNum{
        margin: 0%;
    }
    
    #pop-up-buttons {
    	margin: 5%;
    	font-family: sans-serif;
    	color: white;
    	background-color: #333;
    	width: 10%;
    }
    
    #select-numberOfPlayer{

        vertical-align: middle;
    	font-family: sans-serif;
    	color: white;
    	font-size: 100%;
    }
    
    #replay-button{
    display: none;
    }
</style>
</head>
<body>

<div id="header">
  <h1>Top Trumps Game</h1>

    <p>Please select the number of opponents below and press <b>begin</b></p>

</div>

<div id="overlay">
    <div id="winning-popup-box">
    	<div id="pop-up-buttons">
    	 <a href="/toptrumps/game/">Replay</a>
    	 <br>
    	 <a href="/toptrumps/">Exit</a>
    	</div>
    	<h2 id="win-text">YOU WON!!!</h2>
    </div>
</div>

<div class="navbar">

  <a href="/toptrumps/">Exit</a>
    <button id="btn-submit"; onclick="submit(); getRoundNumber(); disableButtons(); this.style.display='none'">Submit your category!</button>
    <button id="btn-opponent-submit"; onclick="submit(); getRoundNumber(); this.style.display='none';">Submit Opponents category!</button>
    <button id="btn-reveal-winner"; onclick="displayNextRoundButton(); getRoundWinner(); getRoundNumber(); showOpponentsValues(); this.style.display='none';">Show winner!</button>
    <button id="btn-next-round"; onclick=" enableOrDisableButtons(); nextRound(); getRoundNumber(); hideOpponentsValues(); this.style.display='none'; displayOpponentSubmit();">Next round!</button>
    <button id="btn-finish-game"; onclick="finishGame(); this.style.display='none';">FINISH GAME</button>
    <div id="select-numberOfPlayers-box">
    <label id="select-numberOfPlayer"> Select No. of opponents -</label>
    <select id="numberOfPlayers" name="numberOfPlayers" style="font-size: 20px; padding: 10px;">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
    </select>
        </div>
    <div id="replay-button";> <a href="/toptrumps/game/">Replay</a> </div>
                
</div>

                
                
                    <button id="btn-default" onclick="setNumberOfPlayers(); getDeck(); hideOpponentsValues(); roundNum.style.display='block'; this.style.display = 'none'; removeHeader();"
                    >Begin!</button>
    
                <p id="roundNum">Round 1: The active player is highlighted red!</p> 
            
            <div class="card-container">
                
                <div id="playing-card-1">
                    <div id="column-design">
                        <br>
                    YOU<br>
                        
               <p id="HumanCardCount">Cards</p>
                        <br>
                    </div>
                    <div class="card">
                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName-p1">Card Name</h2>
                                <img id="p1-card-image" alt="picture of spaceship">
                                <div id="cat-buttons">
                                    <button id="1" onclick="setCategory(this.id)" disabled=true; style="width: 100%; display: block;"> <p id="p1CardValue1"></p>
                                    </button>
                                    <button id="2" onclick="setCategory(this.id)" disabled=true; style="width: 100%; display: block;"> <p id="p1CardValue2"></p>
                                    </button>
                                    <button id="3" onclick="setCategory(this.id)" disabled=true; style="width: 100%; display: block;"> <p id="p1CardValue3"></p>
                                    </button>
                                    <button id="4" onclick="setCategory(this.id)" disabled=true; style="width: 100%; display: block;"> <p id="p1CardValue4"></p>
                                    </button>
                                    <button id="5" onclick="setCategory(this.id)" disabled=true; style="width: 100%; display: block;"> <p id="p1CardValue5"></p>
                                    </button>
                                </div>

                            </div>
                        </alt>
                    </div>
                    </div>
                
                <div id="playing-card-2">
                    <div id="column-design">
                        <br>
                    PLAYER 2 (AI)<br>
                    <p id="P2CardCount">Cards</p>
                        <br>
                    </div>
                    <div class="card">
                        <div class="container">
                            <h2 id="cardName-p2">Card Name</h2>
                            <img id="p2-card-image" alt="picture of spaceship">
                            <div class="cat-tables">
                                <table class="cat-table">
                                    <tbody>
                                        <tr>
                                           
                                            <td><p id="p2CardValue1"></p></td>
                                        </tr>
                                        <tr>
                                           
                                            <td><p id="p2CardValue2"></p></td>
                                        </tr>
                                        <tr>
                                            
                                            <td><p id="p2CardValue3"></p></td>
                                        </tr>
                                        <tr>
                                            
                                            <td><p id="p2CardValue4"></p></td>
                                        </tr>
                                        <tr>
                                           
                                            <td><p id="p2CardValue5"></p></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="playing-card-3">
                    <div id="column-design">
                        <br>
                    PLAYER 3 (AI)<br>
                    <p id="P3CardCount">Cards</p>
                        <br>
                    </div>
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName-p3">Card Name</h2>
                                <img id="p3-card-image" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                    
                                                    <td><p id="p3CardValue1"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p3CardValue2"></p></td>
                                                </tr>
                                                <tr>
                                                    
                                                    <td><p id="p3CardValue3"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p3CardValue4"></p></td>
                                                </tr>
                                                <tr>
                                                  
                                                    <td><p id="p3CardValue5"></p></td>
                                                </tr>
                                            </tbody>
                                           
                                        </table>
                                    </div>
                            </div>
                        </alt>
                    </div>
                </div>
                 <div id="playing-card-4">
                    <div id="column-design">
                        <br>
                    PLAYER 4 (AI)<br>
                    <p id="P4CardCount">Cards</p>
                        <br>
                    </div>
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName-p4">Card Name</h2>
                                <img id="p4-card-image" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                    
                                                    <td><p id="p4CardValue1"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p4CardValue2"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p4CardValue3"></p></td>
                                                </tr>
                                                <tr>
                                                  
                                                    <td><p id="p4CardValue4"></p></td>
                                                </tr>
                                                <tr>
                                                    
                                                    <td><p id="p4CardValue5"></p></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                            </div>
                        </alt>
                    </div>
                </div>
                 <div id="playing-card-5">
                    <div id="column-design">
                        <br>
                    PLAYER 5 (AI)<br>
                    <p id="P5CardCount">Cards</p>
                        <br>
                    </div>
                    <div class="card">

                        <alt="Avatar" style="width:100%">
                            <div class="container">
                                <h2 id="cardName-p5">Card Name</h2>
                                <img id="p5-card-image" alt="picture of spaceship">
                                <div class="cat-tables">
                                        <table class="cat-table">
                                            <tbody>
                                                <tr>
                                                   
                                                    <td><p id="p5CardValue1"></p></td>
                                                </tr>
                                                <tr>
                                                    
                                                    <td><p id="p5CardValue2"></p></td>
                                                </tr>
                                                <tr>
                                                    
                                                    <td><p id="p5CardValue3"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p5CardValue4"></p></td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td><p id="p5CardValue5"></p></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                            </div>
                        </alt>
                    </div>
                </div>
            </div>
        
            



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
        
        // Global variables
        var category_selected = 1;
        var current_round_num;
        var number_of_players;
        var winning_index;
        var isThereAWinner = false;
     
        function launchGame(){
            // getting numberOfPlayers from dropdown menu and save as variable players
            console.log("LET THE GAME BEGIN");
            
            //  create a CORS request, this is the message we are going to send (a get request in this case)
            var xhr = createCORSRequest('GET',
            "http://localhost:7777/toptrumps/launchGame"); // Request type and URL+parameters
        
            // Message is not sent yet, but we can check that the browser supports CORS
            if (!xhr) {
                alert("CORS not supported");
            }
            
            // We have done everything we need to prepare the CORS request, so send it
            xhr.send()
        }
        
        function checkWhoStarts(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinningIndex"); // Request type and URL+parameters
                    // Message is not sent yet, but we can check that the browser supports CORS
                    if (!xhr) {
                        alert("CORS not supported");
                    }
                    
                    // We have done everything we need to prepare the CORS request, so send it
                    xhr.send()
                    
                    xhr.onload = function(e){
                        winning_index = xhr.response;
                        console.log("winning index is " + winning_index);
                        if (winning_index != 0){
                              document.getElementById("btn-opponent-submit").style.display = 'block';
                              highlightCurrentPlayer();
                              enableOrDisableButtons();
                        }
                        else if (winning_index == 0){
                             highlightCurrentPlayer();
                             enableOrDisableButtons();
                             
                        }
                    
                    }
        }
        
        function hideOpponentsValues(){
   	     var myClasses = document.querySelectorAll('.cat-tables');
   			 i = 0;
   			 length = myClasses.length;

			for (i; i < length; i++) {
  				  myClasses[i].style.visibility = 'hidden';  				  
			}      
			
			document.getElementById("cardName-p2").style.visibility = 'hidden';
		  	document.getElementById("cardName-p3").style.visibility = 'hidden';
		  	document.getElementById("cardName-p4").style.visibility = 'hidden';
		  	document.getElementById("cardName-p5").style.visibility = 'hidden';
		  	document.getElementById("p2-card-image").style.visibility = 'hidden';
		  	document.getElementById("p3-card-image").style.visibility = 'hidden';
		  	document.getElementById("p4-card-image").style.visibility = 'hidden';
		  	document.getElementById("p5-card-image").style.visibility = 'hidden';
        }
        
        
        
        function showOpponentsValues(){
        
        var myClasses = document.querySelectorAll('.cat-tables');
   			 i = 0;
   			 length = myClasses.length;

			for (i; i < length; i++) {
  				  myClasses[i].style.visibility = 'visible';
			}      
			document.getElementById("cardName-p2").style.visibility = 'visible';
		  	document.getElementById("cardName-p3").style.visibility = 'visible';
		  	document.getElementById("cardName-p4").style.visibility = 'visible';
		  	document.getElementById("cardName-p5").style.visibility = 'visible';
		  	document.getElementById("p2-card-image").style.visibility = 'visible';
		  	document.getElementById("p3-card-image").style.visibility = 'visible';
		  	document.getElementById("p4-card-image").style.visibility = 'visible';
		  	document.getElementById("p5-card-image").style.visibility = 'visible';
        }
                
        function setNumberOfPlayers() {     
        
            // getting numberOfPlayers from dropdown menu and save as variable players
            number_of_players = document.getElementById('numberOfPlayers').value;
          	document.getElementById('replay-button').style.display='block';
            //  create a CORS request, this is the message we are going to send (a get request in this case)
            var xhr = createCORSRequest('GET',
            "http://localhost:7777/toptrumps/numberOfPlayers?Number="+number_of_players); // Request type and URL+parameters
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
                document.getElementById('playing-card-1').style.display='block';
                document.getElementById('playing-card-2').style.display='block';
                
                if(number_of_players > 1 ){
                	document.getElementById('playing-card-3').style.display='block';
                }
                if(number_of_players > 2){
                	document.getElementById('playing-card-4').style.display='block';
                }
                if(number_of_players > 3){
                	document.getElementById('playing-card-5').style.display='block';        
                }
                checkWhoStarts(); 
           
            };
          }
        
        function removeHeader(){
        	document.getElementById('select-numberOfPlayers-box').style.display='none';
            document.getElementById('header').style.display='none';
        }
        
        function enableOrDisableButtons(){
        if (winning_index != 0){
                    
                    disableButtons();
                }
                else {
                    enableButtons();
                }
        
        }
        
        function displayNextRoundButton(){
        	document.getElementById("btn-next-round").style.display = 'block';
        }
        
        function setCategory(clicked_id) {
            document.getElementById('btn-submit').style.display='block';
            category_selected = clicked_id;
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
            
            xhr.onload = function(e){
                document.getElementById('btn-reveal-winner').style.display='block';
            } 
        
        }
        
        function getRoundWinner(){
            //  create a CORS request, this is the message we are going to send (a get request in this case)
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundWinner"); // Request type and URL+parameters
            // Message is not sent yet, but we can check that the browser supports CORS
            if (!xhr) {
                alert("CORS not supported");
            }
            
            // We have done everything we need to prepare the CORS request, so send it
            xhr.send()
            
            xhr.onload = function(e){
                
                document.getElementById("roundNum").innerHTML = "Round number " + current_round_num + ":  " + xhr.response;
                
                var xhrWinningIndex = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinningIndex"); // Request type and URL+parameters
                // Message is not sent yet, but we can check that the browser supports CORS
                if (!xhrWinningIndex) {
                    alert("CORS not supported");
                }
                
                // We have done everything we need to prepare the CORS request, so send it
                xhrWinningIndex.send()
                
                xhrWinningIndex.onload = function(e){
                
                winning_index = xhrWinningIndex.response; // the text of the response
                highlightCurrentPlayer();
                checkForOverallGameWin();
                }
            } 
        }
        
        function displayOpponentSubmit(){
        if (winning_index != 0){
                    document.getElementById("btn-opponent-submit").style.display = 'block';
                }
        
        }

        function checkActivePlayers(){
            
            if (number_of_players == 1){
                checkPlayersDeckSize(0);
                checkPlayersDeckSize(1);
                }
            else if(number_of_players == 2){
                checkPlayersDeckSize(0);
                checkPlayersDeckSize(1);
                checkPlayersDeckSize(2);
                }
            else if(number_of_players == 3){
                checkPlayersDeckSize(0);
                checkPlayersDeckSize(1);
                checkPlayersDeckSize(2);
                checkPlayersDeckSize(3);
                }
            else if(number_of_players == 4){
                checkPlayersDeckSize(0);
                checkPlayersDeckSize(1);
                checkPlayersDeckSize(2);
                checkPlayersDeckSize(3);
                checkPlayersDeckSize(4);
            }   
        }
        function highlightCurrentPlayer(){
            if (winning_index == 0){
                document.getElementById("playing-card-1").style.backgroundColor = "rgba(173, 46, 46, 0.4)";
                document.getElementById("playing-card-2").style.backgroundColor = "white";
                document.getElementById("playing-card-3").style.backgroundColor = "white";
                document.getElementById("playing-card-4").style.backgroundColor = "white";
                document.getElementById("playing-card-5").style.backgroundColor = "white";
            }
            else if (winning_index == 1){
                document.getElementById("playing-card-1").style.backgroundColor = "rgba(55, 142, 125, 0.4)";
                document.getElementById("playing-card-2").style.backgroundColor = "rgba(173, 46, 46, 0.4)";
                document.getElementById("playing-card-3").style.backgroundColor = "white";
                document.getElementById("playing-card-4").style.backgroundColor = "white";
                document.getElementById("playing-card-5").style.backgroundColor = "white";
                
            }
            else if (winning_index == 2){
                document.getElementById("playing-card-1").style.backgroundColor = "rgba(55, 142, 125, 0.4)";
                document.getElementById("playing-card-3").style.backgroundColor = "rgba(173, 46, 46, 0.4)";
                document.getElementById("playing-card-2").style.backgroundColor = "white";
                document.getElementById("playing-card-4").style.backgroundColor = "white";
                document.getElementById("playing-card-5").style.backgroundColor = "white";
            }
            else if (winning_index == 3){
                document.getElementById("playing-card-1").style.backgroundColor = "rgba(55, 142, 125, 0.4)";
                document.getElementById("playing-card-4").style.backgroundColor = "rgba(173, 46, 46, 0.4)";
                document.getElementById("playing-card-2").style.backgroundColor = "white";
                document.getElementById("playing-card-3").style.backgroundColor = "white";
                document.getElementById("playing-card-5").style.backgroundColor = "white";
            }
            else if (winning_index == 4){
                document.getElementById("playing-card-1").style.backgroundColor = "rgba(55, 142, 125, 0.4)";
                document.getElementById("playing-card-5").style.backgroundColor = "rgba(173, 46, 46, 0.4)";
                document.getElementById("playing-card-2").style.backgroundColor = "white";
                document.getElementById("playing-card-3").style.backgroundColor = "white";
                document.getElementById("playing-card-4").style.backgroundColor = "white";
            }
           
        }
        
        function checkPlayersDeckSize(i){
            //  create a CORS request, this is the message we are going to send (a get request in this case)
            var xhr = createCORSRequest('GET',"http://localhost:7777/toptrumps/getPlayersDeckSize?i="+i); // Request type and URL+parameters
            // Message is not sent yet, but we can check that the browser supports CORS
            if (!xhr) {
                alert("CORS not supported");
            }
            
            // We have done everything we need to prepare the CORS request, so send it
            xhr.send();
            
            xhr.onload = function(e){
                var responseText = xhr.response; // the text of the response
                if (responseText < 1){
                // this makes the human card disappear
                  removePlayer(i);
                }
            } 
        
        }


        function removePlayer(i){
            if (i==0){
                document.getElementById("playing-card-1").style.display = 'none';
                document.getElementById("btn-finish-game").style.display = 'block';
                document.getElementById('btn-opponent-submit').style.display = 'none';                
            }
            else if (i==1){
                document.getElementById("playing-card-2").style.display = 'none';
            }
            else if (i==2){
                document.getElementById("playing-card-3").style.display = 'none';
            }
            else if (i==3){
                document.getElementById("playing-card-4").style.display = 'none';
            }
            else if (i==4){
                document.getElementById("playing-card-5").style.display = 'none';
            }
    
        }
            
        
        function disableButtons(){
            document.getElementById("1").disabled = true;
            document.getElementById("2").disabled = true;
            document.getElementById("3").disabled = true;
            document.getElementById("4").disabled = true;
            document.getElementById("5").disabled = true;
        }
        
        function enableButtons(){
            document.getElementById("1").disabled = false;
            document.getElementById("2").disabled = false;
            document.getElementById("3").disabled = false;
            document.getElementById("4").disabled = false;
            document.getElementById("5").disabled = false;
        }
        
        function getDeck(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDeck ");
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
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/distributeCards ");
            if(!xhr){
                alert("CORS not supported");
            }
            xhr.send();
        
            xhr.onload = function(e){
                var responseText = xhr.response;
                nextRound();                        
            }
        }
        
              
        function nextRound(){
        
            document.getElementById('btn-submit').style.display='none';
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/nextRound   ");
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                getRoundNumber();
                checkActivePlayers();
				getPlayerCardName(0);
                getPlayer1CardValues();
                getCardNamesAndValues();
                
            } 
            
            checkForOverallGameWin();
        }
        
        function getCardNamesAndValues(){
        		getPlayerCardName(1);
        		getPlayer2CardValues();
                getHumanCardCount();   		
            	getP2CardCount();
            
                if(number_of_players > 1){
                	getP3CardCount();
        			getPlayerCardName(2);      
                    getPlayer3CardValues();
                    }
                if(number_of_players > 2){
                	getP4CardCount();
        			getPlayerCardName(3);
                    getPlayer4CardValues();
                    }
                if(number_of_players > 3){
              		
              		getP5CardCount();
        			getPlayerCardName(4);           
                    getPlayer5CardValues();
                }   
        }
        
        function checkForOverallGameWin(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkForGameWin");
            xhr.send();
            var playerID = parseInt(winning_index)+1;
            xhr.onload = function(e){
                var responseText = xhr.response;
                    if (responseText=="true"){
                        
                        if (winning_index == 0){
                            document.getElementById("win-text").innerHTML = "YOU WON!!!";
                            document.getElementById("overlay").style.display = "block";
                            
                            // CONFETTI CAUSE YOU WON
                            
                        }
                        else {
                        
                            // PLAYERID NEEDS TO BE USED AS INTEGER AND NOT STRING 
                            
                            document.getElementById("win-text").innerHTML = "YOU LOSE!<br />PLAYER " + playerID + " WINS!";
                            document.getElementById("overlay").style.display = "block";
                            // UNLUCKY BECAUSE YOU DIDN'T WIN
                            
                        }
                    }
            } 
        
        }
        
        function getPlayer1CardValues(){
            getPlayer1CardValue(1);
            getPlayer1CardValue(2);
            getPlayer1CardValue(3);
            getPlayer1CardValue(4);
            getPlayer1CardValue(5);
            
          }
          
        function getPlayer2CardValues(){
            getPlayer2CardValue(1);
            getPlayer2CardValue(2);
            getPlayer2CardValue(3);
            getPlayer2CardValue(4);
            getPlayer2CardValue(5);  
          }
        
        function getPlayer3CardValues(){
            getPlayer3CardValue(1);
            getPlayer3CardValue(2);
            getPlayer3CardValue(3);
            getPlayer3CardValue(4);
            getPlayer3CardValue(5);
          }
          
        function getPlayer4CardValues(){
            getPlayer4CardValue(1);
            getPlayer4CardValue(2);
            getPlayer4CardValue(3);
            getPlayer4CardValue(4);
            getPlayer4CardValue(5);
          }
        
        function getPlayer5CardValues(){
            getPlayer5CardValue(1);
            getPlayer5CardValue(2);
            getPlayer5CardValue(3);
            getPlayer5CardValue(4);
            getPlayer5CardValue(5);
          }
          
          
        function finishGame(){
        console.log("finishGame() called");
        var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/finishGame ");
            xhr.send();
            xhr.onload = function(e){
                getRoundWinner();
                getRoundNumber();
                
                getCardCounts();
                getCardNamesAndValues();
                checkActivePlayers();
                
                getRoundWinner();
                getRoundNumber();
         
            } 
        }
          
        function getRoundNumber(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNumber ");
            xhr.send();
            xhr.onload = function(e){
                current_round_num =  xhr.response;
            } 
        }
  
        
        function getPlayer1CardValue(i){
            var xhr = createCORSRequest('GET',
            "http://localhost:7777/toptrumps/getPlayer1CardValue?i="+i); // Request type and URL+parameters
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
                var responseText = xhr.response;
                if (i ==1){
                    document.getElementById("p1CardValue1").innerHTML = "size : " +responseText;
                }
                else if (i==2){
                    document.getElementById("p1CardValue2").innerHTML = "speed : " +responseText;
                }
                else if (i==3){
                    document.getElementById("p1CardValue3").innerHTML = "range : " + responseText;
                }
                else if (i==4){
                    document.getElementById("p1CardValue4").innerHTML = "fire-power : " +responseText;
                }
                else if (i==5){
                    document.getElementById("p1CardValue5").innerHTML = "cargo : " +responseText;
                }
            }
        }
        
        // PLAYER 2 CARD NAME AND CARD VALUES
        function getPlayerCardName(i){
        	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayerCardName?i="+i); // Request type and URL+parameters
            
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
        		var responseText = xhr.response;
        		if (i==0){
                document.getElementById("cardName-p1").innerHTML = responseText;
                fileSource = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg";
                document.getElementById("p1-card-image").src = fileSource;
                }
                else if (i==1){
                document.getElementById("cardName-p2").innerHTML = responseText;
                fileSource = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg";
                document.getElementById("p2-card-image").src = fileSource;
                }
                else if (i==2){
                document.getElementById("cardName-p3").innerHTML = responseText;
                fileSource = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg";
                document.getElementById("p3-card-image").src = fileSource;
                }
                else if (i==3){
                document.getElementById("cardName-p4").innerHTML = responseText;
                fileSource = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg";
                document.getElementById("p4-card-image").src = fileSource;
                }
                else if (i==4){
                document.getElementById("cardName-p5").innerHTML = responseText;
                fileSource = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg";
                document.getElementById("p5-card-image").src = fileSource;
                }
                
        	}
        }
        
        
        function getPlayer2CardValue(i){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayer2CardValue?i="+i); // Request type and URL+parameters
            
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
                var responseText = xhr.response;
                if (i ==1){
                    document.getElementById("p2CardValue1").innerHTML = "size: " +responseText;
                }
                else if (i==2){
                    document.getElementById("p2CardValue2").innerHTML = "speed: " +responseText;
                }
                else if (i==3){
                    document.getElementById("p2CardValue3").innerHTML = "range; " +responseText;
                }
                else if (i==4){
                    document.getElementById("p2CardValue4").innerHTML = "fire-power: " +responseText;
                }
                else if (i==5){
                    document.getElementById("p2CardValue5").innerHTML = "cargo: " +responseText;
                }
            }
        }
 
        
        
        function getPlayer3CardValue(i){
            var xhr = createCORSRequest('GET',"http://localhost:7777/toptrumps/getPlayer3CardValue?i="+i); 
        
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
                var responseText = xhr.response;
                if (i ==1){
                    document.getElementById("p3CardValue1").innerHTML = "size: " +responseText;
                }
                else if (i==2){
                    document.getElementById("p3CardValue2").innerHTML = "speed: "+ responseText;
                }
                else if (i==3){
                    document.getElementById("p3CardValue3").innerHTML = "range: " + responseText;
                }
                else if (i==4){
                    document.getElementById("p3CardValue4").innerHTML = "fire-power: " + responseText;
                }
                else if (i==5){
                    document.getElementById("p3CardValue5").innerHTML = "cargo: " +responseText;
                }
            }
        }
        
        
        // PLAYER 4 CARD NAME AND CARD VALUES
        
        function getPlayer4CardValue(i){
            var xhr = createCORSRequest('GET',"http://localhost:7777/toptrumps/getPlayer4CardValue?i="+i); 
        
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
                var responseText = xhr.response;
                if (i ==1){
                    document.getElementById("p4CardValue1").innerHTML = "size: " +responseText;
                }
                else if (i==2){
                    document.getElementById("p4CardValue2").innerHTML = "speed: " +responseText;
                }
                else if (i==3){
                    document.getElementById("p4CardValue3").innerHTML = "range: " +responseText;
                }
                else if (i==4){
                    document.getElementById("p4CardValue4").innerHTML = "fire-power: " +responseText;
                }
                else if (i==5){
                    document.getElementById("p4CardValue5").innerHTML = "cargo: " + responseText;
                }
            }
        }
        
        
        // PLAYER 5 CARD NAME AND CARD VALUES
        
        function getPlayer5CardValue(i){
            var xhr = createCORSRequest('GET',"http://localhost:7777/toptrumps/getPlayer5CardValue?i="+i); 
        
            if (!xhr) {
                alert("CORS not supported");
            }
            
            xhr.send()
    
            xhr.onload = function(e) {
                var responseText = xhr.response;
                if (i ==1){
                    document.getElementById("p5CardValue1").innerHTML = "size: " +responseText;
                }
                else if (i==2){
                    document.getElementById("p5CardValue2").innerHTML = "speed: " +responseText;
                }
                else if (i==3){
                    document.getElementById("p5CardValue3").innerHTML = "range: " +responseText;
                }
                else if (i==4){
                    document.getElementById("p5CardValue4").innerHTML = "fire-power: " + responseText;
                }
                else if (i==5){
                    document.getElementById("p5CardValue5").innerHTML = "cargo: " +responseText;
                }
            }
        }
        
        //These functions calculate the cards remaining for the players
        //Human player
        function getHumanCardCount(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanCardCount");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                document.getElementById("HumanCardCount").innerHTML = "Deck Size: " +responseText;

            } 
        }
        //AI players
        function getCardCounts(){
            
        }

        function getP2CardCount(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getP2CardCount");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                document.getElementById("P2CardCount").innerHTML = "Deck Size: " +responseText;
            }
        }

        function getP3CardCount(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getP3CardCount");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                document.getElementById("P3CardCount").innerHTML = "Deck Size: " +responseText;
            }
        }

        function getP4CardCount(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getP4CardCount");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                document.getElementById("P4CardCount").innerHTML = "Deck Size: " +responseText;
            }
        }

        function getP5CardCount(){
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getP5CardCount");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.send();
            xhr.onload = function(e){
                var responseText = xhr.response;
                document.getElementById("P5CardCount").innerHTML = "Deck Size: " +responseText;
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