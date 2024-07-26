// A funky function, serves no real purpose here. Just a usual counter
const counter = (function () {
    let count = 6; // Initial tries value

    const decrement = (x = 1) => count = count - x;
    const getCount = () => count;

    return {
        decrement,
        getCount
    }
})();

/*
Initializing all HTML elements, that we will interact with.
Let rightAnswer - self-explanatory
*/
rightAnswer = parseAnswer(rightAnswer);

let successPopup = document.getElementById('successPopup');
let failurePopup = document.getElementById('failurePopup');

let answerBtn = document.getElementById('answerBtn');
let answerInput = document.getElementById('answerInput');

let closeSpan = document.getElementsByClassName('close')[0];
let hiddenButton1 = document.getElementsByClassName('hidden-button')[0];
let hiddenButton2 = document.getElementsByClassName('hidden-button')[1];

let failureText = document.getElementById('failureText');


/**
 ** If we click on an answer button, which we declared earlier, we check if we need to open a popup,
 ** and if we do, ...style.display = "block" will overwrite the "display: none" property in css.
 */
answerBtn.onclick = function () {
    // Empty input scenario
    if (answerInput.value === "")
        return null;

    // Correct guess scenario + parsing answers
    if (parseAnswer(answerInput.value) === rightAnswer) {
        successPopup.style.display = "block"; // ! OPEN TAB WITH RATING A PUZZLE.
        return true;
    }

    // Incorrect guess scenario
    counter.decrement();
    if (counter.getCount() > 0) {
        failureText.innerHTML = counter.getCount();
    } else {
        failureText.innerHTML = "None! Better luck next time!";
        closeSpan.style.display = "none";       // User can't just close the popup now, he either refreshes the page or exits
        hiddenButton1.style.display = "block";  // Initially hidden buttons are now visible
        hiddenButton2.style.display = "block";  //
    }

    failurePopup.style.display = "block";
    return false;
}

/**
 ** Self-explanatory, just closing the popup by setting the display property back to 'none'
 */
closeSpan.onclick = function () {
    failurePopup.style.display = "none";
}

function parseAnswer(answer) {
    return answer.replace(/\s+/g, '')               // remove whitespaces
        .toLowerCase();         // to lowercase
}