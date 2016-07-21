CREATE DATABASE `tagged_photos` /*!40100 DEFAULT CHARACTER SET latin1 */;


CREATE TABLE `facebook_user` (
  `id` bigint(5) NOT NULL,
  `name` varchar(150) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `profile_picture` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ID` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `photos` (
  `id` bigint(5) unsigned NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `link` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `album` varchar(75) DEFAULT NULL,
  `user_id` bigint(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PHOTOS_USER` (`user_id`),
  CONSTRAINT `FK_PHOTOS_USER` FOREIGN KEY (`user_id`) REFERENCES `facebook_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `reaction` (
  `id` bigint(5) NOT NULL,
  `type` varchar(8) NOT NULL,
  `photo_id` bigint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`,`type`,`photo_id`),
  KEY `id_idx` (`photo_id`),
  CONSTRAINT `FK_PHOTO_REACTION` FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE VIEW `reaction_stats` AS
    SELECT
        COUNT(`r`.`id`) AS `count`,
        `r`.`type` AS `type`,
        `r`.`photo_id` AS `photo_id`
    FROM
        `reaction` `r`
    GROUP BY `r`.`type` , `r`.`photo_id`
    ORDER BY `r`.`photo_id` , `r`.`type`;

