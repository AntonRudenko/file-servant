package ru.teamlabs.fileservant.db.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Anton Rudenko.
 */
public class DbResultDto {

    public List<Map<String, Object>> data;
    public long errorCode = 0;

}
