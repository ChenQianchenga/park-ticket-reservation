import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVExporterTest {


    @Test
    public void testCSVExport() throws IOException {
        // 需要导出的数据
        List<String[]> data = Arrays.asList(
                new String[] {"ID", "Name", "Age"},
                new String[] {"1", "Alice", "23"},
                new String[] {"2", "Bob", "29"},
                new String[] {"3", "Charlie", "31"}
        );

        // 设置输出文件路径
        String filePath = "output.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // 写入数据
            writer.writeAll(data);
            System.out.println("CSV文件导出成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
