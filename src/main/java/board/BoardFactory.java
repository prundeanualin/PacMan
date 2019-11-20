package board;

public class BoardFactory {

    public Board createBoard(int lvl) {
        Board bd = new Board(lvl);
        return bd;
    }
}
