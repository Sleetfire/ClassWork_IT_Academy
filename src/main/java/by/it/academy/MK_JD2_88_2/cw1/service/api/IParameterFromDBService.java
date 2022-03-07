package by.it.academy.MK_JD2_88_2.cw1.service.api;

import java.util.List;

public interface IParameterFromDBService<T> {

    List<T> getByColumnName(String columnName, String tableName);

}
