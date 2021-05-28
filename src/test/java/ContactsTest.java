import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;


public class ContactsTest {

    @Test
    public void testLoadContactsWhenFileDoestExist() {
        Contacts contacts = new Contacts();
        contacts.setFilePath("src/test/resources/nofile.csv");
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            contacts.getContacts();
        });
    }

    @Test
    public void testLoadContactsWhenEmptyFile() {
        Contacts contacts = new Contacts();
        contacts.setFilePath("src/test/resources/empty.csv");
        Assertions.assertThrows(DataFormatException.class, () -> {
            contacts.getContacts();
        });
    }

    @Test
    public void testNoOfContactsIntheAddressBook() throws IOException, DataFormatException {
        Contacts contacts = new Contacts();
        contacts.setFilePath("src/test/resources/addressbook.csv");
        Assertions.assertEquals(contacts.getContacts().stream().count(), 5);
    }
    @Test
    public void testValidContacts() throws IOException, DataFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        Contacts contacts = new Contacts();
        contacts.setFilePath("src/test/resources/onerecord.csv");
        Assertions.assertEquals(contacts.getContacts().get(0).getName().strip(),"Bill McKnight");
        Assertions.assertEquals(contacts.getContacts().get(0).getSex().strip(),"Male");
        Assertions.assertEquals(contacts.getContacts().get(0).getAge().format(formatter),"16/03/77");
    }
}
