package com.test;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.test.dto.CustomerDTO;
import com.test.dto.LocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers/import")
    @ResponseBody
    public ResponseEntity<Void> importCustomers(@RequestParam(value = "uploadFile") MultipartFile file) throws IOException {
        customerService.uploadCustomers(file);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers/top")
    public  Map<Float, CustomerDTO> getTopCustomers(@RequestParam(required = false) Float latitude,
                                                    @RequestParam(required = false) Float longitude) {

        Map<Float, CustomerDTO> top = customerService.getTopCustomers(new LocationDTO(latitude, longitude));
        return top.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @DeleteMapping("/customers/clear")
    @ResponseBody
    public ResponseEntity<Void> removeCustomers() {
        customerService.clearCustomers();
        return ResponseEntity.noContent().build();
    }
}
