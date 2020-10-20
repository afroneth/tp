package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different idx value -> returns false
        //assertFalse(commandResult.equals(new CommandResult("feedback", 1, null)));

        // different date value -> returns false
        //assertFalse(commandResult.equals(new CommandResult("feedback", 0, "true")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different idx value -> returns different hashcode
        //assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", 1, null).hashCode());

        // different date value -> returns different hashcode
        //assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", 0, "false").hashCode());
    }

    @Test
    public void showHelp() {
        CommandResult commandResult = new CommandResult("help");

        assertEquals(commandResult.isShowHelp(), false);
    }

    @Test
    public void exit() {
        CommandResult commandResult = new CommandResult("exit");

        assertEquals(commandResult.isShowHelp(), false);
    }
}
