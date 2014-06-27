-- Group [Group]
create table `group` (
   `oid`  integer  not null,
   `groupname`  varchar(255),
  primary key (`oid`)
);


-- Module [Module]
create table `module` (
   `oid`  integer  not null,
   `moduleid`  varchar(255),
   `modulename`  varchar(255),
  primary key (`oid`)
);


-- User [User]
create table `user` (
   `oid`  integer  not null,
   `username`  varchar(255),
   `password`  varchar(255),
   `email`  varchar(255),
   `datanascimento`  date,
   `morada`  varchar(255),
   `foto`  varchar(255),
   `ip`  varchar(255),
   `datacriacao`  date,
   `dataultimoacesso`  date,
   `dataultimaedicao`  date,
  primary key (`oid`)
);


-- GrupoOrcamental [ent1]
create table `grupoorcamental` (
   `oid`  integer  not null,
   `titulo`  varchar(255),
   `descricao`  varchar(255),
   `datainsercao`  date,
   `dataedicao`  date,
  primary key (`oid`)
);


-- Ideia [ent2]
create table `ideia` (
   `oid`  integer  not null,
   `titulo`  varchar(255),
   `descricao`  varchar(255),
   `datainsercao`  date,
   `dataedicao`  date,
   `rating`  integer,
   `quantia`  integer,
   `aprovada`  bit,
  primary key (`oid`)
);


-- Voto [ent3]
create table `voto` (
   `oid`  integer  not null,
  primary key (`oid`)
);


-- Categoria [ent4]
create table `categoria` (
   `oid`  integer  not null,
   `titulo`  varchar(255),
   `descricao`  varchar(255),
   `datainsercao`  date,
   `dataedicao`  date,
  primary key (`oid`)
);


-- Tag [ent5]
create table `tag` (
   `oid`  integer  not null,
   `nome`  varchar(255),
  primary key (`oid`)
);


-- Group_DefaultModule [Group2DefaultModule_DefaultModule2Group]
alter table `group`  add column  `module_oid`  integer;
alter table `group`   add index fk_group_module (`module_oid`), add constraint fk_group_module foreign key (`module_oid`) references `module` (`oid`);


-- Group_Module [Group2Module_Module2Group]
create table `group_module` (
   `group_oid`  integer not null,
   `module_oid`  integer not null,
  primary key (`group_oid`, `module_oid`)
);
alter table `group_module`   add index fk_group_module_group (`group_oid`), add constraint fk_group_module_group foreign key (`group_oid`) references `group` (`oid`);
alter table `group_module`   add index fk_group_module_module (`module_oid`), add constraint fk_group_module_module foreign key (`module_oid`) references `module` (`oid`);


-- User_DefaultGroup [User2DefaultGroup_DefaultGroup2User]
alter table `user`  add column  `group_oid`  integer;
alter table `user`   add index fk_user_group (`group_oid`), add constraint fk_user_group foreign key (`group_oid`) references `group` (`oid`);


-- User_Group [User2Group_Group2User]
create table `user_group` (
   `user_oid`  integer not null,
   `group_oid`  integer not null,
  primary key (`user_oid`, `group_oid`)
);
alter table `user_group`   add index fk_user_group_user (`user_oid`), add constraint fk_user_group_user foreign key (`user_oid`) references `user` (`oid`);
alter table `user_group`   add index fk_user_group_group (`group_oid`), add constraint fk_user_group_group foreign key (`group_oid`) references `group` (`oid`);


-- Ideia_Categoria [rel1]
alter table `ideia`  add column  `categoria_oid`  integer;
alter table `ideia`   add index fk_ideia_categoria (`categoria_oid`), add constraint fk_ideia_categoria foreign key (`categoria_oid`) references `categoria` (`oid`);


-- Ideia_GrupoOrcamental [rel3]
alter table `ideia`  add column  `grupoorcamental_oid`  integer;
alter table `ideia`   add index fk_ideia_grupoorcamental (`grupoorcamental_oid`), add constraint fk_ideia_grupoorcamental foreign key (`grupoorcamental_oid`) references `grupoorcamental` (`oid`);


-- Ideia_Voto [rel6]
alter table `voto`  add column  `ideia_oid`  integer;
alter table `voto`   add index fk_voto_ideia (`ideia_oid`), add constraint fk_voto_ideia foreign key (`ideia_oid`) references `ideia` (`oid`);


-- Voto_User [rel7]
alter table `voto`  add column  `user_oid`  integer;
alter table `voto`   add index fk_voto_user (`user_oid`), add constraint fk_voto_user foreign key (`user_oid`) references `user` (`oid`);


-- User_Ideia [rel8]
alter table `ideia`  add column  `user_oid`  integer;
alter table `ideia`   add index fk_ideia_user (`user_oid`), add constraint fk_ideia_user foreign key (`user_oid`) references `user` (`oid`);


