package com.coderman.dbmodel.check;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by on 2017-10-18.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:  全局静态变量
 */
public class Constants {

    /**
     * db初始化方式
     */
    public static final String DB_INIT_MODEL = "dbInit";

    /**
     * DB初始化路径
     */
    public static final String DB_CONFIG_PATH = "dbConfigPath";


    /**
     * 默认主键设置到id上
     */
    public static final String DEFAULT_KEY_COLUMN = "id";

    /**
     * 主键标示
     */
    public static final String PRI_KEY = "PRI";


    /**
     * 检查模型
     */
    public static final String DB_CHECK_MODEL = "checkModel";

    /**
     * 单库模式
     */
    public static final String SINGLE = "single";


    /**
     * 双库模式
     */
    public static final String DOUBLE = "double";


    /**
     * 默认创建时间字段
     * @return
     */
    public static Set<String> getDefaultCreateDateColumn(){
        Set<String> set = new HashSet<>();
        set.add("create_time");
        set.add("create_date");
        set.add("gmt_create");

        set.add("cr_time");
        set.add("cr_date");

        return set;
    }


    /**
     * 默认创建时间字段
     * @return
     */
    public static Set<String> getDefaultUpdateDateColumn(){
        Set<String> set = new HashSet<>();
        set.add("update_time");
        set.add("update_date");
        set.add("gmt_modified");
        set.add("up_time");
        set.add("up_date");
        return set;
    }


    /**
     * 默认字段
     * @return
     */
    public static Set<String> getDefaultUpdateColumn(){
        Set<String> set = new HashSet<>();
        set.add("00:00:00");
        set.add("00:00:00 00:00:00");
        set.add("00:00:00 00:00:00.0");
        return set;
    }


