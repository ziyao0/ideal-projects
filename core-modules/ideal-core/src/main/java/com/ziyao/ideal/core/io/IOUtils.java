package com.ziyao.ideal.core.io;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.CharsetUtils;
import com.ziyao.ideal.core.HexUtils;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.collection.LineIter;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

/**
 * @author ziyao zhang
 */
public abstract class IOUtils extends NioUtils {

    /**
     * 将Reader中的内容复制到Writer中 使用默认缓存大小，拷贝后不关闭Reader
     *
     * @param reader Reader
     * @param writer Writer
     * @return 拷贝的字节数
     * @ IO异常
     */
    public static long copy(Reader reader, Writer writer) {
        return copy(reader, writer, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 将Reader中的内容复制到Writer中，拷贝后不关闭Reader
     *
     * @param reader     Reader
     * @param writer     Writer
     * @param bufferSize 缓存大小
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(Reader reader, Writer writer, int bufferSize) {
        return copy(reader, writer, bufferSize, null);
    }

    /**
     * 将Reader中的内容复制到Writer中，拷贝后不关闭Reader
     *
     * @param reader         Reader
     * @param writer         Writer
     * @param bufferSize     缓存大小
     * @param streamProgress 进度处理器
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(Reader reader, Writer writer, int bufferSize, StreamProgress streamProgress) {
        return copy(reader, writer, bufferSize, -1, streamProgress);
    }

    /**
     * 将Reader中的内容复制到Writer中，拷贝后不关闭Reader
     *
     * @param reader         Reader
     * @param writer         Writer
     * @param bufferSize     缓存大小
     * @param count          最大长度
     * @param streamProgress 进度处理器
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(Reader reader, Writer writer, int bufferSize, long count, StreamProgress streamProgress) {
        return new ReaderWriterCopier(bufferSize, count, streamProgress).copy(reader, writer);
    }

    /**
     * 拷贝流，使用默认Buffer大小，拷贝后不关闭流
     *
     * @param in  输入流
     * @param out 输出流
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(InputStream in, OutputStream out) {
        return copy(in, out, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param in         输入流
     * @param out        输出流
     * @param bufferSize 缓存大小
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize) {
        return copy(in, out, bufferSize, null);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param in             输入流
     * @param out            输出流
     * @param bufferSize     缓存大小
     * @param streamProgress 进度条
     * @return 传输的byte数
     * @ IO异常
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) {
        return copy(in, out, bufferSize, -1, streamProgress);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param in             输入流
     * @param out            输出流
     * @param bufferSize     缓存大小
     * @param count          总拷贝长度
     * @param streamProgress 进度条
     * @return 传输的byte数
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize, long count, StreamProgress streamProgress) {
        return new StreamCopier(bufferSize, count, streamProgress).copy(in, out);
    }

    /**
     * 拷贝文件流，使用NIO
     *
     * @param in  输入
     * @param out 输出
     * @return 拷贝的字节数
     * @ IO异常
     */
    public static long copy(FileInputStream in, FileOutputStream out) {
        Assert.notNull(in, "FileInputStream is null!");
        Assert.notNull(out, "FileOutputStream is null!");

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = in.getChannel();
            outChannel = out.getChannel();
            return copy(inChannel, outChannel);
        } finally {
            close(outChannel);
            close(inChannel);
        }
    }

    /**
     * 获得一个文件读取器，默认使用UTF-8编码
     *
     * @param in 输入流
     * @return BufferedReader对象
     */
    public static BufferedReader getUtf8Reader(InputStream in) {
        return getReader(in, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 获得一个文件读取器
     *
     * @param in          输入流
     * @param charsetName 字符集名称
     * @return BufferedReader对象
     * @deprecated 请使用 {@link #getReader(InputStream, Charset)}
     */
    @Deprecated
    public static BufferedReader getReader(InputStream in, String charsetName) {
        return getReader(in, Charset.forName(charsetName));
    }

    /**
     * 从{@link BOMInputStream}中获取Reader
     *
     * @param in {@link BOMInputStream}
     * @return {@link BufferedReader}
     */
    public static BufferedReader getReader(BOMInputStream in) {
        return getReader(in, in.getCharset());
    }

    /**
     * 从{@link InputStream}中获取{@link BomReader}
     *
     * @param in {@link InputStream}
     * @return {@link BomReader}
     */
    public static BomReader getBomReader(InputStream in) {
        return new BomReader(in);
    }

    /**
     * 获得一个Reader
     *
     * @param in      输入流
     * @param charset 字符集
     * @return BufferedReader对象
     */
    public static BufferedReader getReader(InputStream in, Charset charset) {
        if (null == in) {
            return null;
        }

        InputStreamReader reader;
        if (null == charset) {
            reader = new InputStreamReader(in);
        } else {
            reader = new InputStreamReader(in, charset);
        }

        return new BufferedReader(reader);
    }

    /**
     * 获得{@link BufferedReader}<br>
     * 如果是{@link BufferedReader}强转返回，否则新建。如果提供的Reader为null返回null
     *
     * @param reader 普通Reader，如果为null返回null
     * @return {@link BufferedReader} or null
     */
    public static BufferedReader getReader(Reader reader) {
        if (null == reader) {
            return null;
        }

        return (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * 获得{@link PushbackReader}<br>
     * 如果是{@link PushbackReader}强转返回，否则新建
     *
     * @param reader       普通Reader
     * @param pushBackSize 推后的byte数
     * @return {@link PushbackReader}
     */
    public static PushbackReader getPushBackReader(Reader reader, int pushBackSize) {
        return (reader instanceof PushbackReader) ? (PushbackReader) reader : new PushbackReader(reader, pushBackSize);
    }

    /**
     * 获得一个Writer，默认编码UTF-8
     *
     * @param out 输入流
     * @return OutputStreamWriter对象
     */
    public static OutputStreamWriter getUtf8Writer(OutputStream out) {
        return getWriter(out, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 获得一个Writer
     *
     * @param out         输入流
     * @param charsetName 字符集
     * @return OutputStreamWriter对象
     * @deprecated 请使用 {@link #getWriter(OutputStream, Charset)}
     */
    @Deprecated
    public static OutputStreamWriter getWriter(OutputStream out, String charsetName) {
        return getWriter(out, Charset.forName(charsetName));
    }

    /**
     * 获得一个Writer
     *
     * @param out     输入流
     * @param charset 字符集
     * @return OutputStreamWriter对象
     */
    public static OutputStreamWriter getWriter(OutputStream out, Charset charset) {
        if (null == out) {
            return null;
        }

        if (null == charset) {
            return new OutputStreamWriter(out);
        } else {
            return new OutputStreamWriter(out, charset);
        }
    }

    /**
     * 从流中读取UTF8编码的内容
     *
     * @param in 输入流
     * @return 内容
     * @ IO异常
     */
    public static String readUtf8(InputStream in) {
        return read(in, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 从流中读取内容，读取完成后关闭流
     *
     * @param in          输入流
     * @param charsetName 字符集
     * @return 内容
     * @ IO异常
     * @deprecated 请使用 {@link #read(InputStream, Charset)}
     */
    @Deprecated
    public static String read(InputStream in, String charsetName) {
        final FastByteArrayOutputStream out = read(in);
        return Strings.isEmpty(charsetName) ? out.toString() : out.toString(charsetName);
    }

    /**
     * 从流中读取内容，读取完毕后关闭流
     *
     * @param in      输入流，读取完毕后关闭流
     * @param charset 字符集
     * @return 内容
     * @ IO异常
     */
    public static String read(InputStream in, Charset charset) {
        return Strings.toString(readBytes(in), charset);
    }

    /**
     * 从流中读取内容，读到输出流中，读取完毕后关闭流
     *
     * @param in 输入流
     * @return 输出流
     * @ IO异常
     */
    public static FastByteArrayOutputStream read(InputStream in) {
        return read(in, true);
    }

    /**
     * 从流中读取内容，读到输出流中，读取完毕后可选是否关闭流
     *
     * @param in      输入流
     * @param isClose 读取完毕后是否关闭流
     * @return 输出流
     * @ IO异常
     */
    public static FastByteArrayOutputStream read(InputStream in, boolean isClose) {
        final FastByteArrayOutputStream out;
        if (in instanceof FileInputStream) {
            // 文件流的长度是可预见的，此时直接读取效率更高
            try {
                out = new FastByteArrayOutputStream(in.available());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            out = new FastByteArrayOutputStream();
        }
        try {
            copy(in, out);
        } finally {
            if (isClose) {
                close(in);
            }
        }
        return out;
    }

    /**
     * 从Reader中读取String，读取完毕后关闭Reader
     *
     * @param reader Reader
     * @return String
     * @ IO异常
     */
    public static String read(Reader reader) {
        return read(reader, true);
    }

    /**
     * 从{@link Reader}中读取String
     *
     * @param reader  {@link Reader}
     * @param isClose 是否关闭{@link Reader}
     * @return String
     * @ IO异常
     */
    public static String read(Reader reader, boolean isClose) {
        final StringBuilder builder = new StringBuilder();
        final CharBuffer buffer = CharBuffer.allocate(DEFAULT_BUFFER_SIZE);
        try {
            while (-1 != reader.read(buffer)) {
                builder.append(buffer.flip());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (isClose) {
                IOUtils.close(reader);
            }
        }
        return builder.toString();
    }

    /**
     * 从流中读取bytes，读取完毕后关闭流
     *
     * @param in {@link InputStream}
     * @return bytes
     * @ IO异常
     */
    public static byte[] readBytes(InputStream in) {
        return readBytes(in, true);
    }

    /**
     * 从流中读取bytes
     *
     * @param in      {@link InputStream}
     * @param isClose 是否关闭输入流
     * @return bytes
     * @ IO异常
     */
    public static byte[] readBytes(InputStream in, boolean isClose) {
        return read(in, isClose).toByteArray();
    }

    /**
     * 读取指定长度的byte数组，不关闭流
     *
     * @param in     {@link InputStream}，为{@code null}返回{@code null}
     * @param length 长度，小于等于0返回空byte数组
     * @return bytes
     * @ IO异常
     */
    public static byte[] readBytes(InputStream in, int length) {
        if (null == in) {
            return null;
        }
        if (length <= 0) {
            return new byte[0];
        }

        final FastByteArrayOutputStream out = new FastByteArrayOutputStream(length);
        copy(in, out, DEFAULT_BUFFER_SIZE, length, null);
        return out.toByteArray();
    }

    /**
     * 读取16进制字符串
     *
     * @param in          {@link InputStream}
     * @param length      长度
     * @param toLowerCase true 传换成小写格式 ， false 传换成大写格式
     * @return 16进制字符串
     * @ IO异常
     */
    public static String readHex(InputStream in, int length, boolean toLowerCase) {
        return HexUtils.encodeHexStr(readBytes(in, length), toLowerCase);
    }

    /**
     * 从流中读取前64个byte并转换为16进制，字母部分使用大写
     *
     * @param in {@link InputStream}
     * @return 16进制字符串
     * @ IO异常
     */
    public static String readHex64Upper(InputStream in) {
        return readHex(in, 64, false);
    }

    /**
     * 从流中读取前8192个byte并转换为16进制，字母部分使用大写
     *
     * @param in {@link InputStream}
     * @return 16进制字符串
     * @ IO异常
     */
    public static String readHex8192Upper(InputStream in) {
        try {
            int i = in.available();
            return readHex(in, Math.min(8192, in.available()), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从流中读取前64个byte并转换为16进制，字母部分使用小写
     *
     * @param in {@link InputStream}
     * @return 16进制字符串
     * @ IO异常
     */
    public static String readHex64Lower(InputStream in) {
        return readHex(in, 64, true);
    }


    /**
     * 从流中读取内容，使用UTF-8编码
     *
     * @param <T>        集合类型
     * @param in         输入流
     * @param collection 返回集合
     * @return 内容
     * @ IO异常
     */
    public static <T extends Collection<String>> T readUtf8Lines(InputStream in, T collection) {
        return readLines(in, CharsetUtils.CHARSET_UTF_8, collection);
    }

    /**
     * 从流中读取内容
     *
     * @param <T>         集合类型
     * @param in          输入流
     * @param charsetName 字符集
     * @param collection  返回集合
     * @return 内容
     * @ IO异常
     * @deprecated 请使用 {@link #readLines(InputStream, Charset, Collection)}
     */
    @Deprecated
    public static <T extends Collection<String>> T readLines(InputStream in, String charsetName, T collection) {
        return readLines(in, CharsetUtils.forName(charsetName), collection);
    }

    /**
     * 从流中读取内容
     *
     * @param <T>        集合类型
     * @param in         输入流
     * @param charset    字符集
     * @param collection 返回集合
     * @return 内容
     * @ IO异常
     */
    public static <T extends Collection<String>> T readLines(InputStream in, Charset charset, T collection) {
        return readLines(getReader(in, charset), collection);
    }

    /**
     * 从Reader中读取内容
     *
     * @param <T>        集合类型
     * @param reader     {@link Reader}
     * @param collection 返回集合
     * @return 内容
     * @ IO异常
     */
    public static <T extends Collection<String>> T readLines(Reader reader, T collection) {
        readLines(reader, (LineHandler) collection::add);
        return collection;
    }

    /**
     * 按行读取UTF-8编码数据，针对每行的数据做处理
     *
     * @param in          {@link InputStream}
     * @param lineHandler 行处理接口，实现handle方法用于编辑一行的数据后入到指定地方
     * @ IO异常
     */
    public static void readUtf8Lines(InputStream in, LineHandler lineHandler) {
        readLines(in, CharsetUtils.CHARSET_UTF_8, lineHandler);
    }

    /**
     * 按行读取数据，针对每行的数据做处理
     *
     * @param in          {@link InputStream}
     * @param charset     {@link Charset}编码
     * @param lineHandler 行处理接口，实现handle方法用于编辑一行的数据后入到指定地方
     * @ IO异常
     */
    public static void readLines(InputStream in, Charset charset, LineHandler lineHandler) {
        readLines(getReader(in, charset), lineHandler);
    }

    /**
     * 按行读取数据，针对每行的数据做处理<br>
     * {@link Reader}自带编码定义，因此读取数据的编码跟随其编码。<br>
     * 此方法不会关闭流，除非抛出异常
     *
     * @param reader      {@link Reader}
     * @param lineHandler 行处理接口，实现handle方法用于编辑一行的数据后入到指定地方
     * @ IO异常
     */
    public static void readLines(Reader reader, LineHandler lineHandler) {
        Assert.notNull(reader, "");
        Assert.notNull(lineHandler, "");

        for (String line : lineIter(reader)) {
            lineHandler.handle(line);
        }
    }


    /**
     * String 转为流
     *
     * @param content     内容
     * @param charsetName 编码
     * @return 字节流
     * @deprecated 请使用 {@link #toStream(String, Charset)}
     */
    @Deprecated
    public static ByteArrayInputStream toStream(String content, String charsetName) {
        return toStream(content, CharsetUtils.forName(charsetName));
    }

    /**
     * String 转为流
     *
     * @param content 内容
     * @param charset 编码
     * @return 字节流
     */
    public static ByteArrayInputStream toStream(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        return toStream(Strings.toBytes(content, charset));
    }

    /**
     * String 转为UTF-8编码的字节流流
     *
     * @param content 内容
     * @return 字节流
     */
    public static ByteArrayInputStream toUtf8Stream(String content) {
        return toStream(content, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 文件转为{@link FileInputStream}
     *
     * @param file 文件
     * @return {@link FileInputStream}
     */
    public static FileInputStream toStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * byte[] 转为{@link ByteArrayInputStream}
     *
     * @param content 内容bytes
     * @return 字节流
     */
    public static ByteArrayInputStream toStream(byte[] content) {
        if (content == null) {
            return null;
        }
        return new ByteArrayInputStream(content);
    }

    /**
     * {@link ByteArrayOutputStream}转为{@link ByteArrayInputStream}
     *
     * @param out {@link ByteArrayOutputStream}
     * @return 字节流
     */
    public static ByteArrayInputStream toStream(ByteArrayOutputStream out) {
        if (out == null) {
            return null;
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * 转换为{@link BufferedInputStream}
     *
     * @param in {@link InputStream}
     * @return {@link BufferedInputStream}
     */
    public static BufferedInputStream toBuffered(InputStream in) {
        Assert.notNull(in, "InputStream must be not null!");
        return (in instanceof BufferedInputStream) ? (BufferedInputStream) in : new BufferedInputStream(in);
    }

    /**
     * 转换为{@link BufferedInputStream}
     *
     * @param in         {@link InputStream}
     * @param bufferSize buffer size
     * @return {@link BufferedInputStream}
     */
    public static BufferedInputStream toBuffered(InputStream in, int bufferSize) {
        Assert.notNull(in, "InputStream must be not null!");
        return (in instanceof BufferedInputStream) ? (BufferedInputStream) in : new BufferedInputStream(in, bufferSize);
    }

    /**
     * 转换为{@link BufferedOutputStream}
     *
     * @param out {@link OutputStream}
     * @return {@link BufferedOutputStream}
     */
    public static BufferedOutputStream toBuffered(OutputStream out) {
        Assert.notNull(out, "OutputStream must be not null!");
        return (out instanceof BufferedOutputStream) ? (BufferedOutputStream) out : new BufferedOutputStream(out);
    }

    /**
     * 转换为{@link BufferedOutputStream}
     *
     * @param out        {@link OutputStream}
     * @param bufferSize buffer size
     * @return {@link BufferedOutputStream}
     */
    public static BufferedOutputStream toBuffered(OutputStream out, int bufferSize) {
        Assert.notNull(out, "OutputStream must be not null!");
        return (out instanceof BufferedOutputStream) ? (BufferedOutputStream) out : new BufferedOutputStream(out, bufferSize);
    }

    /**
     * 转换为{@link BufferedReader}
     *
     * @param reader {@link Reader}
     * @return {@link BufferedReader}
     */
    public static BufferedReader toBuffered(Reader reader) {
        Assert.notNull(reader, "Reader must be not null!");
        return (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * 转换为{@link BufferedReader}
     *
     * @param reader     {@link Reader}
     * @param bufferSize buffer size
     * @return {@link BufferedReader}
     */
    public static BufferedReader toBuffered(Reader reader, int bufferSize) {
        Assert.notNull(reader, "Reader must be not null!");
        return (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader, bufferSize);
    }

    /**
     * 转换为{@link BufferedWriter}
     *
     * @param writer {@link Writer}
     * @return {@link BufferedWriter}
     */
    public static BufferedWriter toBuffered(Writer writer) {
        Assert.notNull(writer, "Writer must be not null!");
        return (writer instanceof BufferedWriter) ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    /**
     * 转换为{@link BufferedWriter}
     *
     * @param writer     {@link Writer}
     * @param bufferSize buffer size
     * @return {@link BufferedWriter}
     */
    public static BufferedWriter toBuffered(Writer writer, int bufferSize) {
        Assert.notNull(writer, "Writer must be not null!");
        return (writer instanceof BufferedWriter) ? (BufferedWriter) writer : new BufferedWriter(writer, bufferSize);
    }

    /**
     * 将{@link InputStream}转换为支持mark标记的流<br>
     * 若原流支持mark标记，则返回原流，否则使用{@link BufferedInputStream} 包装之
     *
     * @param in 流
     * @return {@link InputStream}
     */
    public static InputStream toMarkSupportStream(InputStream in) {
        if (null == in) {
            return null;
        }
        if (!in.markSupported()) {
            return new BufferedInputStream(in);
        }
        return in;
    }

    /**
     * 转换为{@link PushbackInputStream}<br>
     * 如果传入的输入流已经是{@link PushbackInputStream}，强转返回，否则新建一个
     *
     * @param in           {@link InputStream}
     * @param pushBackSize 推后的byte数
     * @return {@link PushbackInputStream}
     */
    public static PushbackInputStream toPushbackStream(InputStream in, int pushBackSize) {
        return (in instanceof PushbackInputStream) ? (PushbackInputStream) in : new PushbackInputStream(in, pushBackSize);
    }

    /**
     * 将指定{@link InputStream} 转换为{@link InputStream#available()}方法可用的流。<br>
     * 在Socket通信流中，服务端未返回数据情况下{@link InputStream#available()}方法始终为{@code 0}<br>
     * 因此，在读取前需要调用{@link InputStream#read()}读取一个字节（未返回会阻塞），一旦读取到了，{@link InputStream#available()}方法就正常了。<br>
     * 需要注意的是，在网络流中，是按照块来传输的，所以 {@link InputStream#available()} 读取到的并非最终长度，而是此次块的长度。<br>
     * 此方法返回对象的规则为：
     *
     * <ul>
     *     <li>FileInputStream 返回原对象，因为文件流的available方法本身可用</li>
     *     <li>其它InputStream 返回PushbackInputStream</li>
     * </ul>
     *
     * @param in 被转换的流
     * @return 转换后的流，可能为{@link PushbackInputStream}
     */
    public static InputStream toAvailableStream(InputStream in) {
        if (in instanceof FileInputStream) {
            // FileInputStream本身支持available方法。
            return in;
        }

        final PushbackInputStream pushbackInputStream = toPushbackStream(in, 1);
        try {
            final int available = pushbackInputStream.available();
            if (available <= 0) {
                //此操作会阻塞，直到有数据被读到
                int b = pushbackInputStream.read();
                pushbackInputStream.unread(b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pushbackInputStream;
    }

    /**
     * 将byte[]写到流中
     *
     * @param out        输出流
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param content    写入的内容
     * @ IO异常
     */
    public static void write(OutputStream out, boolean isCloseOut, byte[] content) {
        try {
            out.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (isCloseOut) {
                close(out);
            }
        }
    }

    /**
     * 将多部分内容写到流中，自动转换为UTF-8字符串
     *
     * @param out        输出流
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param contents   写入的内容，调用toString()方法，不包括不会自动换行
     * @ IO异常
     */
    public static void writeUtf8(OutputStream out, boolean isCloseOut, Object... contents) {
        write(out, CharsetUtils.CHARSET_UTF_8, isCloseOut, contents);
    }

    /**
     * 将多部分内容写到流中，自动转换为字符串
     *
     * @param out         输出流
     * @param charsetName 写出的内容的字符集
     * @param isCloseOut  写入完毕是否关闭输出流
     * @param contents    写入的内容，调用toString()方法，不包括不会自动换行
     * @ IO异常
     * @deprecated 请使用 {@link #write(OutputStream, Charset, boolean, Object...)}
     */
    @Deprecated
    public static void write(OutputStream out, String charsetName, boolean isCloseOut, Object... contents) {
        write(out, CharsetUtils.forName(charsetName), isCloseOut, contents);
    }

    /**
     * 将多部分内容写到流中，自动转换为字符串
     *
     * @param out        输出流
     * @param charset    写出的内容的字符集
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param contents   写入的内容，调用toString()方法，不包括不会自动换行
     * @ IO异常
     */
    public static void write(OutputStream out, Charset charset, boolean isCloseOut, Object... contents) {
        OutputStreamWriter osw = null;
        try {
            osw = getWriter(out, charset);
            for (Object content : contents) {
                if (content != null) {
                    osw.write(Strings.toString(content, CharsetUtils.defaultCharset()));
                }
            }
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (isCloseOut) {
                close(osw);
            }
        }
    }

    /**
     * 将多部分内容写到流中
     *
     * @param out        输出流
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param obj        写入的对象内容
     * @ IO异常
     */
    public static void writeObj(OutputStream out, boolean isCloseOut, Serializable obj) {
        writeObjects(out, isCloseOut, obj);
    }

    /**
     * 将多部分内容写到流中
     *
     * @param out        输出流
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param contents   写入的内容
     * @ IO异常
     */
    public static void writeObjects(OutputStream out, boolean isCloseOut, Serializable... contents) {
        ObjectOutputStream osw = null;
        try {
            osw = out instanceof ObjectOutputStream ? (ObjectOutputStream) out : new ObjectOutputStream(out);
            for (Object content : contents) {
                if (content != null) {
                    osw.writeObject(content);
                }
            }
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (isCloseOut) {
                close(osw);
            }
        }
    }

    /**
     * 从缓存中刷出数据
     *
     * @param flushable {@link Flushable}
     */
    public static void flush(Flushable flushable) {
        if (null != flushable) {
            try {
                flushable.flush();
            } catch (Exception e) {
                // 静默刷出
            }
        }
    }

    /**
     * 关闭<br>
     * 关闭失败不会抛出异常
     *
     * @param closeable 被关闭的对象
     */
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    /**
     * 尝试关闭指定对象<br>
     * 判断对象如果实现了{@link AutoCloseable}，则调用之
     *
     * @param obj 可关闭对象
     */
    public static void closeIfPosible(Object obj) {
        if (obj instanceof AutoCloseable) {
            close((AutoCloseable) obj);
        }
    }

    /**
     * 对比两个流内容是否相同<br>
     * 内部会转换流为 {@link BufferedInputStream}
     *
     * @param input1 第一个流
     * @param input2 第二个流
     * @return 两个流的内容一致返回true，否则false
     * @ IO异常
     */
    public static boolean contentEquals(InputStream input1, InputStream input2) {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }

        try {
            int ch = input1.read();
            while (EOF != ch) {
                int ch2 = input2.read();
                if (ch != ch2) {
                    return false;
                }
                ch = input1.read();
            }

            int ch2 = input2.read();
            return ch2 == EOF;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对比两个Reader的内容是否一致<br>
     * 内部会转换流为 {@link BufferedInputStream}
     *
     * @param input1 第一个reader
     * @param input2 第二个reader
     * @return 两个流的内容一致返回true，否则false
     * @ IO异常
     */
    public static boolean contentEquals(Reader input1, Reader input2) {
        input1 = getReader(input1);
        input2 = getReader(input2);

        try {
            int ch = input1.read();
            while (EOF != ch) {
                int ch2 = input2.read();
                if (ch != ch2) {
                    return false;
                }
                ch = input1.read();
            }

            int ch2 = input2.read();
            return ch2 == EOF;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对比两个流内容是否相同，忽略EOL字符<br>
     * 内部会转换流为 {@link BufferedInputStream}
     *
     * @param input1 第一个流
     * @param input2 第二个流
     * @return 两个流的内容一致返回true，否则false
     * @ IO异常
     */
    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) {
        final BufferedReader br1 = getReader(input1);
        final BufferedReader br2 = getReader(input2);

        try {
            String line1 = br1.readLine();
            String line2 = br2.readLine();
            while (line1 != null && line1.equals(line2)) {
                line1 = br1.readLine();
                line2 = br2.readLine();
            }
            return Objects.equals(line1, line2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算流CRC32校验码，计算后关闭流
     *
     * @param in 文件，不能为目录
     * @return CRC32值
     * @ IO异常
     */
    public static long checksumCRC32(InputStream in) {
        return checksum(in, new CRC32()).getValue();
    }

    /**
     * 计算流的校验码，计算后关闭流
     *
     * @param in       流
     * @param checksum {@link Checksum}
     * @return Checksum
     * @ IO异常
     */
    public static Checksum checksum(InputStream in, Checksum checksum) {
        Assert.notNull(in, "InputStream is null !");
        if (null == checksum) {
            checksum = new CRC32();
        }
        try {
            in = new CheckedInputStream(in, checksum);
            copy(in, new NullOutputStream());
        } finally {
            IOUtils.close(in);
        }
        return checksum;
    }

    /**
     * 计算流的校验码，计算后关闭流
     *
     * @param in       流
     * @param checksum {@link Checksum}
     * @return Checksum
     * @ IO异常
     */
    public static long checksumValue(InputStream in, Checksum checksum) {
        return checksum(in, checksum).getValue();
    }

    /**
     * 返回行遍历器
     * <pre>
     * LineIterator it = null;
     * try {
     * 	it = IOUtils.lineIter(reader);
     * 	while (it.hasNext()) {
     * 		String line = it.nextLine();
     * 		// do something with line
     *    }
     * } finally {
     * 		it.close();
     * }
     * </pre>
     *
     * @param reader {@link Reader}
     * @return {@link LineIter}
     */
    public static LineIter lineIter(Reader reader) {
        return new LineIter(reader);
    }

    /**
     * 返回行遍历器
     * <pre>
     * LineIterator it = null;
     * try {
     * 	it = IOUtils.lineIter(in, Charsets.CHARSET_UTF_8);
     * 	while (it.hasNext()) {
     * 		String line = it.nextLine();
     * 		// do something with line
     *    }
     * } finally {
     * 		it.close();
     * }
     * </pre>
     *
     * @param in      {@link InputStream}
     * @param charset 编码
     * @return {@link LineIter}
     */
    public static LineIter lineIter(InputStream in, Charset charset) {
        return new LineIter(in, charset);
    }

    /**
     * {@link ByteArrayOutputStream} 转换为String
     *
     * @param out     {@link ByteArrayOutputStream}
     * @param charset 编码
     * @return 字符串
     */
    public static String toStr(ByteArrayOutputStream out, Charset charset) {
        return out.toString(charset);
    }
}
