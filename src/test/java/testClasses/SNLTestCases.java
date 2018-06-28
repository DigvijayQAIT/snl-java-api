package testClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
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

/**
 * @author Digvijay
 *
 */
public class SNLTestCases {

	BoardActions boardTest;
	ExceptionActions exceptionTest;

	@BeforeClass
	public void initExceptions() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		boardTest = new BoardActions();
		exceptionTest = new ExceptionActions();
	}

	@Test(expectedExceptions = { JSONException.class, NullPointerException.class, MaxPlayersReachedExeption.class,
			PlayerExistsException.class })
	public void verifyBoardIsWorking() throws InvalidTurnException, NoUserWithSuchUUIDException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardTest.verifyRollDiceWithoutRegisteringAnyUser();
		boardTest.verifyDeleteUserWithoutRegisteringAnyUser();
		boardTest.verifyAddMoreThanFourUsers();
		boardTest.verifyAddSameNameUsers();
	}

	@Test
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
