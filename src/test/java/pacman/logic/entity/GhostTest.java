package pacman.logic.entity;

import org.junit.jupiter.api.Test;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;
import pacman.logic.level.Square;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

    private final String map = "P.*#";
    private Board board;
    private Ghost ghost;

    @Test
    public void updatePacmanDies(){
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(1, 0));
        board.pacman.setAlive(true);
//        GameController.getInstance().getGame().setRunning(true);

//        ghost.setDirection(Direction.LEFT);
        board.pacman.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertFalse(board.pacman.isAlive());
    }

    @Test
    public void updatePacmanAlive() {
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(2, 0));
        board.pacman.setAlive(true);
        board.pacman.setDirection(Direction.DOWN);
//        ghost.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertTrue(board.pacman.isAlive());
    }

//    @Test
//    public void ghostDoesntMove(){
//        board= MapParser.parseMapFromString(map);
//        ghost= new Blinky(board, board.getSquare(1,0));
//        ghost.update(0.5);
//        assertEquals(1, ghost.getX());
//        assertEquals(0, ghost.getY());
//    }

    /**
     * Test to get the square options the ghost can move to.
     * The neighbors don't consist of solids(walls).
     */
    @Test
    public void getOptionsNoWalls(){
        String mapp= "P...*#";
        board= MapParser.parseMapFromString(mapp);
        ghost= new Blinky(board, board.getSquare(2,0));
        List<Square> options = new ArrayList<>();
        options.add(new Square(board, 1,0));
        options.add(new Square(board, 3, 0));
        assertEquals(2, ghost.getOptions().size());
//
        assertEquals(options, ghost.getOptions());

//        assertEquals(options, ghost.getOptions());
    }

//    @Test
//    public void getOptionsWithWalls(){
//        String mapp= "#P.##";
//        board= MapParser.parseMapFromString(mapp);
//        ghost= new Blinky(board, board.getSquare(1,0));
//        List<Square> options = new ArrayList<>();
//        options.add(new Square(board, 0,0));
////        System.out.println(square.hasSolid());
////        assertEquals(ghost.getOptions(), options);
////        assertEquals(1, ghost.getOptions().size());
//        System.out.println(board.pacman.getY());
//        System.out.println(ghost.getY());
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void closestNeighborThrowsException(){
//        String mapp= ".";
//        board= MapParser.parseMapFromString(mapp);
//        ghost= new Blinky(board, board.getSquare(0,0));
//        List<Square> options= new ArrayList<>();
//
//
//    }






}
