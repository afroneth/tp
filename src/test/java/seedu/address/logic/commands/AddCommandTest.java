package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPatient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddCommand addCommand = new AddCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PATIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different patient -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCliniCalFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCliniCalFilePath(Path cliniCalFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCliniCal(ReadOnlyCliniCal cliniCal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCliniCal getCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitCliniCal(String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getUndoCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getRedoCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePatient(patient);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePatient);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyCliniCal getCliniCal() {
            return new CliniCal();
        }

        @Override
        public void commitCliniCal(String command) {
            // Called by AddCommand#execute
        }
    }

}
