package com.github.xphsc.encrypt;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class Base32 {

    private static final String ERR_CANONICAL_LEN = "Invalid Base32 string length";
    private static final String ERR_CANONICAL_END = "Invalid end bits of Base32 string";
    private static final String ERR_INVALID_CHARS = "Invalid character in Base32 string";
    private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
    private static final byte[] LOOKUP = new byte[]{(byte)26, (byte)27, (byte)28, (byte)29, (byte)30, (byte)31, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25};

    public Base32() {
    }

    public static String encode(byte[] bytes) {
        StringBuilder base32 = new StringBuilder((bytes.length * 8 + 4) / 5);
        int i = 0;

        while(i < bytes.length) {
            int currByte = bytes[i++] & 255;
            base32.append(CHARS[currByte >> 3]);
            int digit = (currByte & 7) << 2;
            if(i >= bytes.length) {
                base32.append(CHARS[digit]);
                break;
            }

            currByte = bytes[i++] & 255;
            base32.append(CHARS[digit | currByte >> 6]);
            base32.append(CHARS[currByte >> 1 & 31]);
            digit = (currByte & 1) << 4;
            if(i >= bytes.length) {
                base32.append(CHARS[digit]);
                break;
            }

            currByte = bytes[i++] & 255;
            base32.append(CHARS[digit | currByte >> 4]);
            digit = (currByte & 15) << 1;
            if(i >= bytes.length) {
                base32.append(CHARS[digit]);
                break;
            }

            currByte = bytes[i++] & 255;
            base32.append(CHARS[digit | currByte >> 7]);
            base32.append(CHARS[currByte >> 2 & 31]);
            digit = (currByte & 3) << 3;
            if(i >= bytes.length) {
                base32.append(CHARS[digit]);
                break;
            }

            currByte = bytes[i++] & 255;
            base32.append(CHARS[digit | currByte >> 5]);
            base32.append(CHARS[currByte & 31]);
        }

        return base32.toString();
    }

    public static byte[] decode(String base32) throws IllegalArgumentException {
        switch(base32.length() % 8) {
            case 1:
            case 3:
            case 6:
                throw new IllegalArgumentException("Invalid Base32 string length");
            default:
                byte[] bytes = new byte[base32.length() * 5 / 8];
                int offset = 0;
                int i = 0;

                while(i < base32.length()) {
                    int lookup = base32.charAt(i++) - 50;
                    if(lookup < 0 || lookup >= LOOKUP.length) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                    }

                    byte digit = LOOKUP[lookup];
                    if(digit == -1) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                    }

                    byte nextByte = (byte)(digit << 3);
                    lookup = base32.charAt(i++) - 50;
                    if(lookup >= 0 && lookup < LOOKUP.length) {
                        digit = LOOKUP[lookup];
                        if(digit == -1) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        bytes[offset++] = (byte)(nextByte | digit >> 2);
                        nextByte = (byte)((digit & 3) << 6);
                        if(i >= base32.length()) {
                            if(nextByte != 0) {
                                throw new IllegalArgumentException("Invalid end bits of Base32 string");
                            }
                            break;
                        }

                        lookup = base32.charAt(i++) - 50;
                        if(lookup < 0 || lookup >= LOOKUP.length) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        digit = LOOKUP[lookup];
                        if(digit == -1) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        nextByte |= (byte)(digit << 1);
                        lookup = base32.charAt(i++) - 50;
                        if(lookup < 0 || lookup >= LOOKUP.length) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        digit = LOOKUP[lookup];
                        if(digit == -1) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        bytes[offset++] = (byte)(nextByte | digit >> 4);
                        nextByte = (byte)((digit & 15) << 4);
                        if(i >= base32.length()) {
                            if(nextByte != 0) {
                                throw new IllegalArgumentException("Invalid end bits of Base32 string");
                            }
                            break;
                        }

                        lookup = base32.charAt(i++) - 50;
                        if(lookup < 0 || lookup >= LOOKUP.length) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        digit = LOOKUP[lookup];
                        if(digit == -1) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        bytes[offset++] = (byte)(nextByte | digit >> 1);
                        nextByte = (byte)((digit & 1) << 7);
                        if(i >= base32.length()) {
                            if(nextByte != 0) {
                                throw new IllegalArgumentException("Invalid end bits of Base32 string");
                            }
                            break;
                        }

                        lookup = base32.charAt(i++) - 50;
                        if(lookup < 0 || lookup >= LOOKUP.length) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        digit = LOOKUP[lookup];
                        if(digit == -1) {
                            throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        nextByte |= (byte)(digit << 2);
                        lookup = base32.charAt(i++) - 50;
                        if(lookup >= 0 && lookup < LOOKUP.length) {
                            digit = LOOKUP[lookup];
                            if(digit == -1) {
                                throw new IllegalArgumentException("Invalid character in Base32 string");
                            }

                            bytes[offset++] = (byte)(nextByte | digit >> 3);
                            nextByte = (byte)((digit & 7) << 5);
                            if(i < base32.length()) {
                                lookup = base32.charAt(i++) - 50;
                                if(lookup >= 0 && lookup < LOOKUP.length) {
                                    digit = LOOKUP[lookup];
                                    if(digit == -1) {
                                        throw new IllegalArgumentException("Invalid character in Base32 string");
                                    }

                                    bytes[offset++] = (byte)(nextByte | digit);
                                    continue;
                                }

                                throw new IllegalArgumentException("Invalid character in Base32 string");
                            }

                            if(nextByte != 0) {
                                throw new IllegalArgumentException("Invalid end bits of Base32 string");
                            }
                            break;
                        }

                        throw new IllegalArgumentException("Invalid character in Base32 string");
                    }

                    throw new IllegalArgumentException("Invalid character in Base32 string");
                }

                return bytes;
        }
    }
}
