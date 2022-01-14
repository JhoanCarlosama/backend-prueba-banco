package com.example.backendbanco.utils;

import com.example.backendbanco.entity.Movimiento;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Movimiento> list;

    public ExcelExporter(List<Movimiento> list) {
        this.list = list;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Movimientos");

        Row rowHeader = sheet.createRow(0);
        Row rowTitle = sheet.createRow(1);
        Row row = sheet.createRow(3);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(rowHeader, 2, "MOVIMIENTOS", style);
        createCell(rowTitle, 1, "Nombre: " + list.get(0).getCuenta().getCliente().getNombre(), style);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "cuenta", style);
        createCell(row, 2, "fecha", style);
        createCell(row, 3, "tipo", style);
        createCell(row, 4, "valor", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 4;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Movimiento mvto : list) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, mvto.getId().toString(), style);
            createCell(row, columnCount++, mvto.getCuenta().getNumero().toString(), style);
            createCell(row, columnCount++, mvto.getFecha().toString(), style);
            createCell(row, columnCount++, mvto.getTipo(), style);
            createCell(row, columnCount++, mvto.getValor().toString(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
