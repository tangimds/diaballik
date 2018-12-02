package diaballik.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NoobLevel implements Level {

	@Override
	public Action chooseAction(final Board board) {

	}

	public Action pickAction(final Board board){
		final ArrayList<Piece> blackPieces = new ArrayList<>();
		board.getPieces().stream().filter(p -> p.getColor() == Color.BLACK).forEach(blackPieces::add);
		final Action a;
		final Random r = new Random();
		if(r.nextBoolean()){//Move a piece
			Collections.shuffle(blackPieces);
			final Piece p = blackPieces.get(0);
			int dl = -1 + 2*r.nextInt(1);
			if(r.nextBoolean()){
				a = new MovePiece(p, p.getX()+dl,p.getY());
			}
			else{
				a = new MovePiece(p, p.getX(),p.getY()+dl);
			}
		}
		else{//Move a ball

		}

		if(!a.verifyAction()) a = pickAction(board);
		return a;
	}

}
