delete from bills_products;
delete from bill;

insert into bill (bill_id, date, total_price) values
(1, '', 0.00);

insert into bills_products (bill_id, product_id) values
(1, 1);