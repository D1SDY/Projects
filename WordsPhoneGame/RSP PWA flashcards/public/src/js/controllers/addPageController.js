var newWordInput = document.getElementById("newWord");
var translationInput = document.getElementById('newWordTranslation');
var categoryInput = document.getElementById("addCategory");

document.getElementById('wordsLabel').addEventListener('click', () => generateOptions(JSON.parse(getCategoriesFromLS('words'))));
document.getElementById('phrasesLabel').addEventListener('click', () => generateOptions(JSON.parse(getCategoriesFromLS('phrases'))));


document.getElementById("addWord").addEventListener('click' , () => {
      removeErrors();
      var firstResult = validation(newWordInput);
      var secondResult = validation(translationInput);
      if (firstResult && secondResult) addToCategory();
  });


document.getElementById("addCategoryToPhrases").addEventListener('click',() => {
    removeErrors();
    if (validation(categoryInput)) addCategory("phrases");
  });


document.getElementById("addCategoryToWords").addEventListener('click', () => {
    removeErrors();
    if (validation(categoryInput)) addCategory("words");
  });


newWordInput.addEventListener("click", () => newWord.style.border = "1px solid darkgrey");
translationInput.addEventListener("click", () => translationInput.style.border = "1px solid darkgrey");
categoryInput.addEventListener("click", () => categoryInput.style.border = "1px solid darkgrey");


function validation(input) {
    var word = input.value;
    var hasNumber = /\d/;

    if(word.length == 0) {
      createError(input, 'The field cannot be empty!');
      return false;
    }

  if (hasNumber.test(word)){
      createError(input, 'The field cannot contain numbers!');
      return false;
    }

    var arr = word.split(' ');
    if(arr.length == 1 && arr[0].length > 15) {
      createError(input, 'Too long word!');
      return false;
    }

    return true;
  }


function createError(element, message) {
  element.style.border = "2px solid red";
  element.insertAdjacentHTML("afterend",`<p class='error'>${message}</p>`);
}

function removeErrors() {
  document.querySelectorAll('.error').forEach(e => e.remove());
}
