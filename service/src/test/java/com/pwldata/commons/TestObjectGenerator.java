package com.pwldata.commons;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import com.pwldata.domain.NoteDoc;

public class TestObjectGenerator {


    private static EasyRandom generator;

    public static EasyRandom getRandomizer() {
        if (generator == null) {
            EasyRandomParameters parameters = new EasyRandomParameters();
            parameters.randomize(
                    FieldPredicates.named("createDate")
                            .and(FieldPredicates.ofType(LocalDateTime.class))
                            .and(FieldPredicates.inClass(NoteDoc.class)),
                    new NameDateTimeRandomizer());
            parameters.collectionSizeRange(5, 5);
            parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(NoteDoc.class)));
            generator = new EasyRandom(parameters);
        }
        return generator;
    }

    public static <T> T generateObject(Class<T> t) {
        return getRandomizer().nextObject(t);
    }


    private static class NameDateTimeRandomizer implements Randomizer<LocalDateTime> {

        @Override
        public LocalDateTime getRandomValue() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String stringDate = formatter.format(LocalDateTime.now());
            return LocalDateTime.parse(stringDate,formatter);
        }
    }
}
