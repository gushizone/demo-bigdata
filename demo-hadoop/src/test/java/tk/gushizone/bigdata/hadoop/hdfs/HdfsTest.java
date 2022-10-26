package tk.gushizone.bigdata.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * HDFS常用API
 *
 * @author zhangwei
 * @date 2022/10/25 15:56
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HdfsTest {

    private static final String HDFS_PATH = "hdfs://127.0.0.1:8020";
    private static final String HDFS_USER = "root";
    private static FileSystem fileSystem;


    /**
     * 获取fileSystem
     */
    @Before
    public void prepare() {
        try {
            Configuration configuration = new Configuration();
            // 这里我启动的是单节点的Hadoop,副本系数可以设置为1,不设置的话默认值为3
            configuration.set("dfs.replication", "1");
            fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, HDFS_USER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建目录,支持递归创建
     */
    @Test
    public void t01_mkDir() throws Exception {
        fileSystem.mkdirs(new Path("/hdfs-api/test0/"));
    }


    /**
     * 创建具有指定权限的目录
     */
    @Test
    public void t02_mkDirWithPermission() throws Exception {
        fileSystem.mkdirs(new Path("/hdfs-api/test1/"),
                new FsPermission(FsAction.READ_WRITE, FsAction.READ, FsAction.READ));
    }

    /**
     * 创建文件,并写入内容
     */
    @Test
    public void t03_create() throws Exception {
        // 如果文件存在，默认会覆盖, 可以通过第二个参数进行控制。第三个参数可以控制使用缓冲区的大小
        FSDataOutputStream out = fileSystem.create(new Path("/hdfs-api/test/a.txt"),
                true, 4096);
        out.write("hello hadoop!".getBytes());
        out.write("hello spark!".getBytes());
        out.write("hello flink!".getBytes());
        // 强制将缓冲区中内容刷出
        out.flush();
        out.close();
    }


    /**
     * 判断文件是否存在
     */
    @Test
    public void t04_exist() throws Exception {
        boolean exists = fileSystem.exists(new Path("/hdfs-api/test/a.txt"));
        System.out.println(exists);
    }


    /**
     * 查看文件内容
     */
    @Test
    public void t05_readToString() throws Exception {
        FSDataInputStream inputStream = fileSystem.open(new Path("/hdfs-api/test/a.txt"));
        String context = inputStreamToString(inputStream, "utf-8");
        System.out.println(context);
    }


    /**
     * 文件重命名
     */
    @Test
    public void t06_rename() throws Exception {
        Path oldPath = new Path("/hdfs-api/test/a.txt");
        Path newPath = new Path("/hdfs-api/test/b.txt");
        boolean result = fileSystem.rename(oldPath, newPath);
        System.out.println(result);
    }


    /**
     * 删除文件
     */
    @Test
    public void t07_delete() throws Exception {
        /*
         *  第二个参数代表是否递归删除
         *    +  如果path是一个目录且递归删除为true, 则删除该目录及其中所有文件;
         *    +  如果path是一个目录但递归删除为false,则会则抛出异常。
         */
        boolean result = fileSystem.delete(new Path("/hdfs-api/test/b.txt"), true);
        System.out.println(result);
    }


    /**
     * 上传文件到HDFS
     */
    @Test
    public void t08_copyFromLocalFile() throws Exception {
        // 如果指定的是目录，则会把目录及其中的文件都复制到指定目录下
        Path src = new Path(Objects.requireNonNull(this.getClass().getResource("/hdfs/dir")).getPath());
        Path dst = new Path("/hdfs-api/test/");
        fileSystem.copyFromLocalFile(src, dst);
    }

    /**
     * 上传文件到HDFS
     */
    @Test
    public void t09_copyFromLocalBigFile() throws Exception {

        File file = new File(Objects.requireNonNull(this.getClass().getResource("/hdfs/dir/a.txt")).getPath());
        final float fileSize = file.length();
        InputStream in = new BufferedInputStream(new FileInputStream(file));

        FSDataOutputStream out = fileSystem.create(new Path("/hdfs-api/test/a.txt"),
                new Progressable() {
                    long fileCount = 0;

                    public void progress() {
                        fileCount++;
                        // progress方法每上传大约64KB的数据后就会被调用一次
                        System.out.println("文件上传总进度：" + (fileCount * 64 * 1024 / fileSize) * 100 + " %");
                    }
                });

        IOUtils.copyBytes(in, out, 4096);

    }

    /**
     * 从HDFS上下载文件
     */
    @Test
    public void t10_copyToLocalFile() throws Exception {
        Path src = new Path("/hdfs-api/test/a.txt");
        Path dst = new Path(Objects.requireNonNull(this.getClass().getResource("/hdfs/")).getPath());
        /*
         * 第一个参数控制下载完成后是否删除源文件,默认是true,即删除;
         * 最后一个参数表示是否将RawLocalFileSystem用作本地文件系统;
         * RawLocalFileSystem默认为false,通常情况下可以不设置,
         * 但如果你在执行时候抛出NullPointerException异常,则代表你的文件系统与程序可能存在不兼容的情况(window下常见),
         * 此时可以将RawLocalFileSystem设置为true
         */
        fileSystem.copyToLocalFile(false, src, dst, true);
    }


    /**
     * 查看指定目录下所有文件的信息
     */
    @Test
    public void t11_listFiles() throws Exception {
        FileStatus[] statuses = fileSystem.listStatus(new Path("/hdfs-api"));
        for (FileStatus fileStatus : statuses) {
            //fileStatus的toString方法被重写过，直接打印可以看到所有信息
            System.out.println(fileStatus.toString());
        }
    }


    /**
     * 递归查看指定目录下所有文件的信息
     */
    @Test
    public void t12_listFilesRecursive() throws Exception {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/hdfs-api"), true);
        while (files.hasNext()) {
            System.out.println(files.next());
        }
    }


    /**
     * 查看文件块信息
     */
    @Test
    public void t13_getFileBlockLocations() throws Exception {

        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/hdfs-api/test/a.txt"));
        BlockLocation[] blocks = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        for (BlockLocation block : blocks) {
            System.out.println(block);
        }
    }

    /**
     * 递归删除目录
     */
    @Test
    public void t14_delete() throws Exception {
        fileSystem.delete(new Path("/hdfs-api/"));
    }


    /**
     * 测试结束后,释放fileSystem
     */
    @After
    public void t14_destroy() {
        fileSystem = null;
    }


    /**
     * 把输入流转换为指定编码的字符
     *
     * @param inputStream 输入流
     * @param encode      指定编码类型
     */
    private static String inputStreamToString(InputStream inputStream, String encode) {
        try {
            if (encode == null || ("".equals(encode))) {
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}