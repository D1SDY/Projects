var localSt = JSON.parse(window.localStorage.getItem('words'));
console.log(localSt);

var positions = []

generateButtons();

setPositionsOfCategories();

function setPositionsOfCategories(){
    for(let elem of localSt){
        positions.push(Object.keys(elem)[0]);
    }
    console.log(positions);
}


function generateButtons(){

    for(let elem of localSt){
        let categoryName = Object.keys(elem)[0];
        let butt = document.createElement('button');
        butt.innerText = categoryName;
        
        butt.addEventListener('click', function() {

            let text = butt.innerText;
            let position = positions.indexOf(text);

            let str = '../../cards.html?' + position;
            window.location.href = str;
        });
        document.getElementById('cont').appendChild(butt);
    }
}


//make separate function for addEventListneres()
