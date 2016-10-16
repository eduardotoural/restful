/**
 * 
 */

function myFunction(){
	var d;
	var r;
	
	var input = document.forms["jsfexample"]["Word"].value;
	
	console.log(input);
	
	document.getElementById("demo").innerHTML = "Paragraph changed."+ input;
}

function otherFunction(){
	console.log(6+7);
}

function validateForm() {
    //var x = document.forms["jsfexample"]["test"].value;
	var x = document.getElementById('jsfexample:name').value;
    if (x == null || x == "") {
        alert("Name must be filled out");
        return false;
    }
}