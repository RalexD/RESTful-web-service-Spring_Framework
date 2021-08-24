package com.example.demo.controllers;

import com.example.demo.model.WorldMap;
import com.example.demo.model.WorldMapService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller

public class WorldMapController {

    private WorldMapService worldMapService;



    public WorldMapController(WorldMapService worldMapService) {
        this.worldMapService = worldMapService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }


    @PostMapping("/")
    public String addWorldMap(@RequestParam String City, @RequestParam String Country, @RequestParam String PhoneCode, @RequestParam String Capital, @RequestParam String Population, Model model){
        int PhoneCode_buf= Integer.parseInt(PhoneCode);
        int Population_buf=Integer.parseInt(Population);
        WorldMap worldMap=new WorldMap(City, Country, PhoneCode_buf, Capital, Population_buf);
        worldMapService.getWorldMaps().add(worldMap);
        return "main";
    }


    @GetMapping("/newadd")
    public String addWorldMapGet(@RequestParam("City") String City,
                                 @RequestParam("Country") String Country,
                                 @RequestParam("PhoneCode") String PhoneCode,
                                 @RequestParam("Capital") String Capital,
                                 @RequestParam("Population") String Population,
                                 Model model){
        int PhoneCode_buf= Integer.parseInt(PhoneCode);
        int Population_buf=Integer.parseInt(Population);
        WorldMap worldMap=new WorldMap(City, Country, PhoneCode_buf, Capital, Population_buf);
        worldMapService.getWorldMaps().add(worldMap);
        return "main";
    }


    @GetMapping("/getAll")
    public String getAll( Model model){
        model.addAttribute("worldMaps", worldMapService);
        model.addAttribute("status", true);
        return "getAll";
    }

    @PostMapping("/remove")
    public String removeWorldMap(@RequestParam String ID, Model model){
        int ID_buf= Integer.parseInt(ID);

        worldMapService.removeByID(ID_buf);

        return "redirect:/";
    }


    @GetMapping("/newremove")
    public String removeWorldMapGet(@RequestParam("ID") String ID, Model model){
        int ID_buf= Integer.parseInt(ID);

        worldMapService.removeByID(ID_buf);

        return "main";
    }

    @GetMapping("/remove")
    public String remove( Model model){
        return "remove";
    }



    @GetMapping("/write")
    public void exportCSV() {

        try {
            File file = new File(worldMapService.getDir());
            file.createNewFile();
            System.out.println(file.getCanonicalPath());
            Writer writer = Files.newBufferedWriter(Paths.get(worldMapService.getDir()));
            StatefulBeanToCsv<WorldMap> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .withOrderedResults(true)
                    .build();
            beanToCsv.write(worldMapService.getWorldMaps());
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @GetMapping("/read")
    public void uploadCSVFile() {
        worldMapService.uploadCSVFile();
    }

    @GetMapping("/info")
    public String info( Model model){
        model.addAttribute("many", worldMapService.getWorldMaps().size());
        model.addAttribute("status", true);
        return "info";
    }



}
