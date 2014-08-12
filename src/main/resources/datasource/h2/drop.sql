alter table shoppingcart.shoppingcartitem drop constraint if exists fk_shoppingcartitem_shoppingcart;
alter table shoppingcart.shoppingcartitem drop constraint if exists fk_shoppingcartitem_product;

drop table if exists shoppingcart.shoppingcartitem;
drop table if exists shoppingcart.shoppingcart;
drop table if exists shoppingcart.product;

drop schema if exists shoppingcart;
