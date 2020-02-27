package utils;

import domain.Edit;
import exceptions.NoWikipediaPageForWordException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EditAnalyzer {

    public static void AnalyzeEdits(Edit[] edits) throws NoWikipediaPageForWordException {

    }

    public static Edit mostFrequentEditor(Edit[] edits) {
        HashMap<Edit, Integer> editorCountMap = new HashMap<>();

        for (Edit i : edits) {
            if (editorCountMap.containsKey(i)) {
                editorCountMap.put(i, editorCountMap.get(i) + 1);
            } else {
                editorCountMap.put(i, 1);
            }
        }
        Edit element = null;
        int frequency = 1;

        Set<Map.Entry<Edit, Integer>> entrySet = editorCountMap.entrySet();
        for (Map.Entry<Edit, Integer> entry : entrySet) {
            if (entry.getValue() > frequency) {
                element = entry.getKey();
                frequency = entry.getValue();
            }
        }
        return element;
    }

}
