package utils;

import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class JsonStringParserTest {

    @Test
    void ParseJsonToObjects_Success() throws ParameterIsNotJsonStringException, NoWikipediaPageForWordException {
        var sample = "{\"continue\":{\"rvcontinue\":\"20200208164736|939771163\",\"continue\":\"||\"},\"query\":{\"pages\":{\"19651298\":{\"pageid\":19651298,\"ns\":0,\"title\":\"Soup\",\"revisions\":[{\"user\":\"InternetArchiveBot\",\"timestamp\":\"2020-02-24T17:26:22Z\"},{\"user\":\"Yunshui\",\"timestamp\":\"2020-02-09T12:48:23Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:49:19Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:48:49Z\"}]}}}}";
        var result = JsonStringParser.ParseJsonToObjects(sample);
        assertNotNull(result);
    }

    @Test
    void ParseJsonToObjects_Failure() throws ParameterIsNotJsonStringException, NoWikipediaPageForWordException {
        var sample = "__{\"continue\":{\"rvcontinue\":\"20200208164736|939771163\",\"continue\":\"||\"},\"query\":{\"pages\":{\"19651298\":{\"pageid\":19651298,\"ns\":0,\"title\":\"Soup\",\"revisions\":[{\"user\":\"InternetArchiveBot\",\"timestamp\":\"2020-02-24T17:26:22Z\"},{\"user\":\"Yunshui\",\"timestamp\":\"2020-02-09T12:48:23Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:49:19Z\"},{\"user\":\"Otuo-Akyampong Boakye\",\"timestamp\":\"2020-02-08T16:48:49Z\"}]}}}}";
        assertThrows(ParameterIsNotJsonStringException.class, () -> {
            var resultingPerson = JsonStringParser.ParseJsonToObjects(sample);
        });
    }

    @Test
    void ParseJsonToObjects_NoWikiPage() throws ParameterIsNotJsonStringException, NoWikipediaPageForWordException {
        var sample = "{\"batchcomplete\":\"\",\"query\":{\"normalized\":[{\"from\":\"fjfjdnxjdjfdnfnvjvkjf\",\"to\":\"Fjfjdnxjdjfdnfnvjvkjf\"}],\"pages\":{\"-1\":{\"ns\":0,\"title\":\"Fjfjdnxjdjfdnfnvjvkjf\",\"missing\":\"\"}}}}";
        assertThrows(NoWikipediaPageForWordException.class, () -> {
            var resultingPerson = JsonStringParser.ParseJsonToObjects(sample);
        });
    }
}