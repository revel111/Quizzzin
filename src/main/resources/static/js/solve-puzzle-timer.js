const display = document.getElementById("display");
let timer = null;
let startTime = Date.now();
let elapsedTime = 0;

function startSolvingTimer() {
    timer = setInterval(update, 1000);
}

function update() {
    const currentTime = Date.now();
    elapsedTime = currentTime - startTime;

    let hours = Math.floor(elapsedTime / (1000 * 60 * 60));
    let minutes = Math.floor(elapsedTime / (1000 * 60) % 60);
    let seconds = Math.floor(elapsedTime / 1000 % 60);

    hours = String(hours).padStart(2, "0");
    minutes = String(minutes).padStart(2, "0");
    seconds = String(seconds).padStart(2, "0");

    display.textContent = `${hours}:${minutes}:${seconds}`;
}