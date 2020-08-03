function addCategory(type){

    var category = document.getElementById("addCategory").value;
    document.getElementById("addCategory").value = "";
    var select = document.getElementById("categories");

    if(categoryExists(category, type)){

        let input = document.getElementById("addCategory");
        document.getElementById("addCategory").value = category;
        createError(input, 'Such category already exists!');
    } else {

        let categoryToAdd = {};
        categoryToAdd[category] = {};
        var categories = JSON.parse(getCategoriesFromLS(type));
        categories.push(categoryToAdd);
        console.log(categories);

        saveCategriesToLS(type, categories);

        let option = document.createElement("option");
        option.innerText = category;
        select.appendChild(option);

    }
}

function categoryExists(categoryName, type) {
    for(let elem of JSON.parse(getCategoriesFromLS(type))){
        if(elem.hasOwnProperty(categoryName)){
          return true;
        }
    }
    return false;
}
