package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.Contact;
import model.Group;
import ru.stqa.addressbook.common.CommonFunctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static tests.TestBase.randomFile;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        try {
            var generator = new Generator();
            JCommander.newBuilder()
                    .addObject(generator)
                    .build()
                    .parse(args);
            generator.run();
        } catch (Exception e){
            e.printStackTrace(); // Выведет ошибку в консоль
            System.exit(1);      // Явное завершение с кодом ошибки
        }
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);

        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);

        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<Group>();
        for (int i = 0; i < count; i++){
            result.add(new Group()
                    .withName(CommonFunctions.randomString(i))
                    .withHeader(CommonFunctions.randomString(i))
                    .withFooter(CommonFunctions.randomString(i)));

        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<Contact>();
        for (int i = 0; i < 5; i++){
            result.add(new Contact()
                    .withFirstName(CommonFunctions.randomString(i+1))
                    .withLastName(CommonFunctions.randomString(i+1))
                    .withEmail(CommonFunctions.randomString(i+1))
                    .withAddress(CommonFunctions.randomString(i+1))
                    .withHomePhone(CommonFunctions.randomString(i+1))
                    .withPhoto(randomFile("src/test/resources/images")));

        }
        return result;
    }
}
