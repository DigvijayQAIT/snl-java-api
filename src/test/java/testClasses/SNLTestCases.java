package testClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

import actionClasses.BoardTest;

public class SNLTestCases {

	BoardTest boardTest;

	@BeforeMethod
	public void initVars() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		boardTest = new BoardTest();
	}

	@Test(expectedExceptions = {JSONException.class})
	public void verifyRollDiceWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, InvalidTurnException {
		boardTest.rollDiceWithoutRegisteringAnyUser();
	}

	@Test(expectedExceptions = {NullPointerException.class})
	public void verifyDeleteUserWithoutRegisteringAnyUser()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		boardTest.deleteUserWithoutRegisteringAnyUser();
	}

	@Test(expectedExceptions = {MaxPlayersReachedExeption.class})
	public void verifyAddingMoreThanFourUsers() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardTest.addMoreThanFourUsers();
	}

	@Test(expectedExceptions = {PlayerExistsException.class})
	public void verifyAddingSameNameUsers() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardTest.addSameNameUsers();
	}

	@AfterMethod
	public void tearDown() {
		boardTest = null;
		System.gc();
	}
}
