package com.github.xphsc.lang;

import com.github.xphsc.exception.UtilException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class SerializationUtil {

    public SerializationUtil() {
    }

    public static <T extends Serializable> T clone(T object) {
        if(object == null) {
            return null;
        } else {
            byte[] objectData = serialize(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
            SerializationUtil.ClassLoaderAwareObjectInputStream in = null;

            Serializable var5;
            try {
                in = new SerializationUtil.ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
                Serializable ex = (Serializable)in.readObject();
                var5 = ex;
            } catch (ClassNotFoundException var14) {
                throw new UtilException("ClassNotFoundException while reading cloned object data", var14);
            } catch (IOException var15) {
                throw new UtilException("IOException while reading cloned object data", var15);
            } finally {
                try {
                    if(in != null) {
                        in.close();
                    }
                } catch (IOException var16) {
                    throw new UtilException("IOException on closing cloned object data InputStream.", var16);
                }

            }

            return (T) var5;
        }
    }

    public static <T extends Serializable> T roundtrip(T msg) {
        return (T) deserialize(serialize(msg));
    }

    public static void serialize(Serializable obj, OutputStream outputStream) {
        if(outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        } else {
            ObjectOutputStream out = null;

            try {
                out = new ObjectOutputStream(outputStream);
                out.writeObject(obj);
            } catch (IOException var11) {
                throw new UtilException(var11);
            } finally {
                try {
                    if(out != null) {
                        out.close();
                    }
                } catch (IOException var10) {
                    ;
                }

            }

        }
    }

    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        serialize(obj, baos);
        return baos.toByteArray();
    }

    public static <T> T deserialize(InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        } else {
            ObjectInputStream in = null;

            Object var3;
            try {
                in = new ObjectInputStream(inputStream);
                Object ex = in.readObject();
                var3 = ex;
            } catch (ClassNotFoundException var13) {
                throw new UtilException(var13);
            } catch (IOException var14) {
                throw new UtilException(var14);
            } finally {
                try {
                    if(in != null) {
                        in.close();
                    }
                } catch (IOException var12) {
                    ;
                }

            }

            return (T) var3;
        }
    }

    public static <T> T deserialize(byte[] objectData) {
        if(objectData == null) {
            throw new IllegalArgumentException("The byte[] must not be null");
        } else {
            return deserialize((InputStream)(new ByteArrayInputStream(objectData)));
        }
    }

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        public ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String name = desc.getName();

            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException var7) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException var6) {
                    Class cls = (Class)primitiveTypes.get(name);
                    if(cls != null) {
                        return cls;
                    } else {
                        throw var6;
                    }
                }
            }
        }

        static {
            primitiveTypes.put("byte", Byte.TYPE);
            primitiveTypes.put("short", Short.TYPE);
            primitiveTypes.put("int", Integer.TYPE);
            primitiveTypes.put("long", Long.TYPE);
            primitiveTypes.put("float", Float.TYPE);
            primitiveTypes.put("double", Double.TYPE);
            primitiveTypes.put("boolean", Boolean.TYPE);
            primitiveTypes.put("char", Character.TYPE);
            primitiveTypes.put("void", Void.TYPE);
        }
    }
}
