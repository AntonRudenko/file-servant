package ru.teamlabs.fileservant.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.teamlabs.fileservant.WrongParamsException;
import ru.teamlabs.fileservant.db.dto.SqlRequestDto;
import ru.teamlabs.fileservant.db.dto.DbResultDto;

import java.util.Set;

/**
 * Created by dark on 10/25/14.
 */
@RestController
@RequestMapping("data")
public class DbController {

    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET)
    public Set<String> listDbSources() {
        return dbService.listDbSources().keySet();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public DbResultDto executeQuery(@RequestBody SqlRequestDto dto) {
        if (!StringUtils.hasText(dto.name)) throw new WrongParamsException("Name of db is mandatory param");
        if (!StringUtils.hasText(dto.sql)) throw new WrongParamsException("Sql is mandatory param");

        return dbService.executeSql(dto.name, dto.sql);
    }

}
