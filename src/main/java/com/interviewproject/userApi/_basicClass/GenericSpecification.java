package com.interviewproject.userApi._basicClass;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GenericSpecification<EntityType, RequestType extends BasicRequest> implements Specification<EntityType> {

    private final RequestType request;

    public GenericSpecification(RequestType request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<EntityType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        criteriaQuery.distinct(true);
        try {
            List<Predicate> predicates = getPredicates(root,criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception ignored) {}
        return null;
    }

    protected List<Predicate> getPredicates(Root<?> root, CriteriaBuilder builder) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<Predicate> predicates = new ArrayList<>();
        for (Method method: getFilters(request.getClass())){
            GenericFieldFilter<?> filter = (GenericFieldFilter<?>) method.invoke(request);
            if (filter!= null){
                String fieldName = getFieldName(method.getName());
                if (validateSingleFilter(filter.getEq())) {
                    if (filter.getEq() instanceof String) {
                        predicates.add(builder.like(root.get(fieldName), filter.getEq()+""));
                    }else{
                        predicates.add(builder.equal(root.get(fieldName), filter.getEq()));
                    }
                }
                if (validateSingleFilter(filter.getNe())) predicates.add(builder.notEqual(root.get(fieldName),filter.getNe()));
                if (validateSingleFilter(filter.getIn())) predicates.add(root.get(fieldName).in(filter.getIn()));
                if (validateSingleFilter(filter.getNin())) predicates.add(root.get(fieldName).in(filter.getNin()).not());
                if (validateSingleFilter(filter.getC())) predicates.add(builder.like(root.get(fieldName),"%"+ filter.getC() + "%"));
                if (validateSingleFilter(filter.getCi())) predicates.add(builder.like(root.get(fieldName),"%"+ filter.getCi() + "%"));
                if (validateSingleFilter(filter.getS())) predicates.add(builder.like(root.get(fieldName),filter.getS() + "%"));
                if (validateSingleFilter(filter.getSi())) predicates.add(builder.like(root.get(fieldName),filter.getSi() + "%"));
                if (validateSingleFilter(filter.getE())) predicates.add(builder.like(root.get(fieldName),"%" + filter.getE()));
                if (validateSingleFilter(filter.getEi())) predicates.add(builder.like(root.get(fieldName),"%" + filter.getEi()));
                if (validateSingleFilter(filter.getLt())) predicates.add(createLessThanPredicate(root,fieldName,filter.getLt(),builder));
                if (validateSingleFilter(filter.getLte())) predicates.add(createLessThanOrEqualToPredicate(root,fieldName,filter.getLte(),builder));
                if (validateSingleFilter(filter.getGt())) predicates.add(createGreaterThanPredicate(root,fieldName,filter.getGt(),builder));
                if (validateSingleFilter(filter.getGte())) predicates.add(createGreaterThanOrEqualToPredicate(root,fieldName,filter.getGte(),builder));
            }
        }

        return predicates;
    }

    private Boolean validateSingleFilter(Object filter){
        if (filter == null) return false;
        return !(filter instanceof List) || ((List<?>) filter).size() > 0;
    }

    private Predicate createLessThanPredicate(
        Root<?> root,
        String fieldName,
        Object value,
        CriteriaBuilder builder
    ){
        if (value instanceof Long){
            return builder.lessThan(root.get(fieldName),Long.valueOf(value.toString()));
        }else if (value instanceof String){
            return builder.lessThan(root.get(fieldName),value.toString());
        }else if (value instanceof Date){
            return builder.lessThan(root.get(fieldName),(Date) value);
        }else if (value instanceof Integer){
            return builder.lessThan(root.get(fieldName),Integer.valueOf(value.toString()));
        }else if (value instanceof Boolean){
            return builder.lessThan(root.get(fieldName),Boolean.valueOf(value.toString()));
        }
        return null;
    }

    private Predicate createLessThanOrEqualToPredicate(
        Root<?> root,
        String fieldName,
        Object value,
        CriteriaBuilder builder
    ){
        if (value instanceof Long){
            return builder.lessThanOrEqualTo(root.get(fieldName),Long.valueOf(value.toString()));
        }else if (value instanceof String){
            return builder.lessThanOrEqualTo(root.get(fieldName),value.toString());
        }else if (value instanceof Date){
            return builder.lessThanOrEqualTo(root.get(fieldName),(Date) value);
        }else if (value instanceof Integer){
            return builder.lessThanOrEqualTo(root.get(fieldName),Integer.valueOf(value.toString()));
        }else if (value instanceof Boolean){
            return builder.lessThanOrEqualTo(root.get(fieldName),Boolean.valueOf(value.toString()));
        }
        return null;
    }

    private Predicate createGreaterThanPredicate(
        Root<?> root,
        String fieldName,
        Object value,
        CriteriaBuilder builder
    ){
        if (value instanceof Long){
            return builder.greaterThan(root.get(fieldName),Long.valueOf(value.toString()));
        }else if (value instanceof String){
            return builder.greaterThan(root.get(fieldName),value.toString());
        }else if (value instanceof Date){
            return builder.greaterThan(root.get(fieldName),(Date) value);
        }else if (value instanceof Integer){
            return builder.greaterThan(root.get(fieldName),Integer.valueOf(value.toString()));
        }else if (value instanceof Boolean){
            return builder.greaterThan(root.get(fieldName),Boolean.valueOf(value.toString()));
        }
        return null;
    }

    private Predicate createGreaterThanOrEqualToPredicate(
        Root<?> root,
        String fieldName,
        Object value,
        CriteriaBuilder builder
    ){
        if (value instanceof Long){
            return builder.greaterThanOrEqualTo(root.get(fieldName),Long.valueOf(value.toString()));
        }else if (value instanceof String){
            return builder.greaterThanOrEqualTo(root.get(fieldName),value.toString());
        }else if (value instanceof Date){
            return builder.greaterThanOrEqualTo(root.get(fieldName),(Date) value);
        }else if (value instanceof Integer){
            return builder.greaterThanOrEqualTo(root.get(fieldName),Integer.valueOf(value.toString()));
        }else if (value instanceof Boolean){
            return builder.greaterThanOrEqualTo(root.get(fieldName),Boolean.valueOf(value.toString()));
        }
        return null;
    }


    private List<Method> getFilters(Class<? extends BasicRequest> clazz) throws IntrospectionException {
        List<Method> methods = new ArrayList<>();
        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            if (method.getReturnType().isAssignableFrom(GenericFieldFilter.class))
                methods.add(method);
        }
        return methods;
    }

    private String getFieldName(String methodName){
        String fieldName = methodName.substring(3);
        return firstCharToLowerCase(fieldName);
    }


    private String firstCharToLowerCase(String str) {
        if(str == null || str.length() == 0)
            return "";
        if(str.length() == 1)
            return str.toLowerCase();
        return Character.toLowerCase( str.charAt(0) ) + str.substring(1);
    }

}
