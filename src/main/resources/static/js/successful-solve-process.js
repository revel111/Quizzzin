let confirmBtn = document.getElementById('confirmBtn');
let successPopup = document.getElementById('successPopup');
let scorePopup = document.getElementById('scorePopup');

async function savePoints(score) {
    console.log(score);
    console.log(idUser);
    console.log(idPuzzle);

    const response = await fetch('/quizzzin/api/scores', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({score, idUser, idPuzzle})
    });

    return response.ok;
}

function evaluatePoints() {
    return 100;
}

function process() {
    const score = evaluatePoints();

    savePoints(score);

    const scorePanelContent = document.querySelector('#score');
    if (scorePanelContent) {
        scorePanelContent.textContent = `You have earned ${score} points!`;
        scorePopup.style.display = "block";
    }
}

confirmBtn.onclick = function () {
    scorePopup.style.display = "none";
    successPopup.style.display = "block";
}