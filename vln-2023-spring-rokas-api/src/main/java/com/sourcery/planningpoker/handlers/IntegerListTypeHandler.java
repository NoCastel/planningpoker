package com.sourcery.planningpoker.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@MappedTypes(List.class)
public class IntegerListTypeHandler extends BaseTypeHandler<List<Integer>> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, integers.stream().map(Object::toString).collect(Collectors.joining(",")));
  }

  @Override
  public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return parseIntegerList(resultSet.getString(s));
  }

  @Override
  public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return parseIntegerList(resultSet.getString(i));
  }

  @Override
  public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return parseIntegerList(callableStatement.getString(i));
  }

  private List<Integer> parseIntegerList(String str) {
    return Arrays.stream(str.split(","))
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }
}
