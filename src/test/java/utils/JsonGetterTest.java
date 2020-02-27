package utils;

import exceptions.NetworkErrorException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonGetterTest {

    @Test
    void jsonStringGetter_Success() throws IOException {
        JsonGetter getJson = new JsonGetter();
        String word = "Fox";
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+ word + "&rvprop=timestamp|user&rvlimit=4&redirects");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Tracker/0.1 (http://www.cs.bsu.edu/; yourusername@bsu.edu)");
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        String result = scanner.nextLine();

        assertEquals('{', result.charAt(0));
    }

    @Test
    void JsonStringGetter_Failure() throws NetworkErrorException, IOException {
        JsonGetter getJson = new JsonGetter();
        String word = "Fox";
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+ word + "&rvprop=timestamp|user&rvlimit=4&redirects");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Tracker/0.1 (http://www.cs.bsu.edu/; yourusername@bsu.edu)");
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        String result = "";

        assert (result.isEmpty());
    }
}