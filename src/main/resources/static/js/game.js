let boardSize;
let isGameOver = false;
let playerName = '';

function startGame() {
    playerName = document.getElementById('playerName').value || 'Player'; // Get player name or default to 'Player'
    boardSize = document.getElementById("sizeInput").value;
      // Check if board size is valid and odd
      if (!boardSize || isNaN(boardSize) || boardSize < 3 || boardSize % 2 === 0) {
        alert("Please enter a valid odd board size (3, 5, 7, etc.).");
        return;
    }

    fetch(`/api/game/start?size=${boardSize}`, { method: "POST" })
        .then(res => res.json())
        .then(data => {
            drawBoard(data.board);
            isGameOver = false;
        });
}

function drawBoard(board) {
    const boardDiv = document.getElementById("board");
    boardDiv.innerHTML = "";
    boardDiv.style.gridTemplateColumns = `repeat(${boardSize}, 50px)`;

    board.forEach((row, r) => {
        row.forEach((cell, c) => {
            let cellDiv = document.createElement("div");
            cellDiv.classList.add("cell");
            cellDiv.textContent = cell;

            if (isGameOver) {
                cellDiv.style.pointerEvents = "none";
            } else {
                cellDiv.onclick = () => makeMove(r, c);
            }

            boardDiv.appendChild(cellDiv);
        });
    });
}

function makeMove(row, col) {
    if (isGameOver) return;

    fetch(`/api/game/move?row=${row}&col=${col}`, { method: "POST" })
        .then(res => res.json())
        .then(data => {
            drawBoard(data.board);
            if (data.gameOver) {
                setTimeout(() => {
                    switch(data.winner){
                        case 0: alert(`Game is over with draw!`);
                                break;
                        case 1: alert(`Congrats ${playerName}, you win!`);
                                break;
                        case 2: alert(`Sory ${playerName}, you lose!`);
                                break;
                        
                    }
                  
                    isGameOver = true;
                }, 100);
               
            }
            
        });
}
