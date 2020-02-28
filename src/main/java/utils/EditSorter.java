package utils;

import domain.Edit;

import java.util.ArrayList;
import java.util.List;

public class EditSorter {

    public static List<Edit> getEditCounts(Edit[] edits) {
        List<Edit> revisions = new ArrayList<>();
        for (Edit i: edits) {
            revisions.add(i);
        }

        List<Edit> filtered = new ArrayList<>();

        for (var revision : revisions) {

            Edit foundRevision = null;

            boolean notFound = true;
            for(var filteredRevision:filtered) {
                if(filteredRevision.getUser().equals(revision.getUser())) {
                    notFound = false;
                    foundRevision = filteredRevision;
                    break;
                }
            }

            if(notFound) {
                filtered.add(revision);
            } else {
                foundRevision.increaeEditCount();
            }

        }

        return filtered;
    }
}
