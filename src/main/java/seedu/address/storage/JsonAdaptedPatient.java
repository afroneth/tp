package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String icNumber;
    private final String address;
    private final String email;
    private final String profilePicture;
    private final String sex;
    private final String bloodType;
    private final List<JsonAdaptedAllergy> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("icNumber") String icNumber,
                              @JsonProperty("address") String address,
                              @JsonProperty("email") String email,
                              @JsonProperty("profilePicture") String profilePicture,
                              @JsonProperty("sex") String sex,
                              @JsonProperty("bloodType") String bloodType,
                              @JsonProperty("tagged") List<JsonAdaptedAllergy> tagged) {
        this.name = name;
        this.phone = phone;
        this.icNumber = icNumber;
        this.address = address;
        this.email = email;
        this.profilePicture = profilePicture;
        this.sex = sex;
        this.bloodType = bloodType;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        icNumber = source.getIcNumber().value;
        address = source.getAddress().value;
        email = source.getEmail().value;
        profilePicture = source.getProfilePicture().value;
        sex = source.getSex().value;
        bloodType = source.getBloodType().value;
        tagged.addAll(source.getAllergies().stream()
                .map(JsonAdaptedAllergy::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Allergy> patientAllergies = new ArrayList<>();
        for (JsonAdaptedAllergy tag : tagged) {
            patientAllergies.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (icNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IcNumber.class.getSimpleName()));
        }
        if (!IcNumber.isValidIcNumber(icNumber)) {
            throw new IllegalValueException(IcNumber.MESSAGE_CONSTRAINTS);
        }
        final IcNumber modelIcNumber = new IcNumber(icNumber);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (profilePicture == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProfilePicture.class.getSimpleName()));
        }

        if (!ProfilePicture.isValidFilePath(profilePicture)) {
            throw new IllegalValueException(ProfilePicture.MESSAGE_CONSTRAINTS);
        }
        final ProfilePicture modelProfilePicture = new ProfilePicture(profilePicture);

        if (sex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName()));
        }
        if (!Sex.isValidSex(sex)) {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        final Sex modelSex = new Sex(sex);

        if (bloodType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BloodType.class.getSimpleName()));
        }
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_CONSTRAINTS);
        }
        final BloodType modelBloodType = new BloodType(bloodType);

        final Set<Allergy> modelAllergies = new HashSet<>(patientAllergies);

        return new Patient(modelName, modelPhone, modelIcNumber, modelAddress, modelEmail, modelProfilePicture,
                modelSex, modelBloodType, modelAllergies);
    }

}
