function v(id) {
	 for (var i=1;i<6;i++) {
		 var vid = 'v' + i;
	     if (i == id) {
             showhide = 'block';
             var txt=document.getElementById(vid)
             txt.innerHTML='<a title=\"'+vid+' eelvaade, kliki lingil või pildid et näha vormi\" href=\"V'+id+'\"><img src=\"pildid/'+vid+'.png\"></a>';
        }
        else {
             showhide = 'none';
        }
        if (document.getElementById) { // DOM3 = IE5, NS6 
            document.getElementById(vid).style.display = showhide; 
        } 
        else { 
            if (document.layers) { // Netscape 4 
                document.vid.display = showhide; 
            } 
            else { // IE 4 
                document.all.vid.style.display = showhide; 
            } 
        }
    }
}