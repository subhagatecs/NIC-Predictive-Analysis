package com.example.demo.controller;
import com.example.demo.model.Nic;
import com.example.demo.service.NicService;
import com.opencsv.CSVWriter;
import org.springframework.core.io.Resource;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class NicController {

    @Autowired
    private NicService nicService;

    @PostMapping("/nic")
    public ResponseEntity<String> saveNic(@RequestBody Nic nic){
        boolean result = nicService.saveNic(nic);
        if(result)
            return ResponseEntity.ok("Data Pushed To Redis");
        else
            return ResponseEntity.status(HttpStatus.valueOf(0)).build();
    }

    @GetMapping("/fetch-nic")
    public ResponseEntity<Resource> fetchAllUser(HttpServletResponse response) throws IOException {
        List<Nic> nics = nicService.fetchAllUser();

        // Define the path to the file
        String filename = "Nic.csv";

        Path fileLocation = Paths.get("user.dir", "../..", filename).normalize();
        // Create a CSV writer
        ICsvBeanWriter csvWriter = new CsvBeanWriter(new FileWriter(fileLocation.toString()), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Key", "Category", "Sub_category", "Expected_period_in_month", "Out_data_pattern", "Demand_date", "Demand_qty", "Analysis_year"};
        String[] nameMapping = {"key", "category", "sub_category", "expected_period_in_month", "out_data_pattern", "demand_date", "demand_qty", "analysis_year"};

        // Write the header and data to the CSV file
        csvWriter.writeHeader(csvHeader);
        for (Nic nic : nics) {
            csvWriter.write(nic, nameMapping);
        }
        csvWriter.close();

        // Delete all users from Redis
        nicService.deleteAllUser();

        // Load the file as a resource for download
        Resource fileResource = new UrlResource(fileLocation.toUri());

        // Set the content type and header for file download
        String contentType = "text/csv";
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(fileResource);
    }

}

