package com.garudanest.controller;

import com.garudanest.model.Flat;
import com.garudanest.model.FlatForm;
import com.garudanest.model.TotalMembersHolder;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes("flatForm") // Add flatForm to session attributes
public class FlatController {

    @Autowired
    private FlatService flatService;

    @Autowired
    private WaterSharingService waterSharingService;

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/garudanest-tanker")
    public String showForm(Model model) {
        FlatForm flatForm = new FlatForm();
        flatForm.setFlats(flatService.getFlats()); // Ensure the list is initialized
        model.addAttribute("flatForm", flatForm);
        return "input";
    }

    @PostMapping("/garudanest-tanker/calculate")
    public String calculate(@ModelAttribute FlatForm flatForm, @RequestParam("tankerCost") double tankerCost, Model model) {
        List<Flat> flats = flatForm.getFlats();
        TotalMembersHolder totalMembersHolder = new TotalMembersHolder();
        List<String[]> result = waterSharingService.calculateShares(flats, tankerCost, totalMembersHolder);
        model.addAttribute("result", result);
        model.addAttribute("tankerCost", tankerCost); // Store tanker cost in model
        model.addAttribute("totalMembers", totalMembersHolder.getTotalMembers()); // Store total members in model
        model.addAttribute("flatForm", flatForm); // Add updated flatForm to model
        return "result";
    }

    @GetMapping("/garudanest-tanker/export")
    public ResponseEntity<byte[]> exportToExcel(@ModelAttribute FlatForm flatForm, @RequestParam("tankerCost") double tankerCost) throws IOException {
        List<Flat> flats = flatForm.getFlats(); // Use the updated flats
        TotalMembersHolder totalMembersHolder = new TotalMembersHolder();
        List<String[]> result = waterSharingService.calculateShares(flats, tankerCost, totalMembersHolder);
        ByteArrayInputStream in = excelExportService.exportToExcel(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=WaterShareResults.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }
}
