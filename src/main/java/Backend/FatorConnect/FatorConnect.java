package Backend.FatorConnect;

import Frontend.ProgressBarScreen;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class    FatorConnect {
    private VerifyCnpjRegisterFatorconnet verifyCnpjRegisterFatorconnet;
    private TableEnviromentConfig tableEnviromentConfig;
    private int totalRows;




    public FatorConnect(VerifyCnpjRegisterFatorconnet verifyCnpjRegisterFatorconnet){
        this.verifyCnpjRegisterFatorconnet = new VerifyCnpjRegisterFatorconnet();
    }
public void verifyFatorConnect(String pathname, ProgressBarScreen progressBarScreen){
    try {
        FileInputStream fileInputStream = new FileInputStream(new File(pathname));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        tableEnviromentConfig = new TableEnviromentConfig(sheet);
        int validRowsCount = tableEnviromentConfig.getRealNumberOfRows();
        int invalidRowsCount = tableEnviromentConfig.getEmptyRowsBeforeStart();
        this.totalRows = validRowsCount + invalidRowsCount;
        progressBarScreen.setMax(totalRows);

        JOptionPane.showMessageDialog(null, "Foram encontrados " + validRowsCount + " CNPJs.", "Encerrado", JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                continue; // Pula linhas nulas
            }
            try {
                Cell cnpjCell = row.getCell(3);
                String cnpj = cnpjCell.getStringCellValue();

                if (cnpjCell != null && cnpjCell.getCellType() != CellType.BLANK &&
                        !cnpj.isEmpty() && !cnpj.equals("CNPJ")) {

                    Boolean verification = verifyCnpjRegisterFatorconnet.verifyCnpj(cnpj);
                    if (verification){
                        Cell resultCell = row.createCell(10);
                        resultCell.setCellValue("Já cadastrado");
                        System.out.println("Cadastrado");

                    }else{
                        Cell resultCell = row.createCell(10);
                        resultCell.setCellValue("Não cadastrado");
                        System.out.println("Não cadastrado");
                    }
                }else{
                    continue;
                }

            }catch (Exception e){
                System.out.println(e);
            }

            //Painel de progressão
            progressBarScreen.updateProgress(i);

        }

        FileOutputStream outputStream = new FileOutputStream(new File(pathname));
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
public int getTotalRow(){
        return totalRows;
}

}
