package edu.stevens.friccobo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.lang.Iterable;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Certificates {

  public static void main(String[] args) throws IOException {
    /*
    List<String> results = Files.readAllLines(
        Paths.get("results-2.csv")
        );

    boolean first = true;
    for(String result : results){
      if(first){
        first = false;
        continue;
      }
      String[] parts = result.split(",");
      Result r = new Result(
          Integer.parseInt(parts[0]),
          parts[1],
          parts[2]
          );

      System.out.println(r.toString());
    }*/

    Reader in = new FileReader("results-2.csv");
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
    for (CSVRecord record : records) {
      String placeString = record.get("Place");
      String event = record.get("Event");
      String name = record.get("Name");

      Result r = new Result(Integer.parseInt(placeString), name, event);
      System.out.println(r);
    }
  }


  public static class Result {
    int place;
    String name;
    String event;

    public Result(int place, String name, String event){
      this.place = place;
      this.name = name;
      this.event = event;
    }

    private String getPlace(){
      switch(place){
        case 1:
          return "first";
        case 2:
          return "second";
        case 3:
          return "third";
        default:
          return place + "th";
      }
    }
    public String toString(){ 
      return "Congratulations to " + this.name + " for being " + this.getPlace() + " place in " +this.event;
    }
  }
}
