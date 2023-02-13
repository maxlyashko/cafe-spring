package ua.lyashko.cafe.controllers;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lyashko.cafe.model.Bill;
import ua.lyashko.cafe.model.Product;
import ua.lyashko.cafe.pdf.BillPDFExporter;
import ua.lyashko.cafe.service.BillService;
import ua.lyashko.cafe.service.ProductService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Log4j2
public class BillController {
    private final ProductService productService;
    private final BillService billService;
    private static List<Product> products = new ArrayList<> ( );

    public BillController ( ProductService productService , BillService billService ) {
        this.productService = productService;
        this.billService = billService;
    }

    @GetMapping("/bills/new")
    public String createBillForm ( Model model ) {
        model.addAttribute ( "bill" , new Bill ( ) );
        model.addAttribute ( "products" , productService.getAllProducts ( ) );
        log.info ( "bill model logged" );
        return "create_bill";
    }

    @PostMapping("/bills")
    public String addBill ( @ModelAttribute Bill bill ,
                            Model model ) {
        if (bill == null) {
            return "create_bill";
        }
        bill.setTotalPrice ( billService.getTotalPrice ( bill ) );
        products.addAll ( bill.getProducts ( ) );
        billService.saveBill ( bill );
        model.addAttribute ( "products" , bill.getProducts ( ) );
        log.info ( "bill model logged and saved: " + bill );
        return "create_bill_products";
    }

    @GetMapping("/bills")
    public String start ( Model model ) {
        products = new ArrayList<> ( );
        model.addAttribute ( "bills" , billService.getALlBills ( ) );
        log.info ( "bill list logged: " + billService.getALlBills () );
        return "bills";
    }

    @GetMapping("/bills/view/{id}")
    public String viewBill ( @PathVariable Integer id ,
                             Model model ) {
        Bill bill = billService.getBillById ( id );
        model.addAttribute ( "bill" , bill );
        model.addAttribute ( "products" , bill.getProducts ( ) );
        log.info ( "bill viewed: " + bill);
        return "view_bill_by_id";
    }

    @GetMapping("bills/export/{id}")
    public void exportBill ( @PathVariable Integer id,
                             HttpServletResponse response ) throws DocumentException, IOException {
        Bill bill = billService.getBillById ( id );
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date ());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=bill_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        BillPDFExporter exporter = new BillPDFExporter ( bill );
        exporter.export ( response );
        log.info ( "bill exported: " + bill);
    }


    @GetMapping("/bills/edit/{id}")
    public String editBillForm ( @PathVariable Integer id ,
                                 Model model ) {
        model.addAttribute ( "bill" , billService.getBillById ( id ) );
        model.addAttribute ( "products" , productService.getAllProducts ( ) );
        log.info ( "bill edit logged" );
        return "edit_bill";
    }

    @PostMapping("/bills/{id}")
    public String addProduct ( @ModelAttribute("bill") Bill bill ,
                               @PathVariable Integer id ,
                               Model model ) {
        products.addAll ( bill.getProducts ( ) );
        Bill existingBill = billService.getBillById ( id );
        existingBill.setId ( id );
        existingBill.setProducts ( products );
        existingBill.setTotalPrice ( billService.getTotalPrice ( existingBill ) );
        billService.updateBill ( existingBill );
        model.addAttribute ( "bill" , billService.getBillById ( id ) );
        model.addAttribute ( "products" , existingBill.getProducts ( ) );
        log.info ( "bill by id logged: " + existingBill );
        return "create_bill_products";
    }


    @GetMapping("/bills/{id}")
    public String deleteBill ( @PathVariable Integer id ) {
        billService.deleteBillById ( id );
        log.info ( "bill deleted by id: " + id );
        return "redirect:/bills";
    }

    @GetMapping("/bills/{id}/remove/{index}")
    public String removeProductFromBill ( @PathVariable Integer index ,
                                          @PathVariable Integer id ,
                                          @ModelAttribute Bill bill ,
                                          Model model ) {
        bill = billService.getBillById ( id );
        products.remove ( index.intValue ( ) );
        bill.setProducts ( products );
        bill.setTotalPrice ( billService.getTotalPrice ( bill ) );
        billService.updateBill ( bill );
        model.addAttribute ( "bill" , bill );
        model.addAttribute ( "products" , bill.getProducts ( ) );
        log.info ( "product removed from bill: " + id );
        return "create_bill_products";
    }

}
