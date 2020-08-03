const table = document.getElementsByClassName('table')[0];
let counter = 0;
let checkboxClicked = false;
let fireworkImg= document.getElementById("firework");


document.getElementById('next').addEventListener('click', (e) => {
  console.log(e.target.innerText);
  if (e.target.innerText == "FINISH") {
      e.target.classList.add("toHomeBtn");
    document.querySelectorAll('.cont').forEach(e => e.remove());
    fireworkImg.style.opacity = 1;
    document.getElementById("home-btn").style.display ="none";
    document.getElementById("category-name").innerText = "Congratulations!";
    // let text = document.createElement("p");
    // text.classList.add( "congrats");
    // text.innerText = "Congratulations!";
    // table.appendChild(text);
    e.target.innerText = "BACK TO HOME"
    window.localStorage.removeItem("savedGame");
    return;
  }
  if (e.target.innerText == "BACK TO HOME") {
    window.location.href = "index.html";
    return;
  }
  var checkboxes = document.getElementsByTagName("input");
  for (box of checkboxes) {
    if (box.checked) {
      var [x,y] = box.id.split("/");
      var categoryList = JSON.parse(getCategoriesFromLS('savedGame'));
      categoryList.push([x,y]);
      saveCategriesToLS('savedGame',categoryList);
    }
  }
  table.innerHTML = "";
  counter = JSON.parse(getCategoriesFromLS("counter"));
  counter+=5;
  saveCategriesToLS('counter',counter);
  console.log(JSON.parse(getCategoriesFromLS('savedGame')));
  printCards(JSON.parse(getCategoriesFromLS('savedGame')),counter);
  window.scrollTo({ top: 0, behavior: 'smooth' });
})

if(window.location.search) {

    const substr = window.location.search.substring(1);

    let [type, langFromTo, categoryTo] = substr.split('?');

    console.log(categoryTo);
    var category = Object.entries(selectCorrectCategory(type, categoryTo));
    if (langFromTo == 'cz-ru') {
      saveCategriesToLS('savedGame',category);
    } else {
      for (el of category) {
        console.log(el);
        [el[0], el[1]] = [el[1], el[0]];
        console.log(el);
      }
      saveCategriesToLS('savedGame',category);
    }
    saveCategriesToLS('counter',0);
    printCards(category, counter);

    document.getElementById("category-name").innerText = categoryTo;

} else {
    if(getCategoriesFromLS('savedGame')) {
        document.getElementById("category-name").innerText = JSON.parse(getCategoriesFromLS('savedGame'))[0][0];
        let counter = JSON.parse(getCategoriesFromLS("counter"));
        for (let i = counter; i < counter + 5; i++) {
          let [key, value] = JSON.parse(getCategoriesFromLS('savedGame'))[i];
          table.appendChild(createCard2( key , value));
        }

    } else {
        document.getElementById("category-name").innerText = "You have not any saved game";
        document.getElementById("home-btn").style.display ="none";
        document.getElementById('next').innerText = "BACK TO HOME";
        document.getElementById('next').style.marginTop = "30vh";
    }
}


function printCards(array,counter) {
  if (counter >= JSON.parse(getCategoriesFromLS("savedGame")).length - 5) {
    document.getElementById('next').innerText = "Finish";
  }
  for(let i = counter; i < counter + 5; i++) {
    if (array[i] != null) {
      const [key, value] = array[i];
      table.appendChild(createCard2(key, value));
    }
  }
}


function clickCard2() {
    if(!checkboxClicked) {
        this.classList.toggle('show-back');
    }
    else checkboxClicked = false;

}

function checkClicked() {
    checkboxClicked =  true;
}

function createCard2(word , backWord){
    const id = `${word}/${backWord}`;
    const container = document.createElement('div');
    container.className = "cont";

    const card = document.createElement('div');
    card.className = "box";
    card.addEventListener("click" , clickCard2);

    const check = document.createElement('input');
    check.setAttribute("type", "checkbox");
    check.setAttribute("id",id);
    check.style.visibility = "hidden";
    check.addEventListener("click", checkClicked);


    const checkLabel = document.createElement('label');
    checkLabel.htmlFor = id;
    checkLabel.className = "checkmark";
    checkLabel.addEventListener("click", checkClicked)

    const front = document.createElement('div');
    front.className = "box__face box__face--front";
    const front_text = document.createElement('div');
    front_text.className="text-in-card";
    front_text.innerText = word;
    // front.innerText = word;
    front.appendChild(front_text)
    front.appendChild(check);
    front.appendChild(checkLabel);

    const back = document.createElement('div');
    back.className = "box__face box__face--back";
    const back_text = document.createElement('div');
    back_text.className="text-in-card";
    back_text.innerText = backWord;
    back.appendChild(back_text)
    // back.innerText = backWord;

    card.appendChild(back);
    card.appendChild(front);


    container.appendChild(card);

    return container;
}

function selectCorrectCategory(type, categoryTo) {

    //words/phrases
    let categories = JSON.parse(getCategoriesFromLS(type));
    let obj;

    for(let elem of categories) {
        if(elem.hasOwnProperty(categoryTo)) {
            console.log('it is here!');
            obj = Object.values(elem)[0];
            console.log(obj);
        }
    }


    return obj;
    // for(const [key, value] of Object.entries(obj)) {
    //     table.appendChild(createCard2( key , value));
    //     console.log(Object.entries(obj));
    // }
}
