package com.pwldata.services;

import com.pwl.api.v1.model.Note;
import com.pwl.api.v1.model.UpdatedNote;
import com.pwldata.domain.NoteDoc;
import org.apache.logging.log4j.util.Strings;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

public class MapperService {

    private MapperService() {
    }

    public static Note noteDocToNote(NoteDoc noteDoc) {
        return new Note()
                .createDate(!Objects.isNull(noteDoc.getCreateDate()) ? OffsetDateTime.of(noteDoc.getCreateDate(), ZoneOffset.UTC) : null)
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

    public static NoteDoc noteUpdateToNoteDoc(UpdatedNote updatedNote) {
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.setText(updatedNote.getText());
        noteDoc.setTitle(updatedNote.getTitle());
        if (updatedNote.getTag() != null) {
            noteDoc.setTag(Note.TagEnum.fromValue(updatedNote.getTag().getValue()));
        }
        noteDoc.setCreateDate(updatedNote.getCreateDate().toLocalDateTime());
        return noteDoc;

    }
}
