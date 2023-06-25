package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import tests.gui.TestPrincipalWindow;
import tests.logic.TestSlidingPuzzle;

@Suite
@SelectClasses({ TestPrincipalWindow.class, TestSlidingPuzzle.class })
public class TestSuite {

}
