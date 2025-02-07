package de.hhbk.immoweg24.utils.dataimport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CsvProcessor {
    
    private final String DELIMITER = ";";
    private String filePath = "";
    private String csvTitle = "";
    private List<String> csvHeader;
    private List<String[]> csvData;
    
    // --
    
    
    /**
     * Constructor.
     * @param filePath Dateipfad der CSV-Datei. 
     */
    public CsvProcessor(String filePath) {
        this.filePath = filePath;
    }
    
    
    public CsvProcessor() {}
    
    // -- 

    /**
     * Liest eine CSV anhand des gesetzten filePaths ein. Entnimmt den Titel,
     * die Spaltenheader und die Daten der Datei und speichert diese in den 
     * Instanzvariablen zur weiteren Verarbeitung.
     * 
     * @return True wenn erfolgreich; False wenn unerfolgreich.
     * @throws IOException
     * @throws IllegalArgumentException 
     */
    public boolean readCsv() throws IOException, IllegalArgumentException {
        if (filePath == null || filePath.isEmpty()) return false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            // CSV Titel (erste Zeile)
            String title = br.readLine();
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("CSV file is empty or missing file type.");
            }
            this.csvTitle = title.trim(); // set csvTitle
            
            // Read column headers (second line)
            String headerLine = br.readLine();
            if (headerLine == null || headerLine.isBlank()) {
                throw new IllegalArgumentException("CSV file is missing column headers.");
            }
            List<String> columnHeaders = new ArrayList<>();
            for (String header : headerLine.split(DELIMITER)) {
                columnHeaders.add(header.trim());
            }
            this.csvHeader = columnHeaders; // set column header
            
            // Read data rows (starting from third line)
            List<String[]> dataRows = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                dataRows.add(line.split(DELIMITER));
            }
            this.csvData = dataRows; // set data rows
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    
    public boolean readCsv(String filePath) throws IOException, IllegalArgumentException {
        this.filePath = filePath;
        return readCsv();
    }
    
    // --
    
    
    public Object getImporter() {
        String title = csvTitle.toLowerCase();
        if (title.contains("mietobjekte")) {
            return new MietobjektCsvImporter(MIETOBJEKT_HEADER, csvHeader, csvData);
        } else if (title.contains("mieter")) {
            return new MieterCsvImporter(MIETER_HEADER, csvHeader, csvData);
        } else if (title.contains("zahlungen")) {
            return new ZahlungCsvImporter(ZAHLUNG_HEADER, csvHeader, csvData);
        } else {
            throw new IllegalArgumentException("Unbekannter CSV-Titel: " + csvTitle);
        }
    }


    
    // --
    
    public String getFilePath(String filePath) {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCsvTitle() {
        return csvTitle;
    }

    public void setCsvTitle(String csvTitle) {
        this.csvTitle = csvTitle;
    }

    public List<String> getCsvHeader() {
        return csvHeader;
    }

    public void setCsvHeader(List<String> csvHeader) {
        this.csvHeader = csvHeader;
    }

    public List<String[]> getCsvData() {
        return csvData;
    }

    public void setCsvData(List<String[]> csvData) {
        this.csvData = csvData;
    }
    
    
    // --- Erwartete Header 
    
    public final List<String> MIETOBJEKT_HEADER = List.of( 
        "Objektnummer",
        "Typ",
        "Straße",
        "Hausnummer",
        "Plz",
        "Stadt",
        "Land",
        "Kaltkosten",
        "Summe Nebenkosten",
        "Warmkosten",
        "Status"
    );
    
    public final List<String> MIETER_HEADER = List.of( 
        "Vorname",
        "Nachname",
        "Straße",
        "Hausnummer",
        "Plz",
        "Stadt",
        "Land",
        "Telefon",
        "E-Mail",
        "IBAN",
        "BIC"
    );
    
    public final List<String> ZAHLUNG_HEADER = List.of(  
        "Betrag",
        "IBAN",
        "BIC",
        "Kontoinhaber",
        "Objektnummer",
        "Status",
        "Verwendungszweck",
        "Datum"
    );
}
