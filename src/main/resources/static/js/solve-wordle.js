const state = {
    grid: Array(6)
        .fill()
        .map(() => Array(colNum).fill('')),
    currentRow: 0,
    currentCol: 0
};

let successPopup = document.getElementById('successPopup');

function updateGrid() {
    for (let i = 0; i < state.grid.length; i++)
        for (let j = 0; j < state.grid[i].length; j++) {
            const box = document.getElementById(`box${i}${j}`);
            box.textContent = state.grid[i][j];
        }
}

function startup() {
    const game = document.getElementById('game');
    drawGrid(game);
    registerKeyboardEvents();
}

function drawBox(container, row, col, letter = '') {
    const box = document.createElement('div');

    box.className = 'box';
    box.id = `box${row}${col}`;
    box.textContent = letter;

    container.appendChild(box);
    return box;
}

function drawGrid(container) {
    const grid = document.createElement('div');
    grid.className = 'grid';

    for (let i = 0; i < 6; i++)
        for (let j = 0; j < colNum; j++)
            drawBox(grid, i, j);

    container.appendChild(grid);
}

function registerKeyboardEvents() {
    document.body.onkeydown = async (e) => {
        const key = e.key;

        if (key === 'Enter' && state.currentCol === colNum) {
            const word = getCurrentWord();

            if (await isValid(word)) {
                revealWord(word);
                state.currentRow++;
                state.currentCol = 0;
            } else
                alert("This word does not exist.");
        } else if (key === 'Backspace')
            removeLetter();
        else if (isLetter(key))
            addLetter(key);

        updateGrid();
    };
}

function isLetter(key) {
    return key.length === 1 && key.match(/[a-z]/i);
}

function addLetter(letter) {
    if (state.currentCol === colNum)
        return;

    state.grid[state.currentRow][state.currentCol++] = letter;
}

function removeLetter() {
    if (state.currentCol === 0)
        return;

    state.grid[state.currentRow][--state.currentCol] = '';
}

function getCurrentWord() {
    return state.grid[state.currentRow].reduce((prev, curr) => prev + curr);
}

async function isValid(word) {
    try {
        const response = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        return response.ok;
    } catch (error) {
        console.error('Error:', error);
        return false;
    }
}

function revealWord(word) {
    const row = state.currentRow;
    const duration = 500;

    for (let i = 0; i < colNum; i++) {
        const box = document.getElementById(`box${row}${i}`);
        const letter = box.textContent;

        setTimeout(() => {
            if (letter === answer[i])
                box.classList.add('right');
            else if (letter.includes(letter))
                box.classList.add('wrong');
            else
                box.classList.add('empty');
        }, ((i + 1) * duration) / 2);

        box.classList.add('animated');
        box.style.animationDelay = `${i * duration / 2}ms`;
    }

    setTimeout(() => {
        if (answer === word) {
            // alert('Congratulations!');
            successPopup.style.display = "block";
        }
        else if (state.currentRow === 5) {
            alert(`You lost! The word was ${answer}.`); // ! add losing functionality
        }
    }, 3 * duration);
}

startup();