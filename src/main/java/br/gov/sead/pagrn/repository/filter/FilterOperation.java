package br.gov.sead.pagrn.repository.filter;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum FilterOperation {

    // @formatter:off
    LESS_EQUAL_THAN("<=", CriteriaBuilder::lessThanOrEqualTo),
    GREATER_EQUAL_THAN(">=", CriteriaBuilder::greaterThanOrEqualTo),
    CONTAINS(":>", (b, k, v) -> b.like(k, b.literal("%" + v + "%"))),
    GREATER_THAN(">", CriteriaBuilder::greaterThan),
    LESS_THAN("<", CriteriaBuilder::lessThan),
    EQUALS("::", CriteriaBuilder::equal);
    // @formatter:on

    private final String operationName;
    private final FilterPredicateFunction operation;

    FilterOperation(String operationName, FilterPredicateFunction operation) {
        this.operationName = operationName;
        this.operation = operation;
    }

    public String getOperationName() {
        return operationName;
    }

    public Predicate build(CriteriaBuilder builder, Root<?> entity, String key, Object value) {
        return operation.predicate(builder, entity.get(key), value.toString());
    }

    static FilterOperation parse(String str) {
        for (FilterOperation filter : FilterOperation.values()) {
            if (StringUtils.equals(str, filter.getOperationName())) {
                return filter;
            }
        }

        throw new RuntimeException(String.format("Filter operation '%s' not found", str));
    }

    @FunctionalInterface
    interface FilterPredicateFunction {
        Predicate predicate(CriteriaBuilder builder, Path<String> key, String value);
    }
}