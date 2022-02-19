package com.pwldata.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import com.pwl.api.v1.model.Note;
import com.pwldata.domain.NoteDoc;

public class NoteMapper {

    private NoteMapper() {
    }

    public static Note noteDocToNote(NoteDoc noteDoc) {
        return new Note()
                .createDate(!Objects.isNull(noteDoc.getCreateDate()) ? noteDoc.getCreateDate() : null)
                .text(noteDoc.getText())
                .title(noteDoc.getTitle())
                .id(noteDoc.getId())
                .tag(noteDoc.getTag())
                .stats(getTextStat(noteDoc.getText()));
    }

    private static Map<String, Integer> getTextStat(String text) {
        List<String> list = new ArrayList<>();
        if (Strings.isNotEmpty(text)) {
            StringTokenizer st = new StringTokenizer(text);
            while (st.hasMoreTokens()) {
                list.add(st.nextToken().replaceAll("[^a-zA-Z0-9]", ""));
            }
        }
        return Collections.unmodifiableMap(list.stream().
                collect(Collectors.toMap(String::trim, w -> 1, Integer::sum)));
    }

}
