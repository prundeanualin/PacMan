package ghosts;

import java.util.ArrayList;
import java.util.List;

public class GhostFactory {

    /**.
     * creating the appropriate ghosts, considering the current level
     * @param lvlCount current level
     * @return list of ghosts
     */
    public List createGhosts(int lvlCount) {
        List ghost = new ArrayList();
        ghost.add(0);
        return ghost;
    }
}
