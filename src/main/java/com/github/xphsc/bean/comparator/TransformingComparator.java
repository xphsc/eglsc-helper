package com.github.xphsc.bean.comparator;


import com.github.xphsc.bean.Comparators;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class TransformingComparator <I, O> implements Comparator<I>, Serializable {
    private static final long serialVersionUID = 3456940356043606220L;
    private final Comparator<O> decorated;
    private final Transformer<? super I, ? extends O> transformer;

    public TransformingComparator(Transformer<? super I, ? extends O> transformer) {
        this(transformer, Comparators.NATURAL_COMPARATOR);
    }

    public TransformingComparator(Transformer<? super I, ? extends O> transformer, Comparator<O> decorated) {
        this.decorated = decorated;
        this.transformer = transformer;
    }

   /* public int compare(I obj1, I obj2) {
        Object value1 = this.transformer.transform(obj1);
        Object value2 = this.transformer.transform(obj2);
        return this.decorated.compare(value1, value2);
    }*/

    public int hashCode() {
        byte total = 17;
        int total1 = total * 37 + (this.decorated == null?0:this.decorated.hashCode());
        total1 = total1 * 37 + (this.transformer == null?0:this.transformer.hashCode());
        return total1;
    }

    @Override
    public int compare(I o1, I o2) {
        return 0;
    }

    public boolean equals(Object object) {
        if(this == object) {
            return true;
        } else if(null == object) {
            return false;
        } else if(!object.getClass().equals(this.getClass())) {
            return false;
        } else {
            boolean var10000;
            label48: {
                label32: {
                    TransformingComparator comp = (TransformingComparator)object;
                    if(null == this.decorated) {
                        if(null != comp.decorated) {
                            break label32;
                        }
                    } else if(!this.decorated.equals(comp.decorated)) {
                        break label32;
                    }

                    if(null == this.transformer) {
                        if(null == comp.transformer) {
                            break label48;
                        }
                    } else if(this.transformer.equals(comp.transformer)) {
                        break label48;
                    }
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        }
    }
}
