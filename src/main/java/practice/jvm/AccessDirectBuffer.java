package practice.jvm;

import java.nio.ByteBuffer;

/**
 * 直接内存读写 vs. 堆内存读写 --> 直接内存块
 * 显然直接内存读写更快，如果再加上jvm参数 -server，直接内存更快
 *
 * 直接内存分配 vs. 堆内存分配 --> 堆内存分配更快
 *
 * 直接内存适合申请次数少、频繁读写
 *
 * @title AccessDirectBuffer
 * @Description 直接内存分配 vs. 对内存分配
 * @Author donglongcheng01
 * @Date 2019-07-09
 **/
public class AccessDirectBuffer {

    /**
     * 分配直接内存500字节，反复读写操作，测速
     */
    @SuppressWarnings("Duplicates")
    public void directAccess() {
        long starttime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            // 写模式
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            // 翻转为读模式
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("testDirectWrite: " + (endtime - starttime));
    }

    /**
     * 分配堆内存500字节，反复读写操作，测速
     */
    @SuppressWarnings("Duplicates")
    public void bufferAccess() {
        long starttime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            // 写模式
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            // 翻转为读模式
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("testBufferWrite: " + (endtime - starttime));
    }

    public void directAllocate() {
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("directAllocate: " + (endtime - starttime));
    }

    public void bufferAllocate() {
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocate(1000);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("bufferAllocate: " + (endtime - starttime));
    }

    public static void main(String[] args) {
        AccessDirectBuffer alloc = new AccessDirectBuffer();
//        alloc.bufferAccess();
//        alloc.directAccess();
//
//        alloc.bufferAccess();
//        alloc.directAccess();

        alloc.bufferAllocate();
        alloc.directAllocate();

        alloc.bufferAllocate();
        alloc.directAllocate();
    }


}
