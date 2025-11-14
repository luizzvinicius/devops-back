create sequence seq_movimentacoes start with 1 increment by 1;
create sequence seq_pessoas start with 1 increment by 1;
create table tb_contas (saldo numeric(38,2) not null, pessoa_id bigint, id uuid not null, primary key (id));
create table tb_movimentacoes (valor numeric(38,2) not null, data timestamp(6) not null, id bigint not null, conta_id uuid, primary key (id));
create table tb_pessoas (id bigint not null, cpf varchar(255) not null unique, endereco varchar(255) not null, nome varchar(255) not null, primary key (id));
alter table if exists tb_contas add constraint FK56s957hi4eopaqatt6dbttqya foreign key (pessoa_id) references tb_pessoas;
alter table if exists tb_movimentacoes add constraint FKhp073ulu27flaa53ml1sj2072 foreign key (conta_id) references tb_contas;