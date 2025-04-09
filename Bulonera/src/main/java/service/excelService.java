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
import java.util.List;


/**
 *
 * @author tobi2
 */
public class ExcelService {
    public List<producto> leerProductosDesdeExcel(InputStream inputStream) throws Exception {
        List<producto> productos = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Primera hoja del archivo Excel

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Salta la fila de encabezados

            // Verifica si la fila está completamente vacía
            boolean filaVacia = true;
            for (int i = 0; i < 5; i++) { // Suponiendo 5 columnas mínimas
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (cell != null) {
                    filaVacia = false;
                    break;
                }
            }
            if (filaVacia) continue;

            // Lee las celdas del archivo Excel con verificación de null
            String codProd = obtenerValorCeldaComoTexto(row.getCell(0));
            String nombProd = obtenerValorCeldaComoTexto(row.getCell(1));
            String categoriaProd = obtenerValorCeldaComoTexto(row.getCell(2));
            double precioCompra = obtenerValorCeldaComoNumero(row.getCell(3));
            double precioVenta = obtenerValorCeldaComoNumero(row.getCell(4));

            // Crea un objeto producto
            producto prod = new producto();
            prod.setCod_prod(codProd);
            prod.setNomb_prod(nombProd);
            prod.setCategoria_prod(categoriaProd);
            prod.setPrecio_compra(precioCompraRedondeado.doubleValue());
            prod.setPrecio_venta(precioVentaRedondeado.doubleValue());
            
            

            productos.add(prod);
        }

        workbook.close(); // Cierra el workbook para liberar memoria
        return productos;
    }

    private String obtenerValorCeldaComoTexto(Cell cell) {
        if (cell == null) return ""; // Evita NullPointerException
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue()); // Convierte números a String
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private double obtenerValorCeldaComoNumero(Cell cell) {
        if (cell == null) return 0.0; // Evita NullPointerException
        
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim()); // Intenta convertir un texto en número
            } catch (NumberFormatException e) {
                return 0.0; // Si no es convertible, devuelve 0
            }
        }
        return 0.0;
    }
}
