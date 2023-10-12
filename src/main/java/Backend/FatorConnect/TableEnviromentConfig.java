package Backend.FatorConnect;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class TableEnviromentConfig {

    private int rowCount;
    private int filledCellCount;
    private Sheet sheet;

    public TableEnviromentConfig(Sheet sheet){
        this.sheet = sheet;
        this.rowCount = sheet.getPhysicalNumberOfRows();


    }

    public int getRealNumberOfRows(){
        filledCellCount = 0;
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                continue; // Pula linhas nulas
            }

            Cell cnpjCell = row.getCell(3); // A terceira coluna é a coluna 3 (índice base 0)
            String cnpj = cnpjCell.getStringCellValue();

            if (cnpjCell != null && cnpjCell.getCellType() != CellType.BLANK &&
                    !cnpj.isEmpty() && !cnpj.equals("CNPJ")) {
                filledCellCount++;
            }
        }
        return filledCellCount;
    }

    public int getEmptyRowsBeforeStart(){
        int emptyRows = 0;

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            Cell cnpjCell = row.getCell(3); // A terceira coluna é a coluna 3 (índice base 0)
            String cnpj = cnpjCell.getStringCellValue();

            if (cnpjCell == null || cnpjCell.getCellType() == CellType.BLANK ||
                    cnpj.isEmpty() || cnpj.equals("CNPJ")) {
                emptyRows++;
            }else{
                return emptyRows;
            }
        }
        return emptyRows;
    }

}



