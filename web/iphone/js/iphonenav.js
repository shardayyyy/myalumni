
(function() {

var animateX = -20;
var animateInterval = 0;

var currentPage = null;
var currentDialog = null;
var currentWidth = 0;
var currentHash = location.hash;
var hashPrefix = "#_";
var pageHistory = [];

var loadingId = "";
var RequestObj;
var images = [];



document.getElementsByClassName = function(className) {
	var children = document.getElementsByTagName('*') || document.all;
	var elements = new Array();

	for (var i = 0; i < children.length; i++) {
		var child = children[i];
		var classNames = child.className.split(' ');
		for (var j = 0; j < classNames.length; j++) {
			if (classNames[j] == className) {
				elements.push(child);
 				break;
			}
		}
	}
	return elements;
}



addEventListener("load", function(event)
{
    document.getElementById('spinner').style.display='none';
    var body = document.getElementsByTagName("body")[0];
    for (var child = body.firstChild; child; child = child.nextSibling)
    {
        if (child.nodeType == 1 && child.getAttribute("selected") == "true")
        {
            showPage(child);
            break;
        }
    }

    setInterval(checkOrientAndLocation, 300);
    setTimeout(scrollTo, 0, 0, 1);
}, false);
    
addEventListener("click", function(event)
{
    event.preventDefault();

    var link = event.target;
    while (link && link.localName.toLowerCase() != "a")
        link = link.parentNode;
	
	if (link && link.hash && link.hash=='#')
	{
		// this is a normal link - handle it outside the javascript system
		document.location=link;	
	}
	else if (link && link.hash)
    {
        var page = document.getElementById(link.hash.substr(1));
        if (!page) {
			loadContent(link.hash.substr(1));
		}
		else showPage(page);
    }

}, true);

function loadContent(str)
{
		
	// if its a feed, create a div
	if (str.substr(0,4)=='feed')
	{
		IssueRequest("GET", "/radio/podcasts/"+str.substring(5)+"/assets/iphone_keepnet.sssi", Loader);
		var newEl = document.createElement('div');
		newEl.setAttribute('id', str);
		newEl.setAttribute('title', "Podcast");
		newEl.setAttribute('class', "keepnet");
	}
	else
	{
	// if its a list of feeds, create an ul
		IssueRequest("GET", "lists/" + str + ".sssi", Loader);
		var newEl = document.createElement('ul');
		newEl.setAttribute('id', str);
		newEl.setAttribute('title', "Member Details");
		newEl.setAttribute('class', "list");
	}
	document.body.appendChild(newEl);
	loadingId = str;
	
	
	
	
}



function Loader()
{
   document.getElementById('spinner').style.display='block';
   if (RequestObj.readyState == 4)
	{
      if (RequestObj.status == 200) 
		{ 
			var emptyId = document.getElementById(loadingId);
			emptyId.innerHTML = RequestObj.responseText;

			//hide spinner and move to new page
			document.getElementById('spinner').style.display='none';
			
			if (RequestObj.responseText=='') emptyId.innerHTML="<p>Sorry, there are no information matching your selection.</p>";
			else showPage(emptyId);
		 
		} else {
		 document.getElementById('spinner').style.display='none';
		 throw ("Error");
		}
	}
}


function IssueRequest (method, url, handler)
{
	if(window.XMLHttpRequest) 
	{
		RequestObj = new XMLHttpRequest ();
	}
	
   RequestObj.onreadystatechange = handler;
   RequestObj.open (method, url, true);
   RequestObj.send ("");

}

function checkOrientAndLocation()
{
    if (window.outerWidth != currentWidth)
    {
        currentWidth = window.outerWidth;

        var orient = currentWidth == 320 ? "profile" : "landscape";
        document.body.setAttribute("orient", orient);
    }

    if (location.hash != currentHash)
    {
        currentHash = location.hash;

        var pageId = currentHash.substr(hashPrefix.length);
        var page = document.getElementById(pageId);
        if (page)
        {
            var index = pageHistory.indexOf(pageId);
            var backwards = index != -1;
            if (backwards)
                pageHistory.splice(index, pageHistory.length);
                
            showPage(page, backwards);
        }
    }
}
    
function showPage(page, backwards)
{
    if (currentDialog)
    {
        currentDialog.removeAttribute("selected");
        currentDialog = null;
    }

    if (page.className.indexOf("dialog") != -1)
        showDialog(page);
    else
    {        
        location.href = currentHash = hashPrefix + page.id;
        pageHistory.push(page.id);

        var fromPage = currentPage;
        currentPage = page;

        var pageTitle = document.getElementById("pageTitle");
        // pageTitle.innerHTML = "<img src=\"img/myalumni_120_03.png\" alt=\"MyAlumni\" align=\"absmiddle\"> " + page.title || "";
			pageTitle.innerHTML = page.title;
        var homeButton = document.getElementById("homeButton");
        if (homeButton)
            homeButton.style.display = ("#"+page.id) == homeButton.hash ? "none" : "inline";

        if (fromPage)
            setTimeout(swipePage, 0, fromPage, page, backwards);
    }
}

function swipePage(fromPage, toPage, backwards)
{        
   fromPage.style.display = "none";
   toPage.style.left = "80%";
   toPage.setAttribute("selected", "true");
   scrollTo(0, 1);
   var removed = false;
    
	if (fromPage.hasAttribute("id"))
	{
		fromId = fromPage.getAttribute("id");
		// remove all pages - except welcome, genres and networks
		if (!(fromId == "welcome" || fromId == "firstname" || fromId == "lastname" || fromId == "dormitory" || fromId == "gender" || fromId == "about"))
		{
			fromPage.parentNode.removeChild(fromPage);
			removed = true;
		}
	}
	
    var percent = 100;
    var timer = setInterval(function()
    {
        percent += animateX;
		
		//fromPage.style.left = (backwards ? (100-percent) : (percent-100)) + "%"; 
        toPage.style.left = (backwards ? -percent : percent) + "%"; 
		
		if (percent <= 0)
        {
            percent = 0;
			if (!removed) 
			{
				fromPage.removeAttribute("selected");
				fromPage.style.left = "-100%";
				fromPage.style.display = "block";
			}
			clearInterval(timer);
			
			onSwipePageComplete(toPage);
		}
    }, animateInterval);
}


function onSwipePageComplete(toPage) {
	if (toPage.hasAttribute("class")) 
	{
		if (toPage.getAttribute("class") == "list")
		{
			//load images on list page
			loadImages(toPage);
		}
		else if (toPage.getAttribute("class") == "keepnet")
		{
			// reflect the main image on a keepnet
			reflect(toPage);
		}
	}

}


function loadImages(page)
{
	 spans = new Array();
	 spans = page.getElementsByTagName("span");
	images = new Array(spans.length);
	
	 for(i=0; i<spans.length; i++)
	 {
		if(!spans[i].hasAttribute("id"))
		{
			imageObj = new Image();
			imageObj.number = i;
			//imageObj.style.opacity = 0;
			imageObj.onload = function() {spans[this.number].appendChild(this); /*fadeImage(this.number);*/};
			imageObj.src = spans[i].getAttribute("name");
			spans[i].setAttribute("id", "loaded-image" + i);
			spans[i].removeAttribute("name");
			images[i] = imageObj;
		}
	 }
}

function fadeImage(imageId)
{

    var percent = 0;
    var timer = setInterval(function()
    {
        percent += 0.1;
        if (percent == 1)
        {
            clearInterval(timer);
        }

        images[imageId].style.opacity = percent; 
		
    }, 250);
	
	/* Cancel onload method */
	images[imageId].onload = "";
}




function showDialog(form)
{
    currentDialog = form;
    form.setAttribute("selected", "true");
    
    form.onsubmit = function(event)
    {
        event.preventDefault();
        form.removeAttribute("selected");

        var index = form.action.lastIndexOf("#");
        if (index != -1)
            showPage(document.getElementById(form.action.substr(index+1)));
    }

    form.onclick = function(event)
    {
        if (event.target == form)
            form.removeAttribute("selected");
    }
}


})();


