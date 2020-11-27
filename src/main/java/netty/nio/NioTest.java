package netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by donglongcheng01 on 2018/3/30.
 */
public class NioTest {

    public static void main(String[] args) throws Exception {
        SelectorProvider provider = SelectorProvider.provider();
        Selector selector1 = provider.openSelector();
        Selector selector2 = provider.openSelector();
        System.out.println(selector1 == selector2);
        System.out.println(selector1.equals(selector2));
    }

    public static void pipleTest() throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

    }

    /**
     * nio的selector（Reactor）模式的模板代码
     */
    public static void selectorTest() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("localhost", 8080)); // acceptor

        Selector selector = Selector.open();
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ); // acceptor

        // reactor
        while (true) {

            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isAcceptable()) {

                } else if (selectionKey.isConnectable()) {

                } else if (selectionKey.isReadable()) {

                } else if (selectionKey.isWritable()) {

                }

                // 最后要删除，因为是在迭代中删除，所以必须规规矩矩的用迭代器，不要用增强型for循环
                keyIterator.remove();
            }

        }

    }

    public static void channelToChannelTransfer() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("nio-data-to.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    public static void basicTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

//        System.out.println(inChannel.position()); // 会获取当前Channel的位置（字节）
//        inChannel.position(aFile.length()-10); // 会调整Channel的读取位置（字节）
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("[Read " + bytesRead + " bytes]");
            // buffer从被写入的状态到被读取时一定要调这个flip
            buf.flip();

            while (buf.hasRemaining()) {
                // 每次就打出一个字节
                System.out.print((char) buf.get());
            }

            buf.clear(); // make buffer ready for writing，清空buffer准备下一次的写入
            bytesRead = inChannel.read(buf);

        }
        aFile.close();
    }

}
