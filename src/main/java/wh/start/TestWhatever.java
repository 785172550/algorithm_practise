package wh.start;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestWhatever {
    public static void main(String[] args) throws IOException {
        testYml();
    }

    public static void testYml() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/test.yml"));

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES));
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        SequenceWriter sw = objectMapper.writer().writeValues(System.out);
        sw.write(jsonNode);
    }
}
