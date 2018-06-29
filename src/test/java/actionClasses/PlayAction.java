package actionClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.qainfotech.tap.training.snl.api.Board;
import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

/**
 * @author Digvijay
 *
 */
public class PlayAction {

	Board board;

	public PlayAction() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}

	public void addUser(String name) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException {
		for (int i = 0; i < 2; i++) {
			board.registerPlayer(name + i);
		}
	}

	public void rollDice() throws FileNotFoundException, UnsupportedEncodingException, InvalidTurnException {
		UUID uuid;
		Boolean condition = false;
		while (!condition) {
			for (int i = 0; i < 2; i++) {
				uuid = (UUID) board.getData().getJSONArray("players").getJSONObject(i).get("uuid");
				board.rollDice(uuid);
				int position = board.getData().getJSONArray("players").getJSONObject(i).getInt("position");
				String name = board.getData().getJSONArray("players").getJSONObject(i).getString("name");
				System.out.println("Name: " + name + "\n Position: " + position);
				if (position == 100) {
					System.out.println(name + " wins");
					condition = true;
					break;
				}
			}
		}
	}

	public void deletePlayers()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		UUID uuid;
		String name;
		int after_length;
		int before_length = board.getData().getJSONArray("players").length();
		for (int i = 0; i < before_length;) {
			uuid = (UUID) board.getData().getJSONArray("players").getJSONObject(i).get("uuid");
			name = board.getData().getJSONArray("players").getJSONObject(i).getString("name");
			board.deletePlayer(uuid);
			System.out.println(name + " deleted");
			after_length = board.getData().getJSONArray("players").length();
			if (after_length == 0) {
				return;
			}
		}
	}
}