    /**
     * mysql保留字
     * @return
     */
    public static Set<String> getReservedWordSet(){
        Set<String> set = new HashSet<>();
        set.add("ADD");set.add("ALL");set.add("ALTER");

        set.add("ANALYZE");set.add("AND");set.add("AS");

        set.add("ASC");set.add("ASENSITIVE");set.add("BEFORE");
        set.add("BETWEEN");set.add("BIGINT");set.add("BINARY");

        set.add("BLOB");set.add("BOTH");set.add("BY");

        set.add("CALL");set.add("CASCADE");set.add("CASE");

        set.add("CHANGE");set.add("CHAR");set.add("CHARACTER");

        set.add("CHECK");set.add("COLLATE");set.add("COLUMN");

        set.add("CONDITION");set.add("CONNECTION");set.add("CONSTRAINT");

        set.add("CONTINUE");set.add("CONVERT");set.add("CREATE");

        set.add("CROSS");set.add("CURRENT_DATE");set.add("CURRENT_TIME");

        set.add("CURRENT_TIMESTAMP");set.add("CURRENT_USER");set.add("CURSOR");

        set.add("DATABASE");set.add("DATABASES");set.add("DAY_HOUR");

        set.add("DAY_MICROSECOND");set.add("DAY_MINUTE");set.add("DAY_SECOND");

        set.add("DEC");set.add("DECIMAL");set.add("DECLARE");

        set.add("DEFAULT");set.add("DELAYED");set.add("DELETE");

        set.add("DESC");set.add("DESCRIBE");set.add("DETERMINISTIC");

        set.add("DISTINCT");set.add("DISTINCTROW");set.add("DIV");

        set.add("DOUBLE");set.add("DROP");set.add("DUAL");

        set.add("DISTINCT");set.add("DISTINCTROW");set.add("DIV");

        set.add("EACH");set.add("ELSE");set.add("ELSEIF");

        set.add("ENCLOSED");set.add("ESCAPED");set.add("EXISTS");

        set.add("EXIT");set.add("EXPLAIN");set.add("FALSE");

        set.add("FETCH");set.add("FLOAT");set.add("FLOAT4");

        set.add("FLOAT8");set.add("FOR");set.add("FORCE");

        set.add("FOREIGN");set.add("FROM");set.add("FULLTEXT");

        set.add("GOTO");set.add("GRANT");set.add("GROUP");

        set.add("HAVING");set.add("HIGH_PRIORITY");set.add("HOUR_MICROSECOND");

        set.add("HOUR_MINUTE");set.add("HOUR_SECOND");set.add("IF");

        set.add("IGNORE");set.add("IN");set.add("INDEX");

        set.add("INFILE");set.add("INNER");set.add("IFINOUT");

        set.add("INSENSITIVE");set.add("INSERT");set.add("INT");

        set.add("INT1");set.add("INT2");set.add("INT3");

        set.add("INT4");set.add("INT8");set.add("INTEGER");

        set.add("INTERVAL");set.add("INTO");set.add("IS");

        set.add("ITERATE");set.add("JOIN");set.add("KEY");

        set.add("KEYS");set.add("KILL");set.add("LABEL");

        set.add("LEADING");set.add("LEAVE");set.add("LEFT");

        set.add("LIKE");set.add("LIMIT");set.add("LINEAR");

        set.add("LEADING");set.add("LEAVE");set.add("LEFT");

        set.add("LINES");set.add("LOAD");set.add("LOCALTIME");

        set.add("LOCALTIMESTAMP");set.add("LOCK");set.add("LONG");

        set.add("LONGBLOB");set.add("LONGTEXT");set.add("LOOP");

        set.add("LOW_PRIORITY");set.add("MATCH");set.add("MEDIUMBLOB");

        set.add("MEDIUMINT");set.add("MEDIUMTEXT");set.add("MIDDLEINT");

        set.add("MINUTE_MICROSECOND");set.add("MINUTE_SECOND");set.add("MOD");

        set.add("MODIFIES");set.add("NATURAL");set.add("NOT");

        set.add("NO_WRITE_TO_BINLOG");set.add("NULL");set.add("NUMERIC");

        set.add("ON");set.add("OPTIMIZE");set.add("OPTION");

        set.add("OPTIONALLY");set.add("OR");set.add("ORDER");

        set.add("OUT");set.add("OUTER");set.add("OUTFILE");

        set.add("PRECISION");set.add("PRIMARY");set.add("PROCEDURE");

        set.add("PURGE");set.add("RAID0");set.add("RANGE");

        set.add("READS");set.add("READ");set.add("REAL");

        set.add("REFERENCES");set.add("REGEXP");set.add("RELEASE");

        set.add("RENAME");set.add("REPEAT");set.add("REPLACE");

        set.add("REQUIRE");set.add("RESTRICT");set.add("RETURN");

        set.add("REVOKE");set.add("RIGHT");set.add("RLIKE");

        set.add("SCHEMA");set.add("SCHEMAS");set.add("SECOND_MICROSECOND");

        set.add("SELECT");set.add("SENSITIVE");set.add("SEPARATOR");

        set.add("SET");set.add("SHOW");set.add("SMALLINT");

        set.add("SPATIAL");set.add("SPECIFIC");set.add("SQL");

        set.add("SQLEXCEPTION");set.add("SQLSTATE");set.add("SQLWARNING");

        set.add("SQL_BIG_RESULT");set.add("SQL_CALC_FOUND_ROWS");set.add("SQL_SMALL_RESULT");

        set.add("SSL");set.add("STARTING");set.add("STRAIGHT_JOIN");

        set.add("TABLE");set.add("TERMINATED");set.add("THEN");

        set.add("TINYBLOB");set.add("TINYINT");set.add("TINYTEXT");

        set.add("TO");set.add("TRAILING");set.add("TRIGGER");

        set.add("TRUE");set.add("UNDO");set.add("UNION");

        set.add("UNIQUE");set.add("UNLOCK");set.add("UNSIGNED");

        set.add("UPDATE");set.add("USAGE");set.add("USE");

        set.add("USING");set.add("UTC_DATE");set.add("UTC_TIME");

        set.add("UTC_TIMESTAMP");set.add("VALUES");set.add("VARBINARY");

        set.add("VARCHAR");set.add("VARCHARACTER");set.add("VARYING");

        set.add("WHEN");set.add("WHERE");set.add("WHILE");

        set.add("WITH");set.add("WRITE");set.add("X509");


        set.add("XOR");set.add("YEAR_MONTH");set.add("ZEROFILL");


        //5.7新增

        set.add("ACCOUNT");set.add("ALWAYS");set.add("CHANNEL");

        set.add("COMPRESSION");set.add("ENCRYPTION");set.add("FILE_BLOCK_SIZE");
        set.add("FILTER");set.add("FOLLOWS");set.add("GENERATED");
        set.add("GROUP_REPLICATION");set.add("INSTANCE");set.add("JSON");


        set.add("MASTER_TLS_VERSION");set.add("NEVER");set.add("OPTIMIZER_COSTS");

        set.add("PARSE_GCOL_EXPR");set.add("PRECEDES");set.add("REPLICATE_DO_DB");

        set.add("REPLICATE_DO_TABLE");set.add("REPLICATE_IGNORE_DB");set.add("REPLICATE_IGNORE_TABLE");

        set.add("REPLICATE_REWRITE_DB");set.add("REPLICATE_WILD_DO_TABLE");set.add("REPLICATE_WILD_IGNORE_TABLE");

        set.add("ROTATE");set.add("STACKED");set.add("STORED");

        set.add("VALIDATION");set.add("VIRTUALTACKED");set.add("WITHOUT");

        set.add("XID");


        return set;
    }


    //private Set

}
