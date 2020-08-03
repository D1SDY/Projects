

function createUrlAndDirect() {
  console.log('start pressed');
  let gametype = (document.getElementById('opt1').checked) ? 'words' : 'phrases';
  let lang = (document.getElementById('lang').checked) ? 'ru-cz' : 'cz-ru';

  let urlCode = `${gametype}?${lang}?${document.getElementById('categories').value}`;
  console.log(urlCode)

  window.location.href = `cards.html?${urlCode}`;
}


function lang_change(){
    var checkbox =document.getElementById("lang");
    var checkmark = document.getElementById("checkmark");
    if(checkbox.checked == true)checkmark.innerText= "ru - cz";
    else checkmark.innerText= "cz - ru";

}


function generateOptions(categories) {
  deleteOptions();
  let select = document.getElementById('categories');

  for(let elem of categories) {
      let opt = document.createElement('option');
      opt.innerText = Object.keys(elem)[0];
      select.appendChild(opt);
  }
}

function deleteOptions() {
    let sel = document.getElementById('categories');
    let length = sel.options.length;

    for(let i = 0; i < length; i++) {
        sel.options[0] = null;
    }
}
