import java.util.List;


public class OthelloMaxPlayer extends OthelloPlayer{
	
	protected int d;
	public OthelloMaxPlayer(int depth){
		d = depth;
	}
	
	protected int min(OthelloState state, int depth){
		if (state.gameOver() || depth == 0){
			return state.score();
		}else{
			List<OthelloMove> moves = state.generateMoves();
			int v = Integer.MAX_VALUE;
			for (OthelloMove move: moves){
				OthelloState tmp = state.applyMoveCloning(move);
				int max = max(tmp,  depth - 1);
				v = v < max ? v : max;
			}
			return v;
		}
	}
	
	protected int max(OthelloState state, int depth){
		if (state.gameOver() || depth == 0){
			return state.score();
		}else{
			List<OthelloMove> moves = state.generateMoves();
			int v = Integer.MIN_VALUE;
			for (OthelloMove move: moves){
				OthelloState tmp = state.applyMoveCloning(move);
				int min = min(tmp, depth - 1);
				v = v < min ? min : v;
			}
			return v;
		}
	}
	
	@Override
	public OthelloMove getMove(OthelloState state) {
		OthelloMove result  = null;
		int maxMove = Integer.MIN_VALUE;
		List<OthelloMove> moves = state.generateMoves();
		for (OthelloMove move: moves){
			OthelloState tmp = state.applyMoveCloning(move);
			int cur = min(tmp, d);
			if (maxMove < cur){
				maxMove = cur;
				result = move;
			}
		}
		return result;
	}
}
