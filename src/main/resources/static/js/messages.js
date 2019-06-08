  $(document).ready(function(){
    $('.sidenav').sidenav();
    
    updateScroll();
    
  });
  
 
  function updateScroll(){
      var element = document.getElementById("scrollable");
      element.scrollTop = element.scrollHeight;
      console.log("hello");
  }