package com.android.project.net;

import androidx.annotation.Nullable;

import com.android.project.BuildConfig;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author Finn
 * @date 2020
 */
public final class LoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    //标记： 请求 - 响应 数据成对出现
    private int mRequestTag = 0;
    private static final int MAX_REQUEST_TAG = 9999;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        if (mRequestTag >= MAX_REQUEST_TAG) {
            mRequestTag = 0;
        }
        mRequestTag++;
        final int requestTag = mRequestTag;

        if (BuildConfig.DEBUG) {
            RequestBody requestBody = request.body();
            boolean hasRequestBody = requestBody != null;
            Connection connection = chain.connection();
            Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
            String requestStartMessage = "(" + requestTag + ") " + "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
            if (hasRequestBody) {
                requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
            }
            logger.log(requestStartMessage);

            if (hasRequestBody && !bodyEncoded(request.headers())) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                if (isPlaintext(buffer)) {
                    logger.log("-->" + buffer.readString(charset));
                }
            }

        }
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.body();

        if (BuildConfig.DEBUG) {
            final String responseJson = printResultJson(responseBody);
            final int responseCode = response.code();
            final String responseMsg = response.message();
            final String requestUrl = response.request().url().toString();
            final String responseMessage = "(" + requestTag + ") " + "<-- " + responseCode + ' ' + responseMsg + ' '
                    + requestUrl + " " + responseJson + " (" + (t2 - t1) / 1e6d + "ms)";
            logger.log(responseMessage);
        }

        return response;
    }


    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !"identity".equalsIgnoreCase(contentEncoding);
    }

    /**
     * 打印响应结果
     *
     * @return 解析后的响应结果
     * @throws IOException
     */
    @Nullable
    private String printResultJson(ResponseBody responseBody) throws IOException {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        final Buffer buffer = source.buffer();
        return buffer.clone().readString(UTF8);
    }


    public interface Logger {
        void log(String message);
    }

    public LoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

}
