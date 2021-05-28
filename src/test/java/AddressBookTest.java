import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class AddressBookTest {

    @Test
    public void testNoOfMalesInTheAddressBook() throws IOException, DataFormatException {
        AddressBook addressBook = getAddressBook();
        Assertions.assertEquals(3L,addressBook.getNoOfMalesInTheAddressBook());
    }

    @Test
    public void testGetOldersPersonInTheAddressBook() throws IOException, DataFormatException {
        AddressBook addressBook = getAddressBook();
        Assertions.assertEquals(addressBook.getOldestPersonInTheAddressBook(),"Wes Jackson");
    }

    @Test
    public void testGetNoOfDaysOlderBillThanPaul() throws IOException, DataFormatException {
        AddressBook addressBook = getAddressBook();
        Assertions.assertEquals(2862,addressBook.getNoOfDaysOlderBillThanPaul());
    }

    private AddressBook getAddressBook() throws IOException, DataFormatException {
        AddressBook addressBook = new AddressBook();
        Contacts contacts = new Contacts();
        contacts.setFilePath("src/test/resources/addressbook.csv");
        addressBook.setContacts(contacts);
        return addressBook;
    }
}
