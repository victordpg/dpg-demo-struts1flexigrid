delimiter $$

CREATE TABLE `gcib` (
  `id` int(11) NOT NULL,
  `name` varchar(45) default NULL,
  `file` mediumblob,
  `filetype` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
)