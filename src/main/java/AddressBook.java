import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import static java.util.stream.Collectors.counting;

public class AddressBook {

    public static final String BILL_MC_KNIGHT = "Bill McKnight";
    public static final String PAUL_ROBINSON = "Paul Robinson";
    public static final String MALE = "Male";

    @Autowired
    public Contacts contacts;

    public long getNoOfMalesInTheAddressBook() throws IOException, DataFormatException {
        return contacts.getContacts()
                .stream()
                .filter(person -> person.getSex().strip().equals(MALE))
                .collect(counting());
    }

    public String getOldestPersonInTheAddressBook() throws IOException, DataFormatException {

        return contacts.getContacts()
                .stream()
                .min(Comparator.comparing( Person::getAge )).get()
                .getName();
    }

    private List<Person> getTwoPersons(String firstContactName,String secondContractName) throws IOException, DataFormatException {
        return contacts.getContacts()
                .stream()
                .filter(p -> p.getName().strip().equals(firstContactName) || p.getName().strip().equals(secondContractName))
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
    }

    public long getNoOfDaysOlderBillThanPaul() throws IOException, DataFormatException {
        List<Person> twoPersons = getTwoPersons(BILL_MC_KNIGHT,PAUL_ROBINSON);
        return ChronoUnit.DAYS.between(twoPersons.get(0).getAge(), twoPersons.get(1).getAge());
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

}
