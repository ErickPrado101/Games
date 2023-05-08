var pacMan = {
    x: 0,
    y: 0,
    direction: "right",
    speed: 50
};

function movePacMan() {
    if (pacMan.direction === "right") {
        pacMan.x += 10;
    } else if (pacMan.direction === "left") {
        pacMan.x -= 10;
    } else if (pacMan.direction === "up") {
        pacMan.y -= 10;
    } else if (pacMan.direction === "down") {
        pacMan.y += 10;
    }
    
    var pacManElement = document.createElement("div");
    pacManElement.className = "pac-man";
    pacManElement.style.left = pacMan.x + "px";
    pacManElement.style.top = pacMan.y + "px";
    
    var gameBoard = document.getElementById("game-board");
    gameBoard.appendChild(pacManElement);
    
    setTimeout(movePacMan, pacMan.speed);
}

movePacMan();