function reflect(toPage)
{
	var imageRef = toPage.getElementsByTagName("img")[0];
	
	if (imageRef.getAttribute("class") == "reflect")
	{
		image = new Image();
		image.src = imageRef.src;
		var canvas = document.createElement('canvas');
		if (canvas.getContext)
		{
			
			var height = 60;
			var width = 170;
			var context = canvas.getContext("2d");
		
			canvas.style.height = height+'px';
			canvas.style.width = width+'px';
			canvas.height = height;
			canvas.width = width;
			
			context.save();
			context.translate(0,image.height-1);
			context.scale(1,-1);
			context.drawImage(image, 0, 0, width, image.height);
			context.restore();
			
			context.globalCompositeOperation = "destination-out";
			var gradient = context.createLinearGradient(0, 0, 0, height);
			
			gradient.addColorStop(1, "rgba(255, 255, 255, 1.0)");
			gradient.addColorStop(0, "rgba(255, 255, 255, 0.5)");
	
			context.fillStyle = gradient;
			if (navigator.appVersion.indexOf('WebKit') != -1) 
			{
				context.fill();
			} 
			else 
			{
				context.fillRect(0, 0, width, height*2);
			}
			
			/* add the canvas below the image */
			//var logos = [];
			//logos = document.getElementsByClassName("reflected-image");
			toPage.firstChild.firstChild.nextSibling.appendChild(canvas);
				
		}
		imageRef.setAttribute("class", "reflected");
	}
}