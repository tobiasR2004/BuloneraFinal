/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tobi2
 */
@WebServlet(name = "svPdfdetalle", urlPatterns = {"/svPdfdetalle"})
public class svPdfdetalle extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    

cliente cli = (cliente) request.getSession().getAttribute("clientCabec");    
List<detalle_remito> listDet = (List<detalle_remito>) request.getSession().getAttribute("DetallesList");

if (listDet == null) {
    request.setAttribute("error", "No hay detalles para mostrar");
    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    return;
}

// Ruta de la imagen
String imagePath = getServletContext().getRealPath("/img/LogoBulonera.jpg");

// Crear el PDF en un buffer de memoria (sin guardarlo en el servidor)
ByteArrayOutputStream baos = new ByteArrayOutputStream();
PdfWriter writer = new PdfWriter(baos);
PdfDocument pdf = new PdfDocument(writer);
Document document = new Document(pdf, PageSize.A4.rotate());
document.setMargins(10, 10, 10, 10);


// Verificar si el archivo existe antes de usarlo
File imgFile = new File(imagePath);
if (!imgFile.exists()) {
    System.out.println("Error: El archivo no existe en " + imagePath);
    request.setAttribute("error", "La imagen no fue encontrada.");
    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    return;
}

try {
    ImageData imageData = ImageDataFactory.create(imagePath);
    Image logo = new Image(imageData);
    logo.scaleToFit(300, 200);  // Ajusta tamaño si es necesario
    logo.setFixedPosition(pdf.getDefaultPageSize().getWidth() - 350, pdf.getDefaultPageSize().getHeight() - 95);
    document.add(logo);
} catch (IOException e) {
    request.setAttribute("error", "Error al cargar la imagen: " + e.getMessage());
    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    return;
}


int num_cli = cli.getNroClient();
String nomb_cli = cli.getRazon_social();
String cuit = cli.getCuit_cliente();
            
document.add(new Paragraph("Número de Cliente: " + num_cli));
document.add(new Paragraph("Nombre del Cliente: " + nomb_cli));
document.add(new Paragraph("CUIT: " + cuit));
document.add(new Paragraph("\n"));

// Crear la tabla con un número fijo de columnas
Table table = new Table(UnitValue.createPercentArray(new float[]{3,7,1,3,3}));
table.setWidth(UnitValue.createPercentValue(100));

// Agregar encabezados de la tabla
table.addHeaderCell(crearCeldaHeader("Fecha"));
table.addHeaderCell(crearCeldaHeader("Producto"));
table.addHeaderCell(crearCeldaHeader("Cantidad"));
table.addHeaderCell(crearCeldaHeader("Precio unit"));
table.addHeaderCell(crearCeldaHeader("Importe"));

// Llenar la tabla con datos desde listDet (suponiendo que detalle_remito tiene estos campos)
for (detalle_remito remito : listDet) {
    // Formato de Fecha
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fechaFormateada = formato.format(remito.getFechaDet());

    // Precio Unitario y Importe truncados a 2 decimales (sin redondear)
    BigDecimal precioTruncado = new BigDecimal(remito.getPrecio_unit()).setScale(2, RoundingMode.DOWN);
    BigDecimal importeTruncado = new BigDecimal(remito.getImporte()).setScale(2, RoundingMode.DOWN);

    table.addCell(crearCelda(fechaFormateada, TextAlignment.CENTER));
    table.addCell(crearCelda(remito.getNomb_prod(), TextAlignment.LEFT));
    table.addCell(crearCelda(String.valueOf(remito.getCant_prod()), TextAlignment.CENTER));
    table.addCell(crearCelda(precioTruncado.toString(), TextAlignment.RIGHT));
    table.addCell(crearCelda(importeTruncado.toString(), TextAlignment.RIGHT));
}
// Agregar la tabla al documento
document.add(table);

double totalImporte = 0;
for (detalle_remito remito : listDet) {
    totalImporte += remito.getImporte();
}

// Agregar espacio antes del total
document.add(new Paragraph("\n"));

// Mostrar el total en la parte inferior de la página
DecimalFormat dfTotal = new DecimalFormat("#.00");
document.add(new Paragraph("TOTAL: $" + dfTotal.format(totalImporte))
        .setBold()
        .setFontSize(12)
        .setTextAlignment(TextAlignment.RIGHT));


document.close();

// Establecer encabezado de tipo de contenido para PDF
response.setContentType("application/pdf");
response.setHeader("Content-Disposition", "inline; filename=Detalle.pdf");

// Escribir el archivo PDF al flujo de salida del navegador
try {
    baos.writeTo(response.getOutputStream());
    response.getOutputStream().flush();
} catch (IOException e) {
    request.setAttribute("error", "Error al mostrar el archivo PDF: " + e.getMessage());
    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
}
}  

private Cell crearCelda(String texto, TextAlignment alineacion) {
    return new Cell()
        .add(new Paragraph(texto).setFontSize(9))
        .setTextAlignment(alineacion)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setPadding(2);
}

private Cell crearCeldaHeader(String texto) {
    return new Cell()
        .add(new Paragraph(texto).setFontSize(10).setBold())
        .setTextAlignment(TextAlignment.CENTER)
        .setBackgroundColor(new DeviceGray(0.85f))
        .setPadding(3);
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
