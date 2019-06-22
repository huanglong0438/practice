package mergeCompare;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Compare
 *
 * @title Compare
 * @Description
 * @Author donglongcheng01
 * @Date 2019-01-28
 **/
public class Compare {


    public static void main(String[] args) throws Exception {
        int size = 500;
        createFiles(500);

        File file = new File("mergeFile");
        File[] files = file.listFiles();
        List<File> listFiles = Lists.newArrayList(files);


        File resultFile = new File("resultFile");
        long start = System.currentTimeMillis();
//        zip(listFiles, resultFile);
        mergeIntoOne(listFiles, resultFile);

        long cost = System.currentTimeMillis() - start;

        System.out.println("merge " + size + " files, using: " + cost + "ms");
        for (File subFile : files) {
            subFile.delete();
        }
        resultFile.delete();
    }

    public static void mergeIntoOne(List<File> files, File resultFile) {
        try {
            FileChannel resultChannel = new FileOutputStream(resultFile, true).getChannel();

            for (File file : files) {
                LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                lnr.skip(Long.MAX_VALUE);
                int lineNo = lnr.getLineNumber() + 1;
//                System.out.println(file.getName() + " contains " + lineNo + " lines");
                lnr.close();
                FileChannel blk = new FileInputStream(file).getChannel();
                resultChannel.transferFrom(blk, resultChannel.size(), blk.size());
                blk.close();
            }
            resultChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean zip(List<File> files, File zipFile) {
        boolean status = false;
        if (CollectionUtils.isEmpty(files)) {
//            LOGGER.error("zip files is empty");
            return false;
        }
        ZipArchiveOutputStream zipArchiveOutputStream = null;
        try {
            zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);
            // 将每个文件用ZipArchiveEntry封装
            for (File file : files) {
                if (file.length() == NumberUtils.LONG_ZERO) {
//                    LOGGER.debug("file : " + file.getAbsolutePath() + " is empty.");
                    continue;
                }
                if (file == null) {
                    status = false;
                    break;
                }
                zip(file, zipArchiveOutputStream);
            }
            status = true;
        } catch (IOException ex) {
            status = false;
            ex.printStackTrace();
//            LOGGER.error("error, reason : " + ex.getMessage());
        } finally {
//            IOUtils.closeQuietly(zipArchiveOutputStream);
        }
        return status;
    }

    private static void zip(File file, ZipArchiveOutputStream zipArchiveOutputStream) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            // 创建一个压缩包。
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
            IOUtils.copy(inputStream, zipArchiveOutputStream);
            zipArchiveOutputStream.closeArchiveEntry();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static void createFiles(int size) {
        try {
            for (int fileIndex = 0;fileIndex < size; fileIndex++) {
                String fileName = "mergeFile/data" + fileIndex + ".csv";
                BufferedWriter bw =
                        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for (int i = 0; i < 10000; i++) {
                    String line = "col1-" + i + ",col2-" + i + ",col3-" + i + "\n";
                    bw.write(line);
                }
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
