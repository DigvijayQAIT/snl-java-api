package actionClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.testng.Assert;

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
public class BoardActions {

	private Board board;

	public BoardActions() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}

	public void verifyRollDiceWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, InvalidTurnException {
		board.rollDice(UUID.randomUUID());
	}

	public void verifyDeleteUserWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		board.deletePlayer(null);
	}

	public void verifyAddMoreThanFourUsers() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		String name = "some_name";
		addUser(name, 6, Boolean.TRUE);
	}

	public void verifyAddSameNameUsers() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		String name = "some_name";
		addUser(name, 5, false);
	}

	public void addUser(String name, int numberOfUser, Boolean change_name)
			throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException,
			MaxPlayersReachedExeption, IOException {
		int beforePlayerCount, afterPlayerCount;
		if (change_name) {
			for (int i = 0; i < numberOfUser; ++i) {
				beforePlayerCount = board.getData().getJSONArray("players").length();
				board.registerPlayer(name + i);
				afterPlayerCount = board.getData().getJSONArray("players").length();
				Assert.assertEquals(board.getData().getJSONArray("players").getJSONObject(i).getInt("position"), 0,
						"position of new player is not 0");
				Assert.assertEquals(board.getData().getJSONArray("players").getJSONObject(i).getString("name"),
						name + i, "name of the user registered is not same");
				if (beforePlayerCount >= afterPlayerCount) {
					Assert.fail("Player count does not increase in JSON");
				}
			}
		} else {
			for (int i = 0; i < numberOfUser; ++i) {
				beforePlayerCount = board.getData().getJSONArray("players").length();
				board.registerPlayer(name);
				afterPlayerCount = board.getData().getJSONArray("players").length();
				Assert.assertEquals(board.getData().getJSONArray("players").getJSONObject(i).getInt("position"), 0,
						"position of new player is not 0");
				Assert.assertEquals(board.getData().getJSONArray("players").getJSONObject(i).getString("name"), name,
						"name of the user registered is not same");
				if (beforePlayerCount >= afterPlayerCount) {
					Assert.fail("Player count does not increase in JSON");
				}
			}
		}
	}

	public void verifyDeleteUserWhichDoesNotExist() {
		String name = "any_name";
		UUID uuid;
		try {

			addUser(name, 4, Boolean.TRUE);
			uuid = UUID.randomUUID();
			board.deletePlayer(uuid);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException("any uuid").getClass());
		}
	}

	public void verifyRegisterUserAfterStartingGame() {
		String name = "some_name";
		UUID uuid;
		try {

			addUser(name, 2, Boolean.TRUE);
			for (int i = 0; i < 2; i++) {

				uuid = (UUID) board.getData().getJSONArray("players").getJSONObject(i).get("uuid");
				board.rollDice(uuid);
				board.registerPlayer("player");

			}
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), new GameInProgressException().getClass());
		}
	}

	public void verifyUserIsDeleted() {
		String name = "any_name";
		UUID uuid;
		try {
			addUser(name, 4, Boolean.TRUE);
			uuid = (UUID) board.getData().getJSONArray("players").getJSONObject(0).get("uuid");
			board.deletePlayer(uuid);
			Assert.assertEquals(board.getData().getJSONArray("players").length(), 3);
			board.deletePlayer(uuid);

		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException("any uuid").getClass());
		}
	}

}
