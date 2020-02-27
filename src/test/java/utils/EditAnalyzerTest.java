package utils;

import domain.Edit;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class EditAnalyzerTest {

    @Test
    void mostFrequentEditor() throws ParseException {
        Edit[] edits = new Edit[2];
        edits[0].setUser("tom");
        edits[0].setTimestamp("2012");
        edits[1].setUser("bill");
        edits[2].setTimestamp("2020");
        Edit freqEdit = EditAnalyzer.mostFrequentEditor(edits);
        assertNotNull(freqEdit);
    }
}