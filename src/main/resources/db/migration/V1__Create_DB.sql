create table categories (
    id integer primary key auto_increment,
    name varchar(255),
    request_name varchar(255),
    deleted boolean,
    unique(name),
    unique(request_name)
) engine=InnoDB;

create table banners (
    id integer primary key auto_increment,
    name varchar(255),
    price decimal(8,2),
    category_id integer,
    content text,
    deleted boolean,
    foreign key (category_id) references categories (id),
    unique(name)
) engine=InnoDB;

create table requests (
    id integer primary key auto_increment,
    banner_id integer,
    user_agent text,
    ip_address text,
    date datetime,
    foreign key (banner_id) references banners (id)
) engine=InnoDB;
