package com.github.xphsc.lang;

import com.github.xphsc.exception.ExceptionUtil;
import com.github.xphsc.io.ByteArrayOutputStream;
import com.github.xphsc.io.StreamUtil;
import com.github.xphsc.util.StringUtil;


import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

/** 生成唯一ID。
 * Created by ${huipei.x} on 2017-6-18
 */
public class UUID {

    private static boolean noCase;
    private static String instanceId;
    private static AtomicInteger counter;

    public UUID() {
        this(true);
    }

    public UUID(boolean noCase) {
        // 1. Machine ID - 根据IP/MAC区分
        byte[] machineId = getLocalHostAddress();

        // 2. JVM ID - 根据启动时间区分 + 随机数
        byte[] jvmId = getRandomizedTime();

        instanceId = StringUtil.bytesToString(machineId, noCase) + "-" + StringUtil.bytesToString(jvmId, noCase);

        // counter
        counter = new AtomicInteger();

        noCase = noCase;
    }

    /** 取得local host的地址，如果有可能，取得物理MAC地址。 */
    private static byte[] getLocalHostAddress() {
        Method getHardwareAddress;

        try {
            getHardwareAddress = NetworkInterface.class.getMethod("getHardwareAddress");
        } catch (Exception e) {
            getHardwareAddress = null;
        }

        byte[] addr;

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            if (getHardwareAddress != null) {
                addr = (byte[]) getHardwareAddress.invoke(NetworkInterface.getByInetAddress(localHost)); // maybe null
            } else {
                addr = localHost.getAddress();
            }
        } catch (Exception e) {
            addr = null;
        }

        if (addr == null) {
            addr = new byte[] { 127, 0, 0, 1 };
        }

        return addr;
    }

    /** 取得当前时间，加上随机数。 */
    private byte[] getRandomizedTime() {
        long jvmId = System.currentTimeMillis();
        long random = new SecureRandom().nextLong();

        // 取得上述ID的bytes，并转化成字符串
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeLong(jvmId);
            dos.writeLong(random);
            return baos.toByteArray();
        } catch (Exception e) {
            throw ExceptionUtil.toRuntimeException(e);
        } finally {
            StreamUtil.close(dos);
        }

    }

    public static String nextID() {
        // MACHINE_ID + JVM_ID + 当前时间 + counter
        return instanceId + "-" + StringUtil.longToString(System.currentTimeMillis(), noCase) + "-"
                + StringUtil.longToString(counter.getAndIncrement(), noCase);
    }

}
