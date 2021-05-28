import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.DataFormatException;


public class Contacts {

    private String filePath = "src/main/resources/addressbook.csv";

    private List<Person> loadContacts() throws IOException, DataFormatException {

        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            throw new FileNotFoundException("AddressBook file not found");
        }

        if(Files.lines(path).count()==0){
            throw new DataFormatException("File is empty");
        }

        return new CsvToBeanBuilder(new FileReader(filePath))
                .withType(Person.class)
                .build()
                .parse();
    }

    public List<Person> getContacts() throws IOException, DataFormatException {
        return loadContacts();
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
