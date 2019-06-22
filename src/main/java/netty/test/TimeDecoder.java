package netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 这个ByteToMessageDecoder是ChannelHandlerAdapter的子类，专门用来处理NIO的字节归类的
 * 更简单点可以用ReplayingDecoder
 *
 * Created by donglongcheng01 on 2018/3/30.
 */
public class TimeDecoder extends ByteToMessageDecoder {

    /**
     * list就是用来放成品的
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        // 这里解析的时候把ByteBuf转换成了UnixTime(POJO)
        list.add(new UnixTime(byteBuf.readUnsignedInt()));
    }
}
