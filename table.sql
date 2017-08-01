-- Oracle --
-- Create table
create table QBK_QUESTION
(
  QBK_ID     VARCHAR2(150) not null,
  QBK_TITLE  VARCHAR2(300) not null,
  QBK_KEY    VARCHAR2(50),
  QBK_ANSWER VARCHAR2(999),
  QBK_DATE   DATE not null,
  QBK_TYPE   VARCHAR2(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table QBK_QUESTION
  is 'WWL_问题自查工具表';
-- Add comments to the columns 
comment on column QBK_QUESTION.QBK_ID
  is '主键，uuid+时间戳+随机数';
comment on column QBK_QUESTION.QBK_TITLE
  is '问题内容';
comment on column QBK_QUESTION.QBK_KEY
  is '问题关键词';
comment on column QBK_QUESTION.QBK_ANSWER
  is '问题答案';
comment on column QBK_QUESTION.QBK_DATE
  is '提问时间';
comment on column QBK_QUESTION.QBK_TYPE
  is '问题类别，0：第三方 1：高阳esales 2：19e_esales';
-- Create/Recreate primary, unique and foreign key constraints 
alter table QBK_QUESTION
  add constraint QBK_ID_PK primary key (QBK_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index QBK_KEY_INDEX on QBK_QUESTION (QBK_KEY)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
