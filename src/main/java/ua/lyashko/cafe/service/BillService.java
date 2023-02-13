package ua.lyashko.cafe.service;

import ua.lyashko.cafe.model.Bill;

import java.math.BigDecimal;
import java.util.List;

public interface BillService {
    List<Bill> getALlBills ();

    Bill saveBill ( Bill bill );

    Bill getBillById ( Integer id );

    Bill updateBill (Bill bill);

    BigDecimal getTotalPrice (Bill bill);

    void deleteBillById ( Integer id );

    void deleteProductFromBill (Bill bill, Integer id);
}
