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
public class excelService {
      public List<producto> leerProductosDesdeExcel(InputStream inputStream) throws Exception {
        List<producto> productos = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Primera hoja del archivo Excel

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Salta la fila de encabezados
            
            // Lee las celdas del archivo Excel
            String codProd = row.getCell(0).getStringCellValue();
            String nombProd = row.getCell(1).getStringCellValue();
            String categoriaProd = row.getCell(2).getStringCellValue();
            double precioCompra = row.getCell(3).getNumericCellValue();
            double precioVenta = row.getCell(4).getNumericCellValue();
            
            BigDecimal precioCompraRedondeado = BigDecimal.valueOf(precioCompra).setScale(2, RoundingMode.HALF_UP);
            BigDecimal precioVentaRedondeado = BigDecimal.valueOf(precioVenta).setScale(2, RoundingMode.HALF_UP);

            // Crea un objeto producto
            
            producto prod = new producto();
            prod.setCod_prod(codProd);
            prod.setNomb_prod(nombProd);
            prod.setCategoria_prod(categoriaProd);
            prod.setPrecio_compra(precioCompraRedondeado.doubleValue());
            prod.setPrecio_venta(precioVentaRedondeado.doubleValue());
            
            

            productos.add(prod);
        }

        workbook.close();
        return productos;
    }
    
}
