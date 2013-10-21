$("document").ready(function(){

	var offerID;

	var from = $( "#from" ),
	to = $( "#to" ),
	date = $( "#date" ),
	time = $( "#time" ),
	description = $( "#description" ),
	deadline = $( "#deadline" ),
	freeseats = $( "#freeseats" ),
	email = $( "#signupemail" ),
	password = $( "#signuppassword" ),
	allFields = $( [] ).add( from ).add( to ).add( date ).add( time ).
	add( description ).add( deadline ).add(freeseats),
	offerparams = "from=" + from.val() + "&to=" + to.val() + "&date='" + date.val() + "'&time=" + time.val() + 
	"&description=" + description.val() + "&deadline='" + deadline.val() + "'&freeseats=" + freeseats.val(),  
	tips = $( ".validateTips" );
	$("#newoffer").ajaxForm({

		success : function (response) {

			/* $( "#offers tbody" ).append("<td><a href='/CarPoolingApp/offer.jsp?offerID="+
		        			response + "'>Details</a></td></tr>" );*/

		}
	});

	$( "a" ).button();
	
	$( "#date" ).datepicker({
		inline: true ,
		/*showOn: "button",
		buttonImage: "css/hot-sneaks/images/calendar.gif",
		buttonImageOnly: false,*/
		dateFormat: "yy-mm-dd"
	});
	
	$( "#deadline1" ).datepicker({
		inline: true ,
		/*showOn: "button",
		buttonImage: "css/hot-sneaks/images/calendar.gif",
		buttonImageOnly: false,*/
		dateFormat: "yy-mm-dd"
	});


	$( "#from" ).autocomplete({
		minLength: 0,
		source: "/CarPoolingApp/FindCityServlet"});

	$( "#to" ).autocomplete({
		minLength: 0,
		source: "/CarPoolingApp/FindCityServlet"});
	
	

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

	//hover states on the static widgets
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

				var bValid = true;
				allFields.removeClass( "ui-state-error" );

				$("#login").append("<div style='visibility:hidden;'><input id='submit' type='submit'></div>");
				$("#submit").click();
				$( this ).dialog( "close" );

			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}

		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}


	});

	

	$("#editdialog").dialog({

		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"Save": function() {

				var bValid = true;
				allFields.removeClass( "ui-state-error" );

				$("#edit").append("<div style='visibility:hidden;'><input id='editsubmit' type='submit'></div>");
				$("#editsubmit").click();
				$( this ).dialog( "close" );

			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}

		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
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



	$("#dialog").dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"Submit offer": function() {

				$("#newoffer").append("<div style='visibility:hidden;'><input id='submit' type='submit'></div>");
				$("#submit").click();


				$( this ).dialog( "close" );  
				// }
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});

	$("#users-contain").load("/CarPoolingApp/ShowOffersServlet?"+prmstr);
	var refreshId = setInterval(function() {
		$("#users-contain").load("/CarPoolingApp/ShowOffersServlet?"+prmstr);
	}, 1000);
	$.ajaxSetup({ cache: false });

	function strcmp(a, b) {
		if (a.toString() < b.toString()) return -1;
		if (a.toString() > b.toString()) return 1;
		return 0;
	}

	var prmstr = window.location.search.substr(1);
	var prmarr = prmstr/*.split ("&")*/;
	var params = {};

	//for ( var i = 0; i < prmarr.length; i++) 
	var tmparr = prmarr.split("=");
	params[tmparr[0]] = tmparr[1];

	$.getJSON(

			"/CarPoolingApp/UserPageServlet?" + prmstr, function(data) {
				var items = [];
				var item = [];

				$.each(data, function(key, val) {
					items.push('<li id="' + key + '">' + val + '</li>');
					item.push(val);
				});

				/* $('<ul/>', {
				  'class': 'my-new-list',
				  html: items.join('')
				}).appendTo('body');
				 */

				//change background according to gender
				if(strcmp(item[2],"Female") == 0){
					$("div.info-container-male").toggleClass("info-container-female");
					$("img").append("<img alt='' src='img/driver_f.png'>");
				}
				else $("img").append("<img alt='' src='img/driver_m.png'>");

				$("#name").append(item[0]);
				$("#age").append(item[1]).append(" years old");
				$("#gender").append(item[2]);
				$("#carmodel").append(item[3]);
				$("#phone").append(item[4]);
				$("#addbutton").append(item[5]).ajaxComplete(function(){

					// Dialog Link
					$('#dialog_link').click(function() {
						$('#dialog').dialog('open');
						return false;
					});

					//hover states on the static widgets
					$('#dialog_link, ul#icons li').hover(function() {
						$(this).addClass('ui-state-hover');
					}, function() {
						$(this).removeClass('ui-state-hover');
					});

				});
				
				$("#editprof").append(item[6]).ajaxComplete(function(){

					// Dialog Link
					$("#editbutton").click(function() {
						$('#editdialog').dialog('open');
						return false;
					});

//					hover states on the static widgets
					$('#editbutton, ul#icons li').hover(function() {
						$(this).addClass('ui-state-hover');
					}, function() {
						$(this).removeClass('ui-state-hover');
					});
				});

			});
	
	

});