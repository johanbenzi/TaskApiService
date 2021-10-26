package com.johan.project.taskapiservice.utils;

import com.johan.project.taskapiservice.constants.AppConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {
    public static final Function<Timestamp, String> convertTimeStampToString = (final Timestamp timestamp) ->
            timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT_STRING));
}
