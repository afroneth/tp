package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ALLERGY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ALLERGY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BLOODTYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOODTYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ICNUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ICNUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALLERGY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOODTYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ICNUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;
import seedu.address.testutil.PatientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withAllergies(VALID_ALLERGY_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + ICNUMBER_DESC_BOB
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple ic numbers - last ic number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_AMY + ICNUMBER_DESC_BOB
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_AMY + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple sex - last sex accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_AMY + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple blood types - last blood type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_AMY + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatient));

        // multiple allergies - all accepted
        Patient expectedPatientMultipleAllergies = new PatientBuilder(BOB)
                .withAllergies(VALID_ALLERGY_AMY, VALID_ALLERGY_BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_AMY + ALLERGY_DESC_BOB,
                new AddCommand(expectedPatientMultipleAllergies));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero allergies
        Patient expectedPatient = new PatientBuilder(AMY).withAllergies().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ICNUMBER_DESC_AMY + ADDRESS_DESC_AMY
                        + EMAIL_DESC_AMY + SEX_DESC_AMY + BLOODTYPE_DESC_AMY,
                new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing icNumber prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ICNUMBER_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + VALID_ADDRESS_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_EMAIL_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing sex prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + VALID_SEX_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing bloodType prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + VALID_BLOODTYPE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ICNUMBER_BOB + VALID_ADDRESS_BOB
                + VALID_EMAIL_BOB + VALID_SEX_BOB + VALID_BLOODTYPE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid icNumber
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ICNUMBER_DESC + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                IcNumber.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + INVALID_ADDRESS_DESC
                + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_EMAIL_DESC + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_SEX_DESC + BLOODTYPE_DESC_BOB,
                Sex.MESSAGE_CONSTRAINTS);

        // invalid bloodType
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB + SEX_DESC_BOB + INVALID_BLOODTYPE_DESC,
                BloodType.MESSAGE_CONSTRAINTS);

        // invalid allergy
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + INVALID_ALLERGY_DESC,
                Allergy.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ICNUMBER_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_EMAIL_DESC + SEX_DESC_BOB + BLOODTYPE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + VALID_PHONE_BOB + ICNUMBER_DESC_BOB
                        + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SEX_DESC_BOB + BLOODTYPE_DESC_BOB + ALLERGY_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
