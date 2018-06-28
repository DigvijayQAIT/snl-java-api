package actionClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.Assert;

import com.qainfotech.tap.training.snl.api.Board;
import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

public class BoardTest {

	Board board;

	public BoardTest() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}

	public void rollDiceWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, InvalidTurnException {
		board.rollDice(null);
	}

	public void deleteUserWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		board.deletePlayer(null);
	}

	public void addMoreThanFourUsers() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		String name = "some_name";
		addUser(name, true);
	}

	public void addSameNameUsers() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException {
		String name = "some_name";
		addUser(name, false);
	}
	
	public void addUser(String name, Boolean add_i) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		int beforePlayerCount, afterPlayerCount;
		if (add_i) {
			for (int i = 0; i < 6; ++i) {
				beforePlayerCount = board.getData().getJSONArray("players").length();
				board.registerPlayer(name + i);
				afterPlayerCount = board.getData().getJSONArray("players").length();
				if (beforePlayerCount >= afterPlayerCount) {
					Assert.assertTrue(false, "No new player registered");
				}
			}
		} else {
			for (int i = 0; i < 5; ++i) {
				beforePlayerCount = board.getData().getJSONArray("players").length();
				board.registerPlayer(name);
				afterPlayerCount = board.getData().getJSONArray("players").length();
				if (beforePlayerCount >= afterPlayerCount) {
					Assert.assertTrue(false, "No new player registered");
				}
			}
		}
	}

}
