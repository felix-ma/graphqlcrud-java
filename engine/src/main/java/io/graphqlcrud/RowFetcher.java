/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.graphqlcrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Field;
import graphql.schema.*;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// This must be thread safe, as it will be called by multiple threads at same time
public class RowFetcher implements DataFetcher<Object> {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object get(DataFetchingEnvironment environment) throws Exception {
        Object source = environment.getSource();
        if (source == null) {
            return null;
        }
        ResultSet rs = null;
        if (source instanceof ResultSetList) {
            rs = (ResultSet)((ResultSetList) source).get();
        } else if (source instanceof ResultSet){
            rs = (ResultSet)source;
        }
        Field f = environment.getField();
        String colName = SQLQueryBuilderVisitor.fieldName(f);

        SQLDirective sqlDirective = SQLDirective.find(environment.getFieldDefinition().getDirectives());
        // this is link to another table
        if (sqlDirective != null && rs != null){
            GraphQLFieldDefinition definition = environment.getFieldDefinition();
            GraphQLType type = definition.getType();
            byte[] data = rs.getBytes(f.getName());
            if (data != null) {
                List<?> node = this.mapper.readValue(data, List.class);
                if (type instanceof GraphQLObjectType) {
                    return node.get(0);
                }
                return node;
            } else {
                if (type instanceof GraphQLObjectType) {
                    return null;
                } else {
                    return Collections.emptyList();
                }
            }
        }
        if (rs != null) {
            return rs.getObject(f.getName());
        } else {
            if (source instanceof Map) {
                return ((Map<?, ?>) source).get(colName);
            } else {
                return null;
            }
        }
    }
}
