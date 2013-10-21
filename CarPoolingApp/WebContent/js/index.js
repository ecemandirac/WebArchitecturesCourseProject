$("document").ready(function() {
	
	var name = $( "#name" ),
    email = $( "#signupemail" ),
    password = $( "#signuppassword" ),
    tips = $( ".validateTips" );

	 $( "a" ).button();

	function updateTips( t ) {
	     tips
	         .text( t )
	         .addClass( "ui-state-highlight" );
	     setTimeout(function() {
	         tips.removeClass( "ui-state-highlight", 1500 );
	     }, 500 );
	 }
	
	 function checkLength( o, n, min, max ) {
	     if ( o.val().length > max || o.val().length < min ) {
	         o.addClass( "ui-state-error" );
	         updateTips( "Length of " + n + " must be between " +
	             min + " and " + max + "." );
	         return false;
	     } else {
	         return true;
	     }
	 }
	
	 function checkRegexp( o, regexp, n ) {
	     if ( !( regexp.test( o.val() ) ) ) {
	         o.addClass( "ui-state-error" );
	         updateTips( n );
	         return false;
	     } else {
	         return true;
	     }
	 }
	 

	$("#loginbutton").click(function() {
		$('#logindialog').dialog('open');
		return false;
	});

//	hover states on the static widgets
	$('#loginbutton, ul#icons li').hover(function() {
		$(this).addClass('ui-state-hover');
	}, function() {
		$(this).removeClass('ui-state-hover');
	});
	

	$("#logindialog").dialog({

		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"Login": function() {
			
				$("#login").append("<div style='visibility:hidden;'><input id='loginsubmit' type='submit'></div>");

				$("#loginsubmit").click();
				$( this ).dialog( "close" );

			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}

		},
		close: function() {
			
		}


	});
	
	$("#signupbutton").click(function() {
		$('#signupdialog').dialog('open');
		return false;
	});

//	hover states on the static widgets
	$('#signupbutton, ul#icons li').hover(function() {
		$(this).addClass('ui-state-hover');
	}, function() {
		$(this).removeClass('ui-state-hover');
	});
	

	$("#signupdialog").dialog({

		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"Sign Up": function() {
			
				var bValid = true;
				 //bValid = bValid && checkLength( name, "username", 3, 16 );
				 
                 bValid = bValid && checkLength( email, "email", 6, 80 );
                 bValid = bValid && checkLength( password, "password", 5, 16 );

                 //bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
                 //From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                 bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
                 bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
                 
				
				
				if ( bValid ) {
				$("#signup").append("<div style=''><input id='signupsubmit' type='submit'></div>");
				$("#signupsubmit").click();
				$( this ).dialog( "close" );
				}

			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}

		},
		close: function() {
			
		}


	});

	$( "#accordion" ).accordion();


	$( "#datepicker" ).datepicker({
		/*inline: true */
		showOn: "button",
		buttonImage: "css/hot-sneaks/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat: "yy-mm-dd"
	});


	$( "#autocompleteDept" ).autocomplete({
		minLength: 0,
		source: "/CarPoolingApp/FindCityServlet"});

	$( "#autocompleteArrv" ).autocomplete({
		minLength: 0,
		source: "/CarPoolingApp/FindCityServlet"});

	function submitForm() {
		$.ajax({
			type:"GET", 
			url: "/CarPoolingApp/RideOffersServlet", 
			data:$("#offers").serialize(), 
			success: function(response) {
				$('#offers').find('.result').html(response);
			}
		});

		return false;
	}




	$("#offers").ajaxForm({
		target : "#accordion"

	}).ajaxComplete(function() {
		$('#img').remove();
		$("#accordion").accordion("destroy").accordion({

			collapsible: true
		});

		return false;
	});

	



});
