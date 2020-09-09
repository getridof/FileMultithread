import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperation implements Runnable {

    private String fileLocation;

    public FileOperation(String fileLocation) {

        this.fileLocation = fileLocation;
    }
    public void run() {
        try {
            //reading all lines and filtering only lowercase characters
            List<String> allLines = Files.lines(new File(fileLocation).toPath())
                    .map(s -> s.chars().filter(Character::isLowerCase)
                            .collect(StringBuilder::new, // supplier
                                    StringBuilder::appendCodePoint, // accumulator
                                    StringBuilder::append) // combiner
                            .toString()).collect(Collectors.toList());

            //emptying the contents of the existing file
            FileOutputStream writer = new FileOutputStream(fileLocation);
            writer.write(("").getBytes());
            writer.close();

            //writing all lower case characters back to the file
            Path path = Paths.get(fileLocation);
            try (BufferedWriter writer1 = Files.newBufferedWriter(path)) {
                writer1.write(allLines.stream()
                        .reduce((sum,currLine) ->  sum + "\n"  + currLine)
                        .get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
