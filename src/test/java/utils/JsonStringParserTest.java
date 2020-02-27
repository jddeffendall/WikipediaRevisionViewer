package utils;

import domain.Edit;
import exceptions.ParameterIsNotJsonStringException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class JsonStringParserTest {

    @Test
    void parseToJsonArray_Success() throws ParameterIsNotJsonStringException {
        var sample = "{\"continue\":{\"rvcontinue\":\"20200208164736|939771163\",\"continue\":\"||\"},\"query\":{\"pages\":{\"19651298\":{\"pageid\":19651298,\"ns\":0,\"title\":\"Soup\",\"revisions\":[{\"user\":\"InternetArchiveBot\",\"timestamp\":\"2020-02-24T17:26:22Z\"},{\"user\":\"Yunshui\",\"timestamp\":\"2020-02-09T12:48:23Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:49:19Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:48:49Z\"}]}}}}";
        var result = JsonStringParser.ParseToJsonArray(sample);
        assertNotNull(result);
    }

    @Test
    void parseArrayToObjects_Success() throws ParameterIsNotJsonStringException {
        var sample = "{\"continue\":{\"rvcontinue\":\"20200208164736|939771163\",\"continue\":\"||\"},\"query\":{\"pages\":{\"19651298\":{\"pageid\":19651298,\"ns\":0,\"title\":\"Soup\",\"revisions\":[{\"user\":\"InternetArchiveBot\",\"timestamp\":\"2020-02-24T17:26:22Z\"},{\"user\":\"Yunshui\",\"timestamp\":\"2020-02-09T12:48:23Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:49:19Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:48:49Z\"}]}}}}";
        var result = JsonStringParser.ParseToJsonArray(sample).toString();
        Edit[] edits = JsonStringParser.ParseArrayToObjects(result);
        assertNotNull(edits);
    }
}