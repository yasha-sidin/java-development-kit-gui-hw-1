package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.lines;

public class FileModel implements iGetModel<String> {
    private final File modelFile;

    public FileModel(String fileName) throws IOException {
        this.modelFile = new File(fileName);
        if (!modelFile.exists()) Files.createFile(Path.of(fileName));
    }

    @Override
    public List<String> getModel() throws IOException {
        return lines(Paths.get(modelFile.getAbsolutePath()))
                .collect(Collectors.toList());
    }

    @Override
    public void setData(String data) {
        try(FileWriter fw = new FileWriter(modelFile, true)) {
            fw.append(data).append(String.valueOf('\n'));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
