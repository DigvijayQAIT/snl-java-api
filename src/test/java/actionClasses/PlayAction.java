package actionClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.qainfotech.tap.training.snl.api.Board;
import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

public class PlayAction {
	
	Board board;
	
	public PlayAction() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}

	public void addUser(String name) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		for (int i = 0; i < 2; i++) {
			board.registerPlayer(name + i);
		}
	}

	public void rollDice() throws FileNotFoundException, UnsupportedEncodingException, InvalidTurnException {
		UUID uuid;
		for (int i = 0; i < 2; i++) {
			uuid = (UUID) board.getData().getJSONArray("players").getJSONObject(i).get("uuid");
			board.rollDice(uuid);
		}
		
	}
	
}
