function calcLenght(){
    const logo=document.querySelectorAll('#logo path');
    for(var i=0;i<logo.length;i++){
      console.log(logo[i].getTotalLength());  
    }
}
function playAuido(){
    var sound = new Audio("./src/assets/sounds/start.mp3");
    sound.play();
    
}
  