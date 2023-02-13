package ua.lyashko.cafe.service;

import org.springframework.stereotype.Service;
import ua.lyashko.cafe.model.Bill;
import ua.lyashko.cafe.model.Product;
import ua.lyashko.cafe.repository.BillRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl ( BillRepository billRepository ) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getALlBills () {
        return (List<Bill>) billRepository.findAll ( );
    }

    @Override
    public Bill saveBill ( Bill bill ) {
        return billRepository.save ( bill );
    }

    @Override
    public Bill getBillById ( Integer id ) {
        return billRepository.findById ( id ).get ( );
    }

    @Override
    public Bill updateBill ( Bill bill ) {
        return billRepository.save ( bill );
    }

    @Override
    public BigDecimal getTotalPrice ( Bill bill ) {
        BigDecimal totalPrice = BigDecimal.valueOf ( 0 );
        for (Product product : bill.getProducts ( )) {
            totalPrice = totalPrice.add ( product.getPrice ( ) );
        }
        return totalPrice;
    }

    @Override
    public void deleteBillById ( Integer id ) {
        billRepository.deleteById ( id );
    }

    @Override
    public void deleteProductFromBill (Bill bill, Integer id ) {
        List<Product> products = new ArrayList<> ( bill.getProducts ( ) );
        products.remove ( id );
        bill.setProducts ( products );
    }
}
