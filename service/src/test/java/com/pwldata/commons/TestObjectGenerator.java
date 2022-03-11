package com.pwldata.commons;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestObjectGenerator {


    private static EasyRandom generator;

    public static EasyRandom getRandomizer(Class<?> clazz) {
        if (generator == null) {
            EasyRandomParameters parameters = new EasyRandomParameters();
            parameters.randomize(
                    FieldPredicates.named("createDate")
                            .and(FieldPredicates.ofType(LocalDateTime.class))
                            .and(FieldPredicates.inClass(clazz)),
                    new NameDateTimeRandomizer());
            parameters.collectionSizeRange(5, 5);
            parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(clazz)));
            generator = new EasyRandom(parameters);
        }
        return generator;
    }

    public static <T> T generateObject(Class<T> t) {
        return getRandomizer(t).nextObject(t);
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
