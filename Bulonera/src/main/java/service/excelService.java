/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Bulonera.logica.producto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author tobi2
 */
public class excelService {

    public List<producto> leerProductosDesdeExcel(InputStream inputStream) throws Exception {
        List<producto> productos = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            try {
                Row headerRow = sheet.getRow(4); // encabezados en fila 5
                if (headerRow == null) {
                    continue;
                }

                // Leer encabezados
                Map<String, Integer> indices = new HashMap<>();
                for (Cell cell : headerRow) {
                    String encabezado = obtenerValorCeldaComoTexto(cell).toLowerCase().trim();
                    indices.put(encabezado, cell.getColumnIndex());
                }

                // Verifica columnas requeridas
                if (!indices.containsKey("codigo") || !indices.containsKey("nombre")
                        || !indices.containsKey("categoria") || !indices.containsKey("precio compra")
                        || !indices.containsKey("precio venta")) {
                    System.out.println("⚠️  Hoja ignorada por columnas faltantes: " + sheet.getSheetName());
                    continue;
                }

                // Leer filas de datos
                for (int j = 5; j <= sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }

                    String codProd = obtenerValorCeldaComoTexto(row.getCell(indices.get("codigo")));
                    String nombProd = obtenerValorCeldaComoTexto(row.getCell(indices.get("nombre")));
                    String categoriaProd = obtenerValorCeldaComoTexto(row.getCell(indices.get("categoria")));
                    double precioCompra = obtenerValorCeldaComoNumero(row.getCell(indices.get("precio compra")), evaluator);
                    double precioVenta = obtenerValorCeldaComoNumero(row.getCell(indices.get("precio venta")), evaluator);
                    
                    if (codProd == null || codProd.trim().isEmpty()) {
                        continue;
                    }
                    
                    if (productos.stream().anyMatch(p -> p.getCod_prod().equals(codProd))) {
                        continue; // código ya agregado
                    }
                    
                    producto prod = new producto();
                    prod.setCod_prod(codProd);
                    prod.setNomb_prod(nombProd);
                    prod.setCategoria_prod(categoriaProd);
                    prod.setPrecio_compra(precioCompra);
                    prod.setPrecio_venta(precioVenta);

                    productos.add(prod);
                }

            } catch (Exception e) {
                System.out.println("⚠️  Error en hoja: " + sheet.getSheetName() + " → " + e.getMessage());
                // Aquí podrías loguear más detalles si es necesario
            }
        }

        workbook.close();
        return productos;
    }

    private String obtenerValorCeldaComoTexto(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private double obtenerValorCeldaComoNumero(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }
    
    private double obtenerValorCeldaComoNumero(Cell cell, FormulaEvaluator evaluator) {
    if (cell == null) {
        return 0.0;
    }

    CellValue cellValue = evaluator.evaluate(cell);

    if (cellValue == null) {
        return 0.0;
    }

    switch (cellValue.getCellType()) {
        case NUMERIC:
            return cellValue.getNumberValue();
        case STRING:
            try {
                return Double.parseDouble(cellValue.getStringValue().trim());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        default:
            return 0.0;
    }
}
}
