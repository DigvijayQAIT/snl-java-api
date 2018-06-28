package testClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

import actionClasses.BoardActions;
import actionClasses.ExceptionActions;
import actionClasses.PlayAction;

/**
 * @author Digvijay
 *
 */
public class SNLTestCases {

	BoardActions boardTest;
	ExceptionActions exceptionTest;
	PlayAction play;

	@BeforeClass
	public void initExceptions() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		boardTest = new BoardActions();
		exceptionTest = new ExceptionActions();
		play = new PlayAction();
	}
	
	@Test
	public void playGame() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		play.addUser("someUser");
		play.rollDice();
	}

	//@Test(expectedExceptions = { JSONException.class, NoUserWithSuchUUIDException.class, MaxPlayersReachedExeption.class, PlayerExistsException.class })
	public void verifyBoardIsWorking() throws InvalidTurnException, NoUserWithSuchUUIDException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardTest.verifyRollDiceWithoutRegisteringAnyUser();
		boardTest.verifyDeleteUserWithoutRegisteringAnyUser();
		boardTest.verifyAddMoreThanFourUsers();
		boardTest.verifyAddSameNameUsers();
		boardTest.verifyRegisterUserAfterStartingGame();
		boardTest.verifyDeleteUserWhichDoesNotExist();
		boardTest.verifyUserIsDeleted();
	}

	//@Test
	public void verifyExceptionsAreWorking() {
		exceptionTest.verifyGameInProgressException();
		exceptionTest.verifyInvalidTurnException();
		exceptionTest.verifyMaxPlayersReachedExeption();
		exceptionTest.verifyNoUserWithSuchUUIDException();
		exceptionTest.verifyPlayerExistsException();
	}

	@AfterClass
	public void tearDownException() {
		boardTest = null;
		exceptionTest = null;
		System.gc();
	}
}
