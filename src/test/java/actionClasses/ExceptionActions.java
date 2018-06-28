package actionClasses;

import java.util.UUID;

import org.testng.Assert;

import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

/**
 * @author Digvijay
 *
 */
public class ExceptionActions {

	/**
	 * Objects
	 */
	private GameInProgressException gameInProgressException;
	private InvalidTurnException invalidTurnException;
	private MaxPlayersReachedExeption maxPlayersReachedExeption;
	private NoUserWithSuchUUIDException noUserWithSuchUUIDException;
	private PlayerExistsException playerExistsException;

	
	/**
	 * Variables
	 */
	private String gameInProgressMessage, invalidTurnMessage, maxPlayerReachedMessage, noUserMessage, playerExistMessage;
	private String playerName;
	private Integer players;
	private UUID uuid;

	/**
	 * Constructor
	 */
	public ExceptionActions() {
		initVars();
		initObjects();
	}

	/**
	 * Test case for GameInProgressException
	 */
	public void verifyGameInProgressException() {
		Assert.assertEquals(gameInProgressMessage, gameInProgressException.getMessage());
		System.out.println("GameInProgressException - Working");

	}

	/**
	 * Test case for InvalidTurnException
	 */
	public void verifyInvalidTurnException() {
		Assert.assertEquals(invalidTurnMessage, invalidTurnException.getMessage());
		System.out.println("InvalidTurnException - Working");
	}

	/**
	 * Test case for MaxPlayerReachedException
	 */
	public void verifyMaxPlayersReachedExeption() {
		Assert.assertEquals(maxPlayerReachedMessage, maxPlayersReachedExeption.getMessage());
		System.out.println("MaxPlayersReachedExeption - Working");
	}

	/**
	 * Test case for NoUserWithSuchUUIDException
	 */
	public void verifyNoUserWithSuchUUIDException() {
		Assert.assertEquals(noUserMessage, noUserWithSuchUUIDException.getMessage());
		System.out.println("NoUserWithSuchUUIDException - Working");
	}

	/**
	 * Test case for PlayerExistsException
	 */
	public void verifyPlayerExistsException() {
		Assert.assertEquals(playerExistMessage, playerExistsException.getMessage());
		System.out.println("PlayerExistsException - Working");
	}

	/**
	 * Initialize Variables
	 */
	private void initVars() {
		uuid = UUID.randomUUID();
		players = 5;
		playerName = "some_name";
		gameInProgressMessage = "New player cannot join since the game has started";
		invalidTurnMessage = "Player '" + uuid + "' does not have the turn";
		maxPlayerReachedMessage = "The board already has maximum allowed Player: " + players;
		noUserMessage = "No Player with uuid '" + playerName + "' on board";
		playerExistMessage = "Player '" + playerName + "' already exists on board";
	}
	
	/**
	 * Initialize Objects
	 */
	private void initObjects() {
		gameInProgressException = new GameInProgressException();
		invalidTurnException = new InvalidTurnException(uuid);
		maxPlayersReachedExeption = new MaxPlayersReachedExeption(players);
		noUserWithSuchUUIDException = new NoUserWithSuchUUIDException(playerName);
		playerExistsException = new PlayerExistsException(playerName);
	}
}
