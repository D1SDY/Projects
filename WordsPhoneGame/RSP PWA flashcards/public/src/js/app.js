if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register("/sw.js")
      .then(function() {
        console.log("SW registered");
      });
  }

function getCategoriesFromLS(type) {
  return window.localStorage.getItem(type);
};


function saveCategriesToLS(type, categoriesList) {
  window.localStorage.setItem(type, JSON.stringify(categoriesList));
}
