






//listner of button action
$( document ).ready(function() {
    $("#btn").click(
    function(){
      sendAjaxForm( 'ajax_form', 'action_ajax_form.php');
      return false; 
    }
  );
});
 //sending ajax function
function sendAjaxForm(ajax_form, url) {
    $.ajax({
        url:     url, 
        type:     "POST",
        dataType: "html",
        data: $("#"+ajax_form).serialize(),  
        success: function(response) { //
          result = $.parseJSON(response);
          $('#myUL').html(newElement(result.todo));

      },
      error: function(response) { //
            $('#result_form').html('Error while sending data');
      }
  });
}
//creating close button near all li whith class lp 
var myNodelist = document.getElementsByClassName("lp");
var i;
for (i = 0; i < myNodelist.length; i++) {
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    myNodelist[i].appendChild(span);
  }
//close button works  
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    var temp=close[i].parentElement.innerHTML.indexOf('<span class="close">×</span>');
    var victim=close[i].parentElement.innerHTML.slice(0,temp);
    close[i].onclick = function() {
    var div = this.parentElement;
    div.style.display = "none";
    toDelete(victim,'toDelete.php');
    }
  }
  //sending ajax function
function toDelete(victim,url){
  $.ajax({
    url:     url, 
    type:     "POST", 
    dataType: "html", 
    data:"victim="+victim,
    success: function(response) { //all ok,data was send
          window.location.reload();
      },
  })
}

//creating new element in <uL> with class="todo"(creating new todo)
function newElement(inputValue) {
    var li = document.createElement("li");
    var t = document.createTextNode(inputValue);
    li.appendChild(t);
    if (inputValue === ''||inputValue=="lil hacker") {
      alert("Am i joke to u??");
    } else {
    document.getElementById("myUL").appendChild(li);
    }
    document.getElementById("myInput").value = "";
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);

    for (i = 0; i < close.length; i++) {
      close[i].onclick = function() {

        var div = this.parentElement;
        div.style.display = "none";
      }
    } 
    window.location.reload();
    }
