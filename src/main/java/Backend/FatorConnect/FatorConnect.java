package Backend.FatorConnect;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FatorConnect {
    private VerifyCnpjRegisterFatorconnet verifyCnpjRegisterFatorconnet;

    public FatorConnect(VerifyCnpjRegisterFatorconnet verifyCnpjRegisterFatorconnet){
        this.verifyCnpjRegisterFatorconnet = new VerifyCnpjRegisterFatorconnet();
    }
public void verifyFatorConnect(String pathname){
    try {
        FileInputStream fileInputStream = new FileInputStream(new File(pathname));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);


        int rowCount = sheet.getPhysicalNumberOfRows() - 1;

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                continue; // Pula linhas nulas
            }

            Cell cnpjCell = row.getCell(3);

            try {
                String cnpj = cnpjCell.getStringCellValue();
                if (cnpj.equals("CNPJ") || cnpj == null) {
                    continue;
                }else{
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

                }

            }catch (Exception e){
                System.out.println(e);
            }

        }
        FileOutputStream outputStream = new FileOutputStream(new File(pathname));
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
