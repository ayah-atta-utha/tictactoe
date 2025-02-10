package  com.fwd.recruitment.tictactoe.model;

public class GameState {
    private String[][] board;
    private boolean gameOver;
    private String message;
    private int winner; //1=player,2=ai

    public GameState(char[][] board, boolean gameOver, int winner, String message) {
        this.board = convertCharArrayToStringArray(board);
        this.gameOver = gameOver;
        this.message = message;
        this.winner=winner;
    }

    public String[][] getBoard() { return board; }
    public boolean isGameOver() { return gameOver; }
    public String getMessage() { return message; }
    public int getWinner(){ return winner;}

    private String[][] convertCharArrayToStringArray(char[][] board) {
        String[][] stringBoard = new String[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                stringBoard[i][j] = String.valueOf(board[i][j]);
            }
        }
        return stringBoard;
    }
}