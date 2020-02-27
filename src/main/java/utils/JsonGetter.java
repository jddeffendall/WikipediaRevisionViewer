package utils;

import exceptions.NetworkErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class JsonGetter {

    public String JsonStringGetter(String word) throws IOException, NetworkErrorException {
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(word, ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Tracker/0.1 (http://www.cs.bsu.edu/; yourusername@bsu.edu)");
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        String result = scanner.nextLine();

        if (result.isEmpty()) {
            throw new NetworkErrorException();
        }

        return result;
    }
}
