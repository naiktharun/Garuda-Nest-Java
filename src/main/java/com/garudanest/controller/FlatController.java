package com.garudanest.controller;

import com.garudanest.model.Flat;
import com.garudanest.service.ExcelExportService;
import com.garudanest.service.FlatService;
import com.garudanest.service.WaterSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FlatController {

    @Autowired
    private FlatService flatService;

    @Autowired
    private WaterSharingService waterSharingService;

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/garudanest-tanker")
    public String showForm(Model model) {
        model.addAttribute("flats", flatService.getFlats());
        return "input";
    }

@PostMapping("/calculate")
public String calculate(Model model, @RequestParam("tankerCost") double tankerCost) {
    List<Flat> flats = flatService.getFlats();
    List<String[]> result = waterSharingService.calculateShares(flats, tankerCost);
    model.addAttribute("result", result);
    model.addAttribute("tankerCost", tankerCost); // Store tanker cost in model
    return "result";
}


@GetMapping("/export")
public ResponseEntity<byte[]> exportToExcel(@RequestParam("tankerCost") double tankerCost) throws IOException {
    List<Flat> flats = flatService.getFlats();
    List<String[]> result = waterSharingService.calculateShares(flats, tankerCost);
    ByteArrayInputStream in = excelExportService.exportToExcel(result);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=WaterShareResults.xlsx");

    return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(in.readAllBytes());
}

}
