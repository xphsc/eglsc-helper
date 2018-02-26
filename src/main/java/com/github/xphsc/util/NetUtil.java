package com.github.xphsc.util;

import com.github.xphsc.io.StreamUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class NetUtil {

    public static final String LOCAL_HOST = "localhost";
    public static final String LOCAL_IP = "127.0.0.1";
    public static final String DEFAULT_MASK = "255.255.255.0";
    public static final int INT_VALUE_127_0_0_1 = 2130706433;

    public NetUtil() {
    }

    public static String resolveIpAddress(String hostname) {
        try {
            InetAddress ignore;
            if(hostname != null && !hostname.equalsIgnoreCase("localhost")) {
                ignore = Inet4Address.getByName(hostname);
            } else {
                ignore = InetAddress.getLocalHost();
            }

            return ignore.getHostAddress();
        } catch (UnknownHostException var2) {
            return null;
        }
    }

    public static int getIpAsInt(String ipAddress) {
        int ipIntValue = 0;
        String[] tokens = StringUtil.split(ipAddress, '.');
        String[] var3 = tokens;
        int var4 = tokens.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String token = var3[var5];
            if(ipIntValue > 0) {
                ipIntValue <<= 8;
            }

            ipIntValue += Integer.parseInt(token);
        }

        return ipIntValue;
    }

    public static int getMaskAsInt(String mask) {
        if(!validateAgaintIPAdressV4Format(mask)) {
            mask = "255.255.255.0";
        }

        return getIpAsInt(mask);
    }

    public static boolean isSocketAccessAllowed(int localIp, int socketIp, int mask) {
        boolean _retVal = false;
        if(socketIp == 2130706433 || (localIp & mask) == (socketIp & mask)) {
            _retVal = true;
        }

        return _retVal;
    }

    public static boolean validateAgaintIPAdressV4Format(String input) {
        if(input == null) {
            return false;
        } else {
            int hitDots = 0;
            char[] data = input.toCharArray();

            for(int i = 0; i < data.length; ++i) {
                char c = data[i];
                int b = 0;

                do {
                    if(c < 48 || c > 57) {
                        return false;
                    }

                    b = b * 10 + c - 48;
                    ++i;
                    if(i >= data.length) {
                        break;
                    }

                    c = data[i];
                } while(c != 46);

                if(b > 255) {
                    return false;
                }

                ++hitDots;
            }

            return hitDots == 4;
        }
    }

    public static String resolveHostName(byte[] ip) {
        try {
            InetAddress ignore = InetAddress.getByAddress(ip);
            return ignore.getHostName();
        } catch (UnknownHostException var2) {
            return null;
        }
    }

    public static byte[] downloadBytes(String url) throws IOException {
        InputStream inputStream = (new URL(url)).openStream();
        Throwable var2 = null;

        byte[] var3;
        try {
            var3 = StreamUtil.readBytes(inputStream);
        } catch (Throwable var12) {
            var2 = var12;
            throw var12;
        } finally {
            if(inputStream != null) {
                if(var2 != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                    }
                } else {
                    inputStream.close();
                }
            }

        }

        return var3;
    }


    public static void downloadFile(String url, File file) throws IOException {
        InputStream inputStream = (new URL(url)).openStream();
        Throwable var3 = null;

        try {
            ReadableByteChannel rbc = Channels.newChannel(inputStream);
            Throwable var5 = null;

            try {
                FileChannel fileChannel = FileChannel.open(file.toPath(), new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});
                Throwable var7 = null;

                try {
                    fileChannel.transferFrom(rbc, 0L, 9223372036854775807L);
                } catch (Throwable var51) {
                    var7 = var51;
                    throw var51;
                } finally {
                    if(fileChannel != null) {
                        if(var7 != null) {
                            try {
                                fileChannel.close();
                            } catch (Throwable var50) {
                                var7.addSuppressed(var50);
                            }
                        } else {
                            fileChannel.close();
                        }
                    }

                }
            } catch (Throwable var53) {
                var5 = var53;
                throw var53;
            } finally {
                if(rbc != null) {
                    if(var5 != null) {
                        try {
                            rbc.close();
                        } catch (Throwable var49) {
                            var5.addSuppressed(var49);
                        }
                    } else {
                        rbc.close();
                    }
                }

            }
        } catch (Throwable var55) {
            var3 = var55;
            throw var55;
        } finally {
            if(inputStream != null) {
                if(var3 != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var48) {
                        var3.addSuppressed(var48);
                    }
                } else {
                    inputStream.close();
                }
            }

        }

    }
}
