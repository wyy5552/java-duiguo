CREATE TABLE tb_user (
  uid int auto_increment primary key,
  username varchar(20) not null unique,
  password varchar(30)
);

CREATE TABLE tb_file(
  fid int auto_increment primary key,
  uid int,
  fname varchar(50) not null,
  fcontent blob,
  foreign key (uid) references tb_user(uid)
);