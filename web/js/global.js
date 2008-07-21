// JavaScript Document
<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


<!-- Idea by:  Nic Wolfe (Nic@TimelapseProductions.com) -->
<!-- Web URL:  http://fineline.xs.mw -->

<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->

<!-- Begin
function popUp(URL) {
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=500,height=320,left = 250,top = 250');");
}


function ChangeColor(tableRow, highLight){
    if (highLight)
    {
      tableRow.style.backgroundColor = '#dcfac9';
    }
    else
    {
      tableRow.style.backgroundColor = 'white';
    }
}

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

function Counter(field, max_comment, fieldName){
var len=0;
len+=field.value.length;
   if (len > max_comment){
   alert("The " + fieldName + " field has a limit of " + max_comment + " characters.\n\n You have entered " + len + " characters.");
   field.value=field.value.substring(0,field.value.length+max_comment-len);
   field.focus();
   }
}


<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->



function newPopup(mypage,myname){
	var win= null;
	var w = 500;
	var h = 320 ;
	var scroll = 'no';
	var winl = (screen.width-w)/2;
	var wint = (screen.height-h)/2;
	var settings ='height='+h+',';
	settings +='width='+w+',';
	settings +='top='+wint+',';
	settings +='left='+winl+',';
	settings +='scrollbars='+scroll+',';
	settings +='resizable=no';
	win=window.open(mypage,myname,settings);
	if(parseInt(navigator.appVersion) >= 4){win.window.focus();}
	}
	function MM_openBrWindow(theURL,winName,features) {
	window.open(theURL,winName,features);
}

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

function showLinkInParent(url) {
    opener.location.href = url;
}

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

    function displayOptions(form) { //v2.0
        var val = form.value;
        disableAll();
        document.memberForm.gender.value = '';
    if ( val == 'fullSearch'){
        enableAll();
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'memberUserName'){
        document.memberForm.memberUserName.disabled = false;	document.memberForm.memberUserName.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'email'){
        document.memberForm.email.disabled = false;	document.memberForm.email.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }
    else if( val == 'lastName'){
        document.memberForm.lastName.disabled = false;	document.memberForm.lastName.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'firstName'){
        document.memberForm.firstName.disabled = false;	document.memberForm.firstName.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'dormitory' && document.memberForm.dormitoryId != null){
        document.memberForm.dormitoryId.disabled = false;	document.memberForm.dormitoryId.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = false;
    }else if( val == 'yearIn'){
        document.memberForm.firstName.disabled = false;	document.memberForm.firstName.style.background  = '#FFFFFF';
        document.memberForm.lastName.disabled = false;	document.memberForm.lastName.style.background  = '#FFFFFF';
        document.memberForm.yearIn.disabled = false;	document.memberForm.yearIn.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = false;
    }else if( val == 'yearOut'){
        document.memberForm.yearOut.disabled = false;	document.memberForm.yearOut.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = false;
    }else if( val == 'nickName'){
        document.memberForm.nickName.disabled = false;  document.memberForm.nickName.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;    document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'maidenName'){
        document.memberForm.maidenName.disabled = false;  document.memberForm.maidenName.style.background  = '#FFFFFF';
        document.memberForm.gender.value = 'F';
        document.memberForm.partialNameSearch.disabled = false;    document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'marriageName'){
        document.memberForm.lastName.disabled = false;  document.memberForm.lastName.style.background  = '#FFFFFF';
        document.memberForm.gender.disabled = true;	document.memberForm.gender.value = 'F'; document.memberForm.gender.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.disabled = false;    document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }else if( val == 'gender'){
        document.memberForm.gender.disabled = false;  document.memberForm.gender.style.background  = '#FFFFFF';
        document.memberForm.gender.value = '';
        document.memberForm.partialNameSearch.checked = false;
    }
    }


    function enableAll() {
        document.memberForm.firstName.disabled = false;	document.memberForm.firstName.style.background  = '#FFFFFF';
        document.memberForm.memberUserName.disabled = false;	document.memberForm.memberUserName.style.background  = '#FFFFFF';
        document.memberForm.email.disabled = false;	document.memberForm.email.style.background  = '#FFFFFF';
        document.memberForm.lastName.disabled = false;	document.memberForm.lastName.style.background  = '#FFFFFF';
        document.memberForm.maidenName.disabled = false;	document.memberForm.maidenName.style.background  = '#FFFFFF';
        document.memberForm.nickName.disabled = false;	document.memberForm.nickName.style.background  = '#FFFFFF';
        document.memberForm.gender.disabled = false;	document.memberForm.gender.style.background  = '#FFFFFF';
        document.memberForm.yearIn.disabled = false;	document.memberForm.yearIn.style.background  = '#FFFFFF';
        document.memberForm.yearOut.disabled = false;	document.memberForm.yearOut.style.background  = '#FFFFFF';
        if (document.memberForm.dormitoryId != null){
        	document.memberForm.dormitoryId.disabled = false;	document.memberForm.dormitoryId.style.background  = '#FFFFFF';
        }
        document.memberForm.partialNameSearch.disabled = false;  	document.memberForm.partialNameSearch.style.background  = '#FFFFFF';
        document.memberForm.partialNameSearch.checked = true;
    }


    function disableAll() {
        document.memberForm.firstName.disabled = true;  document.memberForm.firstName.style.background  = '#BBBBBB';
        document.memberForm.memberUserName.disabled = true;  document.memberForm.memberUserName.style.background  = '#BBBBBB';
         document.memberForm.email.disabled = true;  document.memberForm.email.style.background  = '#BBBBBB';
        document.memberForm.lastName.disabled = true;	document.memberForm.lastName.style.background  = '#BBBBBB';
        document.memberForm.maidenName.disabled = true;	document.memberForm.maidenName.style.background  = '#BBBBBB';
        document.memberForm.nickName.disabled = true;	document.memberForm.nickName.style.background  = '#BBBBBB';
        document.memberForm.gender.disabled = true;	document.memberForm.gender.style.background  = '#BBBBBB';
        document.memberForm.yearIn.disabled = true;	document.memberForm.yearIn.style.background  = '#BBBBBB';
        document.memberForm.yearOut.disabled = true;	document.memberForm.yearOut.style.background  = '#BBBBBB';
        if (document.memberForm.dormitoryId != null){
        	document.memberForm.dormitoryId.disabled = true;	document.memberForm.dormitoryId.style.background  = '#BBBBBB';
        }
        document.memberForm.partialNameSearch.disabled = true;	document.memberForm.partialNameSearch.style.background  = '#BBBBBB';
        document.memberForm.partialNameSearch.checked = false;
    }

<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->


function ViewPhoto(img){
  foto1= new Image();
  foto1.src=(img);
  Controlla(img);
}
function Controlla(img){
  if((foto1.width!=0)&&(foto1.height!=0)){
    viewFoto(img);
  }
  else{
    funzione="Controlla('"+img+"')";
    intervallo=setTimeout(funzione,20);
  }
}
function viewFoto(img){
  largh=foto1.width+20;
  altez=foto1.height+20;
  stringa="width="+largh+",height="+altez;
  finestra=window.open(img,"",stringa);
}

// End -->
