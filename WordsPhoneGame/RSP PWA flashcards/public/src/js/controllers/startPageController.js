document.getElementById('wordsLabel').addEventListener('click', () => generateOptions(JSON.parse(getCategoriesFromLS('words'))));

document.getElementById('phrasesLabel').addEventListener('click', () => generateOptions(JSON.parse(getCategoriesFromLS('phrases'))));

document.getElementById('startActual').addEventListener('click', () => createUrlAndDirect());

document.getElementById('lang').addEventListener('click', () => lang_change());
