

function addToCategory() {
  console.log(123);
    var word = document.getElementById("newWord").value;
    var translation = document.getElementById('newWordTranslation').value;
    let selectedCategory = document.getElementById('categories').value;
    console.log('selected',selectedCategory);
    addToSelectedCategory(selectedCategory, word, translation);
}




//TO DO:
//check if the word-translation is already in category
function addToSelectedCategory(category, word, translation) {
    var type = (document.getElementById('wordsRadio').checked) ? "words" : "phrases";
    console.log(type);
    var categories = JSON.parse(getCategoriesFromLS(type));
    for(let elem of categories) {
        if(Object.keys(elem)[0] === category) {
            var categoryWords = Object.keys(elem[category]);
            var categoryTranslations = Object.values(elem[category]);
            if(!wordExists(categoryWords, word, "newWord") && !wordExists(categoryTranslations, translation, 'newWordTranslation')){
              Object.values(elem)[0][word] = translation;
              saveCategriesToLS(type, categories);
              document.getElementById("newWord").value = "";
              document.getElementById('newWordTranslation').value = "";
            }
        }
    }
  }


function wordExists(words, word, elementId){
  for(let w of words){
    console.log(w);
    if(w === word){
      let input = document.getElementById(elementId);
      input.style.border = "2px solid red";
      input.insertAdjacentHTML("afterend","<p class='error'>Such word already exists</p>");
      return true
    }
  }
  return false;
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
