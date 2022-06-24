create table admin_user (
  user_id bigint(20) auto_increment primary key comment '主键',
  `name` varchar(8) not null default '' comment '昵称',
  login_name varchar(8) not null default '' comment '登录账号',
  login_pass char(32) not null default '' comment '登录密码，采用MD5+halt加密',
  pass_halt varchar(16) not null default '' comment '密码halt',
  last_login_time datetime comment '最近登录时间',
  last_login_ip varchar(32) not null default '' '最近登录IP'
)  engine=InnoDB comment '管理员';

create table web_info (
  web_id bigint(20) auto_increment primary key comment '主键',
  title varchar(64) not null default '' comment '网站标题',
  keyword varchar(128) not null default '' comment '网站关键字',
  description varchar(128) not null default '' comment '网站描述信息',
  bottom varchar(256) not null default '' comment '网站底部信息'
) engine=InnoDB comment '网站信息';

create table friend_link (
  link_id bigint(20) auto_increment primary key comment '主键',
  `name` varchar(16) not null default '' comment '链接网站名称',
  link varchar(128) not null default '' comment '链接地址',
  `orderby` int(10) not null default 0 comment '排序值',
  `is_show` tinyint(1) not null default 0 comment '前台是否显示：0不显示，1显示',
  `start_time` datetime comment '展示起始时间',
  `end_time` datetime comment '展示终止时间'
) engine=InnoDB comment '友情链接';

create table article_cate (
   cate_id bigint(20) auto_increment primary key comment '主键',
   cate_name varchar(24) not null default '' comment '分类名称',
   orderby int(10) not null default 0 comment '排序值',
   is_delete tinyint(1) not null default 0 comment '逻辑删除字段：0正常，1：删除'
) engine=InnoDB comment '作文分类';

create table article (
    article_id bigint(20) auto_increment primary key comment '主键',
    cate_id bigint(20) not null default 0 comment '作文分类',
    title varchar(64) not null default '' comment '标题',
    keyword varchar(128) not null default '' comment '关键字',
    is_hot tinyint(1) not null default 0 comment '是否为热门',
    is_favor tinyint(1) not null default 0 comment '是否推荐',
    is_sub tinyint(1) not null default 0 comment '是否为专题作用',
    create_time datetime comment '文章创建时间',
    is_delete tinyint(1) not null default 0 comment '逻辑删除字段：0正常，1：删除'
) engine=InnoDB comment '作文';

create table article_content (
    article_id bigint(20) unique comment '与article表的article_id关联',
    content text comment '作文内容'
) engine=InnoDB comment '作文内容';