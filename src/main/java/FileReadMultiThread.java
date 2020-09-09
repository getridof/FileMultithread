import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReadMultiThread {
    public static void main(String[] args) {

        //get the number of cores to create threads
        int cores = Runtime.getRuntime().availableProcessors();

        //folder name to fetch all files in that folder
        final File folder = new File("/home/you/Desktop");
        List<File> allFliles = Arrays.asList(folder.listFiles());

        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        //giving all files to the threadpool to execute
        for (File allFlile : allFliles) {
            executorService.execute(new FileOperation(allFlile.toPath().toString()));
        }
    }

}
