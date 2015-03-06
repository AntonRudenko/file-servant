package ru.teamlabs.fileservant.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.teamlabs.fileservant.db.dto.DbResultDto;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Rudenko.
 */
@Service
public class DbService {

    Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    public Map<String, DataSource> listDbSources() {
        return applicationContext.getBeansOfType(DataSource.class);
    }

    public DbResultDto executeSql(String db, String sql) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(db);
        return getDbResultDto(sql, jdbcTemplate);
    }

    private DbResultDto getDbResultDto(String sql, JdbcTemplate jdbcTemplate) {
        DbResultDto dbResultDto = new DbResultDto();
        List<Map<String, Object>> result = jdbcTemplate.query(sql,
                (ResultSet resultSet, int i) -> {
                    ResultSetMetaData md = resultSet.getMetaData();
                    Map<String, Object> rowMap = new HashMap<>();
                    for (int column = 1; column < md.getColumnCount(); column++) {
                        rowMap.put(
                            md.getColumnName(column),
                            resultSet.getObject(column)
                        );
                    }
                    return rowMap;
                }
        );
        dbResultDto.data = result;
        return dbResultDto;
    }

    private JdbcTemplate getJdbcTemplate(String db) {
        JdbcTemplate jdbcTemplate = jdbcTemplateMap.get(db);

        if (jdbcTemplate == null) {
            DataSource dataSource = (DataSource) applicationContext.getBean(db);
            if (dataSource == null) throw new RuntimeException("Db not found");

            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplateMap.put(db, jdbcTemplate);
        }
        return jdbcTemplate;
    }
}
