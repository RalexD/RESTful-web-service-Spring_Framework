package com.example.demo.model;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorldMapService {

    private static List<WorldMap> worldMaps = new ArrayList<>();
    private String Dir="E:/WorldMaps.csv";

    public List<WorldMap> getWorldMaps() {


        return worldMaps;
    }

    public void add (WorldMap worldMap){

        this.getWorldMaps().add(worldMap);
    }

    public String getDir() {


        return Dir;
    }

    public void removeByID(int ID){

        for(int i=0; i<this.getWorldMaps().size(); i++){
            if(this.getWorldMaps().get(i).getID()==ID){
                this.getWorldMaps().remove(i);
            }
        }
    }

   @SuppressWarnings({"rawtypes","unchecked"})
    public void uploadCSVFile() {
        this.getWorldMaps().clear();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(Dir));
            CsvToBean<WorldMap> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(WorldMap.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            for (WorldMap worldMap : csvToBean) {
                this.add(worldMap);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
