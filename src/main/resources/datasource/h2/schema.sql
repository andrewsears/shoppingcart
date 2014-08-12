create schema if not exists shoppingcart authorization sa;

create table if not exists shoppingcart.product (
	id bigint primary key not null,
	name varchar(255),
	description clob,
	sku varchar(255),
	price decimal(20,2)
);

create table if not exists shoppingcart.shoppingcart (
	id bigint primary key not null
);

create table if not exists shoppingcart.shoppingcartitem (
	id bigint primary key not null,
	shoppingcart_id int not null,
	product_id int not null,
	quantity int default 0,
	constraint fk_shoppingcartitem_shoppingcart foreign key (shoppingcart_id) references shoppingcart.shoppingcart(id),
	constraint fk_shoppingcartitem_product foreign key (product_id) references shoppingcart.product(id),
);

