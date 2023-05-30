fun main() {
    val grid = MutableList(3) { MutableList(3) {' '}}
    displayGrid(grid)
    var currentPlayer = 'X'
    while (true) {
        print("> ")
        val inputCoordinates = readLine()
        if(inputCoordinates != null) {
            val coordinates = inputCoordinates.trim().split(" ")
            if (coordinates.size == 2) {
                val row = coordinates[0].toIntOrNull()
                val col = coordinates[1].toIntOrNull()
                if(row !=null && col !=null) {
                    if(row in 1..3 && col in 1..3) {
                        val rowIndex = row - 1
                        val colIndex = col - 1
                        if ( grid[rowIndex][colIndex] == ' ' || grid[rowIndex][colIndex] == '_') {
                            // update the grid with the players move
                            grid[rowIndex][colIndex] = currentPlayer
                            //display updated grid
                            displayGrid(grid) 
                            //check if game is over
                            val gameState = analyzeGameState(grid)
                            if (gameState != "Game not finished") {
                                println(gameState)
                                break
                            }
                            // Switch to the next player
                            currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
                            continue
                            // return 
                        } else {
                            println("This cell is occupied! Choose another one!")
                        }
                    } else {
                        println("Coordinates should be from 1 to 3!")
                    }
                } else {
                    println("You should enter numbers!")
                }
            }
        }
    }
}

fun displayGrid(grid: List<List<Char>>) {
    println("---------")
    for (row in grid) {
        print("| ")
        for (cell in row) {
            print("$cell ")
        }
        println("|")
    }
    println("---------")
}

fun analyzeGameState(grid: List<List<Char>>): String {
    var xCount = 0
    var oCount = 0
    var emptyCount = 0
    var xWins = false
    var oWins = false
    // check for rows
    for (row in 0..grid.size - 1) {
        if (grid[row][0] != ' ' && grid[row][0] == grid[row][1] && grid[row][0] == grid[row][2])
        {
            if (grid[row][0] == 'X') xWins = true
            if (grid[row][0] == 'O') oWins = true
        }
    }
    //check for columns
    for (col in 0..grid.size - 1) {
        if (grid[0][col] != ' ' && grid[0][col] == grid[1][col] && grid[0][col] == grid[2][col])
        {
            if (grid[0][col] == 'X') xWins = true
            if (grid[0][col] == 'O') oWins = true
        }
    }
    //check for diagonal
      if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
        if (grid[0][0] == 'X') xWins = true
        if (grid[0][0] == 'O') oWins = true
    }
    if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
        if (grid[0][2] == 'X') xWins = true
        if (grid[0][2] == 'O') oWins = true
    }

    for (row in grid) {
        for (cell in row) {
            when (cell) {
                'X' -> xCount++
                'O' -> oCount++
                 else -> emptyCount++
            }
        }
    }
    return when{
        // xWins && oWins || Math.abs(xCount - oCount) >=2 -> "Impossible"
        xWins -> "X wins"
        oWins -> "O wins"
        emptyCount == 0 -> "Draw"
        else -> "Game not finished"
    }
}
