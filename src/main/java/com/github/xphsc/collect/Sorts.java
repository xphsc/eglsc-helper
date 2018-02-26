package com.github.xphsc.collect;

import com.github.xphsc.lang.Validator;
import com.github.xphsc.bean.Comparators;
import com.github.xphsc.bean.PropertyComparator;
import com.github.xphsc.bean.comparator.ReverseComparator;
import com.github.xphsc.collect.sort.SortField;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Sorts {

    /**
     * 排序list。只需要返回一个可排序的值，例如Integer。
     * @param list
     * @param sortField
     */
    public static <T, R extends Comparable<R>> void sortList(List<T> list, final SortField<T, R> sortField) {
        if(list == null || sortField == null) {
            return;
        }

        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T left, T right) {
                boolean isLeftNull = left == null;
                boolean isRightNull = right == null;
                if (isLeftNull && isRightNull) {
                    return 0;
                } else if (isLeftNull) {
                    return sortField.isNullFirst() ? -1 : 1;
                } else if (isRightNull) {
                    return sortField.isNullFirst() ? 1 : -1;
                }

                R comparableLeft = sortField.apply(left);
                R comparableRight = sortField.apply(right);
                isLeftNull = comparableLeft == null;
                isRightNull = comparableRight == null;
                if (isLeftNull && isRightNull) {
                    return 0;
                } else if (isLeftNull) {
                    return sortField.isNullFirst() ? -1 : 1;
                } else if (isRightNull) {
                    return sortField.isNullFirst() ? 1 : -1;
                }

                return sortField.isAsc() ? comparableLeft.compareTo(comparableRight)
                        : comparableRight.compareTo(comparableLeft);
            }
        });
    }

    public static <T> void sortList(List<T> list, final SortField<T, ? extends Comparable<?>>... sortFields) {
        if(list == null || sortFields == null || sortFields.length == 0) {
            return;
        }
        Collections.sort(list, new Comparator<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(T left, T right) {
                for(SortField<T, ? extends Comparable<?>> sortField : sortFields) {
                    boolean isLeftNull = left == null;
                    boolean isRightNull = right == null;
                    if(isLeftNull && isRightNull) {
                        continue;
                    } else if (isLeftNull) {
                        return sortField.isNullFirst() ? -1 : 1;
                    } else if (isRightNull) {
                        return sortField.isNullFirst() ? 1 : -1;
                    }

                    Comparable<Object> comparableLeft = (Comparable<Object>) sortField.apply(left);
                    Comparable<Object> comparableRight = (Comparable<Object>) sortField.apply(right);
                    isLeftNull = comparableLeft == null;
                    isRightNull = comparableRight == null;
                    if(isLeftNull && isRightNull) {
                        continue;
                    } else if (isLeftNull) {
                        return sortField.isNullFirst() ? -1 : 1;
                    } else if (isRightNull) {
                        return sortField.isNullFirst() ? 1 : -1;
                    }

                    int compareResult = comparableLeft.compareTo(comparableRight);
                    if(compareResult == 0) {
                        continue;
                    }
                    return (sortField.isAsc() ? 1 : -1) * compareResult;
                }
                return 0;
            }
        });
    }

    public static <K, V> Map<K, V> sortMapByKeyAsc(Map<K, V> map) {
        Validator.notBlank("key", "propertyName can\'t be blank!", new Object[0]);
        return Maps.isEmpty(map)?Collections.emptyMap():sortMap(map, new PropertyComparator("key"));
    }

    public static <K, V> Map<K, V> sortMapByKeyDesc(Map<K, V> map) {
        return Maps.isEmpty(map)?Collections.emptyMap():sortMap(map, new ReverseComparator(new PropertyComparator("key")));
    }

    public static <K, V> Map<K, V> sortMap(Map<K, V> map, Comparator<Map.Entry<K, V>> mapEntryComparator) {
        if(null == map) {
            return Collections.emptyMap();
        } else {
            Validator.notNull(mapEntryComparator, "mapEntryComparator can\'t be null!", new Object[0]);
            List mapEntryList = Lists.toList(map.entrySet());
            return (Map<K, V>) Maps.toMap(sortList(mapEntryList, new Comparator[]{mapEntryComparator}));
        }

    }

    public static <T> List<T> sortList(List<T> list, Comparator... comparators) {
        if(null == list) {
            return Collections.emptyList();
        } else if(Validator.isNullOrEmpty(comparators)) {
            return list;
        } else {
            Collections.sort(list, toComparator(comparators));
            return list;
        }
    }

    public static  <T> void sortList(List<T> list, final String sortField, final String sortOrder) {
        if(list == null || list.size() < 2) {
            return;
        }
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T left, T right) {
                try {
                    Class clazz = left.getClass();
                    Field field = clazz.getDeclaredField(sortField);
                    field.setAccessible(true);
                    String typeName = field.getType().getName().toLowerCase();
                    Object objectleft = field.get(left);
                    Object objectright = field.get(right);
                    boolean ASC_order = (sortOrder == null || "ASC".equalsIgnoreCase(sortOrder));
                    if(typeName.endsWith("string")) {
                        String valueleft = objectleft.toString();
                        String valueright = objectright.toString();
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("short")) {
                        Short valueleft = Short.parseShort(objectleft.toString());
                        Short valueright = Short.parseShort(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("byte")) {
                        Byte valueleft = Byte.parseByte(objectleft.toString());
                        Byte valueright = Byte.parseByte(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("char")) {
                        Integer valueleft = (int)(objectleft.toString().charAt(0));
                        Integer valueright = (int)(objectright.toString().charAt(0));
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("int") || typeName.endsWith("integer")) {
                        Integer valueleft = Integer.parseInt(objectleft.toString());
                        Integer valueright = Integer.parseInt(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("long")) {
                        Long valueleft = Long.parseLong(objectleft.toString());
                        Long valueright = Long.parseLong(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("float")) {
                        Float valueleft = Float.parseFloat(objectleft.toString());
                        Float valueright = Float.parseFloat(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("double")) {
                        Double valueleft = Double.parseDouble(objectleft.toString());
                        Double valueright = Double.parseDouble(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("boolean")) {
                        Boolean valueleft = Boolean.parseBoolean(objectleft.toString());
                        Boolean valueright = Boolean.parseBoolean(objectright.toString());
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("date")) {
                        Date valueleft = (Date)(objectleft);
                        Date valueright = (Date)(objectright);
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else if(typeName.endsWith("timestamp")) {
                        Timestamp valueleft = (Timestamp)(objectleft);
                        Timestamp valueright = (Timestamp)(objectright);
                        return ASC_order ? valueleft.compareTo(valueright) : valueright.compareTo(valueleft);
                    }
                    else {
                        Method method = field.getType().getDeclaredMethod("compareTo", new Class[]{field.getType()});
                        method.setAccessible(true);
                        int result  = (Integer)method.invoke(objectleft, new Object[]{objectright});
                        return ASC_order ? result : result*(-1);
                    }
                }
                catch (Exception e) {
                    String err = e.getLocalizedMessage();
                    System.out.println(err);
                    e.printStackTrace();
                }

                return 0;
            }
        });
    }

    private static <O> Comparator<O> toComparator(Comparator... comparators) {
        return 1 == comparators.length?comparators[0]: Comparators.chainedComparator(comparators);
    }



}
