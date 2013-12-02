import java.util.List;


public class OthelloMinPlayer extends OthelloMaxPlayer{

	public OthelloMinPlayer(int depth) {
		super(depth);
	}
	
	@Override
	public OthelloMove getMove(OthelloState state) {
		OthelloMove result  = null;
		int minMove = Integer.MAX_VALUE;
		List<OthelloMove> moves = state.generateMoves();
		for (OthelloMove move: moves){
			OthelloState tmp = state.applyMoveCloning(move);
			int cur = max(tmp, d);
			if (minMove > cur){
				minMove = cur;
				result = move;
			}
		}
		return result;
	}

}
